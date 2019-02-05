package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;

public class signupController {

    @FXML
    private TextField SignupFirstName;

    @FXML
    private TextField SignupLastName;

    @FXML
    private TextField SignupUserName;

    @FXML
    private TextField SignupLocation;

    @FXML
    private CheckBox SignupcheckboxMale;

    @FXML
    private CheckBox SignupCheckboxFemale;

    @FXML
    private TextField SignupPassword;

    @FXML
    private Button Signupbutton;

    @FXML
    void initialize() {


        Signupbutton.setOnAction(event -> {
            createUser();

            Signupbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/Loginview.fxml"));
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

        private void createUser(){
            DatabaseHandler baseHandler = new DatabaseHandler();

            String name = SignupFirstName.getText();
            String last = SignupLastName.getText();
            String username = SignupUserName.getText();
            String pass = SignupPassword.getText();
            String location = SignupLocation.getText();

            String gender = "";
            if (SignupCheckboxFemale.isSelected()) {

                gender = "female";
            }else gender = "male";

            User user = new User(name, last, username,pass,location,gender);

            baseHandler.SignUpUpser(user);

        }

    }



