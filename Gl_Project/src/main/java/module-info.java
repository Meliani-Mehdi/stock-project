module Org.Main {
    exports Org.Main;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Org.Main.Controllers
    to javafx.fxml;
    opens FXML to javafx.fxml;
    exports Org.Main.Controllers;
}