package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bson.Document;

public class RemoveStock {
    @FXML
    ComboBox CmbRemovePName;                               //id
    @FXML
    ComboBox CmbRemovePID;
    @FXML
    TextField txtRQuantity;

    public String IDsearch;                                 //variables
    public String AvailableQuantity;
    public String IDSelect;
    public String UProduct;
    public Integer newQuantity;
    public String NameSearching;

    MongoDatabase database = DBConnection.connect();
    MongoCollection<Document> ProductSearching = database.getCollection("Products");
    MongoCollection<Document> ProductStockNameSearching = database.getCollection("Stocks");

    @FXML
    public void initialize() {
        FindIterable<Document> findIterable = ProductSearching.find();
        ObservableList ComboProducts_List = FXCollections.observableArrayList();
        ObservableList ComboProductName = FXCollections.observableArrayList();

        for (Document count : findIterable) {              //search Ids and Product names witch already have in Stocks
            IDsearch = (String) count.get("ProductID");
            ComboProducts_List.add(IDsearch);
            NameSearching = (String) count.get("ProductName");
            ComboProductName.add(NameSearching);
        }
        CmbRemovePID.setItems(ComboProducts_List);
        CmbRemovePName.setItems(ComboProductName);
    }

    @FXML
    public void RemoveStock() {
        UProduct = (String) CmbRemovePName.getValue();          //get values from here
        IDSelect = (String) CmbRemovePID.getValue();
        String productquantity = txtRQuantity.getText();
        int productQ = Integer.parseInt(productquantity);       //Convert to Integer
        Boolean HaveID = true;

        if (productquantity.equals("") || IDSelect.equals("")) {        //checking validations
            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your Product Field,ID Field or Quantity Field is Empty");
            msg1.showAndWait();                 //Empty Fields Alert message

        }
        Document searchProduct = new Document();
        searchProduct.put("ProductID", IDSelect);
        FindIterable<Document> findIterable = ProductStockNameSearching.find(searchProduct);

        for (Document count : findIterable) {
            IDsearch = (String) count.get("ProductID");
            AvailableQuantity = (String) count.get("Quantity");

            if (IDSelect.equals(IDsearch)) {
                HaveID = false;                         //Checking by Boolean values
                break;
            } else {
                HaveID = true;
            }
        }
        if (HaveID == true) {                               //if Boolean Value True
            Document StockDoc = new Document();
            StockDoc.put("ProductID", IDSelect);
            StockDoc.put("Quantity", productquantity);
            ProductStockNameSearching.insertOne(StockDoc);


            Alert msg4 = new Alert(Alert.AlertType.NONE);       //Success Alert Message
            msg4.setAlertType(Alert.AlertType.INFORMATION);
            msg4.setContentText("Removed Successfully");
            msg4.showAndWait();
        } else {
            int availableAmnt = Integer.parseInt(AvailableQuantity);
            newQuantity = availableAmnt - productQ;                     //remove Product from Stock
            String finalValue = String.valueOf(newQuantity);

            Document updatingAmount = new Document();
            updatingAmount.put("ProductID", IDSelect);

            Document updateValue=new Document();
            updateValue.put("Quantity", finalValue);
            Document newValue=new Document();
            newValue.put("$set", updateValue);
            ProductStockNameSearching.updateOne(updatingAmount,newValue);       //value updates in here

            Alert msg4 = new Alert(Alert.AlertType.NONE);
            msg4.setAlertType(Alert.AlertType.INFORMATION);
            msg4.setContentText("Product Removed  successfully in Stocks.");       //Update Successfully Alert Message
            msg4.showAndWait();
        }
    }
}









