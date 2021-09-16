package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class DeleteCategory {
    @FXML
    TextField txtDeleteCatName;                             //FX id
    public static String CategoryNameChecked;               //Variable

    @FXML
    public void deleteCategory() {
        String categorynameDeleteText = txtDeleteCatName.getText(); //get Values
        Boolean FoundCategory = false;                          //Boolean


        if (categorynameDeleteText.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your CategoryName field is Empty");
            msg1.showAndWait(); //Alert message for empty Field

        } else if (!categorynameDeleteText.equals(categorynameDeleteText.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("CategoryName Field must contain with lowercase letters");
            msg2.showAndWait();     //Alert message for uppercase letters

        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Categories");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {       //Checking
                CategoryNameChecked = count.getString("CategoryName");
                if (categorynameDeleteText.equals(CategoryNameChecked)) {
                    FoundCategory = true;
                }
            }
            if (!FoundCategory) {
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.ERROR);
                msg3.setContentText("Category doesn't Exist in Database");
                msg3.showAndWait(); //Category Doesn't Exist Alert message
            } else {
                MongoDatabase database1 = DBConnection.connect();
                MongoCollection collection = database1.getCollection("Categories");

                Document document = new Document();
                document.put("CategoryName", categorynameDeleteText);
                collection.deleteMany(document);        //Deleting Category Doc

                Alert msg4 = new Alert(Alert.AlertType.NONE);
                msg4.setAlertType(Alert.AlertType.INFORMATION);
                msg4.setContentText("Category delete successfully in your cart");
                msg4.showAndWait(); //Alert message for successfully Deleting


            }


        }
    }
}

