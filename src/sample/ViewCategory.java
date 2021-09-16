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
import sample.modle.Category;

public class ViewCategory {
    @FXML
    TableView tblViewCategory;      //FX id
    @FXML
    TableColumn columnCategory;

    @FXML
    public void initialize(){               //Initialize Data Base in here

        columnCategory.setCellValueFactory(new PropertyValueFactory<>("categoryname"));

        display();
    }


    @FXML
    public void display(){
        MongoDatabase database = DBConnection.connect();        //View and Display

        MongoCollection collection = database.getCollection("Categories");

        FindIterable<Document> findIterable = collection.find();

        ObservableList mainList = FXCollections.observableArrayList();

        for (Document doc: findIterable) {
             Category Categories= new Category();

            Categories.setCategoryname(doc.getString("CategoryName"));
            mainList.add(Categories);
        }

        tblViewCategory.setItems(mainList);     //View Category in Table
    }

}

