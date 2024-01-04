package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Make_Order_Button.fire();
    }

    @FXML
    Label Username_label = new Label();
    @FXML
    Label Username_Label_Circle = new Label();
    public void display_name(String username) {
        Username_label.setText(username);
        Username_Label_Circle.setText(String.valueOf(username.charAt(0)).toUpperCase());
    }
    @FXML
    private StackPane App_Center_Pane;
    @FXML
    private StackPane Main_Pane;
    private Stage stage;
    private double xOffset, yOffset;
    public void Exit(){
        showCustomConfirmationAlert("You are about to exit the application");
    }
    @FXML
    void Set_Full_Screen() {
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
    //////////////////////////////// side bar buttons /////////////////////////////////////////////////////////////////
    @FXML
    private Button Administration_button;

    @FXML
    private Button Log_Out_Button;

    @FXML
    private Button Make_Order_Button;

    @FXML
    private Button Sell_Button;

    @FXML
    private Button Settings_button;

    @FXML
    private Button Inventory_Button;
    /////////////////////////////////////////// side bar icons ////////////////////////////////////////////////////
    @FXML
    private ImageView Administration_Icon;

    @FXML
    private ImageView Inventory_Icon;

    @FXML
    private ImageView Log_Out_Icon;

    @FXML
    private ImageView Make_Order_Icon;

    @FXML
    private ImageView Sell_Icon;

    @FXML
    private ImageView Settings_Icon;
    ////////////////////////////////////////// side bar click functions ////////////////////////////////////////////////////
    public boolean adm_active=false;
    public boolean Make_active=false;
    public boolean Sell_active=false;
    public boolean Inv_active=false;
    public boolean sett_active=false;
    ////////////////////////////////////////////// Administration Button //////////////////////////////////////////////////
    public void administration_click() {
        adm_active=true;
        Make_active=false;
        Sell_active=false;
        Inv_active=false;
        sett_active=false;
        ////////////////////// active boolean////////////////////////////////////////////////////////////////////////////////
        Administration_button.getStyleClass().clear();
        Administration_button.getStyleClass().add("side-Bar-navigation-Active");


        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Make_Order_Button.getStyleClass().clear();
        Make_Order_Button.getStyleClass().add("side-Bar-navigation-first");

        Sell_Button.getStyleClass().clear();
        Sell_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Settings_button.getStyleClass().clear();
        Settings_button.getStyleClass().add("side-Bar-navigation-neutral");

        Inventory_Button.getStyleClass().clear();
        Inventory_Button.getStyleClass().add("side-Bar-navigation-neutral");
        //// active style

        Image AdministrationActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-active.png")));
        Administration_Icon.setImage(AdministrationActive);

        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);

        Image MakeOrderNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png")));
        Make_Order_Icon.setImage(MakeOrderNeutral);

        Image SellNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-neutral.png")));
        Sell_Icon.setImage(SellNeutral);

        Image InventoryNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-neutral.png")));
        Inventory_Icon.setImage(InventoryNeutral);

        Image SettingsNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-neutral.png")));
        Settings_Icon.setImage(SettingsNeutral);
    }

    ////////////////////////////////////////////// Make_Order_Button  //////////////////////////////////////////////////
    public void Make_Order_click() throws IOException {
        adm_active=false;
        Make_active=true;
        Sell_active=false;
        Inv_active=false;
        sett_active=false;
        ////////////////////// active boolean////////////////////////////////////////////////////////////////////////////////
        Make_Order_Button.getStyleClass().clear();
        Make_Order_Button.getStyleClass().add("side-Bar-navigation-Active");

        Administration_button.getStyleClass().clear();
        Administration_button.getStyleClass().add("side-Bar-navigation-neutral");

        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Sell_Button.getStyleClass().clear();
        Sell_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Settings_button.getStyleClass().clear();
        Settings_button.getStyleClass().add("side-Bar-navigation-neutral");

        Inventory_Button.getStyleClass().clear();
        Inventory_Button.getStyleClass().add("side-Bar-navigation-neutral");
        //// active style
        Image MakeOrderActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/plus-active.png")));
        Make_Order_Icon.setImage(MakeOrderActive);

        Image AdministrationNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-neutral.png")));
        Administration_Icon.setImage(AdministrationNeutral);

        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);

        Image SellNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-neutral.png")));
        Sell_Icon.setImage(SellNeutral);

        Image InventoryNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-neutral.png")));
        Inventory_Icon.setImage(InventoryNeutral);

        Image SettingsNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-neutral.png")));
        Settings_Icon.setImage(SettingsNeutral);
        ////////////////////////////// center pane change ////////////////////////////////////
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/FXML/Make_Invoice.fxml")));
        BorderPane content;
        try {
            content = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        content.setPrefSize(App_Center_Pane.getWidth(),App_Center_Pane.getHeight());
        App_Center_Pane.getChildren().addFirst(content);

    }
    ////////////////////////////////////////////// Sell_Button  //////////////////////////////////////////////////
    public void Sell_click() {
        adm_active=false;
        Make_active=false;
        Sell_active=true;
        Inv_active=false;
        sett_active=false;
        ////////////////////// active boolean////////////////////////////////////////////////////////////////////////////////
        Sell_Button.getStyleClass().clear();
        Sell_Button.getStyleClass().add("side-Bar-navigation-Active");

        Administration_button.getStyleClass().clear();
        Administration_button.getStyleClass().add("side-Bar-navigation-neutral");

        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Make_Order_Button.getStyleClass().clear();
        Make_Order_Button.getStyleClass().add("side-Bar-navigation-first");

        Settings_button.getStyleClass().clear();
        Settings_button.getStyleClass().add("side-Bar-navigation-neutral");

        Inventory_Button.getStyleClass().clear();
        Inventory_Button.getStyleClass().add("side-Bar-navigation-neutral");
        //// active style
        Image SellActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-active.png")));
        Sell_Icon.setImage(SellActive);

        Image AdministrationNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-neutral.png")));
        Administration_Icon.setImage(AdministrationNeutral);

        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);

        Image MakeOrderNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png")));
        Make_Order_Icon.setImage(MakeOrderNeutral);

        Image InventoryNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-neutral.png")));
        Inventory_Icon.setImage(InventoryNeutral);

        Image SettingsNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-neutral.png")));
        Settings_Icon.setImage(SettingsNeutral);
    }
    ////////////////////////////////////////////// Inventory_Button  //////////////////////////////////////////////////
    public void Inventory_click() {
        adm_active=false;
        Make_active=false;
        Sell_active=false;
        Inv_active=true;
        sett_active=false;
        ////////////////////// active boolean////////////////////////////////////////////////////////////////////////////////
        Inventory_Button.getStyleClass().clear();
        Inventory_Button.getStyleClass().add("side-Bar-navigation-Active");

        Administration_button.getStyleClass().clear();
        Administration_button.getStyleClass().add("side-Bar-navigation-neutral");

        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Make_Order_Button.getStyleClass().clear();
        Make_Order_Button.getStyleClass().add("side-Bar-navigation-first");

        Sell_Button.getStyleClass().clear();
        Sell_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Settings_button.getStyleClass().clear();
        Settings_button.getStyleClass().add("side-Bar-navigation-neutral");
        //// active style
        Image InventoryActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-active.png")));
        Inventory_Icon.setImage(InventoryActive);

        Image AdministrationNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-neutral.png")));
        Administration_Icon.setImage(AdministrationNeutral);

        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);

        Image MakeOrderNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png")));
        Make_Order_Icon.setImage(MakeOrderNeutral);

        Image SellNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-neutral.png")));
        Sell_Icon.setImage(SellNeutral);

        Image SettingsNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-neutral.png")));
        Settings_Icon.setImage(SettingsNeutral);
    }
    ////////////////////////////////////////////// Settings_button  //////////////////////////////////////////////////
    public void Settings_click() {
        adm_active=false;
        Make_active=false;
        Sell_active=false;
        Inv_active=false;
        sett_active=true;
        ////////////////////// active boolean////////////////////////////////////////////////////////////////////////////////
        Settings_button.getStyleClass().clear();
        Settings_button.getStyleClass().add("side-Bar-navigation-Active");

        Administration_button.getStyleClass().clear();
        Administration_button.getStyleClass().add("side-Bar-navigation-neutral");

        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Make_Order_Button.getStyleClass().clear();
        Make_Order_Button.getStyleClass().add("side-Bar-navigation-first");

        Sell_Button.getStyleClass().clear();
        Sell_Button.getStyleClass().add("side-Bar-navigation-neutral");

        Inventory_Button.getStyleClass().clear();
        Inventory_Button.getStyleClass().add("side-Bar-navigation-neutral");
        //// active style
        Image SettingsActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-active.png")));
        Settings_Icon.setImage(SettingsActive);

        Image AdministrationNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-neutral.png")));
        Administration_Icon.setImage(AdministrationNeutral);

        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);

        Image MakeOrderNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png")));
        Make_Order_Icon.setImage(MakeOrderNeutral);

        Image SellNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-neutral.png")));
        Sell_Icon.setImage(SellNeutral);

        Image InventoryNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-neutral.png")));
        Inventory_Icon.setImage(InventoryNeutral);
    }
////////////////////////////////////////// side bar hover functions ////////////////////////////////////////////////////
    public void administration_On_Mouse_Enter() {
            if (!Objects.equals(Administration_button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
                Administration_button.getStyleClass().clear();
                Administration_button.getStyleClass().add("side-Bar-navigation-Active");
                Image AdministrationActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-active.png")));
                Administration_Icon.setImage(AdministrationActive);
            }
    }
    public void Log_Out_On_Mouse_Enter() {
        if (!Objects.equals(Log_Out_Button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
            Log_Out_Button.getStyleClass().clear();
            Log_Out_Button.getStyleClass().add("side-Bar-navigation-Active");
            Image LogOutActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-active.png")));
            Log_Out_Icon.setImage(LogOutActive);
        }
    }
    public void Make_Order_On_Mouse_Enter() {
        if (!Objects.equals(Make_Order_Button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
            Make_Order_Button.getStyleClass().clear();
            Make_Order_Button.getStyleClass().add("side-Bar-navigation-Active");
            Image MakeOrderActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/plus-active.png")));
            Make_Order_Icon.setImage(MakeOrderActive);
        }
    }

    public void Sell_On_Mouse_Enter() {
        if (!Objects.equals(Sell_Button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
            Sell_Button.getStyleClass().clear();
            Sell_Button.getStyleClass().add("side-Bar-navigation-Active");
            Image SellActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-active.png")));
            Sell_Icon.setImage(SellActive);
        }
    }
    public void Inventory_On_Mouse_Enter() {
        if (!Objects.equals(Inventory_Button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
            Inventory_Button.getStyleClass().clear();
            Inventory_Button.getStyleClass().add("side-Bar-navigation-Active");
            Image InventoryActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-active.png")));
            Inventory_Icon.setImage(InventoryActive);
        }
    }
    public void Settings_On_Mouse_Enter() {
        if (!Objects.equals(Settings_button.getStyleClass().getFirst(), "side-Bar-navigation-Active")){
            Settings_button.getStyleClass().clear();
            Settings_button.getStyleClass().add("side-Bar-navigation-Active");
            Image SettingsActive = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-active.png")));
            Settings_Icon.setImage(SettingsActive);
        }
    }
    /////////////////////////////////////////// side bar mouse exit functions ////////////////////////////////////////////////////
    public void administration_On_Mouse_Exit() {
        if (!adm_active){
            Administration_button.getStyleClass().clear();
            Administration_button.getStyleClass().add("side-Bar-navigation-neutral");
            Image AdministrationNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/administration-neutral.png")));
            Administration_Icon.setImage(AdministrationNeutral);
        }
    }
    public void Log_Out_On_Mouse_Exit() {
        Log_Out_Button.getStyleClass().clear();
        Log_Out_Button.getStyleClass().add("side-Bar-navigation-neutral");
        Image LogOutNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logout-neutral.png")));
        Log_Out_Icon.setImage(LogOutNeutral);
    }
    public void Make_Order_On_Mouse_Exit() {
        if (!Make_active){
            Make_Order_Button.getStyleClass().clear();
            Make_Order_Button.getStyleClass().add("side-Bar-navigation-first");
            Image MakeOrderNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png")));
            Make_Order_Icon.setImage(MakeOrderNeutral);
        }
    }
    public void Sell_On_Mouse_Exit() {
        if (!Sell_active){
            Sell_Button.getStyleClass().clear();
            Sell_Button.getStyleClass().add("side-Bar-navigation-neutral");
            Image SellNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/cart-neutral.png")));
            Sell_Icon.setImage(SellNeutral);
        }
    }
    public void Inventory_On_Mouse_Exit() {
        if (!Inv_active){
            Inventory_Button.getStyleClass().clear();
            Inventory_Button.getStyleClass().add("side-Bar-navigation-neutral");
            Image InventoryNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/Inventory-neutral.png")));
            Inventory_Icon.setImage(InventoryNeutral);
        }
    }
    public void Settings_On_Mouse_Exit() {
        if (!sett_active){
            Settings_button.getStyleClass().clear();
            Settings_button.getStyleClass().add("side-Bar-navigation-neutral");
            Image SettingsNeutral = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/settings-neutral.png")));
            Settings_Icon.setImage(SettingsNeutral);
        }
    }

    public static void createDatabase() {
        String url = "jdbc:sqlite:main.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()) {

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS clients (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                adresse TEXT,
                                phone_num TEXT,
                                sold_total REAL,
                                reste REAL,
                                paid REAL
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS deposits (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                solde REAL,
                                reste REAL,
                                payment REAL,
                                id_provider INTEGER,
                                id_client INTEGER,
                                id_seller INTEGER,
                                type TEXT,
                                payment_status TEXT
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS factures (
                                id INTEGER PRIMARY KEY,
                                date DATE NOT NULL,
                                update_date DATE,
                                update_time TIMESTAMP,
                                solde REAL,
                                reste REAL,
                                payment REAL,
                                id_provider INTEGER,
                                id_client INTEGER,
                                id_seller INTEGER,
                                type TEXT,
                                payment_status TEXT
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS groupes (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                marge REAL
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS products (
                                id INTEGER PRIMARY KEY,
                                bar_code VARCHAR NOT NULL,
                                reference VARCHAR,
                                name VARCHAR NOT NULL,
                                buying_price REAL,
                                selling_price REAL,
                                stock INTEGER,
                                photo VARCHAR,
                                id_groupe INTEGER
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS product_facts (
                                id INTEGER PRIMARY KEY,
                                id_fact INTEGER,
                                id_prod INTEGER,
                                product_price REAL,
                                product_qte INTEGER,
                                tva REAL
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS bon_de_livraisons (
                                id INTEGER PRIMARY KEY,
                                date DATE NOT NULL,
                                old_reste REAL,
                                reste REAL,
                                payment REAL,
                                id_client INTEGER,
                                id_user INTEGER,
                                id_provider INTEGER
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS providers (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                adresse TEXT,
                                phone_num TEXT,
                                sold_total REAL,
                                reste REAL,
                                paid REAL
                            );""");

            statement.execute("""
                            CREATE TABLE IF NOT EXISTS users (
                                id INTEGER PRIMARY KEY,
                                username TEXT NOT NULL,
                                password TEXT NOT NULL,
                                'last' INTEGER NOT NULL
                            );""");
            conn.close();
            conn.isClosed();
        } catch (SQLException e) {
            System.out.println("error");
        }
    }
    public void Log_Out_click() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Login.fxml")));
        Scene scene=new Scene(root);
        createDatabase();
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
        stage=(Stage) Main_Pane.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(500);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
