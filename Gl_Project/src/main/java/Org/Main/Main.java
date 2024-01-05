package Org.Main;

import Org.Main.Controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.Objects;

public class Main extends Application {
    private double xOffset, yOffset;
    public static String name;
    public static String password;
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/Fonts/Quicksand-VariableFont_wght.ttf"), 12);
        FXMLLoader loader =new FXMLLoader(Objects.requireNonNull(getClass().getResource("/FXML/Login.fxml")));
        Parent root = loader.load();
        Scene scene=new Scene(root);
        createDatabase();
        LoginController controller =loader.getController();
        controller.Last_User(name,password);
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
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().getRoot().setStyle("-fx-background-radius: 10;");
        stage.show();
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
                                l INTEGER NOT NULL
                            );""");

            ResultSet result = statement.executeQuery("""
                                SELECT username, password
                                FROM users
                                WHERE l = 1
                                AND (SELECT COUNT(*) FROM users WHERE l = 1) = 1;
                            """);

            if (result.next()) {
                name = result.getString("username");
                password = result.getString("password");
            }
            else {
                System.out.println("No matching record found.");
                statement.execute("""
                            UPDATE users SET l = 0;
                            """);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
