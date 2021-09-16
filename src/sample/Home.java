package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Home {
    @FXML
    Button btnLogOUt;                                               //FX id
    @FXML
    MenuItem MnuAddC;
    @FXML
    public void AddCategorySelect()throws Exception{                   //Go add Category Window by selecting menu Items
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        primaryStage.setTitle("E-Market");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    @FXML
    public void ViewCategorySelect()throws Exception {                  //Go view Category Window Using menu items
        Stage ViewCategoryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ViewCategory.fxml"));
        ViewCategoryStage.setTitle("E-Market");
        ViewCategoryStage.setScene(new Scene(root, 800, 600));
        ViewCategoryStage.show();
    }
    @FXML
    public void EditTableSelect()throws Exception {                     //Go Edit Category Window using menu items
        Stage EditTableStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EditTable.fxml"));
        EditTableStage.setTitle("E-Market");
        EditTableStage.setScene(new Scene(root, 800, 600));
        EditTableStage.show();
    }
    @FXML
    public void DeleteCategorySelect()throws Exception {                //Go Delete Category Window using menu Items
        Stage DeleteCategoryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteCategory.fxml"));
        DeleteCategoryStage.setTitle("E-Market");
        DeleteCategoryStage.setScene(new Scene(root, 800, 600));
        DeleteCategoryStage.show();
    }
    @FXML
    public void AddProductSelect()throws Exception {                    //Go Product Add window by selecting menu Items
        Stage AddProductStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        AddProductStage.setTitle("E-Market");
        AddProductStage.setScene(new Scene(root, 800, 600));
        AddProductStage.show();
    }
    @FXML
    public void SearchProductSelect()throws Exception {             //Go Search Product window by selecting menu Items
        Stage SearchProductStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchProduct.fxml"));
        SearchProductStage.setTitle("E-Market");
        SearchProductStage.setScene(new Scene(root, 800, 600));
        SearchProductStage.show();
    }
    @FXML
    public void ViewProductSelect()throws Exception {               //Go View Product Window by selecting menu Items
        Stage ViewProductStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ViewProduct.fxml"));
        ViewProductStage.setTitle("E-Market");
        ViewProductStage.setScene(new Scene(root, 800, 600));
        ViewProductStage.show();
    }
    @FXML
    public void EditProductSelect()throws Exception {               //Go Edit Product Window by selecting menu items
        Stage EditProductStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        EditProductStage.setTitle("E-Market");
        EditProductStage.setScene(new Scene(root, 800, 600));
        EditProductStage.show();
    }
    @FXML
    public void DeleteProductSelect()throws Exception {             //Go Delete product window by selecting menu items
        Stage DeleteProductStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteProduct.fxml"));
        DeleteProductStage.setTitle("E-Market");
        DeleteProductStage.setScene(new Scene(root, 800, 600));
        DeleteProductStage.show();
    }
    @FXML
    public void AddStockSelect()throws Exception {                  //Go Add Stock window by selecting menu items
        Stage AddStockStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddStock.fxml"));
        AddStockStage.setTitle("E-Market");
        AddStockStage.setScene(new Scene(root, 800, 600));
        AddStockStage.show();
    }
    @FXML
    public void RemoveStockSelect()throws Exception {               //Go Remove Stock window by selecting menu items
        Stage RemoveStockStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("RemoveStock.fxml"));
        RemoveStockStage.setTitle("E-Market");
        RemoveStockStage.setScene(new Scene(root, 800, 600));
        RemoveStockStage.show();
    }
    @FXML
    public void StockDetailsAllSelect()throws Exception {           //Go All Stock Details by selecting menu items
        Stage StockDetailsAllStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StockDetailsAll.fxml"));
        StockDetailsAllStage.setTitle("E-Market");
        StockDetailsAllStage.setScene(new Scene(root, 800, 600));
        StockDetailsAllStage.show();
    }
    @FXML
    public void StockDetailsSingleSelect()throws Exception {       //Go single Stock details window selecting menu items
        Stage StockDetailsSingleStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SingleStockDetails.fxml"));
        StockDetailsSingleStage.setTitle("E-Market");
        StockDetailsSingleStage.setScene(new Scene(root, 800, 600));
        StockDetailsSingleStage.show();
    }
    @FXML
    public void LogOut(ActionEvent event) throws Exception{         //Log Out in Application
        Stage previousStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        previousStage.close();
    }




}
