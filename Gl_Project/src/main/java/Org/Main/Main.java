package Org.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    private double xOffset, yOffset;
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/Fonts/Quicksand-VariableFont_wght.ttf"), 12);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Login.fxml")));
        Scene scene=new Scene(root);
        root.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            if(yOffset<=37){
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().getRoot().setStyle("-fx-background-radius: 10;");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
