package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Label lblWelcome;                                              //FXid
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassWord;
    @FXML
    private Label lblStatus;

    public void LogIn(ActionEvent event) throws Exception {             //Log In
        if (txtUserName.getText().equals("Chathu") && txtPassWord.getText().equals("1234")) {
            lblStatus.setText("LogIn Success");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            primaryStage.setTitle("E-Market");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

        }else{
                lblStatus.setText("Invalid Username or Password");      //Re enter User name and PW
            }
        }
    }






