package bookmark.service;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




































import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import util.Awsconfig;





































import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;

import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.amazonaws.util.json.JSONWriter;

//@DynamoDBTable(tableName="bookmark_user") 
@Service
public class UserService {
//    @Autowired
//  //  @Qualifier("awsconfig")
//      Awsconfig awsconfig;
//    
//    
    String bookmark_user_firstname;
    String bookmark_user_password;
    String bookmark_user_lastname;
    String bookmark_user_email;
    String bookmark_user_createddate; 
    
  static AmazonDynamoDBClient dynamoDBClient;
//   DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
  //static DynamoDB dynamoDB;
  
  

public boolean createUser(String user) throws Exception {
    config();
// newItem(bookmark_user_firstname, bookmark_user_lastname);
    System.out.println(user);
    String tableName = "bookmark_user";
    System.out.println("Resul11111t: " + user);
    JSONObject jObject  = new JSONObject(user);
    System.out.println("Resul12221t: " + user);
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
    return true;
}
private static void config() throws Exception {
AWSCredentials credentials = null;

      credentials = new ProfileCredentialsProvider("default").getCredentials();
      
      dynamoDBClient = new AmazonDynamoDBClient(credentials);
      DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
      
      Region singapore = Region.getRegion(Regions.AP_SOUTHEAST_1);
      dynamoDBClient.setRegion(singapore);
      
      DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("bookmark_user");
      TableDescription tableDescription = dynamoDBClient.describeTable(describeTableRequest).getTable();
      System.out.println("test" + tableDescription);  
  }

////-----------------------------
public String getresourcegroup() throws Exception {
    config();
//    JSONObject jObject  = new JSONObject(resourcegroup);
//    String email = jObject.getString("bookmark_user_email");
    String tableName = "Bookmark_Resourcegroup"; 
    Condition scanFilterCondition = new Condition()
    .withComparisonOperator(ComparisonOperator.EQ.toString())
    .withAttributeValueList(new AttributeValue().withS("asdf@gmail.com"));
Map<String, Condition> conditions = new HashMap<String, Condition>();
conditions.put("bookmark_user_email", scanFilterCondition);
ScanRequest scanRequest = new ScanRequest()
    .withTableName(tableName)
    .withScanFilter(conditions);
ScanResult result = dynamoDBClient.scan(scanRequest);

// String getresult = marshall(result);
    return "";
}

////////------------------------------------------



public boolean loginUser(String user) throws Exception {
   config();
 //  getresourcegroup();
      boolean result = false; 
   JSONObject jObject  = new JSONObject(user);
   String password = jObject.getString("bookmark_user_password");
   String email = jObject.getString("bookmark_user_email");
    AWSCredentials credentials = null;
    
    credentials = new ProfileCredentialsProvider("default").getCredentials();
    
    dynamoDBClient = new AmazonDynamoDBClient(credentials);
    
  
    dynamoDBClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1)); 
    
    DynamoDB dynamodb = new DynamoDB(dynamoDBClient);

   String tableName = "bookmark_user"; 
   HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
   HashMap<String, AttributeValue> key1 = new HashMap<String, AttributeValue>();
   key.put("bookmark_user_email", new AttributeValue().withS(email));     
   key1.put("bookmark_user_password", new AttributeValue().withS(password));
  // Table table  = dynamodb.getTable(tableName);
  // System.out.println("eee"+table.getIndex("bookmark_user_email"));
   GetItemRequest getItemRequest = new GetItemRequest()
   .withTableName(tableName)
   .withKey(key).addKeyEntry("bookmark_user_password", new AttributeValue().withS(password));
   GetItemResult getresult = dynamoDBClient.getItem(getItemRequest); 
 //  System.out.println("eeeeee"+getresult);

   HashMap<String,AttributeValue> item1=(HashMap<String,AttributeValue>)getresult.getItem();
   if(item1!=null){
       Map<String,AttributeValue> item = getresult.getItem();
       String passwordresult =item.get("bookmark_user_password").getS();
       if(password.equals(passwordresult)){
       result = true;
   }else{
       result = false;
   }
   }
//    //String passwordresult =item.get("bookmark_user_password").getS();
//   Map<String,AttributeValue> item = getresult.getItem();
//    JSONObject jsonObject = new JSONObject(item);
//    String resultjson = jsonObject.toString();
//    String abc = marshall(getresult); 
//    System.out.println("eeeeeeresultjson"+ abc);
  return result ;
//    
}

public String marshall(GetItemResult getItemResult){
    if (getItemResult == null) {
      throw new AmazonClientException("Invalid argument passed to marshall(...)");
    }
    try {
      StringWriter stringWriter=new StringWriter();
      JSONWriter jsonWriter=new JSONWriter(stringWriter);
      jsonWriter.object();
      if (getItemResult.getItem() != null) {
        jsonWriter.key("Item").object();
        for (      String key : getItemResult.getItem().keySet()) {
          AttributeValue value=getItemResult.getItem().get(key);
          if (value != null) {
            jsonWriter.key(key).object();
            if (value.getN() != null) {
              jsonWriter.key("N").value(value.getN());
            }
   else           if (value.getS() != null) {
              jsonWriter.key("S").value(value.getS());
            }
   else           if (value.getSS() != null) {
              jsonWriter.key("SS").value(value.getSS());
            }
   else           if (value.getNS() != null) {
              jsonWriter.key("NS").value(value.getNS());
            }
            jsonWriter.endObject();
          }
        }
        jsonWriter.endObject();
      }
      jsonWriter.key("ConsumedCapacityUnits").value(0.5);
      jsonWriter.endObject();
      return stringWriter.toString();
    }
   catch (  Throwable t) {
      throw new AmazonClientException("Unable to marshall request to JSON: " + t.getMessage(),t);
    }
  }


public boolean updateUserToken(String user) throws Exception {
    config();
    JSONObject jObject  = new JSONObject(user);
    String password = jObject.getString("bookmark_user_password");
    String email = jObject.getString("bookmark_user_email");
    String token = jObject.getString("bookmark_user_token");
    HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
    key.put("bookmark_user_email", new AttributeValue().withS(email));
      
    String tableName = "bookmark_user"; 
   UpdateItemRequest updateitemrequest = new UpdateItemRequest()
   .withTableName(tableName).withKey(key).addKeyEntry("bookmark_user_password", new AttributeValue().withS(password))
   .addAttributeUpdatesEntry("Bookmark_user_token",  new AttributeValueUpdate(new AttributeValue(token), AttributeAction.PUT));
   UpdateItemResult result = dynamoDBClient.updateItem(updateitemrequest);
    return  true ;
}

public boolean logoutUser(String user) throws Exception {
    config();
    JSONObject jObject  = new JSONObject(user);
   String token = jObject.getString("bookmark_user_token");;
   String password = jObject.getString("bookmark_user_password");
    String email = jObject.getString("bookmark_user_email");
    HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
    key.put("bookmark_user_email", new AttributeValue().withS(email));
      
    String tableName = "bookmark_user"; 
   UpdateItemRequest updateitemrequest = new UpdateItemRequest()
   .withTableName(tableName).withKey(key).addKeyEntry("bookmark_user_password", new AttributeValue().withS(password))
   .addAttributeUpdatesEntry("Bookmark_user_token", new AttributeValueUpdate()
   .withAction(AttributeAction.DELETE));
   dynamoDBClient.updateItem(updateitemrequest);
    return  true ;
}

public String checkUserToken(String user) throws Exception {
    config();
        
    JSONObject jObject  = new JSONObject(user);
    String password = jObject.getString("bookmark_user_password");
    String email = jObject.getString("bookmark_user_email");
    String token = jObject.getString("bookmark_user_token");
    String tableName = "bookmark_user"; 
    HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
    HashMap<String, AttributeValue> key1 = new HashMap<String, AttributeValue>();
    key.put("bookmark_user_email", new AttributeValue().withS(email));     
    key1.put("bookmark_user_password", new AttributeValue().withS(password));
   // Table table  = dynamodb.getTable(tableName);
   // System.out.println("eee"+table.getIndex("bookmark_user_email"));
    GetItemRequest getItemRequest = new GetItemRequest()
    .withTableName(tableName)
    .withKey(key).addKeyEntry("bookmark_user_password", new AttributeValue().withS(password)).addKeyEntry("Bookmark_user_token", new AttributeValue().withS(token));
    
    GetItemResult result = dynamoDBClient.getItem(getItemRequest); 
     HashMap<String,AttributeValue> item=(HashMap<String,AttributeValue>)result.getItem();
     String tokenresult =item.get("Bookmark_user_token").getS();
   System.out.println("password is"+tokenresult);
  return tokenresult ;
 //    
 }


//public static Map<String, AttributeValue> newItem(String bookmark_user_firstname, String bookmark_user_lastname,String bookmark_user_createddate ) {
//    Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
//    item.put("bookmark_user_firstname", new AttributeValue(bookmark_user_firstname));
//    item.put("bookmark_user_lastname", new AttributeValue(bookmark_user_lastname));
//    item.put("bookmark_user_createddate", new AttributeValue(bookmark_user_createddate));
//    return item;
//}
  }




