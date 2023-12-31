module Org.Main {
    exports Org.Main;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires org.xerial.sqlitejdbc;

    opens Org.Main.Controllers
    to javafx.fxml;
    opens FXML to javafx.fxml;
    exports Org.Main.Controllers;
}
