package com.accelbookmark.newbookmark.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.accelbookmark.newbookmark.facade.UserFacade;
import com.accelbookmark.newbookmark.service.UserService;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

@Controller
public class UserController {
//    @Autowired
//    @Qualifier("userService")
//    UserService userService;
    
    @Autowired
//    @Qualifier("userFacade")
    UserFacade userFacade;
    

    
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<String> createUser(@RequestBody String user) throws JSONException {

      try {
          JSONObject jObject  = new JSONObject(user);
        String  password = jObject.getString("bookmark_user_password");
        String  email = jObject.getString("bookmark_user_email");
         String firstname = jObject.getString("bookmark_user_firstname");
     if(password != null  || email !=null || firstname !=null) {
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
    
      try {
       
          userFacade.loginUser(user);  
          return new ResponseEntity<String>("success", HttpStatus.OK);
      } catch (Exception e) {
          System.out.println("Exception thrown  :" + e);
          return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
      }
         
    
  }
  
  
}
