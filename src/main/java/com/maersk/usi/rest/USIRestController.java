package com.maersk.usi.rest;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import com.maersk.usi.util.JWTGenerate;
import java.io.IOException;

@RestController
public class USIRestController {

	@Value("${myml.url}")
	private String sspUrl;
	private	ResponseEntity<String> usiSessionInfoResponse;
	private	ResponseEntity<String> usiCarrierInfoResponse;
	private	ResponseEntity<String> usiCustomerCodeInfoResponse;
	private SessionEntities sessionEntities = new SessionEntities();
	private UserEntities carrierEntities = new UserEntities();
	private UserEntities customerCodeEntities = new UserEntities();

	private static final Logger log = LoggerFactory.getLogger(USIRestController.class);
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
    }


		private boolean populateLegacyObjects(RestTemplate restTemplate, HttpHeaders headers)
		{
			ObjectMapper objectMapper = new ObjectMapper();
			CombinedToken token = new CombinedToken();
			log.debug(headers.getFirst(HttpHeaders.COOKIE));
			log.info("SSP Url is " +sspUrl);
			HttpHeaders nh = new HttpHeaders();
			nh.set(HttpHeaders.COOKIE,headers.getFirst(HttpHeaders.COOKIE));
			nh.set(HttpHeaders.USER_AGENT,headers.getFirst(HttpHeaders.USER_AGENT));
			HttpEntity he = new HttpEntity(nh);
			try
			{
				usiSessionInfoResponse = restTemplate.exchange (sspUrl+"/agent-api/getsessioninfo", HttpMethod.GET, he, String.class);
				// Perform other steps only if the user is logged in
				if (validateLogin ())
				{
					log.info ("User is logged in");
				usiCarrierInfoResponse = restTemplate.exchange (sspUrl+"/agent-api/getdatacontext?key=Carrier", HttpMethod.GET, he, String.class);
				usiCustomerCodeInfoResponse = restTemplate.exchange (sspUrl+"/agent-api/getdatacontext?key=Customer_Code", HttpMethod.GET, he, String.class);
					sessionEntities = objectMapper.readValue(usiSessionInfoResponse.getBody(), SessionEntities.class);
					carrierEntities = objectMapper.readValue(usiCarrierInfoResponse.getBody(), UserEntities.class);
					customerCodeEntities = objectMapper.readValue(usiCustomerCodeInfoResponse.getBody(), UserEntities.class);

				}
				else
				{
					log.info ("User is NOT logged in");
					return false;
				}
			}
			catch (Exception e)
			{
				log.error(e.getMessage());
				return false;
			}
			return true;
		}

		private boolean validateLogin()
		{
			if (usiSessionInfoResponse != null && (usiSessionInfoResponse.getStatusCode() == HttpStatus.OK) )
				return true;
			else
				return false;
		}

		@RequestMapping(value = "/connect/authorize", method = RequestMethod.GET, produces = "application/json")
		public CombinedToken authorize(RestTemplate restTemplate, @RequestHeader HttpHeaders headers, HttpServletResponse response,
                                        @RequestParam("response_type") String response_type,
                                        @RequestParam("client_id") String client_id,
                                        @RequestParam("redirect_uri") String redirect_uri,
                                        @RequestParam("scope") String scope,
                                        @RequestParam("state") String state,
                                        @RequestParam("nonce") String nonce

                                        ) throws IOException
		{
			CombinedToken token = new CombinedToken();
			// If we could successfully populate the legacy objects then proceed with generating token else return an empty token
			if(populateLegacyObjects(restTemplate, headers))
			{
				JWTGenerate jg = new JWTGenerate();
        String access_token =jg.getAccessToken(sessionEntities.getSessionEntities(), customerCodeEntities.getUserEntities(), client_id);
        String id_token = jg.getIdToken(sessionEntities.getSessionEntities(),client_id, nonce, access_token ) ;
				token.setId_token(id_token);
				token.setAccess_token(access_token);
				token.setToken_type("Bearer");
				token.setExpires_in(3600);
        response.sendRedirect(redirect_uri+"#access_token="+access_token+"&token_type=Bearer&expires_in=3600&id_token="+id_token+"&state="+state);
			}
      else
      {
        response.sendRedirect(sspUrl+"/webuser/login?originalUrl=https://autht.maerskline.com/connect/authorize?response_type="+response_type+"&client_id="+client_id+"&redirect_uri="+redirect_uri+"&scope="+scope+"&nonce="+nonce+"&state="+state);
      }
			return token;
		}


    @RequestMapping(value = "/connect/sessioninfo", method = RequestMethod.GET, produces = "application/json")
    public SessionEntities session(RestTemplate restTemplate, @RequestHeader HttpHeaders headers)
		{
			populateLegacyObjects(restTemplate, headers);
			return sessionEntities;
    }
    @RequestMapping(value = "/connect/getcarrier", method = RequestMethod.GET, produces = "application/json")
    public UserEntities carrier(RestTemplate restTemplate, @RequestHeader HttpHeaders headers)
		{
			populateLegacyObjects(restTemplate, headers);
			return carrierEntities;
    }



    @RequestMapping(value = "/connect/getcustomercode", method = RequestMethod.GET, produces = "application/json")
    public UserEntities customerCode(RestTemplate restTemplate, @RequestHeader HttpHeaders headers)
		{
			populateLegacyObjects(restTemplate, headers);
			return customerCodeEntities;
    }
}
