package bookmark.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookmark.facade.UserFacade;
import bookmark.service.UserService;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
@Controller
public class UserController {
    private static final String HMAC_ALGO = "HmacSHA256";
    
   
  
    @Autowired
    // @Qualifier("userFacade")
    UserFacade userFacade;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> createUser(@RequestBody String user) throws JSONException {

        JSONObject jObject = new JSONObject(user);
        String password = jObject.getString("bookmark_user_password");
        String email = jObject.getString("bookmark_user_email");
        String firstname = jObject.getString("bookmark_user_firstname");
        String date = Calendar.getInstance().getTime().toString();
        try {
            user = jObject.put("bookmark_user_createddate", date).toString();

            if (password != null || email != null || firstname != null) {

                userFacade.createUser(user);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<String>("success", HttpStatus.OK);

    }

    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> loginUser(@RequestBody String user) throws JSONException {
        JSONObject jObject = new JSONObject(user);
        String password = jObject.getString("bookmark_user_password");
        String email = jObject.getString("bookmark_user_email");
        try {

            if (password != null || email != null) {
                boolean result = userFacade.loginUser(user);
                if (result) {
                 String token = createTokenForUser(user);
                 user = jObject.put("bookmark_user_token", token).toString();
                  userFacade.updateUser(user);
                  jObject.remove("bookmark_user_password");
                  jObject.remove("bookmark_user_email");
                    System.out.println("success");
                    return new ResponseEntity<String>(user, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
    }
    
    public String createTokenForUser(String user) throws JSONException {
        JSONObject jObject = new JSONObject(user);
        String password = jObject.getString("bookmark_user_password");
        String email = jObject.getString("bookmark_user_email");
        String authString = password + ":" + email;
        System.out.println("auth string: " + authString);
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        System.out.println("Base64 encoded auth string: " + authStringEnc);
        return authStringEnc;
}

    @RequestMapping(value = "/userlogout", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> logoutUser(HttpServletRequest request,
            HttpServletResponse response) throws JSONException {
        
        try {
        
            JSONObject jObject = new JSONObject();
            String token = request.getHeader("token");
            String tokendecoded = new String(Base64.decodeBase64(token));
           String password = tokendecoded.split(":")[0];
           String email = tokendecoded.split(":")[1];
          String user = jObject.put("bookmark_user_password", password).toString();
           user =   jObject.put("bookmark_user_email", email).toString();
            if (token != null || email != null|| password!=null) {
                boolean result = userFacade.logoutUser(user);
                if (result) {
                    return new ResponseEntity<String>("Success", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
    }
    
}
