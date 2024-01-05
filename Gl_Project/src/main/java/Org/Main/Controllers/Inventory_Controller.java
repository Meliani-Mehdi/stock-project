package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Inventory_Controller implements Initializable {
    @FXML
    private StackPane Inventory_Main;
    @FXML
    private VBox Products;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Products_Top_Button_Active();
    }
    ////////////////////////////////////////// Top buttons Active /////////////////////////////////////////////////////
    private boolean Products_active = false;
    private boolean Clients_active = false;
    private boolean Categories_active = false;
    private boolean Deposits_active = false;
    @FXML
    private Button Products_Top_Button;
    @FXML
    private Button Clients_Top_Button;
    @FXML
    private Button Categories_Top_Button;
    @FXML
    private Button Deposits_Top_Button;
    public void Products_Top_Button_Active(){
        Products_Top_Button.getStyleClass().removeLast();
        Products_Top_Button.getStyleClass().add("top-Button-active");
        Clients_Top_Button.getStyleClass().removeLast();
        Clients_Top_Button.getStyleClass().add("top-Button-neutral");
        Categories_Top_Button.getStyleClass().removeLast();
        Categories_Top_Button.getStyleClass().add("top-Button-neutral");
        Deposits_Top_Button.getStyleClass().removeLast();
        Deposits_Top_Button.getStyleClass().add("top-Button-neutral");
        ////////////////////////////////////////// Boolean /////////////////////////////////////////////////////
        Products_active=true;
        Clients_active=false;
        Categories_active=false;
        Deposits_active=false;
        /////////////////////////////////////// change the main section ////////////////////////////////////////
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Products);
        Products.setVisible(true);
    }
    public void Clients_Top_Button_Active(){
        Clients_Top_Button.getStyleClass().removeLast();
        Clients_Top_Button.getStyleClass().add("top-Button-active");
        Products_Top_Button.getStyleClass().removeLast();
        Products_Top_Button.getStyleClass().add("top-Button-neutral");
        Categories_Top_Button.getStyleClass().removeLast();
        Categories_Top_Button.getStyleClass().add("top-Button-neutral");
        Deposits_Top_Button.getStyleClass().removeLast();
        Deposits_Top_Button.getStyleClass().add("top-Button-neutral");
        ////////////////////////////////////////// Boolean /////////////////////////////////////////////////////
        Products_active=false;
        Clients_active=true;
        Categories_active=false;
        Deposits_active=false;
    }
    public void Categories_Top_Button_Active(){
        Categories_Top_Button.getStyleClass().removeLast();
        Categories_Top_Button.getStyleClass().add("top-Button-active");
        Products_Top_Button.getStyleClass().removeLast();
        Products_Top_Button.getStyleClass().add("top-Button-neutral");
        Clients_Top_Button.getStyleClass().removeLast();
        Clients_Top_Button.getStyleClass().add("top-Button-neutral");
        Deposits_Top_Button.getStyleClass().removeLast();
        Deposits_Top_Button.getStyleClass().add("top-Button-neutral");
        ////////////////////////////////////////// Boolean /////////////////////////////////////////////////////
        Products_active=false;
        Clients_active=false;
        Categories_active=true;
        Deposits_active=false;
    }
    public void Deposits_Top_Button_Active(){
        Deposits_Top_Button.getStyleClass().removeLast();
        Deposits_Top_Button.getStyleClass().add("top-Button-active");
        Products_Top_Button.getStyleClass().removeLast();
        Products_Top_Button.getStyleClass().add("top-Button-neutral");
        Clients_Top_Button.getStyleClass().removeLast();
        Clients_Top_Button.getStyleClass().add("top-Button-neutral");
        Categories_Top_Button.getStyleClass().removeLast();
        Categories_Top_Button.getStyleClass().add("top-Button-neutral");
        ////////////////////////////////////////// Boolean /////////////////////////////////////////////////////
        Products_active=false;
        Clients_active=false;
        Categories_active=false;
        Deposits_active=true;
    }
    ////////////////////////////////////////// Top buttons Active /////////////////////////////////////////////////////
    public void Products_Top_Button_On_Mouse_Enter(){
        if (!Objects.equals(Products_Top_Button.getStyleClass().getLast(), "top-Button-active")){
            Products_Top_Button.getStyleClass().removeLast();
            Products_Top_Button.getStyleClass().add("top-Button-hover");
        }
    }
    public void Clients_Top_Button_On_Mouse_Enter(){
        if (!Objects.equals(Clients_Top_Button.getStyleClass().getLast(), "top-Button-active")){
            Clients_Top_Button.getStyleClass().removeLast();
            Clients_Top_Button.getStyleClass().add("top-Button-hover");
        }
    }
    public void Categories_Top_Button_On_Mouse_Enter(){
        if (!Objects.equals(Categories_Top_Button.getStyleClass().getLast(), "top-Button-active")){
            Categories_Top_Button.getStyleClass().removeLast();
            Categories_Top_Button.getStyleClass().add("top-Button-hover");
        }
    }
    public void Deposits_Top_Button_On_Mouse_Enter(){
        if (!Objects.equals(Deposits_Top_Button.getStyleClass().getLast(), "top-Button-active")){
            Deposits_Top_Button.getStyleClass().removeLast();
            Deposits_Top_Button.getStyleClass().add("top-Button-hover");
        }
    }
    /////////////////////////////////////////// Top Button mouse exit  ////////////////////////////////////////////////////
    public void Products_Top_Button_On_Mouse_Exit() {
        if (!Products_active){
            Products_Top_Button.getStyleClass().removeLast();
            Products_Top_Button.getStyleClass().add("top-Button-neutral");
        }
    }
    public void Clients_Top_Button_On_Mouse_Exit() {
        if (!Clients_active){
            Clients_Top_Button.getStyleClass().removeLast();
            Clients_Top_Button.getStyleClass().add("top-Button-neutral");
        }
    }
    public void Categories_Top_Button_On_Mouse_Exit() {
        if (!Categories_active){
            Categories_Top_Button.getStyleClass().removeLast();
            Categories_Top_Button.getStyleClass().add("top-Button-neutral");
        }
    }
    public void Deposits_Top_Button_On_Mouse_Exit() {
        if (!Deposits_active){
            Deposits_Top_Button.getStyleClass().removeLast();
            Deposits_Top_Button.getStyleClass().add("top-Button-neutral");
        }
    }
    /////////////////////////////////////////////////////////////  Products  ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void Return_To_Products(){
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Products);
        Products.setVisible(true);
    }
    @FXML
    private VBox Add_Product;
    public void Go_To_Add_Product(){
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Add_Product);
        Add_Product.setVisible(true);
    }
}
