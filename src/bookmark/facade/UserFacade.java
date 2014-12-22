package bookmark.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import bookmark.service.UserService;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

@Service("userFacade")
public class UserFacade {
    @Autowired

    UserService userService;
    
    public boolean createUser(String user) throws Exception {   
              return userService.createUser(user);
}

    public boolean loginUser(String user) throws JSONException {
        boolean result = false;
        JSONObject jObject  = new JSONObject(user);
        String key= jObject.getString("bookmark_user_password");
      
        try {
            String password= userService.loginUser(user);
            if(key.equals(password)){
                return true; 
             }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("authentication failed");
            System.err.println(e.getMessage());
      
        }
        return result ;
        
    }
}