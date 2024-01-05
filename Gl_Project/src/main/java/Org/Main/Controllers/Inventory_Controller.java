package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.*;
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
        ////////////////////////////////////// show products //////////////////////////////////////////////
        Show_Products_In_The_Table();
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
        Layer.setVisible(false);
        Add_Product.setVisible(false);
    }
    @FXML
    private AnchorPane Layer;
    @FXML
    private VBox Add_Product;
    public void Go_To_Add_Product(){
        Inventory_Main.getChildren().addAll(Layer,Add_Product);
        Layer.setVisible(true);
        Add_Product.setVisible(true);
    }
    ////////////////////////////////////////////// create new product //////////////////////////////////////////////////
    @FXML
    private TextField Product_Barcode_Text_Field;

    @FXML
    private TextField Product_Name_Text_Field;

    @FXML
    private TextField Product_Reference_Text_Field;

    @FXML
    private TextField Product_Stock_Text_Field;

    @FXML
    private TextField Product_Buy_Price_Text_Field;

    @FXML
    private TextField Product_Sell_Price_Text_Field;
    ///// after you make the function try it in the app it's ready it just needs the logique for the database
    // mzl madertch l photo ta3 l product for the moment t9ad tzid ghi hado
    public void add_Product_To_Database(){
        String Bar_Code=Product_Barcode_Text_Field.getText();
        String Name=Product_Name_Text_Field.getText();
        String Reference=Product_Reference_Text_Field.getText();
        String Stock=Product_Stock_Text_Field.getText();
        String Buy_Price=Product_Buy_Price_Text_Field.getText();
        String Sell_Price=Product_Sell_Price_Text_Field.getText();
        if(
                !Bar_Code.isBlank() && !Name.isBlank() && !Reference.isBlank() && !Stock.isBlank() && !Buy_Price.isBlank() && !Sell_Price.isBlank()
        ){
            String url = "jdbc:sqlite:main.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                PreparedStatement query = conn.prepareStatement("INSERT INTO products (bar_code, reference, name, buying_price, selling_price, stock) VALUES(?, ?, ?, ?, ?, ?);");
                query.setString(1, Bar_Code);
                query.setString(2, Reference);
                query.setString(3, Name);
                query.setString(4, Buy_Price);
                query.setString(5, Sell_Price);
                query.setString(6, Stock);
                query.execute();
                conn.close();
            } catch (SQLException e) {
                System.out.println("error");
            }
            printMat(getProductsMatrix());//printMat is a quick function that i made to print the results of the prod table
                                          //getProductsMatrix is a function that returns a matrix of the SQL table products
        }
        else System.out.println("please enter all values");
    }
    //////////////////////////////////////////////// show products in the table ////////////////////////////////////////
    @FXML
    private GridPane Products_Table;
    @FXML
    private CheckBox CheckBoxExample;
    public void Show_Products_In_The_Table(){
        RowConstraints con = new RowConstraints();
        Products_Table.getRowConstraints().add(con);

        CheckBox checkBox = new CheckBox();
        checkBox.setText("");
        checkBox.getStylesheets().addAll(CheckBoxExample.getStylesheets());

        HBox hbox = new HBox(checkBox);
        hbox.getStyleClass().add("Product-Table-First-Col-Label");
        hbox.setAlignment(Pos.CENTER);
        Products_Table.add(hbox, 0, Products_Table.getRowConstraints().size() - 1);

        Button temp=new Button("edit");
        temp.getStyleClass().add("Product-Table-Last-Col-Label");
        temp.setFont(Font.font("Segoe UI", 18));
        temp.setPrefWidth(110);
        temp.setMaxWidth(temp.getPrefWidth());
        temp.setPrefHeight(hbox.getPrefHeight());
        temp.setMaxHeight(hbox.getPrefHeight());
        Products_Table.add(temp, 9, Products_Table.getRowConstraints().size() - 1);

        ////////// to add a product to the table just change the contents of the array
        String[] product={"3","Hello-34","61238540100","Mounir","200","300","300","Local Shop"};

        for (int col = 1; col < 9; col++) {
            ColumnConstraints cell=Products_Table.getColumnConstraints().get(col);
            Label emptyLabel = new Label(product[col-1]);
            emptyLabel.setPadding(new Insets(0, 0, 0, 5));
            emptyLabel.setPrefWidth(cell.getMaxWidth());
            emptyLabel.setPrefHeight(40);
            emptyLabel.setMinHeight(40);
            emptyLabel.setFont(Font.font("Segoe UI", 18));
            emptyLabel.getStyleClass().add("Product-Table-Label");
            Products_Table.add(emptyLabel, col, Products_Table.getRowConstraints().size() - 1);
        }
    }

    ////////////////////////////////////makes the SQL products table into a matrix/////////////////////////////////////////
    public static String[][] getProductsMatrix() {
        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement countQuery = conn.createStatement();
            ResultSet countResult = countQuery.executeQuery("SELECT COUNT(*) FROM products");
            countResult.next();
            int numRows = countResult.getInt(1);

            String[][] ren = new String[numRows][6];

            Statement dataQuery = conn.createStatement();
            ResultSet resultSet = dataQuery.executeQuery("SELECT bar_code, reference, name, buying_price, selling_price, stock FROM products");

            int row = 0;
            while (resultSet.next()) {
                ren[row][0] = resultSet.getString("bar_code");
                ren[row][1] = resultSet.getString("reference");
                ren[row][2] = resultSet.getString("name");
                ren[row][3] = resultSet.getString("buying_price");
                ren[row][4] = resultSet.getString("selling_price");
                ren[row][5] = resultSet.getString("stock");
                row++;
            }

            return ren;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public static void printMat(String[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Empty matrix");
            return;
        }

        for (String[] strings : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(strings[j] + "\t");
            }
            System.out.println();
        }
    }
}
