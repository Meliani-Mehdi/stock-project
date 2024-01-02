package Org.Main.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane Main_Pane;
    private Stage stage;

    @FXML
    private TextField SignIn_Username_Textfield;

    @FXML
    private PasswordField SignIn_Password_TextField;
    public void Exit(){
        stage = (Stage) Main_Pane.getScene().getWindow();
        System.exit(0);
    }
    public void Minimize(){
        stage = (Stage) Main_Pane.getScene().getWindow();

        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main_Pane.requestFocus();

        Main_Pane.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Sign_in_Button.fire();
            }
        });
    }

    public static void createDatabase() {
        String url = "jdbc:sqlite:your-database-name.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement()) {

            // Create clients table
            String createClientsTableSQL =
                    "CREATE TABLE IF NOT EXISTS clients (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    name TEXT NOT NULL,\n" +
                            "    adresse TEXT,\n" +
                            "    phone_num TEXT,\n" +
                            "    sold_total REAL,\n" +
                            "    reste REAL,\n" +
                            "    paid REAL\n" +
                            ");";
            statement.execute(createClientsTableSQL);
            System.out.println("Table 'clients' created successfully.");

            // Create deposits table
            String createDepositsTableSQL =
                    "CREATE TABLE IF NOT EXISTS deposits (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    name TEXT NOT NULL,\n" +
                            "    solde REAL,\n" +
                            "    reste REAL,\n" +
                            "    payment REAL,\n" +
                            "    id_provider INTEGER,\n" +
                            "    id_client INTEGER,\n" +
                            "    id_seller INTEGER,\n" +
                            "    type TEXT,\n" +
                            "    payment_status TEXT\n" +
                            ");";
            statement.execute(createDepositsTableSQL);
            System.out.println("Table 'deposits' created successfully.");

            // Create factures table
            String createFacturesTableSQL =
                    "CREATE TABLE IF NOT EXISTS factures (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    date DATE NOT NULL,\n" +
                            "    update_date DATE,\n" +
                            "    update_time TIMESTAMP,\n" +
                            "    solde REAL,\n" +
                            "    reste REAL,\n" +
                            "    payment REAL,\n" +
                            "    id_provider INTEGER,\n" +
                            "    id_client INTEGER,\n" +
                            "    id_seller INTEGER,\n" +
                            "    type TEXT,\n" +
                            "    payment_status TEXT\n" +
                            ");";
            statement.execute(createFacturesTableSQL);
            System.out.println("Table 'factures' created successfully.");

            // Create groupes table
            String createGroupesTableSQL =
                    "CREATE TABLE IF NOT EXISTS groupes (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    name TEXT NOT NULL,\n" +
                            "    marge REAL\n" +
                            ");";
            statement.execute(createGroupesTableSQL);
            System.out.println("Table 'groupes' created successfully.");

            // Create products table
            String createProductsTableSQL =
                    "CREATE TABLE IF NOT EXISTS products (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    bar_code VARCHAR NOT NULL,\n" +
                            "    reference VARCHAR,\n" +
                            "    name VARCHAR NOT NULL,\n" +
                            "    buying_price REAL,\n" +
                            "    selling_price REAL,\n" +
                            "    stock INTEGER,\n" +
                            "    photo VARCHAR,\n" +
                            "    id_groupe INTEGER\n" +
                            ");";
            statement.execute(createProductsTableSQL);
            System.out.println("Table 'products' created successfully.");

            // Create product_facts table
            String createProductFactsTableSQL =
                    "CREATE TABLE IF NOT EXISTS product_facts (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    id_fact INTEGER,\n" +
                            "    id_prod INTEGER,\n" +
                            "    product_price REAL,\n" +
                            "    product_qte INTEGER,\n" +
                            "    tva REAL\n" +
                            ");";
            statement.execute(createProductFactsTableSQL);
            System.out.println("Table 'product_facts' created successfully.");

            // Create bon_de_livraisons table
            String createBonDeLivraisonsTableSQL =
                    "CREATE TABLE IF NOT EXISTS bon_de_livraisons (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    date DATE NOT NULL,\n" +
                            "    old_reste REAL,\n" +
                            "    reste REAL,\n" +
                            "    payment REAL,\n" +
                            "    id_client INTEGER,\n" +
                            "    id_user INTEGER,\n" +
                            "    id_provider INTEGER\n" +
                            ");";
            statement.execute(createBonDeLivraisonsTableSQL);
            System.out.println("Table 'bon_de_livraisons' created successfully.");

            // Create providers table
            String createProvidersTableSQL =
                    "CREATE TABLE IF NOT EXISTS providers (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    name TEXT NOT NULL,\n" +
                            "    adresse TEXT,\n" +
                            "    phone_num TEXT,\n" +
                            "    sold_total REAL,\n" +
                            "    reste REAL,\n" +
                            "    paid REAL\n" +
                            ");";
            statement.execute(createProvidersTableSQL);
            System.out.println("Table 'providers' created successfully.");

            // Create users table
            String createUsersTableSQL =
                    "CREATE TABLE IF NOT EXISTS users (\n" +
                            "    id INTEGER PRIMARY KEY,\n" +
                            "    username TEXT NOT NULL,\n" +
                            "    password TEXT NOT NULL,\n" +
                            "    'last' INTEGER NOT NULL\n" +
                            ");";
            statement.execute(createUsersTableSQL);
            System.out.println("Table 'users' created successfully.");

            System.out.println("Database and tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button Sign_in_Button;
    public void Test() {
        createDatabase();
        System.out.println("noice");
    }

    @FXML
    private Label Need_An_Account_Button;
}
