package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateTaskController {


    @FXML
    public AnchorPane rootAnchorpane;

    @FXML
    private TextField TaskUpdate;

    @FXML
    private TextField DescriptionUpdate;

    @FXML
    public Button UpdateTaskButton;

    public Task mytask;

    @FXML
    void initialize() {




    }
    public String getTask(){
        return TaskUpdate.getText().trim();
    }

    public String getDescription(){
        return DescriptionUpdate.getText().trim();
    }

    public void setTask(String task){
        this.TaskUpdate.setText(task);
    }

    public void setDescription(String description){
        this.DescriptionUpdate.setText(description);
    }
//
//    public void refreshList() throws SQLException {
//        System.out.println("Calling refresh List");
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation((getClass().getResource("/sample/view/list.fxml")));
//
//        try {
//            loader.load();
//            ListController listController = loader.getController();
//            listController.RefreshList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
