package sample.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {

    private FadeTransition fadeTransition;

    public Fade(Node node){
        fadeTransition = new FadeTransition(Duration.millis(2000), node);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
    }

    public void fadeout(){
        fadeTransition.play();
    }
}
