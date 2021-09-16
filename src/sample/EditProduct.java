package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class EditProduct {
    @FXML
    TextField txtExcistingProduct;              //FX id
    @FXML
    TextField txtNewProduct;
    public static String ProductNameChecked;

    @FXML
    public void UpdateProduct() {
        String ExistingProNameText = txtExcistingProduct.getText();
        String NewProNameText = txtNewProduct.getText();
        Boolean FoundProduct = false;
        if (ExistingProNameText.equals("") && NewProNameText.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ExistingProName field or NewProName field is Empty");
            msg1.showAndWait();         //Alert Message for Empty Fields

        } else if (!ExistingProNameText.equals(ExistingProNameText.toLowerCase()) && !NewProNameText.equals(NewProNameText.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("ExistingProName and NewProName fields must contain with lowercase letters");
            msg2.showAndWait();         //Alert message for Uppercase letters(Incorrect Inputs)

        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> ProductCheck = database.getCollection("Products");
            FindIterable<Document> findIterable = ProductCheck.find();

            for (Document count : findIterable) {                   //Product Name Checked
                ProductNameChecked = count.getString("ProductName");
                if (ExistingProNameText.equals(ProductNameChecked)) {
                    FoundProduct = true;
                }
            }
            if (FoundProduct) {
                                                                //new Documents
                BasicDBObject updatePro = new BasicDBObject();
                updatePro.put("ProductName", ExistingProNameText);
                BasicDBObject updatedValPro = new BasicDBObject();
                updatedValPro.put("ProductName", NewProNameText);
                BasicDBObject newValPro = new BasicDBObject();
                newValPro.put("$set", updatedValPro);
                database.getCollection("Products").updateOne(updatePro, newValPro);

                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.INFORMATION);
                msg3.setContentText("Product updated Successfully");
                msg3.showAndWait();                     //Alert message for successfully update.


            }
        }
    }
}