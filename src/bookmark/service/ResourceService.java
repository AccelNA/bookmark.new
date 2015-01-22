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
        
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Bookmark_Resource");
        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
        System.out.println("test" + tableDescription);  
         System.out.println(resource);
         String tableName = "Bookmark_Resource";
         JSONObject jObject  = new JSONObject(resource);
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
        JSONObject jObject  = new JSONObject(resourcegroup);
        String resourcegroupname = jObject.getString("Bookmark_resourcegroup_name");
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Bookmark_Resourcegroup");
        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
        System.out.println("test" + tableDescription);  
         System.out.println(resourcegroup);
         String tableName = "Bookmark_Resourcegroup";
         System.out.println("Resul11111t: " + resourcegroup);
        
         Map<String, AttributeValue> map = new HashMap<String,AttributeValue>();
         System.out.println("Resul11111sweeweghbt");
         Iterator iter = jObject.keys();
         while(iter.hasNext()){
             String key = (String)iter.next();
             String value = jObject.getString(key);
             AttributeValue aatr = new AttributeValue(value);
             map.put(key,aatr);
     }
         PutItemRequest putItemRequest = new PutItemRequest()
         .withTableName(tableName)
         .withItem(map);
         PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
         System.out.println("Result: " + putItemResult);
//         DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
//         Table table = dynamoDB.getTable("Bookmark_Resourcegroup");
//         Item  item = new Item()
//         .withPrimaryKey("Bookmark_resourcegroup_name",resourcegroupname)
//         .withJSON("resource",resourcegroup);
//         table.putItem(item);
        return true;
    }

    public boolean addActivity(String activity) throws Exception {
        config();
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Bookmark_Activity");
        TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
        System.out.println("test" + tableDescription);  
         System.out.println(activity);
         String tableName = "Bookmark_Activity";
         System.out.println("Resul11111t: " + activity);
         JSONObject jObject  = new JSONObject(activity);
         System.out.println("Resul12221t: " + activity);
         Map<String, AttributeValue> map = new HashMap<String,AttributeValue>();
         Iterator iter = jObject.keys();
         while(iter.hasNext()){
             String key = (String)iter.next();
             String value = jObject.getString(key);
             AttributeValue aatr = new AttributeValue(value);
             map.put(key,aatr);
     }
         PutItemRequest putItemRequest = new PutItemRequest()
         .withTableName(tableName)
         .withItem(map);
         PutItemResult putItemResult = dynamoDBClient.putItem(putItemRequest);
         System.out.println("Result: " + putItemResult);
        return true;
    }

    public String getresourcegroup(String resourcegroup) throws Exception {
        
      List<String> userNamesList = new ArrayList<String>();  
        
        config();
        JSONObject jObject  = new JSONObject(resourcegroup);
        String email = jObject.getString("bookmark_user_email");
        String tableName = "Bookmark_Resourcegroup"; 
//        Condition scanFilterCondition = new Condition()
//        .withComparisonOperator(ComparisonOperator.EQ.toString())
//        .withAttributeValueList(new AttributeValue().withS(email));
//        
//    Map<String, Condition> conditions = new HashMap<String, Condition>();
//    conditions.put("bookmark_user_email", scanFilterCondition);
//    
//    ScanRequest scanRequest = new ScanRequest()
//        .withTableName(tableName)
//        .withScanFilter(conditions)
//        .withProjectionExpression("Bookmark_resourcegroup_name");
    Map<String, AttributeValue> expressionAttributeValues = 
            new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":email", new AttributeValue().withS(email)); 
    ScanRequest scanRequest = new ScanRequest()
    .withTableName(tableName)
    .withFilterExpression("bookmark_user_email = :email")
    .withExpressionAttributeValues(expressionAttributeValues)
    .withProjectionExpression("Bookmark_resourcegroup_name");
ScanResult result = dynamoDBClient.scan(scanRequest);
for (Map<String, AttributeValue> item : result.getItems()) {
    System.out.println("itemsssss"+item);
}
//
 for (Map<String, AttributeValue> item : result.getItems()){
     userNamesList.add(item.get("Bookmark_resourcegroup_name").getS());
     
 }
 
 JSONArray resultarray = new JSONArray(userNamesList);
 
   
   // String getresult = marshall(result);
        return resultarray.toString();
    }

    public String getResourceGroupdetails(String resourcegroup) throws Exception {
        List<ResourceGroupdto> groupList = new ArrayList<ResourceGroupdto>();  
        config();
        JSONObject jObject  = new JSONObject(resourcegroup);
        String email = jObject.getString("bookmark_user_email");
        String tableName = "Bookmark_Resourcegroup"; 
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":email", new AttributeValue().withS(email)); 
        ScanRequest scanRequest = new ScanRequest()
        .withTableName(tableName)
        .withFilterExpression("bookmark_user_email = :email")
        .withExpressionAttributeValues(expressionAttributeValues)
         .withProjectionExpression("Bookmark_resourcegroup_name,Bookmark_resourcegroup_decription");
        //  .withProjectionExpression("Bookmark_resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
         try {
             for (Map<String, AttributeValue> item : result.getItems()){
          ResourceGroupdto resourcegroupdto  = new ResourceGroupdto();
          resourcegroupdto.setGroupName(item.get("Bookmark_resourcegroup_name").getS());
          resourcegroupdto.setGroupDescription(  null != item.get("Bookmark_resourcegroup_decription") ?  item.get("Bookmark_resourcegroup_decription").getS() : "");
          groupList.add(resourcegroupdto);
        }
        
       
            JSONArray resultarray = new JSONArray(groupList);
        
          
          // String getresult = marshall(result);
               return resultarray.toString();
        } catch (Exception e) {
            return null;
        }
        
        
    }

    public boolean updateResourceGroup(String resourcegroup) throws Exception {
        config();
        JSONObject jObject  = new JSONObject(resourcegroup);
        String groupkey =jObject.getString("Bookmark_resourcegroup_name");
        String date = jObject.getString("Bookmark_resource_updateddate");
       String email = jObject.getString("bookmark_user_email");
       String description = jObject.getString("Bookmark_resourcegroup_decription");
       
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Bookmark_resourcegroup_name", new AttributeValue().withS(groupkey));
        String tableName = "Bookmark_Resourcegroup";
        UpdateItemRequest updateitemrequest = new UpdateItemRequest()
        .withTableName(tableName).withKey(key)
        
        .addAttributeUpdatesEntry("Bookmark_resourcegroup_decription", new AttributeValueUpdate(new AttributeValue(description),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Bookmark_resourcgroupe_updateddate", new AttributeValueUpdate(new AttributeValue( date),  AttributeAction.PUT));
        UpdateItemResult result = dynamoDBClient.updateItem(updateitemrequest);
        return true;
    }

    public String getResourceDetails(String resourcegroup) throws Exception {
        List<Resourcedto> resourceList = new ArrayList<Resourcedto>();  
        config();
        JSONObject jObject  = new JSONObject(resourcegroup);
        String email = jObject.getString("bookmark_user_email");
        String tableName = "Bookmark_Resource"; 
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":email", new AttributeValue().withS(email)); 
        ScanRequest scanRequest = new ScanRequest()
        .withTableName(tableName)
        .withFilterExpression("Bookmark_resource_user_email = :email")
        .withExpressionAttributeValues(expressionAttributeValues)
         .withProjectionExpression("Bookmark_resource_name,Bookmark_resource_decription,Bookmark_resource_path,Bookmark_resource_priority,Bookmark_resourcegroup_name,bookmark_resource_createddate");
        //  .withProjectionExpression("Bookmark_resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
         try {
             for (Map<String, AttributeValue> item : result.getItems()){
          Resourcedto resourcedto  = new Resourcedto();
          resourcedto.setResourceName(item.get("Bookmark_resource_name").getS());
          resourcedto.setResourcePath(item.get("Bookmark_resource_path").getS());
          resourcedto.setResourcePriority(item.get("Bookmark_resource_priority").getS());
          resourcedto.setResourceGroupName(item.get("Bookmark_resourcegroup_name").getS());
          resourcedto.setCreatedDate(item.get("bookmark_resource_createddate").getS());
          resourcedto.setResourceDescription(null != item.get("Bookmark_resource_decription") ?  item.get("Bookmark_resource_decription").getS() : "");
          resourceList.add(resourcedto);
        }
        
       
            JSONArray resultarray = new JSONArray(resourceList);
        
               return resultarray.toString();
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return null;
        }
        
    }

    public boolean resourceGroupdelete(String bookmark_resource_groupName) throws Exception {
       config();
       String tableName = "Bookmark_Resourcegroup";
       HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
       key.put("Bookmark_resourcegroup_name", new AttributeValue().withS(bookmark_resource_groupName));
       DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
       .withTableName(tableName)
       .withKey(key);
       DeleteItemResult  deleteItemResult = dynamoDBClient.deleteItem(deleteItemRequest);
        return true;
    }

    public boolean updateResource(String resource) throws Exception {
        config();
        JSONObject jObject  = new JSONObject(resource);
        String resourcename =jObject.getString("Bookmark_resource_name"); 
        String date = jObject.getString("Bookmark_resource_updateddate");
       String email = jObject.getString("bookmark_user_email");
       String description = jObject.getString("Bookmark_resource_decription");
       String path = jObject.getString("Bookmark_resource_path");
       String priority = jObject.getString("Bookmark_resource_priority");
       String groupname = jObject.getString("Bookmark_resourcegroup_name");
       HashMap<String, AttributeValue> key1 = new HashMap<String, AttributeValue>();
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Bookmark_resource_user_email", new AttributeValue().withS(email));
        key1.put("Bookmark_resource_priority", new AttributeValue().withS(priority));
        String tableName = "Bookmark_Resource";
        UpdateItemRequest updateitemrequest = new UpdateItemRequest()
        .withTableName(tableName)
        .withKey(key).addKeyEntry("Bookmark_resource_priority", new AttributeValue().withS(priority))
        .addAttributeUpdatesEntry("Bookmark_resource_name", new AttributeValueUpdate(new AttributeValue(resourcename),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Bookmark_resource_decription", new AttributeValueUpdate(new AttributeValue(description),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Bookmark_resource_path", new AttributeValueUpdate(new AttributeValue(path),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Bookmark_resourcegroup_name", new AttributeValueUpdate(new AttributeValue(groupname),  AttributeAction.PUT))
        .addAttributeUpdatesEntry("Bookmark_resource_updateddate", new AttributeValueUpdate(new AttributeValue( date),  AttributeAction.PUT));
        UpdateItemResult result = dynamoDBClient.updateItem(updateitemrequest);
        return true;
    }

    public boolean addNote(String note) throws Exception {
            config();
            DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("Bookmark_Activity");
            TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
            System.out.println("test" + tableDescription);  
             System.out.println(note);
             String tableName = "Bookmark_Activity";
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
        String resourcename =jObject.getString("Bookmark_activity_Resource_name"); 
        String tableName = "Bookmark_Activity"; 
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":resourcename", new AttributeValue().withS(resourcename)); 
        ScanRequest scanRequest = new ScanRequest()
        .withTableName(tableName)
        .withFilterExpression("Bookmark_activity_Resource_name = :resourcename")
        .withExpressionAttributeValues(expressionAttributeValues)
         .withProjectionExpression("Bookmark_activity_Resource_name,Bookmark_activity_notes");
        //  .withProjectionExpression("Bookmark_resourcegroup_decription");
        ScanResult result = dynamoDBClient.scan(scanRequest);
         try {
             for (Map<String, AttributeValue> item : result.getItems()){
                 Activitydto activitydto  = new Activitydto();
                 activitydto.setActivity_resourcename(item.get("Bookmark_activity_Resource_name").getS());
                 activitydto.setNote(null != item.get("Bookmark_activity_notes") ?  item.get("Bookmark_activity_notes").getS() : "");
                 activityList.add(activitydto);
        }
            JSONArray resultarray = new JSONArray(activityList);
        
               return resultarray.toString();
        } catch (Exception e) {
            System.out.println("Exception thrown  :" + e);
            return null;
        }
    }

    public Boolean resourcedelete(String resource) throws Exception {
        JSONObject jObject  = new JSONObject(resource);
        String resourcename =jObject.getString("Bookmark_resource_name"); 
        String priority = jObject.getString("Bookmark_resource_priority");
        String email = jObject.getString("Bookmark_resource_user_email");
        config();
        String tableName = "Bookmark_Resource";
        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put("Bookmark_resource_user_email", new AttributeValue().withS(email));
        Map<String, AttributeValue> expressionAttributeValues = 
                new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":resourcename", new AttributeValue().withS(resourcename)); 
        DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
        .withTableName(tableName)
        .withKey(key).addKeyEntry("Bookmark_resource_priority", new AttributeValue().withS(priority))
        .withConditionExpression("Bookmark_resource_name = :resourcename")
        .withExpressionAttributeValues(expressionAttributeValues);
        DeleteItemResult  deleteItemResult = dynamoDBClient.deleteItem(deleteItemRequest);
         return true;
     
    }

  
    }
    

   
    

     

