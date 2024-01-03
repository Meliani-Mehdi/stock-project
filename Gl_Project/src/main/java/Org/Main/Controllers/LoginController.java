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
import java.sql.*;
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


    static public void searchLog(String name, String pass){
        String url = "jdbc:sqlite:main.db";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement quary = conn.prepareStatement("""
                            SELECT * FROM users WHERE username = ? AND password = ? ;
                            """)) {

            quary.setString(1,name);
            quary.setString(2,pass);

            try (ResultSet resultSet = quary.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("\nWelcome "+name+"!");
                } else {
                    System.out.println("\nUser not found, please check your info.");
                }
            }

            conn.close();
            System.out.println(conn.isClosed());
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    @FXML
    private Button Sign_in_Button;
    public void Test() {
        searchLog(SignIn_Username_Textfield.getText(),SignIn_Password_TextField.getText());
    }

    @FXML
    private TextField SignIn_Username_Textfield;

    @FXML
    private PasswordField SignIn_Password_TextField;

    //users that I made for testing :
    /*
    mehdi,prrr
    bob,12345
    3dnan,mathIsGood
    kazuha,imOpAF
    test,test
    admin,admin
     */

}
