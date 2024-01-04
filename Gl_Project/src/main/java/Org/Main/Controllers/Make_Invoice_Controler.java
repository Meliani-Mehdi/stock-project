package Org.Main.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Make_Invoice_Controler {
    @FXML
    private Button Add_Product_Button;
    @FXML
    private GridPane Products_Table;
    public void Add_Product_Func(){
        RowConstraints con = new RowConstraints();
        Products_Table.getRowConstraints().add(con);
        String[] product={"3","Hello-34","61238540100","Mounir","15","300","0","300","4500","4500","Local Shop"};
        for (int col = 0; col < 11; col++) {
            ColumnConstraints cell=Products_Table.getColumnConstraints().get(col);
            Label emptyLabel = new Label(product[col]);
            emptyLabel.setPadding(new Insets(0, 0, 0, 3));
            emptyLabel.setPrefWidth(cell.getMaxWidth());
            emptyLabel.setPrefHeight(40);
            emptyLabel.setMinHeight(40);
            if(col==10){
                emptyLabel.getStyleClass().add("Product-Table-Last-Col-Label");
            } else if (col==0) {
                emptyLabel.getStyleClass().add("Product-Table-First-Col-Label");
            } else emptyLabel.getStyleClass().add("Product-Table-Label");
            Products_Table.add(emptyLabel, col, Products_Table.getRowConstraints().size() - 1);
        }
    }

}
