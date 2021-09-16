package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

public class EditTable {
    @FXML
    TextField txtExistingCat;               //Fx id
    @FXML
    TextField txtNewCat;
    public static String CategoryNameChecked;

    @FXML
    public void ClickUpdate() {
        String ExistingCatNameText = txtExistingCat.getText();          //get Values
        String NewCatNameText = txtNewCat.getText();
        Boolean FoundCategory = false;
        if (ExistingCatNameText.equals("") && NewCatNameText.equals("")) {

            Alert msg1 = new Alert(Alert.AlertType.NONE);
            msg1.setAlertType(Alert.AlertType.ERROR);
            msg1.setContentText("Your ExistingCatName field or NewCatName field is Empty");
            msg1.showAndWait();         //Empty Field Error Alert Message
        } else if (!ExistingCatNameText.equals(ExistingCatNameText.toLowerCase()) && !NewCatNameText.equals(NewCatNameText.toLowerCase())) {

            Alert msg2 = new Alert(Alert.AlertType.NONE);
            msg2.setAlertType(Alert.AlertType.ERROR);
            msg2.setContentText("ExistingCatName and NewCatName fields must contain with lowercase letters");
            msg2.showAndWait();         //Alert message for Uppercase letters

        } else {
            MongoDatabase database = DBConnection.connect();
            MongoCollection<Document> CategoryCheck = database.getCollection("Categories");
            FindIterable<Document> findIterable = CategoryCheck.find();

            for (Document count : findIterable) {
                CategoryNameChecked = count.getString("CategoryName");
                if (ExistingCatNameText.equals(CategoryNameChecked)) {
                    FoundCategory = true;
                }
            }
            if (FoundCategory) {

                BasicDBObject updateCat=new BasicDBObject();
                updateCat.put("CategoryName",ExistingCatNameText);
                BasicDBObject updatedVal=new BasicDBObject();
                updatedVal.put("CategoryName",NewCatNameText);
                BasicDBObject newVal=new BasicDBObject();
                newVal.put("$set",updatedVal);                                  //Update Category
                database.getCollection("Categories").updateOne(updateCat,newVal);



                Alert msg3 = new Alert(Alert.AlertType.NONE);
                msg3.setAlertType(Alert.AlertType.INFORMATION);
                msg3.setContentText("Category updated Successfully");
                msg3.showAndWait();                 //Category Updated Successful Alert


            }


        }
    }
}
