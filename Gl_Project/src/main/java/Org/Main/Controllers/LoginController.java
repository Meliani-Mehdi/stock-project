package Org.Main.Controllers;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private Button test;
    public void Test() {
        ScaleTransition scale=new ScaleTransition(Duration.millis(500),test);
        scale.setByY(1.5);
        scale.setByX(1.5);
        scale.setCycleCount(1);
        scale.play();
    }
}
