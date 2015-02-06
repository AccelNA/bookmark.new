package bookmark.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bookmark.facade.ResourceFacade;
import bookmark.facade.UserFacade;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
@Controller
public class ResourceController {
   

        
        @Autowired
//        @Qualifier("userFacade")
       ResourceFacade resourceFacade;
        

        
      @RequestMapping(value = "/resource", method = RequestMethod.POST)
      public @ResponseBody ResponseEntity<String> addResource(@RequestBody String resource) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resource);
              resource = jObject.put("Resource_createddate", date).toString();
              String token = jObject.getString("Resource_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resource =jObject.remove("Resource_user_token").toString();
              resource = jObject.put("Resource_user_email", email).toString();
              String uuid = UUID.randomUUID().toString();
              resource = jObject.put("Resource_id", uuid).toString();
              resourceFacade.addResource(resource); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
//      public static void generateuuid (String[] args) {
//          final String uuid = UUID.randomUUID().toString();
//          System.out.println("uuid = " + uuid);
//      }
      @RequestMapping(value = "/resourcegroup", method = RequestMethod.POST)
      public @ResponseBody ResponseEntity<String> addResourceGroup(@RequestBody String resourcegroup) throws JSONException {

          try {
              String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resourcegroup);
              resourcegroup = jObject.put("Resourcegroup_createddate", date).toString();
              String token = jObject.getString("Resourcegroup_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resourcegroup =jObject.remove("Resourcegroup_user_token").toString();
              resourcegroup = jObject.put("Resourcegroup_user_email", email).toString();
              String uuid = UUID.randomUUID().toString();
              resourcegroup = jObject.put("Resourcegroup_id", uuid).toString();
              resourceFacade.addResourceGroup(resourcegroup); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcegroupgetname", method = RequestMethod.GET)
      public @ResponseBody ResponseEntity<String> getResourceGroupName(HttpServletRequest request,
              HttpServletResponse response) throws JSONException {
          
          try {
              String bookmark_user_token =request.getHeader("token");
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             String resourcegroup = jObject.put("bookmark_user_email", email).toString();
              String responseData = resourceFacade.getResourceGroup(resourcegroup);
              return new ResponseEntity<String>(responseData, HttpStatus.OK);
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
      } 
      

      @RequestMapping(value = "/resourcegroup", method = RequestMethod.GET)
      public @ResponseBody ResponseEntity<String> getResourceGroup(HttpServletRequest request,
              HttpServletResponse response) throws JSONException {

          try {
              String bookmark_user_token =request.getHeader("token");
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             String resourcegroup = jObject.put("bookmark_user_email", email).toString();
              String responseData = resourceFacade.getResourceGroupdetails(resourcegroup);
              return new ResponseEntity<String>(responseData, HttpStatus.OK);
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
      }
          @RequestMapping(value = "/resource", method = RequestMethod.GET)
          public @ResponseBody ResponseEntity<String> getResource(HttpServletRequest request,
                  HttpServletResponse response) throws JSONException {

              try {
                  String Resource_user_token =request.getHeader("token");
                  String tokendecoded = new String(Base64.decodeBase64(Resource_user_token));
                  String email = tokendecoded.split(":")[1];
                  
               //   JSONObject jObject = new JSONObject();
              //   String resourcegroup = jObject.put("Resource_user_email", email).toString();
                  String responseData = resourceFacade.getResourceDetails(email);
                  return new ResponseEntity<String>(responseData, HttpStatus.OK);
                     } catch (Exception e) {
                  System.out.println("Exception thrown  :" + e);
                  return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
              }
               
      } 
      @RequestMapping(value = "/resourcegroup", method = RequestMethod.PUT)
      public @ResponseBody ResponseEntity<String> editResourceGroup(@RequestBody String resourcegroup) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resourcegroup);
              
              resourcegroup = jObject.put("Resourcegroup_updateddate", date).toString();
              String token = jObject.getString("Resourcegroup_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resourcegroup =jObject.remove("Resourcegroup_user_token").toString();
              resourcegroup = jObject.put("Resourcegroup_user_email", email).toString();
              resourceFacade.updateResourceGroup(resourcegroup); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcgroupedelete/{Resourcegroup_id}", method = RequestMethod.DELETE)
      public @ResponseBody ResponseEntity<String> resourceGroupdelete(HttpServletRequest request,
              HttpServletResponse response, @PathVariable String Resourcegroup_id) throws JSONException {

          try { 
              String bookmark_user_token =request.getHeader("token");
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             //String resourcegroup = jObject.put("Bookmark_resourcegroup_name", bookmark_resource_groupName).toString();
        Boolean  result = resourceFacade.resourceGroupdelete(Resourcegroup_id);
        if (result = true){
              return new ResponseEntity<String>("success", HttpStatus.OK);
        }
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
          return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
      }   
      
      @RequestMapping(value = "/resource", method = RequestMethod.PUT)
      public @ResponseBody ResponseEntity<String> editResource(@RequestBody String resource) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resource);
              
              resource = jObject.put("Resource_updateddate", date).toString();
              String token = jObject.getString("Resource_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resource =jObject.remove("Resource_user_token").toString();
              resource = jObject.put("Resource_user_email", email).toString();
              resourceFacade.updateResource(resource); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      
      
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> addNote(@RequestBody String note) throws JSONException {

        try {  String date = Calendar.getInstance().getTime().toString();
            JSONObject jObject = new JSONObject(note);
            note = jObject.put("Activity_createddate", date).toString();
            String token = jObject.getString("activity_user_token");
            String tokendecoded = new String(Base64.decodeBase64(token));
            String email = tokendecoded.split(":")[1];
            note =jObject.remove("activity_user_token").toString();
            note = jObject.put("Activity_user_email", email).toString();
            String uuid = UUID.randomUUID().toString();
            note = jObject.put("Activity_id", uuid).toString();
            resourceFacade.addNote(note); 
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
           
             return new ResponseEntity<String>("success", HttpStatus.OK);
      
    }
    
    @RequestMapping(value = "/resourcedelete/{Resource_id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<String> resourcedelete(HttpServletRequest request,
            HttpServletResponse response, @PathVariable String Resource_id) throws JSONException {

        try { 
            String bookmark_user_token =request.getHeader("token");
            String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
            String email = tokendecoded.split(":")[1];
        Boolean  result = resourceFacade.resourcedelete(Resource_id);
      if (result = true){
            return new ResponseEntity<String>("success", HttpStatus.OK);
      }
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
    }   
    @RequestMapping(value = "/getactivity/{bookmark_resource_name}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> getActivity(HttpServletRequest request,
            HttpServletResponse response, @PathVariable String bookmark_resource_name) throws JSONException {

        try {
            String bookmark_user_token =request.getHeader("token");
            String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
            String email = tokendecoded.split(":")[1];
            JSONObject jObject = new JSONObject();
           String activity = jObject.put("Activity_user_email", email).toString();
           activity = jObject.put("Activity_resource_name", bookmark_resource_name).toString();
            String responseData = resourceFacade.getActivityDetails(activity);
            return new ResponseEntity<String>(responseData, HttpStatus.OK);
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
         
} 

}
