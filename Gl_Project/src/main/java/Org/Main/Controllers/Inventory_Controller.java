package Org.Main.Controllers;

import Org.Main.Alerts;
import Org.Main.Classes.Client;
import Org.Main.Classes.Product;
import Org.Main.getData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class Inventory_Controller implements Initializable {
    Alerts alert = new Alerts();
    @FXML
    private StackPane Inventory_Main;
    @FXML
    private VBox Products;
    public void Only_Numeric(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    textField.setText(oldValue);
                }
            });
        }
    }
    @FXML
    private Button Return_To_Products_Button;
    @FXML
    private ComboBox<String> Client_Filter_Combo_Box;
    private ObservableList<String> Client_Filter_List=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Products_Top_Button_Active();
        Only_Numeric(Product_Barcode_Text_Field,Product_Stock_Text_Field,Product_Buy_Price_Text_Field,Product_Sell_Price_Text_Field);
        Product_Sell_Price_Text_Field.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                add_Product_To_Database();
            }
        });
        Add_Product.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Return_To_Products_Button.fire();
            }
        });
        Product_Filter_List.addAll("Reference","Name");
        Product_Filter_Combo_Box.getItems().addAll(Product_Filter_List);
        Product_Filter_Combo_Box.setValue(Product_Filter_List.getFirst());

        Client_Filter_List.addAll("Name","adress");
        Client_Filter_Combo_Box.getItems().addAll(Client_Filter_List);
        Client_Filter_Combo_Box.setValue(Client_Filter_List .getFirst());
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
        removeNonFirstRowChildren(Products_Table);
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
        /////////////////////////////////////// change the main section ////////////////////////////////////////
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Clients);
        Clients.setVisible(true);
        ////////////////////////////////////// show clients /////////////////////////////////////////////////
        removeNonFirstRowChildren(Clients_Table);
        Show_Clients_In_The_Table();
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
        Image default_image=new Image("file:C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png");
        Product_Image.setImage(default_image);
    }
    @FXML
    private AnchorPane Layer;
    @FXML
    private VBox Add_Product;
    public void Go_To_Add_Product(){
        Inventory_Main.getChildren().addAll(Layer,Add_Product);
        Layer.setVisible(true);
        Add_Product.setVisible(true);
        Add_Product.requestFocus();
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

    @FXML
    private ImageView Product_Image;
    private File New_Product_file;
    @FXML
    private Rectangle Image_Container;
    public void Import_Image() {
        FileChooser image=new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp");
        image.setTitle("Chose Product image");
        image.getExtensionFilters().add(imageFilter);
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Pictures");
        assert resource != null;
        image.setInitialDirectory(new File(resource.getFile()));
        New_Product_file=image.showOpenDialog(Products.getScene().getWindow());
        if(New_Product_file!=null){
            getData.path=New_Product_file.getAbsolutePath();
            Image image1 = new Image(New_Product_file.toURI().toString(), 160, 150, false, true);
            Product_Image.setImage(image1);
        }
    }
    public void remove_Image(){
        Image default_image=new Image("file:C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png");
        Product_Image.setImage(default_image);
    }
    public void add_Product_To_Database(){
        String Bar_Code=Product_Barcode_Text_Field.getText();
        String Name=Product_Name_Text_Field.getText();
        String Reference=Product_Reference_Text_Field.getText();
        String Stock=Product_Stock_Text_Field.getText();
        String Buy_Price=Product_Buy_Price_Text_Field.getText();
        String Sell_Price=Product_Sell_Price_Text_Field.getText();
        String Image_Path;
        try {
            Image_Path=New_Product_file.getAbsolutePath();
        }catch (NullPointerException e){
            Image_Path="C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png";
        }
        if(!Bar_Code.isBlank() && !Name.isBlank() && !Reference.isBlank() && !Stock.isBlank() && !Buy_Price.isBlank() && !Sell_Price.isBlank() && Double.parseDouble(Buy_Price)>=0 && Double.parseDouble(Sell_Price)>=0 && Double.parseDouble(Stock)>=0){
            if (Double.parseDouble(Buy_Price)>Double.parseDouble(Sell_Price)) {
                AtomicBoolean confirm=alert.showCustomConfirmationAlert("You sure about the sell price ??");
                if (confirm.get()){
                    String url = "jdbc:sqlite:main.db";
                    try (Connection conn = DriverManager.getConnection(url)) {
                        PreparedStatement query = conn.prepareStatement("INSERT INTO products (bar_code, reference, name, buying_price, selling_price, stock, photo) VALUES(?, ?, ?, ?, ?, ?, ?);");
                        query.setString(1, Bar_Code);
                        query.setString(2, Reference);
                        query.setString(3, Name);
                        query.setString(4, Buy_Price);
                        query.setString(5, Sell_Price);
                        query.setString(6, Stock);
                        query.setString(7, Image_Path);
                        query.execute();
                        conn.close();
                    } catch (SQLException e) {
                        alert.showCustomErrorAlert("error");
                    }
                    removeNonFirstRowChildren(Products_Table);
                    Show_Products_In_The_Table();
                    alert.showCustomAlert("success");
                }
            }else{
                String url = "jdbc:sqlite:main.db";
                try (Connection conn = DriverManager.getConnection(url)) {
                    PreparedStatement query = conn.prepareStatement("INSERT INTO products (bar_code, reference, name, buying_price, selling_price, stock, photo) VALUES(?, ?, ?, ?, ?, ?, ?);");
                    query.setString(1, Bar_Code);
                    query.setString(2, Reference);
                    query.setString(3, Name);
                    query.setString(4, Buy_Price);
                    query.setString(5, Sell_Price);
                    query.setString(6, Stock);
                    query.setString(7, Image_Path);
                    query.execute();
                    conn.close();
                } catch (SQLException e) {
                    alert.showCustomErrorAlert("error");
                }
                removeNonFirstRowChildren(Products_Table);
                Show_Products_In_The_Table();
                alert.showCustomAlert("success");
            }
        }
        else alert.showCustomErrorAlert("please enter all values");
    }
    public void removeNonFirstRowChildren(GridPane gridPane) {
        gridPane.getChildren().removeIf(node -> {
            Integer rowIndex = GridPane.getRowIndex(node);
            return rowIndex != null && rowIndex != 0;
        });
    }
    //////////////////////////////////////////////// show products in the table ////////////////////////////////////////
    @FXML
    private GridPane Products_Table;

    public void Show_Products_In_The_Table(){
        Product[] product_list=getProductsMatrix();
        assert product_list != null;
        int list_size=product_list.length;
        for (Product value : product_list) {
            RowConstraints con = new RowConstraints();
            Products_Table.getRowConstraints().add(con);

            CheckBox checkBox = new CheckBox();
            checkBox.setText("");
            checkBox.getStylesheets().addAll(CheckBoxExample.getStylesheets());

            HBox hbox = new HBox(checkBox);
            hbox.getStyleClass().add("Product-Table-First-Col-Label");
            hbox.setAlignment(Pos.CENTER);
            Products_Table.add(hbox, 0, Products_Table.getRowConstraints().size() - 1);

            Button temp = new Button("edit");
            temp.setOnMouseClicked(event -> {
                int itemIndex = Products_Table.getChildren().indexOf(temp);
                Go_Edit_Product(itemIndex);
            });
            temp.getStyleClass().add("Product-Table-Last-Col-Label");
            temp.setStyle("-fx-text-fill:#05b074;");
            temp.setFont(Font.font("Segoe UI", 18));
            temp.setPrefWidth(110);
            temp.setCursor(Cursor.HAND);
            temp.setMaxWidth(temp.getPrefWidth());
            temp.setPrefHeight(hbox.getPrefHeight());
            temp.setMaxHeight(hbox.getPrefHeight());
            Products_Table.add(temp, 9, Products_Table.getRowConstraints().size() - 1);

            String[] product = {String.valueOf(value.getId()),
                    String.valueOf(value.getReference()),
                    String.valueOf(value.getCodebar()),
                    String.valueOf(value.getName()),
                    String.valueOf(value.getStock()),
                    String.valueOf(value.getBuying_Price()),
                    String.valueOf(value.getSelling_Price()),
                    "Local Shop"
            };
            for (int col = 1; col < 9; col++) {
                ColumnConstraints cell = Products_Table.getColumnConstraints().get(col);
                Label emptyLabel = new Label(product[col - 1]);
                emptyLabel.setPadding(new Insets(0, 0, 0, 5));
                emptyLabel.setPrefWidth(cell.getMaxWidth());
                emptyLabel.setPrefHeight(40);
                emptyLabel.setMinHeight(40);
                emptyLabel.setFont(Font.font("Segoe UI", 18));
                emptyLabel.getStyleClass().add("Product-Table-Label");
                Products_Table.add(emptyLabel, col, Products_Table.getRowConstraints().size() - 1);
            }
        }
    }

    ////////////////////////////////////makes the SQL products table into a matrix/////////////////////////////////////////
    public Product[] getProductsMatrix() {
        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            ArrayList<Product> productList = new ArrayList<>();

            try (Statement dataQuery = conn.createStatement()) {
                ResultSet resultSet = dataQuery.executeQuery("SELECT id, bar_code, reference, name, buying_price, selling_price, stock FROM products");

                while (resultSet.next()) {
                    productList.add(new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("bar_code"),
                            resultSet.getString("reference"),
                            resultSet.getString("name"),
                            resultSet.getDouble("buying_price"),
                            resultSet.getDouble("selling_price"),
                            resultSet.getInt("stock")
                    ));
                }
            }
        conn.close();
        return productList.toArray(new Product[0]);
        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
            return null;
        }
    }

    @FXML
    private CheckBox CheckBoxExample;

    public void Delete_Product() {
        AtomicBoolean confirm=alert.showCustomConfirmationAlert("You sure want to delete this ??");
        if (confirm.get()){
            for (int row = 0; row < Products_Table.getRowCount(); row++) {
                int currentRow = row;
                CheckBox checkBox = getCheckBoxInRow(Products_Table, currentRow);

                if (checkBox != null && checkBox.isSelected()) {
                    Products_Table.getChildren().removeIf(node -> {
                        Integer rowIndex = GridPane.getRowIndex(node);

                        if (rowIndex != null && rowIndex == currentRow) {
                            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == 1
                                    && node instanceof Label) {
                                String value = ((Label) node).getText();
                                Delete_Product_From_Database(value);
                            }
                            return true;
                        }
                        return false;
                    });
                }
            }
        }
    }

    private  CheckBox getCheckBoxInRow(GridPane gridPane, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);

            if (rowIndex != null && rowIndex == row && node instanceof HBox) {
                HBox hBox = (HBox) node;

                for (Node childNode : hBox.getChildren()) {
                    if (childNode instanceof CheckBox) {
                        return (CheckBox) childNode;
                    }
                }
            }
        }
        return null;
    }
    public void Delete_Product_From_Database(String Product_Id){
        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setString(1,Product_Id);
            preparedStatement.execute();
            conn.close();
            } catch (SQLException x) {
            System.out.println("error");
        }
    }
    @FXML
    private VBox Edit_Product;
    @FXML
    private Button but57;
    @FXML
    private Button Delete_Product_Image_Button;

    public void Go_Edit_Product(int itemIndex){
        Inventory_Main.getChildren().addAll(Layer,Edit_Product);
        Layer.setVisible(true);
        Edit_Product.setVisible(true);
        get_Product_Info_To_Edit(Products_Table,itemIndex);
        but57.setOnAction(event -> edit_Product(Integer.parseInt(((Label)Products_Table.getChildren().get(itemIndex+1)).getText())));
        Delete_Product_Image_Button.setOnAction(event -> Edit_Product_remove_Image(Integer.parseInt(((Label)Products_Table.getChildren().get(itemIndex+1)).getText())));
        getProduct_Image(Integer.parseInt(((Label)Products_Table.getChildren().get(itemIndex+1)).getText()));
    }
    public void Return_To_Products_From_Edit(){
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Products);
        Layer.setVisible(false);
        Edit_Product.setVisible(false);
    }
    @FXML
    private ImageView Edited_Product_Image;

    @FXML
    private TextField Edited_Product_Barcode_Text_Field;

    @FXML
    private TextField Edited_Product_Name_Text_Field;

    @FXML
    private TextField Edited_Product_Reference_Text_Field;

    @FXML
    private TextField Edited_Product_Stock_Text_Field;

    @FXML
    private TextField Edited_Product_Buy_Price_Text_Field;

    @FXML
    private TextField Edited_Product_Sell_Price_Text_Field;
    public void get_Product_Info_To_Edit(GridPane gridPane,int itemIndex){
        Edited_Product_Reference_Text_Field.setText(((Label)gridPane.getChildren().get(itemIndex+2)).getText());
        Edited_Product_Barcode_Text_Field.setText(((Label)gridPane.getChildren().get(itemIndex+3)).getText());
        Edited_Product_Name_Text_Field.setText(((Label)gridPane.getChildren().get(itemIndex+4)).getText());
        Edited_Product_Stock_Text_Field.setText(((Label)gridPane.getChildren().get((itemIndex+5))).getText());
        Edited_Product_Buy_Price_Text_Field.setText(((Label)gridPane.getChildren().get(itemIndex+6)).getText());
        Edited_Product_Sell_Price_Text_Field.setText(((Label)gridPane.getChildren().get(itemIndex+7)).getText());
    }
    public void getProduct_Image(int id) {
        String search = Search_Products_Text_Field.getText();
        String url = "jdbc:sqlite:main.db";
        String img = null;
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement countQuery = conn.createStatement();
            ResultSet countResult = countQuery.executeQuery("SELECT photo FROM products WHERE id = " + id);
            countResult.next();
            img = countResult.getString(1);
        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
        }
        Image_Path = img;
        Image default_image = new Image("file:" + Image_Path);
        Edited_Product_Image.setImage(default_image);
    }
    private String Image_Path;
    public void edit_Product(int id){
        String Bar_Code=Edited_Product_Barcode_Text_Field.getText();
        String Name=Edited_Product_Name_Text_Field.getText();
        String Reference=Edited_Product_Reference_Text_Field.getText();
        String Stock=Edited_Product_Stock_Text_Field.getText();
        String Buy_Price=Edited_Product_Buy_Price_Text_Field.getText();
        String Sell_Price=Edited_Product_Sell_Price_Text_Field.getText();

        if(!Bar_Code.isEmpty() && !Name.isEmpty() && !Reference.isEmpty() && !Stock.isEmpty() && !Buy_Price.isEmpty() && !Sell_Price.isEmpty()){
            if (Double.parseDouble(Buy_Price)>Double.parseDouble(Sell_Price)) {
                AtomicBoolean confirm=alert.showCustomConfirmationAlert("You sure about the sell price ??");
                if (confirm.get()){
                    String url = "jdbc:sqlite:main.db";
                    try (Connection conn = DriverManager.getConnection(url)) {
                        PreparedStatement query = conn.prepareStatement("UPDATE products set bar_code = ?, reference = ?, name = ?, buying_price = ?, selling_price = ?, stock = ?, photo = ? WHERE id = ?;");
                        query.setString(1, Bar_Code);
                        query.setString(2, Reference);
                        query.setString(3, Name);
                        query.setString(4, Buy_Price);
                        query.setString(5, Sell_Price);
                        query.setString(6, Stock);
                        query.setString(7, Image_Path);
                        query.setInt(8, id);
                        query.execute();
                        conn.close();
                    } catch (SQLException e) {
                        alert.showCustomErrorAlert("error");
                    }
                    removeNonFirstRowChildren(Products_Table);
                    Show_Products_In_The_Table();
                    alert.showCustomAlert("success");
                }
            }else{
                String url = "jdbc:sqlite:main.db";
                try (Connection conn = DriverManager.getConnection(url)) {
                    PreparedStatement query = conn.prepareStatement("UPDATE products set bar_code = ?, reference = ?, name = ?, buying_price = ?, selling_price = ?, stock = ?, photo = ? WHERE id = ?;");
                    query.setString(1, Bar_Code);
                    query.setString(2, Reference);
                    query.setString(3, Name);
                    query.setString(4, Buy_Price);
                    query.setString(5, Sell_Price);
                    query.setString(6, Stock);
                    query.setString(7, Image_Path);
                    query.setInt(8, id);
                    query.execute();
                    conn.close();
                } catch (SQLException e) {
                    alert.showCustomErrorAlert("error");
                }
                removeNonFirstRowChildren(Products_Table);
                Show_Products_In_The_Table();
                alert.showCustomAlert("success");
            }
        }
        else alert.showCustomErrorAlert("please enter all values");
    }
    public void Edit_Product_Import_Image(){
        FileChooser image=new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp");
        image.setTitle("Chose Product image");
        image.getExtensionFilters().add(imageFilter);
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Pictures");
        assert resource != null;
        image.setInitialDirectory(new File(resource.getFile()));
        File file=image.showOpenDialog(Products.getScene().getWindow());
        if(file!=null){
            getData.path=file.getAbsolutePath();
            Image_Path=file.getAbsolutePath();
            Image image1 = new Image(file.toURI().toString(), 160, 150, false, true);
            Edited_Product_Image.setImage(image1);
        }
    }
    public void Edit_Product_remove_Image(int id){
        AtomicBoolean confirm=alert.showCustomConfirmationAlert("You sure want to remove this ??");
        if (confirm.get()){
            String url = "jdbc:sqlite:main.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                PreparedStatement query = conn.prepareStatement("UPDATE products set photo = ? WHERE id = ?;");
                query.setString(1, "C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png");
                query.setInt(2, id);
                query.execute();
                conn.close();
            } catch (SQLException e) {
                alert.showCustomErrorAlert("error");
            }
            Image_Path="C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png";
            Image default_image=new Image("file:C:\\Users\\user\\OneDrive\\Documents\\GitHub\\stock-project\\Gl_Project\\src\\main\\resources\\Icons\\Inventory\\upload.png");
            Edited_Product_Image.setImage(default_image);
        }
    }
    @FXML
    private TextField Search_Products_Text_Field;
    public void Search_Product(){
        removeNonFirstRowChildren(Products_Table);
        Product[] product_list=SearchProductMatrix();
        assert product_list != null;
        int list_size=product_list.length;
        for (Product value : product_list) {
            RowConstraints con = new RowConstraints();
            Products_Table.getRowConstraints().add(con);

            CheckBox checkBox = new CheckBox();
            checkBox.setText("");
            checkBox.getStylesheets().addAll(CheckBoxExample.getStylesheets());

            HBox hbox = new HBox(checkBox);
            hbox.getStyleClass().add("Product-Table-First-Col-Label");
            hbox.setAlignment(Pos.CENTER);
            Products_Table.add(hbox, 0, Products_Table.getRowConstraints().size() - 1);

            Button temp = new Button("edit");
            temp.setOnMouseClicked(event -> {
                int itemIndex = Products_Table.getChildren().indexOf(temp);
                Go_Edit_Product(itemIndex);
            });
            temp.getStyleClass().add("Product-Table-Last-Col-Label");
            temp.setFont(Font.font("Segoe UI", 18));
            temp.setPrefWidth(110);
            temp.setCursor(Cursor.HAND);
            temp.setMaxWidth(temp.getPrefWidth());
            temp.setPrefHeight(hbox.getPrefHeight());
            temp.setMaxHeight(hbox.getPrefHeight());
            Products_Table.add(temp, 9, Products_Table.getRowConstraints().size() - 1);

            String[] product = {String.valueOf(value.getId()),
                    String.valueOf(value.getReference()),
                    String.valueOf(value.getCodebar()),
                    String.valueOf(value.getName()),
                    String.valueOf(value.getStock()),
                    String.valueOf(value.getBuying_Price()),
                    String.valueOf(value.getSelling_Price()),
                    "Local Shop"
            };
            for (int col = 1; col < 9; col++) {
                ColumnConstraints cell = Products_Table.getColumnConstraints().get(col);
                Label emptyLabel = new Label(product[col - 1]);
                emptyLabel.setPadding(new Insets(0, 0, 0, 5));
                emptyLabel.setPrefWidth(cell.getMaxWidth());
                emptyLabel.setPrefHeight(40);
                emptyLabel.setMinHeight(40);
                emptyLabel.setFont(Font.font("Segoe UI", 18));
                emptyLabel.getStyleClass().add("Product-Table-Label");
                Products_Table.add(emptyLabel, col, Products_Table.getRowConstraints().size() - 1);
            }
        }
    }
    @FXML
    private ComboBox<String> Product_Filter_Combo_Box;
    private ObservableList<String> Product_Filter_List= FXCollections.observableArrayList();
    public Product[] SearchProductMatrix() {
        String filter=Product_Filter_Combo_Box.getValue();
        filter=filter.toLowerCase();
        if (Objects.equals(filter, "categorie"))filter="groupe";
        String search=Search_Products_Text_Field.getText();
        if (search.isBlank()) return getProductsMatrix();

        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            ArrayList<Product> productList = new ArrayList<>();

            try (PreparedStatement countQuery = conn.prepareStatement("SELECT id, bar_code, reference, name, buying_price, selling_price, stock FROM products WHERE "+filter+" LIKE ? ")) {
                countQuery.setString(1, "%" + search + "%");

                try (ResultSet resultSet = countQuery.executeQuery()) {
                    while (resultSet.next()) {
                        productList.add(new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("bar_code"),
                                resultSet.getString("reference"),
                                resultSet.getString("name"),
                                resultSet.getDouble("buying_price"),
                                resultSet.getDouble("selling_price"),
                                resultSet.getInt("stock")
                        ));
                    }
                }
            }
            conn.close();
            return productList.toArray(new Product[0]);

        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
            return null;
        }
    }
    ////////////////////////////////////////////////// Clients //////////////////////////////////////////////////////
    @FXML
    private VBox Clients;
    @FXML
    private VBox Add_Client;
    public void Go_To_Add_Client(){
        Inventory_Main.getChildren().addAll(Layer,Add_Client);
        Layer.setVisible(true);
        Add_Client.setVisible(true);
        Add_Client.requestFocus();
    }
    public void Return_To_Clients(){
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Clients);
        Layer.setVisible(false);
        Add_Client.setVisible(false);
    }
    @FXML
    private TextField Add_Client_Adresse;

    @FXML
    private TextField Add_Client_Credit_Limit;

    @FXML
    private TextField Add_Client_Name;

    @FXML
    private TextField Add_Client_Phone_Num;

    @FXML
    private GridPane Clients_Table;
    public void add_Client_To_Database(){
        String Name=Add_Client_Name.getText();
        String Phone_Num=Add_Client_Phone_Num.getText();
        String Adresse=Add_Client_Adresse.getText();
        String Credit_Limit=Add_Client_Credit_Limit.getText();
        if(!Name.isBlank() && !Phone_Num.isBlank() && !Adresse.isBlank()){
            String url = "jdbc:sqlite:main.db";
            try (Connection conn = DriverManager.getConnection(url)) {
                PreparedStatement query = conn.prepareStatement("INSERT INTO clients (adresse, name, phone_num, sold_total, reste, paid, credit) VALUES(?, ?, ?, 0, 0, 0, ?);");
                query.setString(1, Adresse);
                query.setString(2, Name);
                query.setString(3, Phone_Num);
                if(Credit_Limit.isBlank())query.setString(4, "0");
                else query.setString(4, Credit_Limit);
                query.execute();
                conn.close();
            } catch (SQLException e) {
                alert.showCustomErrorAlert("error");
            }
            removeNonFirstRowChildren(Clients_Table);
            Show_Clients_In_The_Table();
            alert.showCustomAlert("success");

        }
        else alert.showCustomErrorAlert("please enter all Info");
    }
    public void Show_Clients_In_The_Table(){
        Client[] client_list=getClientsMatrix();
        assert client_list != null;
        int list_size=client_list.length;
        for (Client value : client_list) {
            RowConstraints con = new RowConstraints();
            Clients_Table.getRowConstraints().add(con);

            CheckBox checkBox = new CheckBox();
            checkBox.setText("");
            checkBox.getStylesheets().addAll(CheckBoxExample.getStylesheets());

            HBox hbox = new HBox(checkBox);
            hbox.getStyleClass().add("Product-Table-First-Col-Label");
            hbox.setAlignment(Pos.CENTER);
            Clients_Table.add(hbox, 0, Clients_Table.getRowConstraints().size() - 1);

            Button temp = new Button("Stats");
            temp.getStyleClass().add("Product-Table-Last-Col-Label");
            temp.setStyle("-fx-text-fill:#05b074;");
            temp.setFont(Font.font("Segoe UI", 18));
            temp.setPrefWidth(110);
            temp.setCursor(Cursor.HAND);
            temp.setMaxWidth(temp.getPrefWidth());
            temp.setOnAction(event -> {
                int itemIndex = Clients_Table.getChildren().indexOf(temp);
                Go_Stats_Client(itemIndex);
            });
            temp.setPrefHeight(hbox.getPrefHeight());
            temp.setMaxHeight(hbox.getPrefHeight());
            Clients_Table.add(temp, 8, Clients_Table.getRowConstraints().size() - 1);

            String[] client = {String.valueOf(value.getId()),
                    String.valueOf(value.getAdresse()),
                    String.valueOf(value.getPhone_Num()),
                    String.valueOf(value.getName()),
                    String.valueOf(value.getSolde()),
                    String.valueOf(value.getRest()),
                    String.valueOf(value.getpaid())
            };
            for (int col = 1; col < 8; col++) {
                ColumnConstraints cell = Clients_Table.getColumnConstraints().get(col);
                Label emptyLabel = new Label(client[col - 1]);
                emptyLabel.setPadding(new Insets(0, 0, 0, 5));
                emptyLabel.setPrefWidth(cell.getMaxWidth());
                emptyLabel.setPrefHeight(40);
                emptyLabel.setMinHeight(40);
                emptyLabel.setFont(Font.font("Segoe UI", 18));
                emptyLabel.getStyleClass().add("Product-Table-Label");
                Clients_Table.add(emptyLabel, col, Clients_Table.getRowConstraints().size() - 1);
            }
        }
    }

    ////////////////////////////////////makes the SQL products table into a matrix/////////////////////////////////////////
    public Client[] getClientsMatrix() {
        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            ArrayList<Client> clientList = new ArrayList<>();

            try (Statement dataQuery = conn.createStatement()) {
                ResultSet resultSet = dataQuery.executeQuery("SELECT id, adresse, name, phone_num FROM clients");

                while (resultSet.next()) {
                    clientList.add(new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("adresse"),
                            resultSet.getString("name"),
                            resultSet.getString("phone_num"),
                            (double)0,
                            (double)0,
                            (double)0
                    ));
                }
            }
            conn.close();
            return clientList.toArray(new Client[0]);
        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
            return null;
        }
    }
    public void Delete_Client() {
        AtomicBoolean confirm=alert.showCustomConfirmationAlert("You sure want to delete this ??");
        if (confirm.get()){
            for (int row = 0; row < Clients_Table.getRowCount(); row++) {
                int currentRow = row;
                CheckBox checkBox = getCheckBoxInRow(Clients_Table, currentRow);

                if (checkBox != null && checkBox.isSelected()) {
                    Clients_Table.getChildren().removeIf(node -> {
                        Integer rowIndex = GridPane.getRowIndex(node);

                        if (rowIndex != null && rowIndex == currentRow) {
                            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == 1
                                    && node instanceof Label) {
                                String value = ((Label) node).getText();
                                Delete_Client_From_Database(value);
                            }
                            return true;
                        }
                        return false;
                    });
                }
            }
        }
    }

    public void Delete_Client_From_Database(String Client_Id){
        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM clients WHERE id = ?");
            preparedStatement.setString(1,Client_Id);
            preparedStatement.execute();
            conn.close();
        } catch (SQLException x) {
            alert.showCustomErrorAlert("Error");
        }
    }
    @FXML
    private VBox Client_Stats;
    @FXML
    private Button but571;
    public void Go_Stats_Client(int itemIndex){
        Inventory_Main.getChildren().addAll(Layer,Client_Stats);
        Layer.setVisible(true);
        Client_Stats.setVisible(true);
        get_Client_Info_To_Stats(Clients_Table,itemIndex);
        but571.setOnAction(event -> Edit_Client(Integer.parseInt(((Label)Clients_Table.getChildren().get(itemIndex+1)).getText())));
    }
    public void Return_To_Client_From_Stats(){
        Inventory_Main.getChildren().clear();
        Inventory_Main.getChildren().add(Clients);
        Layer.setVisible(false);
        Client_Stats.setVisible(false);
    }
    @FXML
    private TextArea Stats_Client_Adresse;

    @FXML
    private TextField Stats_Client_Credit_Limit;

    @FXML
    private TextField Stats_Client_Name;

    @FXML
    private TextField Stats_Client_Phone;

    @FXML
    private TextField Stats_Client_Rest;

    @FXML
    private TextField Stats_Client_Sold;

    @FXML
    private TextField Stats_Client_Paid;
    public void get_Client_Info_To_Stats(GridPane gridPane,int itemIndex){
        Stats_Client_Name.setText(((Label)gridPane.getChildren().get(itemIndex+2)).getText());
        Stats_Client_Phone.setText(((Label)gridPane.getChildren().get(itemIndex+3)).getText());
        Stats_Client_Adresse.setText(((Label)gridPane.getChildren().get(itemIndex+4)).getText());
        Stats_Client_Sold.setText(((Label)gridPane.getChildren().get((itemIndex+5))).getText());
        Stats_Client_Rest.setText(((Label)gridPane.getChildren().get(itemIndex+6)).getText());
        Stats_Client_Paid.setText(((Label)gridPane.getChildren().get(itemIndex+7)).getText());

        String url = "jdbc:sqlite:main.db";
        try (Connection conn = DriverManager.getConnection(url)) {

            try (Statement dataQuery = conn.createStatement()) {
                PreparedStatement query = conn.prepareStatement("SELECT credit FROM clients WHERE id = ?");
                query.setString(1, ((Label)gridPane.getChildren().get(itemIndex+1)).getText());
                ResultSet resultSet = query.executeQuery();
                while (resultSet.next()) {
                    Stats_Client_Credit_Limit.setText(String.valueOf(resultSet.getDouble("credit")));
                }
            }
            conn.close();
        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
        }
    }
    public void Edit_Client(int id) {
        String Name = Stats_Client_Name.getText();
        String Adress = Stats_Client_Adresse.getText();
        String Phone = Stats_Client_Phone.getText();
        String Credit_Limit = Stats_Client_Credit_Limit.getText();

        String url = "jdbc:sqlite:main.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement query = conn.prepareStatement("UPDATE clients set name = ?, adresse = ?, phone_num = ?, credit = ? WHERE id = ?;");
            query.setString(1, Name);
            query.setString(2, Adress);
            query.setString(3, Phone);
            query.setString(4, Credit_Limit);
            query.setInt(5, id);
            query.execute();
            conn.close();
            removeNonFirstRowChildren(Clients_Table);
            Show_Clients_In_The_Table();
            alert.showCustomAlert("Success");
        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
        }
    }

    public void Search_Client(){
        removeNonFirstRowChildren(Clients_Table);
        Client[] client_list=SearchClientMatrix();
        assert client_list != null;
        int list_size=client_list.length;
        for (Client value : client_list) {
            RowConstraints con = new RowConstraints();
            Clients_Table.getRowConstraints().add(con);

            CheckBox checkBox = new CheckBox();
            checkBox.setText("");
            checkBox.getStylesheets().addAll(CheckBoxExample.getStylesheets());

            HBox hbox = new HBox(checkBox);
            hbox.getStyleClass().add("Product-Table-First-Col-Label");
            hbox.setAlignment(Pos.CENTER);
            Clients_Table.add(hbox, 0, Clients_Table.getRowConstraints().size() - 1);

            Button temp = new Button("Stats");
            temp.getStyleClass().add("Product-Table-Last-Col-Label");
            temp.setStyle("-fx-text-fill:#05b074;");
            temp.setFont(Font.font("Segoe UI", 18));
            temp.setPrefWidth(110);
            temp.setCursor(Cursor.HAND);
            temp.setMaxWidth(temp.getPrefWidth());
            temp.setOnAction(event -> {
                int itemIndex = Clients_Table.getChildren().indexOf(temp);
                Go_Stats_Client(itemIndex);
            });
            temp.setPrefHeight(hbox.getPrefHeight());
            temp.setMaxHeight(hbox.getPrefHeight());
            Clients_Table.add(temp, 8, Clients_Table.getRowConstraints().size() - 1);

            String[] client = {String.valueOf(value.getId()),
                    String.valueOf(value.getAdresse()),
                    String.valueOf(value.getPhone_Num()),
                    String.valueOf(value.getName()),
                    String.valueOf(value.getSolde()),
                    String.valueOf(value.getRest()),
                    String.valueOf(value.getpaid())
            };
            for (int col = 1; col < 8; col++) {
                ColumnConstraints cell = Clients_Table.getColumnConstraints().get(col);
                Label emptyLabel = new Label(client[col - 1]);
                emptyLabel.setPadding(new Insets(0, 0, 0, 5));
                emptyLabel.setPrefWidth(cell.getMaxWidth());
                emptyLabel.setPrefHeight(40);
                emptyLabel.setMinHeight(40);
                emptyLabel.setFont(Font.font("Segoe UI", 18));
                emptyLabel.getStyleClass().add("Product-Table-Label");
                Clients_Table.add(emptyLabel, col, Clients_Table.getRowConstraints().size() - 1);
            }
        }
    }

    @FXML
    private TextField Client_Search;
    //////////////////////////////////// add the client search function here in the same way we created product search
    public Client[] SearchClientMatrix(){
        String Filter=Client_Filter_Combo_Box.getValue();
        Filter = Filter.toLowerCase();
        String search = Client_Search.getText();
        if (search.isBlank()) return getClientsMatrix();

        String url = "jdbc:sqlite:main.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            ArrayList<Client> clientList = new ArrayList<>();

            try (PreparedStatement countQuery = conn.prepareStatement("SELECT id, adresse, name, phone_num FROM clients WHERE "+Filter+" LIKE ? ")) {
                countQuery.setString(1, "%" + search + "%");

                try (ResultSet resultSet = countQuery.executeQuery()) {
                    while (resultSet.next()) {
                        clientList.add(new Client(
                                resultSet.getInt("id"),
                                resultSet.getString("adresse"),
                                resultSet.getString("name"),
                                resultSet.getString("phone_num"),
                                (double)0,
                                (double)0,
                                (double)0
                        ));
                    }
                }
            }
            conn.close();
            return clientList.toArray(new Client[0]);

        } catch (SQLException e) {
            alert.showCustomErrorAlert("Error");
            return null;
        }
    }
}
