package sample;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBConnection {



        public static MongoDatabase connect(){
            //Establish db connection
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("OnlineGrocery");

            //create a collection
            if(database.getCollection("Categories")==null){
                database.createCollection("Categories");
            }
            if (database.getCollection("Products")==null){
                database.createCollection("Products");
            }
            if (database.getCollection("Stocks")==null){
                database.createCollection("Stocks");
            }

            return database;
        }

}
