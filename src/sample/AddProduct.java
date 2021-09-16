package sample;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

public class AddProduct {
    @FXML
    TextField txtaddPName;                      //FX id
    @FXML
    TextField txtaddPID;
    @FXML
    ComboBox cmboCategory;

    public  String SelectCategory;

    @FXML
    public void initialize(){                       //Initialize dataBase


       MongoDatabase database =DBConnection.connect();

       MongoCollection<Document>CategorySearching=database.getCollection("Categories");
       FindIterable<Document>findIterable=CategorySearching.find();
       ObservableList ComboCategory_List = FXCollections.observableArrayList();

        for (Document count :findIterable){
            SelectCategory=count.getString("CategoryName");
            ComboCategory_List.add(SelectCategory);

        }

        cmboCategory.setItems(ComboCategory_List);              //select Category from ComboList

    }

    @FXML
    public void AddProductCommand(ActionEvent event){

        Boolean FoundCategory = false;                              //Boolean Values
        String productName = txtaddPName.getText();


        if (productName.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ProductName field is Empty");
            msg1.showAndWait();                             //Alert Message for Empty Field

        } else if (!productName.equals(productName.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("ProductName Field must contain with lowercase letters");
            msg2.showAndWait();                             //Alert message for UpperCase letters

        } else {
            SelectCategory=(String)cmboCategory.getValue();         //Get values from comboList

            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Products");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {
                SelectCategory = count.getString("ProductName");
                if (productName.equals(SelectCategory)) {
                    FoundCategory = true;
                }
            }
            if (FoundCategory) {
                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.ERROR);
                msg3.setContentText("Product already Exists in Database");      //Alert Message for Existing Product
                msg3.showAndWait();
            } else {
                MongoDatabase database1 = DBConnection.connect();
                MongoCollection collection = database1.getCollection("Products");

                Document document = new Document();                                     //New Document create
                document.put("PCategoryName",cmboCategory.getValue());
                document.put("ProductName", txtaddPName.getText());
                document.put("ProductID",txtaddPID.getText());
                collection.insertOne(document);

                Alert msg4 = new Alert(Alert.AlertType.NONE);
                msg4.setAlertType(Alert.AlertType.INFORMATION);
                msg4.setContentText("Product add successfully to your cart");
                msg4.showAndWait();                             //Alert message for successfully adding


            }
        }
    }
}