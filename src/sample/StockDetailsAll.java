package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import sample.modle.Stock;

public class StockDetailsAll {
    @FXML                                       //FXid
    TableView tblViewAllProduct;
    @FXML
    TableColumn clmnProductID;
    @FXML
    TableColumn clmnQuantity;


    @FXML
    public void initialize(){                   //InitializeData base

        clmnProductID.setCellValueFactory(new PropertyValueFactory<>("productids"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("productquantity"));

        display();
    }


    @FXML
    public void display(){                          //view code
        MongoDatabase database = DBConnection.connect();

        MongoCollection collection = database.getCollection("Stocks");

        FindIterable<Document> findIterable = collection.find();

        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc: findIterable) {
            Stock Stocks = new Stock();
            Stocks.setProductids(doc.getString("ProductID"));
            Stocks.setProductquantity(doc.getString("Quantity"));
            mainList.add(Stocks);
        }



        tblViewAllProduct.setItems(mainList);                   //listView
    }

}
