package Org.Main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class DashboardController {
    @FXML
    private StackPane Main_Pane;
    private Stage stage;
    private double xOffset, yOffset;
    public void Exit(){
        showCustomConfirmationAlert("You are about to exit the application");
    }
    @FXML
    void Set_Full_Screen(ActionEvent event) {
        stage = (Stage) Main_Pane.getScene().getWindow();
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(!stage.isFullScreen());
    }
    public void Minimize(){
        stage = (Stage) Main_Pane.getScene().getWindow();
        stage.setIconified(true);
    }
    private  void showCustomConfirmationAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.centerOnScreen();

        AnchorPane alertRoot = new AnchorPane();
        alertRoot.setPrefSize(462, 191);

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Alert_Icons/dinosaur_Confirm.png"))));
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        AnchorPane.setTopAnchor(imageView, 10.0);
        AnchorPane.setLeftAnchor(imageView, 199.0);

        Label errorLabel = new Label(message);
        errorLabel.setFont(Font.font("SansSerif Regular", 23));
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setPrefWidth(409);
        AnchorPane.setTopAnchor(errorLabel, 75.0);
        AnchorPane.setLeftAnchor(errorLabel, 27.0);

        HBox buttonBox = new HBox(30);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setPrefSize(436, 59);
        AnchorPane.setTopAnchor(buttonBox, 113.0);
        AnchorPane.setLeftAnchor(buttonBox, 13.0);

        Button confirmButton = new Button("Yes");
        confirmButton.getStyleClass().add("Button-Green");
        confirmButton.setFont(Font.font("SansSerif Regular", 20));
        confirmButton.setOnAction(e -> {
            stage = (Stage) Main_Pane.getScene().getWindow();
            System.exit(0);
        });
        confirmButton.setPrefSize(100, 50);
        confirmButton.setCursor(Cursor.HAND);

        Button cancelButton = new Button("No");
        cancelButton.getStyleClass().add("Button-Green");
        cancelButton.setFont(Font.font("SansSerif Regular", 20));
        cancelButton.setOnAction(e -> alertStage.close());
        cancelButton.setPrefSize(100, 50);
        cancelButton.setCursor(Cursor.HAND);

        buttonBox.getChildren().addAll(confirmButton,cancelButton);

        alertRoot.getChildren().addAll(imageView, errorLabel, buttonBox);

        Scene scene = new Scene(alertRoot);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Style/Alert._Style.css")).toExternalForm());

        alertStage.setScene(scene);

        alertRoot.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        alertRoot.setOnMouseDragged(e -> {
            alertStage.setX(e.getScreenX() - xOffset);
            alertStage.setY(e.getScreenY() - yOffset);
        });
        alertStage.showAndWait();
    }

}
