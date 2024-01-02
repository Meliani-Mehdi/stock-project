package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
                    """
                            CREATE TABLE IF NOT EXISTS clients (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                adresse TEXT,
                                phone_num TEXT,
                                sold_total REAL,
                                reste REAL,
                                paid REAL
                            );""";
            statement.execute(createClientsTableSQL);
            System.out.println("Table 'clients' created successfully.");

            // Create deposits table
            String createDepositsTableSQL =
                    """
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
                            );""";
            statement.execute(createDepositsTableSQL);
            System.out.println("Table 'deposits' created successfully.");

            // Create factures table
            String createFacturesTableSQL =
                    """
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
                            );""";
            statement.execute(createFacturesTableSQL);
            System.out.println("Table 'factures' created successfully.");

            // Create groupes table
            String createGroupesTableSQL =
                    """
                            CREATE TABLE IF NOT EXISTS groupes (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                marge REAL
                            );""";
            statement.execute(createGroupesTableSQL);
            System.out.println("Table 'groupes' created successfully.");

            // Create products table
            String createProductsTableSQL =
                    """
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
                            );""";
            statement.execute(createProductsTableSQL);
            System.out.println("Table 'products' created successfully.");

            // Create product_facts table
            String createProductFactsTableSQL =
                    """
                            CREATE TABLE IF NOT EXISTS product_facts (
                                id INTEGER PRIMARY KEY,
                                id_fact INTEGER,
                                id_prod INTEGER,
                                product_price REAL,
                                product_qte INTEGER,
                                tva REAL
                            );""";
            statement.execute(createProductFactsTableSQL);
            System.out.println("Table 'product_facts' created successfully.");

            // Create bon_de_livraisons table
            String createBonDeLivraisonsTableSQL =
                    """
                            CREATE TABLE IF NOT EXISTS bon_de_livraisons (
                                id INTEGER PRIMARY KEY,
                                date DATE NOT NULL,
                                old_reste REAL,
                                reste REAL,
                                payment REAL,
                                id_client INTEGER,
                                id_user INTEGER,
                                id_provider INTEGER
                            );""";
            statement.execute(createBonDeLivraisonsTableSQL);
            System.out.println("Table 'bon_de_livraisons' created successfully.");

            // Create providers table
            String createProvidersTableSQL =
                    """
                            CREATE TABLE IF NOT EXISTS providers (
                                id INTEGER PRIMARY KEY,
                                name TEXT NOT NULL,
                                adresse TEXT,
                                phone_num TEXT,
                                sold_total REAL,
                                reste REAL,
                                paid REAL
                            );""";
            statement.execute(createProvidersTableSQL);
            System.out.println("Table 'providers' created successfully.");

            // Create users table
            String createUsersTableSQL =
                    """
                            CREATE TABLE IF NOT EXISTS users (
                                id INTEGER PRIMARY KEY,
                                username TEXT NOT NULL,
                                password TEXT NOT NULL,
                                'last' INTEGER NOT NULL
                            );""";
            statement.execute(createUsersTableSQL);

            System.out.println("Table 'users' created successfully.");

            System.out.println("Database and tables created successfully.");

        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    @FXML
    private Button Sign_in_Button;
    public void Test() {
        createDatabase();
        System.out.println("noice");
    }

    @FXML
    private TextField SignIn_Username_Textfield;

    @FXML
    private PasswordField SignIn_Password_TextField;
// create a user in the database and try to log in with that user if the user info is not wrong the user will log in if the user info are wrong print wrong
    // close the database
}
