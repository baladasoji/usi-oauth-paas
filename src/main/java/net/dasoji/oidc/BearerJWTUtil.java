package net.dasoji.oidc;

import java.util.*;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import java.io.IOException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

public class BearerJWTUtil {

    private static final String AUTH_HEADER = "authorization";
    private static final String BEARER_FRAGMENT = "Bearer";
    private static String jwtString = null ;
    private static final Logger logger = LoggerFactory.getLogger(BearerJWTUtil.class);
    public static DecodedJWT getJWTfromHeader(HttpServletRequest request)
    {
    	DecodedJWT jwt = null ;
            String allheaders = "";
            String token="" ;
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements())
            {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                if (key!=null && key.equalsIgnoreCase(AUTH_HEADER))
                {
                    token=value;
                    token = token.substring(7);

                }
                allheaders = allheaders + key + value ;
            }
            if (!token.equals(""))
            {
                try {
                    jwt = JWT.decode(token);
                } catch (JWTDecodeException exception){
                    exception.printStackTrace(System.err);
                }
            }
        System.out.println ("Decoded JWT and it is "+jwt);
        return jwt;

    }
    public static String getJWTString(HttpServletRequest request)
    {
        DecodedJWT jwt = getJWTfromHeader(request);
        String jwtString = StringUtils.newStringUtf8(Base64.decodeBase64(jwt.getPayload()));
        return jwtString;
    }

    public static boolean assertRole(HttpServletRequest request, String role)
    {
    	boolean result = false ;
	try
	{

		DecodedJWT jwt=getJWTfromHeader(request);
		if (jwt == null)
			return result;
		logger.info (jwt.getToken());
		// First try to get the roles as simple string
		String rolesString = jwt.getClaim("roles").asString();
		if (rolesString !=null )
		{
			result= rolesString.contains(role);
		}
		else
		{
			List<String> rolesList = jwt.getClaim("roles").asList(String.class);	
			if (rolesList != null)
				result = rolesList.contains(role);
		}
			
	}
	catch (Exception e)
	{
		logger.error("Error in role check", e);
	}
	return result ;
    }
}
