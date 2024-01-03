package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane Main_Pane;
    private Stage stage;

    public void Exit(){
        showCustomConfirmationAlert("You are about to exit the application");
    }
    public void Minimize(){
        stage = (Stage) Main_Pane.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main_Pane.requestFocus();
        SignIn_Username_Textfield.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                SignIn_Password_TextField.requestFocus();
            }
        });
        SignIn_Password_TextField.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Sign_in_Button.fire();
            }
        });
    }

    private double xOffset, yOffset;
    public void searchLog(String name, String pass){
        String url = "jdbc:sqlite:main.db";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement quary = conn.prepareStatement("""
                            SELECT * FROM users WHERE username = ? AND password = ? ;
                            """)) {

            quary.setString(1,name);
            quary.setString(2,pass);

            try (ResultSet resultSet = quary.executeQuery()) {
                if (resultSet.next()) {
                    showCustomAlert("Welcome "+name);
                } else {
                    showCustomErrorAlert("Wrong user information");
                }
            }
            conn.close();
        } catch (SQLException e) {
            showCustomErrorAlert("Error");
        }
    }

    @FXML
    private TextField SignIn_Username_Textfield;

    @FXML
    private PasswordField SignIn_Password_TextField;

    @FXML
    private Button Sign_in_Button;
    public void Test() {
        searchLog(SignIn_Username_Textfield.getText(),SignIn_Password_TextField.getText());
    }



    //users that I made for testing :
    /*
    mehdi,prrr
    bob,12345
    3dnan,mathIsGood
    kazuha,imOpAF
    test,test
    admin,admin
     */

    /////////////////////////////////////////// Error alert /////////////////////////////////////////////////////////////////////
    private  void showCustomErrorAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.setY(328);
        alertStage.setX(539);

        AnchorPane alertRoot = new AnchorPane();
        alertRoot.setPrefSize(462, 191);

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Alert_Icons/dinosaur_alert.png"))));
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

        Button confirmButton = new Button("Ok");
        confirmButton.getStyleClass().add("Button");
        confirmButton.setFont(Font.font("SansSerif Regular", 20));
        confirmButton.setOnAction(e -> alertStage.close());
        confirmButton.setPrefSize(100, 50);
        confirmButton.setCursor(Cursor.HAND);
        buttonBox.getChildren().addAll(confirmButton);

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
    /////////////////////////////////////////// Message alert /////////////////////////////////////////////////////////////////////
    private  void showCustomAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.setY(328);
        alertStage.setX(539);

        AnchorPane alertRoot = new AnchorPane();
        alertRoot.setPrefSize(462, 191);

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Alert_Icons/dinosaur.png"))));
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

        Button confirmButton = new Button("Ok");
        confirmButton.getStyleClass().add("Button-Green");
        confirmButton.setFont(Font.font("SansSerif Regular", 20));
        confirmButton.setOnAction(e -> {
            alertStage.close();
            try {
                To_Dashboard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        confirmButton.setPrefSize(100, 50);
        confirmButton.setCursor(Cursor.HAND);
        buttonBox.getChildren().addAll(confirmButton);

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
    /////////////////////////////////////////// confirmation alert /////////////////////////////////////////////////////////////////////
    private  void showCustomConfirmationAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED);
        alertStage.setY(328);
        alertStage.setX(539);

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
    public void To_Dashboard() throws IOException {
        stage =(Stage) Main_Pane.getScene().getWindow();
        stage.close();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/Dashboard.fxml"));
        Parent root = loader.load();
        Stage primaryStage=new Stage();
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
