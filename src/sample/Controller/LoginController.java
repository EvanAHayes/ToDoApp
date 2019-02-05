package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    private int userID;

    @FXML
    private Label userCreatedLabel;

    @FXML
    private URL location;

    @FXML
    private TextField LoginUserName;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button LoginSignUpButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(event -> {


        String loginText = LoginUserName.getText().trim();
        String loginpassword = LoginPassword.getText().trim();

        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginpassword);


           ResultSet userRow = databaseHandler.getuser(user);

           int counter = 0;

           try {
               while (userRow.next()) {
                   counter++;
                   userID = userRow.getInt("userid");
               }
               //how to get it to only count 1 row in database
               if(counter == 1) {
                   showAdditemScreen();

               }else{
                   Shaker shakerusername = new Shaker(LoginUserName);
                   Shaker shakerpass = new Shaker(LoginPassword);
                   shakerusername.shake();
                   shakerpass.shake();
               }

           }catch (SQLException e) {
               e.printStackTrace();
           }




        });


        LoginSignUpButton.setOnAction(event -> {
            //take user to sign up screen


            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });


    }
private void showAdditemScreen(){

        // go to item screne
    LoginSignUpButton.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/view/AddItem.fxml"));
    try {
        loader.load();
        userCreatedLabel.setVisible(true);
    } catch (IOException e) {
        e.printStackTrace();
    }

    Parent root = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));

    AddItemController addItemController = loader.getController();
    addItemController.setUserid(userID);

    stage.showAndWait();

}


}

