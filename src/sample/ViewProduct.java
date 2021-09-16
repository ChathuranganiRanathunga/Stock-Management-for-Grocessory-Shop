package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;
import sample.modle.Product;

public class ViewProduct {
    @FXML
    TableView tblViewProduct;               //FX ID
    @FXML
    TableColumn clmnCatName;
    @FXML
    TableColumn clmnProductName;
    @FXML
    TableColumn clmnProductID;

    @FXML
    public void initialize(){                           //Initialise Data Base
        clmnCatName.setCellValueFactory(new PropertyValueFactory<>("Pcategoryname"));
        clmnProductName.setCellValueFactory(new PropertyValueFactory<>("productname") );
        clmnProductID.setCellValueFactory(new PropertyValueFactory<>("productid"));

        display();
    }
    @FXML
    public void display(){
        MongoDatabase database=DBConnection.connect();                      //View
        MongoCollection collection=database.getCollection("Products");
        FindIterable<Document>findIterable=collection.find();
        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc:findIterable){
            Product Products=new Product();

            Products.setPcategoryname(doc.getString("PCategoryName")); //Get values
            Products.setProductname(doc.getString("ProductName"));
            Products.setProductid(doc.getString("ProductID"));

            mainList.add(Products);

        }
        tblViewProduct.setItems(mainList);                  //View in Table

    }

}




