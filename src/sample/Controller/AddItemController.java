package sample.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Fade;
import sample.animations.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    public static int Userid;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NoTaskLabel;

    @FXML
    private ImageView addButton;

    @FXML
    void initialize() {

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker butShake = new Shaker(addButton);
            butShake.shake();



            addButton.relocate(0,5);
            NoTaskLabel.relocate(0,45);
            addButton.setOpacity(0);
            NoTaskLabel.setOpacity(0);


            try {
                AnchorPane formpane = FXMLLoader.load(getClass().getResource("/sample/view/AddItemForm.fxml"));

                AddItemController.Userid = getUserID();


                //addItemFormController addItemFormController = new addItemFormController();
                //addItemFormController.setUserID(getUserID());

                Fade fade = new Fade(formpane);
                fade.fadeout();

                rootAnchorPane.getChildren().setAll(formpane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }


    public void setUserid(int userId){

        this.Userid = userId;
        System.out.println(this.Userid);
    }

    public int getUserID(){
        return this.Userid;
    }
}
