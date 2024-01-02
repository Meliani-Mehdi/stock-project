package Org.Main.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

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
    private Button test;
    public void Test() {
        createDatabase();
        System.out.println("noice");
    }
}
