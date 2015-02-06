package bookmark.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bookmark.dto.Activitydto;
import bookmark.dto.ResourceGroupdto;
import bookmark.dto.Resourcedto;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.amazonaws.util.json.JSONWriter;

@Service
public class ResourceService {
    static AmazonDynamoDBClient dynamoDBClient;
    
    
    public boolean createresource(String resource) throws Exception {
        config();

        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Resource");
        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
        System.out.println("test" + tableDescription);
        String tableName = "Resource";
        JSONObject jObject = new JSONObject(resource);
        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        Iterator iter = jObject.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String value = jObject.getString(key);
            AttributeValue aatr = new AttributeValue(value);
            map.put(key, aatr);
        }
        PutItemRequest putItemRequest = new PutItemRequest().withTableName(tableName).withItem(map);
        PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
        System.out.println("Result: " + putItemResult);
        return true;
    }

    private static void config() throws Exception {
        AWSCredentials credentials = null;

        credentials = new ProfileCredentialsProvider("default").getCredentials();

        dynamoDBClient = new AmazonDynamoDBClient(credentials);
        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);

        Region singapore = Region.getRegion(Regions.AP_SOUTHEAST_1);
        dynamoDBClient.setRegion(singapore);

    }

    public boolean createResourceGroup(String resourcegroup) throws Exception {
        config();
        JSONObject jObject = new JSONObject(resourcegroup);
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Resourcegroup");
        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
        System.out.println("test" + tableDescription);
        System.out.println(resourcegroup);
        String tableName = "Resourcegroup";
        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
        System.out.println("Resul11111sweeweghbt");
        Iterator iter = jObject.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String value = jObject.getString(key);
            AttributeValue aatr = new AttributeValue(value);
            map.put(key, aatr);
        }
        PutItemRequest putItemRequest = new PutItemRequest().withTableName(tableName).withItem(map);
        PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
        System.out.println("Result: " + putItemResult);
        return true;
    }

//    public boolean addActivity(String activity) throws Exception {
//        config();
//        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Activity");
//        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
//        System.out.println("test" + tableDescription);  
//         System.out.println(activity);
//         String tableName = "Activity";
//         JSONObject jObject  = new JSONObject(activity);
//         Map<String, AttributeValue> map = new HashMap<String,AttributeValue>();
//         Iterator iter = jObject.keys();
//         while(iter.hasNext()){
//             String key = (String)iter.next();
//             String value = jObject.getString(key);
//             AttributeValue aatr = new AttributeValue(value);
//             map.put(key,aatr);
//     }
//         PutItemRequest putItemRequest = new PutItemRequest()
//         .withTableName(tableName)
//         .withItem(map);
//         PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
//         System.out.println("Result: " + putItemResult);
//        return true;
//    }

    public String getresourcegroup(String resourcegroup) throws Exception {

        List<String> userNamesList = new ArrayList<String>();

        config();
        JSONObject jObject = new JSONObject(resourcegroup);
        String email = jObject.getString("bookmark_user_email");
        String tableName = "Resourcegroup";
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":email", new AttributeValue().withS(email));
        ScanRequest scanRequest = new ScanRequest().withTableName(tableName)
                .withFilterExpression("Resourcegroup_user_email = :email")
                .withExpressionAttributeValues(expressionAttributeValues)
                .withProjectionExpression("Resourcegroup_name");
        ScanResult result = dynamoDBClient.scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()) {
            System.out.println("itemsssss" + item);
        }
        for (Map<String, AttributeValue> item : result.getItems()) {
            userNamesList.add(item.get("Resourcegroup_name").getS());
        }
        JSONArray resultarray = new JSONArray(userNamesList);
        return resultarray.toString();
    }

    public String getResourceGroupdetails(String resourcegroup) throws Exception {
        List<ResourceGroupdto> groupList = new ArrayList<ResourceGroupdto>();
        config();
        JSONObject jObject = new JSONObject(resourcegroup);
        String email = jObject.getString("bookmark_user_email");
        String tableName = "Resourcegroup";
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":email", new AttributeValue().withS(email));
        ScanRequest scanRequest = new ScanRequest().withTableName(tableName)
                .withFilterExpression("Resourcegroup_user_email = :email")
                .withExpressionAttributeValues(expressionAttributeValues)
                .withProjectionExpression("Resourcegroup_id,Resourcegroup_name,Resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
        try {
            for (Map<String, AttributeValue> item : result.getItems()) {
                ResourceGroupdto resourcegroupdto = new ResourceGroupdto();
                resourcegroupdto.setId(item.get("Resourcegroup_id").getS());
                resourcegroupdto.setGroupName(item.get("Resourcegroup_name").getS());
                resourcegroupdto.setGroupDescription(null != item.get("Resourcegroup_decription") ? item.get(
                        "Resourcegroup_decription").getS() : "");
                groupList.add(resourcegroupdto);
            }
            JSONArray resultarray = new JSONArray(groupList);
            return resultarray.toString();
        } catch (Exception e) {
            return null;
        }

    }

    public boolean updateResourceGroup(String resourcegroup) throws Exception {
        config();
        JSONObject jObject  = new JSONObject(resourcegroup);
        String groupkey =jObject.getString("Resourcegroup_id");
        String groupname = jObject.getString("Resourcegroup_name");
        String date = jObject.getString("Resourcegroup_updateddate");
       String description = jObject.getString("Resourcegroup_decription");
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Resourcegroup_id", new AttributeValue().withS(groupkey));
        String tableName = "Resourcegroup";
        UpdateItemRequest updateitemrequest = new UpdateItemRequest()
        .withTableName(tableName).withKey(key)
        .addAttributeUpdatesEntry("Resourcegroup_name", new AttributeValueUpdate(new AttributeValue(groupname),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resourcegroup_decription", new AttributeValueUpdate(new AttributeValue(description),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resourcegroup_updateddate", new AttributeValueUpdate(new AttributeValue( date),  AttributeAction.PUT));
        UpdateItemResult result = dynamoDBClient.updateItem(updateitemrequest);
        return true;
    }

    public String getResourceDetails(String email) throws Exception {
        List<Resourcedto> resourceList = new ArrayList<Resourcedto>();  
        config();
//        JSONObject jObject  = new JSONObject(resourcegroup);
//        String email = jObject.getString("bookmark_user_email");
        String tableName = "Resource"; 
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":email", new AttributeValue().withS(email)); 
        ScanRequest scanRequest = new ScanRequest()
        .withTableName(tableName)
        .withFilterExpression("Resource_user_email = :email")
        .withExpressionAttributeValues(expressionAttributeValues)
         .withProjectionExpression("Resource_id,Resource_name,Resource_decription,Resource_path,Resource_priority,Resource_resourcegroup_name,Resource_createddate");
        //  .withProjectionExpression("Bookmark_resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
         try {
             for (Map<String, AttributeValue> item : result.getItems()){
          Resourcedto resourcedto  = new Resourcedto();
          resourcedto.setId(item.get("Resource_id").getS());
          resourcedto.setResourceName(item.get("Resource_name").getS());
          resourcedto.setResourcePath(item.get("Resource_path").getS());
          resourcedto.setResourcePriority(item.get("Resource_priority").getS());
          resourcedto.setResourceGroupName(item.get("Resource_resourcegroup_name").getS());
          resourcedto.setCreatedDate(item.get("Resource_createddate").getS());
          resourcedto.setResourceDescription(null != item.get("Resource_decription") ?  item.get("Resource_decription").getS() : "");
          resourceList.add(resourcedto);
        }
        
       
            JSONArray resultarray = new JSONArray(resourceList);
        
               return resultarray.toString();
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return null;
        }
        
    }

    public boolean resourceGroupdelete(String Resourcegroup_id) throws Exception {
       config();
       String tableName = "Resourcegroup";
       HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
       key.put("Resourcegroup_id", new AttributeValue().withS(Resourcegroup_id));
       DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
       .withTableName(tableName)
       .withKey(key);
       DeleteItemResult  deleteItemResult = dynamoDBClient.deleteItem(deleteItemRequest);
        return true;
    }

    public boolean updateResource(String resource) throws Exception {
        config();
        JSONObject jObject  = new JSONObject(resource);
         String id = jObject.getString("Resource_id");
        String resourcename =jObject.getString("Resource_name"); 
        String date = jObject.getString("Resource_updateddate");
     //  String email = jObject.getString("Resource_user_email");
       String description = jObject.getString("Resource_decription");
       String path = jObject.getString("Resource_path");
       String priority = jObject.getString("Resource_priority");
       String groupname = jObject.getString("Resource_resourcegroup_name");
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Resource_id", new AttributeValue().withS(id));
        String tableName = "Resource";
        UpdateItemRequest updateitemrequest = new UpdateItemRequest()
        .withTableName(tableName)
        .withKey(key)
        .addAttributeUpdatesEntry("Resource_name", new AttributeValueUpdate(new AttributeValue(resourcename),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resource_priority", new AttributeValueUpdate(new AttributeValue(priority),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resource_decription", new AttributeValueUpdate(new AttributeValue(description),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resource_path", new AttributeValueUpdate(new AttributeValue(path),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resource_resourcegroup_name", new AttributeValueUpdate(new AttributeValue(groupname),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Resource_updateddate", new AttributeValueUpdate(new AttributeValue( date),  AttributeAction.PUT));
        UpdateItemResult result = dynamoDBClient.updateItem(updateitemrequest);
        return true;
    }

    public boolean addNote(String note) throws Exception {
            config();
            DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Activity");
            TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
            System.out.println("test" + tableDescription);  
             System.out.println(note);
             String tableName = "Activity";
             JSONObject jObject  = new JSONObject(note);
             Map<String, AttributeValue> map = new HashMap<String,AttributeValue>();
             Iterator iter = jObject.keys();
             while(iter.hasNext()){
                 String key = (String)iter.next();
                 String value = jObject.getString(key);
                 AttributeValue aatr = new AttributeValue(value);
                 map.put(key,aatr);
         }
             PutItemRequest putItemRequest =  new PutItemRequest()
             .withTableName(tableName)
             .withItem(map);
             PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
             System.out.println("Result: " + putItemResult);
            return true;
        }
    public String getActivityDetails(String activity) throws Exception {
        List<Activitydto> activityList = new ArrayList<Activitydto>();  
        config();
        JSONObject jObject  = new JSONObject(activity);
        String resourcename =jObject.getString("Activity_resource_name"); 
        String tableName = "Activity"; 
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":resourcename", new AttributeValue().withS(resourcename)); 
        ScanRequest scanRequest = new ScanRequest()
        .withTableName(tableName)
        .withFilterExpression("Activity_resource_name = :resourcename")
        .withExpressionAttributeValues(expressionAttributeValues)
         .withProjectionExpression("Activity_resource_name,Activity_notes");
        //  .withProjectionExpression("Bookmark_resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
         try {
             for (Map<String, AttributeValue> item : result.getItems()){
                 Activitydto activitydto  = new Activitydto();
                 activitydto.setActivity_resourcename(item.get("Activity_resource_name").getS());
                 activitydto.setNote(null != item.get("Activity_notes") ?  item.get("Activity_notes").getS() : "");
                 activityList.add(activitydto);
        }
            JSONArray resultarray = new JSONArray(activityList);
        
               return resultarray.toString();
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return null;
        }
    }

    public Boolean resourcedelete(String Resource_id) throws Exception {
        config();
        String tableName = "Bookmark_Resource";
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Resource_id", new AttributeValue().withS(Resource_id));
        DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
        .withTableName(tableName)
        .withKey(key);
        DeleteItemResult  deleteItemResult = dynamoDBClient.deleteItem(deleteItemRequest);
         return true;
     
    }

  
    }
    

   
    

     

