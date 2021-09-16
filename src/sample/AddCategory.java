package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;
import sample.modle.Category;

public class AddCategory {
    @FXML
    TextField txtCategoryAdd;                           //FX id
    public static String CategoryNameChecked;

    public void AddCategory() {
        String categoryname = txtCategoryAdd.getText();     //get text in here
        Boolean FoundCategory = false;


        if (categoryname.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your CategoryName field is Empty");
            msg1.showAndWait();             //Alert message for empty Field


        } else if (!categoryname.equals(categoryname.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("CategoryName Field must contain with lowercase letters");
            msg2.showAndWait();         //Alert message for uppercase letters
        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Categories");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {           //Searching
                CategoryNameChecked = count.getString("CategoryName");
                if (categoryname.equals(CategoryNameChecked)) {
                    FoundCategory = true;
                }
            }
            if (FoundCategory) {
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.ERROR);
                msg3.setContentText("Category already Exists in Database");
                msg3.showAndWait();         //Already  Exist Category  Alert message
            } else {
                MongoDatabase database1 = DBConnection.connect();
                MongoCollection collection = database1.getCollection("Categories");

                Document document = new Document();                 //new Doc
                document.put("CategoryName", txtCategoryAdd.getText());
                collection.insertOne(document);

                Alert msg4 = new Alert(Alert.AlertType.NONE);
                msg4.setAlertType(Alert.AlertType.INFORMATION);
                msg4.setContentText("Category add successfully to your cart");
                msg4.showAndWait();             //Alert message for Successfully Adding


            }


        }
    }
}







