package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class SearchProduct {
    @FXML
    TextField txtSearchProduct;                     //FX id
    public static String SearchProductName;

    public void SearchProduct() {
        String productname = txtSearchProduct.getText();        //Searching
        Boolean FoundProduct = false;

        if (productname.equals("")){

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ProductName field is Empty");
            msg1.showAndWait();             //Empty Field Alert message

        } else if (!productname.equals(productname.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("ProductName Field must contain with lowercase letters");
            msg2.showAndWait();         //Uppercase letters Alert Messages

        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Products");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {           //Searching products
                SearchProductName = count.getString("ProductName");
                if (productname.equals(SearchProductName)) {
                    FoundProduct = true;

                }
            }
            if (!FoundProduct) {
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.ERROR);
                msg3.setContentText("The Product have not in the cart.plz Try again..........");
                msg3.showAndWait();     //Error Alert Message haven't Found
            }else{
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.INFORMATION);
                msg3.setContentText("The product you have searched for is in your cart");
                msg3.showAndWait(); //Alert Message for already have Product in DataBase



            }
        }
    }
}
