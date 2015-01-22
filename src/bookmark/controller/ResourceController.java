package bookmark.controller;

import java.util.Calendar;

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
        

        
      @RequestMapping(value = "/resourceadd", method = RequestMethod.POST)
      public @ResponseBody ResponseEntity<String> addResource(@RequestBody String resource) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resource);
              resource = jObject.put("bookmark_resource_createddate", date).toString();
              String token = jObject.getString("bookmark_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resource =jObject.remove("bookmark_user_token").toString();
              resource = jObject.put("Bookmark_resource_user_email", email).toString();
              resourceFacade.addResource(resource); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcegroupadd", method = RequestMethod.POST)
      public @ResponseBody ResponseEntity<String> addResourceGroup(@RequestBody String resourcegroup) throws JSONException {

          try {
              String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resourcegroup);
              resourcegroup = jObject.put("bookmark_user_createddate", date).toString();
              String token = jObject.getString("bookmark_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resourcegroup =jObject.remove("bookmark_user_token").toString();
              resourcegroup = jObject.put("bookmark_user_email", email).toString();
           
              resourceFacade.addResourceGroup(resourcegroup); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcegroupgetname/{bookmark_user_token}", method = RequestMethod.GET)
      public @ResponseBody ResponseEntity<String> getResourceGroupName(@PathVariable String bookmark_user_token) throws JSONException {

          try {
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             String resourcegroup = jObject.put("bookmark_user_email", email).toString();
              //resourcegroup = jObject.put("Bookmark_resourcegroup_name", resourcename).toString();
              String responseData = resourceFacade.getResourceGroup(resourcegroup);
              return new ResponseEntity<String>(responseData, HttpStatus.OK);
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
      } 
      
      
      @RequestMapping(value = "/activityadd", method = RequestMethod.POST)
      public @ResponseBody ResponseEntity<String> addActivity(@RequestBody String activity) throws JSONException {

          try {
              resourceFacade.addActivity(activity); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcegroupget/{bookmark_user_token}", method = RequestMethod.GET)
      public @ResponseBody ResponseEntity<String> getResourceGroup(@PathVariable String bookmark_user_token) throws JSONException {

          try {
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             String resourcegroup = jObject.put("bookmark_user_email", email).toString();
              //resourcegroup = jObject.put("Bookmark_resourcegroup_name", resourcename).toString();
              String responseData = resourceFacade.getResourceGroupdetails(resourcegroup);
              return new ResponseEntity<String>(responseData, HttpStatus.OK);
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
      }
          @RequestMapping(value = "/resourceget/{bookmark_user_token}", method = RequestMethod.GET)
          public @ResponseBody ResponseEntity<String> getResource(@PathVariable String bookmark_user_token) throws JSONException {

              try {
                  String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
                  String email = tokendecoded.split(":")[1];
                  
                  JSONObject jObject = new JSONObject();
                 String resourcegroup = jObject.put("bookmark_user_email", email).toString();
                  //resourcegroup = jObject.put("Bookmark_resourcegroup_name", resourcename).toString();
                  String responseData = resourceFacade.getResourceDetails(resourcegroup);
                  return new ResponseEntity<String>(responseData, HttpStatus.OK);
                     } catch (Exception e) {
                  System.out.println("Exception thrown  :" + e);
                  return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
              }
               
      } 
      @RequestMapping(value = "/resourcegroupedit", method = RequestMethod.PUT)
      public @ResponseBody ResponseEntity<String> editResourceGroup(@RequestBody String resourcegroup) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resourcegroup);
              
              resourcegroup = jObject.put("Bookmark_resource_updateddate", date).toString();
              String token = jObject.getString("bookmark_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resourcegroup =jObject.remove("bookmark_user_token").toString();
//              resourcegroup =jObject.remove("groupName").toString();
//              resourcegroup =jObject.remove("groupDescription").toString();
              resourcegroup = jObject.put("bookmark_user_email", email).toString();
              resourceFacade.updateResourceGroup(resourcegroup); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      @RequestMapping(value = "/resourcegroupdelete/{bookmark_user_token}/{bookmark_resource_groupName}", method = RequestMethod.DELETE)
      public @ResponseBody ResponseEntity<String> resourceGroupdelete(@PathVariable String bookmark_user_token, @PathVariable String bookmark_resource_groupName) throws JSONException {

          try {
              String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
              String email = tokendecoded.split(":")[1];
              
              JSONObject jObject = new JSONObject();
             String resourcegroup = jObject.put("Bookmark_resourcegroup_name", bookmark_resource_groupName).toString();
             
              //resourcegroup = jObject.put("Bookmark_resourcegroup_name", resourcename).toString();
        Boolean  result = resourceFacade.resourceGroupdelete(bookmark_resource_groupName);
        if (result = true){
              return new ResponseEntity<String>("success", HttpStatus.OK);
        }
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
          return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
      }   
      
      @RequestMapping(value = "/resourceedit", method = RequestMethod.PUT)
      public @ResponseBody ResponseEntity<String> editResource(@RequestBody String resource) throws JSONException {

          try {  String date = Calendar.getInstance().getTime().toString();
              JSONObject jObject = new JSONObject(resource);
              
              resource = jObject.put("Bookmark_resource_updateddate", date).toString();
              String token = jObject.getString("bookmark_user_token");
              String tokendecoded = new String(Base64.decodeBase64(token));
              String email = tokendecoded.split(":")[1];
              resource =jObject.remove("bookmark_user_token").toString();
//              resourcegroup =jObject.remove("groupName").toString();
//              resourcegroup =jObject.remove("groupDescription").toString();
              resource = jObject.put("bookmark_user_email", email).toString();
              resourceFacade.updateResource(resource); 
                 } catch (Exception e) {
              System.out.println("Exception thrown  :" + e);
              return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
          }
             
               return new ResponseEntity<String>("success", HttpStatus.OK);
        
      }
      
      
    @RequestMapping(value = "/addnote", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> addNote(@RequestBody String note) throws JSONException {

        try {  String date = Calendar.getInstance().getTime().toString();
            JSONObject jObject = new JSONObject(note);
            note = jObject.put("Bookmark_activity_createddate", date).toString();
            String token = jObject.getString("bookmark_user_token");
            String tokendecoded = new String(Base64.decodeBase64(token));
            String email = tokendecoded.split(":")[1];
            note =jObject.remove("bookmark_user_token").toString();
            note = jObject.put("Bookmark_activity_user_email", email).toString();
            resourceFacade.addNote(note); 
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
           
             return new ResponseEntity<String>("success", HttpStatus.OK);
      
    }
    
    @RequestMapping(value = "/resourcedelete/{bookmark_user_token}/{bookmark_resource_name}/{bookmark_resource_priority}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<String> resourcedelete(@PathVariable String bookmark_user_token, @PathVariable String bookmark_resource_name, @PathVariable String bookmark_resource_priority) throws JSONException {

        try {
            String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
            String email = tokendecoded.split(":")[1];
            
            JSONObject jObject = new JSONObject();
           String resource = jObject.put("Bookmark_resource_name", bookmark_resource_name).toString();
            resource = jObject.put("Bookmark_resource_user_email", email).toString();
            resource = jObject.put("Bookmark_resource_priority", bookmark_resource_priority).toString();
           
            //resourcegroup = jObject.put("Bookmark_resourcegroup_name", resourcename).toString();
      Boolean  result = resourceFacade.resourcedelete(resource);
      if (result = true){
            return new ResponseEntity<String>("success", HttpStatus.OK);
      }
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
    }   
    @RequestMapping(value = "/activityget/{bookmark_user_token}/{bookmark_resource_name}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> getActivity(@PathVariable String bookmark_user_token,@PathVariable String bookmark_resource_name) throws JSONException {

        try {
            String tokendecoded = new String(Base64.decodeBase64(bookmark_user_token));
            String email = tokendecoded.split(":")[1];
            
            JSONObject jObject = new JSONObject();
           String activity = jObject.put("Bookmark_activity_user_email", email).toString();
           activity = jObject.put("Bookmark_activity_Resource_name", bookmark_resource_name).toString();
            String responseData = resourceFacade.getActivityDetails(activity);
            return new ResponseEntity<String>(responseData, HttpStatus.OK);
               } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return new ResponseEntity<String>("failed", HttpStatus.PRECONDITION_FAILED);
        }
         
} 

}
