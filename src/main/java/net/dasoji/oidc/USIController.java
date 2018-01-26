package net.dasoji.oidc;

import java.util.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
@RestController
public class USIController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/usi", method = RequestMethod.GET, produces = "application/json")
    public String index() throws IOException {
        String jwtjson = BearerJWTUtil.getJWTString(request);
        return jwtjson;
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET, produces = "application/json")
    public String getBooking() throws IOException {
        if (BearerJWTUtil.assertRole(request, "Booking"))
        {
          return "Booking number 100";
        }
        else
        {
//            response.sendError(403, "No permission to access booking");
          return "No permission to access booking";
        }
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET, produces = "application/json")
    public String getInvoices() throws IOException {
        if (BearerJWTUtil.assertRole(request, "Finance"))
        {
          return "Invoice number 100";
        }
        else
        {
  //          response.sendError(403, "No permission to access invoices");
          return "No permission to access invoices";
        }
    }
}
