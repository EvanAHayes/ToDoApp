package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Fade;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class addItemFormController {


    private int userID;


    private DatabaseHandler databaseHandler;


    @FXML
    private Button todoButton;

    @FXML
    private Label SuccessfulLabel;

    @FXML
    private TextField taskField;

    @FXML
    private TextField DiscriptionField;

    @FXML
    private Button SaveTaskButton;

    @FXML
    void initialize() {

        databaseHandler= new DatabaseHandler();
        Task task = new Task();
        todoButton.setVisible(true);
        int taskNumber = 0;

        try {
            taskNumber = databaseHandler.getallTask(AddItemController.Userid);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        todoButton.setText("My To Do : " + "(" + taskNumber +")");
        todoButton.setOnAction(event-> {
            //send users to list screen

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
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


        SaveTaskButton.setOnAction(event -> {

        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());


        String myTask = taskField.getText().trim();
        String taskdiscription = DiscriptionField.getText().trim();

        if(!myTask.equals("") || !taskdiscription.equals("")){

            System.out.println("User ID " + AddItemController.Userid);
            
            task.setUserID(AddItemController.Userid);
            task.setDatecreated(timestamp);
            task.setDiscription(taskdiscription);
            task.setTask(myTask);
            databaseHandler.insertTask(task);


            SuccessfulLabel.setVisible(true);
            Fade fade = new Fade(SuccessfulLabel);
            fade.fadeout();


            taskField.setText("");
            DiscriptionField.setText("");

            //System.out.println("task added");
        }else{

            System.out.println("nothing added");

        }




    });

    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        System.out.println(this.userID);
    }
}
