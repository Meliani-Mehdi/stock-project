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
            // Create a sample table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	username text NOT NULL,\n"
                    + "	password text NOT NULL\n"
                    + ");";
            statement.execute(createTableSQL);

            System.out.println("Database and table created successfully.");

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
    private TextField SignIn_Username_Textfield;

    @FXML
    private PasswordField SignIn_Password_TextField;
}
