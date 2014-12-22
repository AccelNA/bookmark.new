package bookmark.service;
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
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
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
import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

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
   
  static AmazonDynamoDBClient dynamoDBClient;
//   DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
//  static DynamoDB dynamoDB;
  
  

public boolean createUser(String user) throws Exception {
    
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
    config();
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
      Table table = dynamoDB.getTable("bookmark_user");
      System.out.println("test" + tableDescription);  
  }

  
public String loginUser(String user) throws Exception {
    config();
    JSONObject jObject  = new JSONObject(user);
    String value= jObject.getString("bookmark_user_email");
    String Key  = "bookmark_user_email";
    Map<String, AttributeValue> map = new HashMap<String,AttributeValue>();
    AttributeValue aatr = new AttributeValue(value);
    map.put(Key,aatr);
   GetItemResult result = dynamoDBClient.getItem("bookmark_user", map);
    String password = ((Map<String, AttributeValue>) result).get(bookmark_user_password).toString();
    System.out.println("eee"+password);
   return password ;
    
}

public static Map<String, AttributeValue> newItem(String bookmark_user_firstname, String bookmark_user_lastname) {
    Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
    item.put("bookmark_user_firstname", new AttributeValue(bookmark_user_firstname));
  
    return item;
}
  }




