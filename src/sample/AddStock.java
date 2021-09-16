package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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

public class AddStock {
    @FXML
    ComboBox CmbProducts;                   //FX id
    @FXML
    ComboBox CmbProductIDs;
    @FXML
    TextField txtQuantity;                  //variables
    public String IDsearch;
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
        ObservableList ComboProductName= FXCollections.observableArrayList();

        for (Document count : findIterable) {                   //searching currently have data in here
            IDsearch = (String) count.get("ProductID");
            ComboProducts_List.add(IDsearch);
            NameSearching=(String)count.get("ProductName");
            ComboProductName.add(NameSearching);
        }
        CmbProductIDs.setItems(ComboProducts_List);             //select add to combo box
        CmbProducts.setItems(ComboProductName);
    }

    @FXML
    public void AddStock() {
        UProduct = (String) CmbProducts.getValue();             //get values which have in combo
        IDSelect = (String) CmbProductIDs.getValue();
        String productquantity = txtQuantity.getText();
        int productQ = Integer.parseInt(productquantity);       //covert to integer
        Boolean HaveID = true;

        if (productquantity.equals("") || IDSelect.equals("")) {
            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ProductName Field,Id Field or Quantity Field is Empty");
            msg1.showAndWait();                 //Empty Field Alert Messages

        }
        Document searchProduct =new Document();                 //Searching Products And Ids have in Stocks
        searchProduct.put("ProductID",IDSelect);
        FindIterable<Document> findIterable = ProductStockNameSearching.find(searchProduct);

        for (Document count : findIterable) {
            IDsearch = (String) count.get("ProductID");
            AvailableQuantity = (String) count.get("Quantity");

            if (IDSelect.equals(IDsearch)) {            //Checking Boolean
                HaveID = false;
                break;
            } else {
                HaveID = true;
            }
        }
        if (HaveID==true) {
            Document StockDoc=new Document();
            StockDoc.put("ProductID", IDSelect);
            StockDoc.put("Quantity", productquantity);
            ProductStockNameSearching.insertOne(StockDoc);      //add to stock


            Alert msg4 = new Alert(Alert.AlertType.NONE);
            msg4.setAlertType(Alert.AlertType.INFORMATION);
            msg4.setContentText("Category add successfully to Stock");
            msg4.showAndWait();                 //Success Alert msg

        } else {
            int availableAmnt = Integer.parseInt(AvailableQuantity);    //Calculating Stocks in here
            newQuantity = availableAmnt + productQ;
            String finalValue = String.valueOf(newQuantity);

            Document updatingAmount=new Document();             //new Document
            updatingAmount.put("ProductID", IDSelect);


            Document updateValue=new Document();
            updateValue.put("Quantity", finalValue);
            Document newValue=new Document();
            newValue.put("$set", updateValue);                          //Updating Values in here
            ProductStockNameSearching.updateOne(updatingAmount,newValue);

            Alert msg4 = new Alert(Alert.AlertType.NONE);
            msg4.setAlertType(Alert.AlertType.INFORMATION);
            msg4.setContentText("Product added Stock to  successfully.");
            msg4.showAndWait();                         //Success Alert msg
        }


    }
}







