package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Observable;

public class ListController {

    @FXML
    private ListView<Task> listTaskView;

    @FXML
    private TextField listtaskField;

    @FXML
    private TextField listDiscriptionField;

    @FXML
    private Button ListSaveTaskButton;

    @FXML
    private ImageView ListRefreshButton;

    private
     ObservableList<Task> tasks;

    private
    ObservableList<Task> refreshedTask;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        tasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultset = databaseHandler.getTaskbyUser(AddItemController.Userid);

        while(resultset.next()){
            Task task = new Task();
            task.setTaskid(resultset.getInt("taskid"));
            task.setTask(resultset.getString("task"));
            task.setDatecreated(resultset.getTimestamp("datecreated"));
            task.setDiscription(resultset.getString("discription"));
            tasks.addAll(task);
        }


        listTaskView.setItems(tasks);
        listTaskView.setCellFactory(CellController -> new CellController());

        ListRefreshButton.setOnMouseClicked(event -> {
            try {
                RefreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ListSaveTaskButton.setOnAction(event -> addNewTask());
    }

    public void RefreshList() throws SQLException {

        System.out.println("refresh list called");

        refreshedTask = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultset = databaseHandler.getTaskbyUser(AddItemController.Userid);

        while(resultset.next()){
            Task task = new Task();
            task.setTaskid(resultset.getInt("taskid"));
            task.setTask(resultset.getString("task"));
            task.setDatecreated(resultset.getTimestamp("datecreated"));
            task.setDiscription(resultset.getString("discription"));

            refreshedTask.addAll(task);
        }

        listTaskView.setItems(refreshedTask);
        listTaskView.setCellFactory(CellController -> new CellController());

    }

    public void addNewTask(){

            if( !listtaskField.getText().equals("") || !listDiscriptionField.getText().equals("")) {
                databaseHandler = new DatabaseHandler();
                Task mynewtask = new Task();

                Calendar calendar = Calendar.getInstance();

                java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

                mynewtask.setUserID(AddItemController.Userid);
                mynewtask.setTask(listtaskField.getText().trim());
                mynewtask.setDiscription(listDiscriptionField.getText().trim());
                mynewtask.setDatecreated(timestamp);

                databaseHandler.insertTask(mynewtask);

                listtaskField.setText("");
                listDiscriptionField.setText("");

                try {
                    initialize();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
    }

}


