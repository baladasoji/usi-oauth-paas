package com.maersk.usi.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.IOException;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import com.auth0.jwt.algorithms.Algorithm;
import java.security.MessageDigest;
import java.util.*;
import com.maersk.usi.rest.*;
import java.nio.charset.StandardCharsets;

public class JWTGenerate
{
  private static final String PRIVATE_KEY_FILE_RSA = "certs/privkey8.pem";
  private static final String PUBLIC_KEY_FILE_RSA = "certs/pubkey.pem";
  private static final Logger logger = LoggerFactory.getLogger(JWTGenerate.class);
  private static final  String ISSUER = "https://autht.maerskline.com";
  private static final  String KEY_ID = "B36D568F46A3AA89BA98FDFD73F99837D2A1C6D4" ;
  private static final long VALIDITY_MILLI_SECONDS = 28800000 ; // 8 hours for the token expiry ;

  private String getApplicationRoles(String allroles, String application)
  {
    List<String> list = new ArrayList<String>(Arrays.asList(allroles.split(",")));
    list.removeIf(s -> !s.contains(application));
    List<String> rolelist = new ArrayList<String>();
    StringBuilder sb = new StringBuilder();
    for (String temp: list)
    {
      String temp1 =temp.replace(application+":","");
      sb.append(temp1).append(" ");
    }
    return sb.toString().trim();
  }

  private List<String> getApplicationRolesAsList (String allroles, String application)
  {

    List<String> list = new ArrayList<String>(Arrays.asList(allroles.split(",")));
    list.removeIf(s -> !s.contains(application));
    List<String> rolelist = new ArrayList<String>();
    for (String temp: list)
    {
      String temp1 =temp.replace(application+":","");
      rolelist.add(temp1);
    }
    return rolelist;
  }

  public String getAccessToken(SessionInfo si, DCInfo customerinfo, String client_id)
  {
    try {
    RSAPrivateKey privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(PRIVATE_KEY_FILE_RSA, "RSA");
    RSAPublicKey publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(PUBLIC_KEY_FILE_RSA, "RSA");
    logger.debug ("Public key info " + publicKey.getModulus());
    logger.debug ("private key info " + privateKey.getModulus());
    logger.debug ("Public key algorithm " + publicKey.getAlgorithm());
    logger.debug ("private key algorithm " + privateKey.getAlgorithm());
    Algorithm algorithm = Algorithm.RSA256(publicKey,privateKey);
    //Algorithm algorithm = Algorithm.HMAC256("ThisisBalaDasojisBigSecretKey");
    String alwaysOnApplicationName = "AlwaysOn";
    String token = JWT.create()
        .withIssuer(ISSUER)
        .withKeyId(KEY_ID)
	      .withClaim("aud",client_id)
//	.withClaim("scope","profile")
//	.withClaim("appid","rcywhyza-2wdEIe9m5ppAq1xBr8x32c6D")
      //  .withClaim("firstname",ui.getFirstName())
      //  .withClaim("lastname",ui.getLastName())
        .withSubject(si.getLoggedOnPrincipal())
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withExpiresAt(new Date(System.currentTimeMillis()+VALIDITY_MILLI_SECONDS))
//        .withClaim("roles",getApplicationRoles(si.getRoles(),alwaysOnApplicationName))
        .withArrayClaim("roles",getApplicationRolesAsList(si.getRoles(),alwaysOnApplicationName).toArray(new String[0]))
        .withClaim("carrier", si.getCarrier())
        .withClaim("customer_code",customerinfo.getOutput())
        .sign(algorithm);
      return token;
      } catch (JWTCreationException exception){
        logger.error("error creating jwt ", exception);
          //Invalid Signing configuration / Couldn't convert Claims.
      } catch (IOException ioe){
        logger.error("error reading the private key",ioe);
      }
      catch (Exception e){
        logger.error("General Error",e);
      }
      return null ;
  }

  public String getIdToken(SessionInfo si, String client_id, String nonce, String access_token)
  {
    try {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(access_token.getBytes(StandardCharsets.UTF_8));
    String encoded_hash = Base64.getEncoder().encodeToString(Arrays.copyOfRange(hash,0,hash.length/2));

    RSAPrivateKey privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(PRIVATE_KEY_FILE_RSA, "RSA");
    RSAPublicKey publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(PUBLIC_KEY_FILE_RSA, "RSA");
    logger.debug ("Public key info " + publicKey.getModulus());
    logger.debug ("private key info " + privateKey.getModulus());
    logger.debug ("Public key algorithm " + publicKey.getAlgorithm());
    logger.debug ("private key algorithm " + privateKey.getAlgorithm());
    Algorithm algorithm = Algorithm.RSA256(publicKey,privateKey);
    //Algorithm algorithm = Algorithm.HMAC256("ThisisBalaDasojisBigSecretKey");
    String token = JWT.create()
        .withIssuer(ISSUER)
        .withKeyId(KEY_ID)
        .withClaim("firstname",si.getFirstName())
        .withClaim("lastname",si.getLastName())
        .withClaim("email",si.getEmail())
        .withClaim("city",si.getCity())
        .withClaim("country",si.getCountry())
        .withClaim("office",si.getOfficeName())
	      .withClaim("aud",client_id)
        .withClaim("at_hash",encoded_hash)
        .withSubject(si.getLoggedOnPrincipal())
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withExpiresAt(new Date(System.currentTimeMillis()+VALIDITY_MILLI_SECONDS))
        .sign(algorithm);
        return token;
      } catch (JWTCreationException exception){
        logger.error("error creating jwt ", exception);
          //Invalid Signing configuration / Couldn't convert Claims.
      } catch (IOException ioe){
        logger.error("error reading the private key",ioe);
      }
      catch (Exception e){
        logger.error("General Error",e);
      }
      return null ;
  }


  public String getToken()
  {
      try {
      RSAPrivateKey privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(PRIVATE_KEY_FILE_RSA, "RSA");
      RSAPublicKey publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(PUBLIC_KEY_FILE_RSA, "RSA");
      logger.debug ("Public key info " + publicKey.getModulus());
      logger.debug ("private key info " + privateKey.getModulus());
      logger.debug ("Public key algorithm " + publicKey.getAlgorithm());
      logger.debug ("private key algorithm " + privateKey.getAlgorithm());
      Algorithm algorithm = Algorithm.RSA256(publicKey,privateKey);
      String[] roles = { "booking", "finance", "documentation", "import", "printbills" };
      String token = JWT.create()
          .withIssuer("https://login.dasoji.net")
          .withKeyId("B36D568F46A3AA89BA98FDFD73F99837D2A1C6D4")
          .withClaim("firstname","Bala")
          .withClaim("lastname","Dasoji")
          .withSubject("bala.dasoji@maersk.com")
          .withIssuedAt(new Date(System.currentTimeMillis()))
          .withExpiresAt(new Date(System.currentTimeMillis()+60000))
          .withArrayClaim("roles",roles)
          .sign(algorithm);
          return token;
      } catch (JWTCreationException exception){
        logger.error("error creating jwt ", exception);
          //Invalid Signing configuration / Couldn't convert Claims.
      } catch (IOException ioe){
        logger.error("error reading the private key",ioe);
      }
      catch (Exception e){
        logger.error("General Error",e);
      }
      return null ;
  }
}
