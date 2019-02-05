package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class CellController extends ListCell<Task> {

    @FXML
    private ImageView refreshButton;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView IconImagView;

    @FXML
    private Label taskLabel;

    @FXML
    private Label DesCriptionLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private ImageView DeleteButton;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            taskLabel.setText(myTask.getTask());
            DateLabel.setText(myTask.getDatecreated().toString());
            DesCriptionLabel.setText(myTask.getDiscription());

            int taskID = myTask.getTaskid();

            DeleteButton.setOnMouseClicked(event -> {
                databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.deleteTask(AddItemController.Userid, taskID);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                getListView().getItems().remove(getItem());
            });

            setText(null);
            setGraphic(rootAnchorPane);
        }

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {

            refreshButton.setOnMouseClicked(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/UpdateTaskForm.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                databaseHandler = new DatabaseHandler();
                UpdateTaskController updateTaskController = loader.getController();
                updateTaskController.setDescription(myTask.getDiscription());
                updateTaskController.setTask(myTask.getTask());



                updateTaskController.UpdateTaskButton.setOnAction(event1 -> {

                    Calendar calendar = Calendar.getInstance();

                    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());


                    System.out.println("taskid " + myTask.getTaskid());


                    try {

                        databaseHandler.UpdateTask(timestamp, updateTaskController.getDescription(),
                                updateTaskController.getTask(), myTask.getTaskid());


                        //updateTaskController.refreshList();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                });
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();



            });


        }
    }
}






