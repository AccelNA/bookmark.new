package util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

@Component("awsconfig")
public class Awsconfig {
    
    static AmazonDynamoDBClient dynamoDB;
    public static void config() throws Exception {
        AWSCredentials credentials = null;
              
              credentials = new ProfileCredentialsProvider("default").getCredentials();
              
              dynamoDB = new AmazonDynamoDBClient(credentials);
              Region singapore = Region.getRegion(Regions.AP_SOUTHEAST_1);
              dynamoDB.setRegion(singapore);
              
              
              DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName("bookmark_user");
              TableDescription tableDescription = dynamoDB.describeTable(describeTableRequest).getTable();
              
              
              
              System.out.println("test" + tableDescription);  
          }
public static Map<String, AttributeValue> newItem(String bookmark_user_firstname, String bookmark_user_lastname, String bookmark_user_email, String bookmark_user_password) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("bookmark_user_firstname", new AttributeValue(bookmark_user_firstname));
       item.put("bookmark_user_lastname", new AttributeValue(bookmark_user_lastname));
        item.put("bookmark_user_email",  new AttributeValue(bookmark_user_email));
        item.put("bookmark_user_password", new AttributeValue(bookmark_user_password));

        return item;
  }
}
