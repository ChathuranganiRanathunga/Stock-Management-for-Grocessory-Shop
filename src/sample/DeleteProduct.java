package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class DeleteProduct {
    @FXML
    TextField txtDeletePName;               //FX id
    public static String CategoryNameChecked;
    @FXML
    TextField txtDeleteProductID;

    @FXML
    public void DeleteProduct() {
        String ProductNameText = txtDeletePName.getText();          //getValue
        Boolean FoundCategory = false;

        if (ProductNameText.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ProductName field is Empty");
            msg1.showAndWait();     //Empty Field Alert message

        } else if (!ProductNameText.equals(ProductNameText.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("ProductName Field must contain with lowercase letters");
            msg2.showAndWait();     //Uppercase Letter alert message

        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Products");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {
                CategoryNameChecked = count.getString("ProductName");   //Checking
                if (!ProductNameText.equals(CategoryNameChecked)) {
                    FoundCategory = true;
                }
            }
            if (!FoundCategory) {
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.ERROR);
                msg3.setContentText("Product doesn't Exists in Database");
                msg3.showAndWait();                 //Product haven't  in Database

            } else {
                MongoDatabase database1 = DBConnection.connect();
                MongoCollection collection = database1.getCollection("Products");

                Document document = new Document();
                document.put("ProductName", ProductNameText);
                collection.deleteMany(document);        //delete Value

                Alert msg4 = new Alert(Alert.AlertType.NONE);
                msg4.setAlertType(Alert.AlertType.INFORMATION);
                msg4.setContentText("Product delete successfully in your cart");
                msg4.showAndWait();         //Alert message for successfully Deleting

            }

        }
    }
}


