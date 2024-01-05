package Org.Main.Controllers;

import Org.Main.ButtonCloner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Make_Invoice_Controler implements Initializable {


    @FXML
    private Button Add_Product_Button;
    @FXML
    private GridPane Products_Table;
    private boolean changing =false;
    public void Add_Product_Func(){
        RowConstraints con = new RowConstraints();
        Products_Table.getRowConstraints().add(con);
        String[] product={"3","Hello-34","61238540100","Mounir","15","300","0","300","4500","4500","Local Shop"};
        for (int col = 0; col < 11; col++) {
            ColumnConstraints cell=Products_Table.getColumnConstraints().get(col);
            Label emptyLabel = new Label(product[col]);
            emptyLabel.setPadding(new Insets(0, 0, 0, 5));
            emptyLabel.setPrefWidth(cell.getMaxWidth());
            emptyLabel.setPrefHeight(40);
            emptyLabel.setMinHeight(40);
            if (col<7 && col>3){
                emptyLabel.setOnMouseClicked(mouseEvent -> {
                    if(!changing){
                        select_line(emptyLabel);
                        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                            changing=true;
                            int rowIndex = GridPane.getRowIndex(emptyLabel);
                            int colIndex = GridPane.getColumnIndex(emptyLabel);
                            Change_label_content(emptyLabel,Products_Table,rowIndex,colIndex);
                        }
                    }
                });
            }else{
                emptyLabel.setOnMouseClicked(mouseEvent -> {
                    if(!changing){
                        select_line(emptyLabel);
                    }
                });
            }
            if(col==10){
                emptyLabel.getStyleClass().add("Product-Table-Last-Col-Label");
            } else if (col==0) {
                emptyLabel.getStyleClass().add("Product-Table-First-Col-Label");
            } else emptyLabel.getStyleClass().add("Product-Table-Label");
            Products_Table.add(emptyLabel, col, Products_Table.getRowConstraints().size() - 1);
        }
    }
    public void Change_label_content(Label label,GridPane pane,int row,int col){
        int pos=(row*10)+col+row;
        Label temp = (Label) pane.getChildren().get(pos);
        TextField textField = createEditableTextField(label.getText());
        textField.setPrefSize(temp.getWidth(), temp.getHeight());
        textField.getStyleClass().addAll(temp.getStyleClass());
        GridPane.setConstraints(textField, col, row);
        pane.getChildren().set(pos,textField);
        textField.requestFocus();
        textField.selectAll();

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Switch back to Label and update the text when TextField loses focus
                changing = false;
                temp.setText(textField.getText());
                pane.getChildren().set(pos, temp);
            }
        });

        textField.setOnAction(e -> {
            // When Enter is pressed, switch back to Label and update the text
            changing=false;
            temp.setText(textField.getText());
            pane.getChildren().set(pos,temp);
        });
    }
    private TextField createEditableTextField(String initialText) {
        return new TextField(initialText);
    }
    public void select_line(Label label){
        int rowIndex = GridPane.getRowIndex(label);
        for (int row = 0; row < Products_Table.getRowCount(); row++) {
            if (row==rowIndex){
                for (int j = 0; j < Products_Table.getColumnCount(); j++) {
                    javafx.scene.Node node = getNodeByRowColumnIndex(row, j, Products_Table);
                    if (node != null) {
                        node.setStyle("-fx-background-color: #28B6F655;");
                    }
                }
            }
            else {
                for (int j = 0; j < Products_Table.getColumnCount(); j++) {
                    javafx.scene.Node node = getNodeByRowColumnIndex(row, j, Products_Table);
                    if (node != null) {
                        node.setStyle("-fx-background-color: white;");
                    }
                }
            }
        }
    }
    private javafx.scene.Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);

            if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == column) {
                return node;
            }
        }
        return null;
    }

    @FXML
    private Button Facture_Tab;
    @FXML
    private HBox Tabs_HBox;
    int i=2;
    public void addFacture(){
        Button clonedButton = ButtonCloner.cloneButton(Facture_Tab);
        clonedButton.getStyleClass().add("Tab-Button-neutral");
        clonedButton.setText("invoice ("+i+")");
        i++;
        clonedButton.setOnAction(e -> Change_Tab(clonedButton));
        Button clonedExitButton = ButtonCloner.cloneButton(Close_Tab_Button);
        clonedExitButton.getStyleClass().add("Tab-Close-Button");
        clonedExitButton.setOnAction(e -> Close_Tab_Function(clonedExitButton));
        Tabs_HBox.getChildren().add(Tabs_HBox.getChildren().size()-1,clonedButton);
        Tabs_HBox.getChildren().add(Tabs_HBox.getChildren().size()-1,clonedExitButton);
        clonedButton.fire();
    }
    public void Change_Tab(Button ClickedButton){
        int index=Tabs_HBox.getChildren().indexOf(ClickedButton);
        if(!Objects.equals(ClickedButton.getStyleClass().getLast(), "Tab-Button-Active")){
            ClickedButton.getStyleClass().add("Tab-Button-Active");
            for (int j = 0; j <Tabs_HBox.getChildren().size()-1 ; j++) {
                if (j!=index){
                    Button temp=(Button) Tabs_HBox.getChildren().get(j);
                    temp.getStyleClass().remove("Tab-Button-Active");
                    temp.getStyleClass().add("Tab-Button-neutral");
                }
            }
        }
    }
    @FXML
    private Button Close_Tab_Button;
    public void Close_Tab_Function(Button ClickedButton){
        int index=Tabs_HBox.getChildren().indexOf(ClickedButton);
        boolean isactive= Objects.equals(Tabs_HBox.getChildren().get(index - 1).getStyleClass().getLast(), "Tab-Button-Active");
        if (Tabs_HBox.getChildren().size()-2>1){
            Tabs_HBox.getChildren().remove(index);
            Tabs_HBox.getChildren().remove(index-1);
        }
        if (Tabs_HBox.getChildren().size()==index){
            if(!Objects.equals(Tabs_HBox.getChildren().get(index-3).getStyleClass().getLast(), "Tab-Button-Active") && isactive){
                Tabs_HBox.getChildren().get(index-3).getStyleClass().add("Tab-Button-Active");
            }
        }else {
            if(!Objects.equals(Tabs_HBox.getChildren().get(index-1).getStyleClass().getLast(), "Tab-Button-Active") && isactive){
                Tabs_HBox.getChildren().get(index-1).getStyleClass().add("Tab-Button-Active");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Facture_Tab.setOnAction(e -> Change_Tab(Facture_Tab));
        Close_Tab_Button.setOnAction(e -> Close_Tab_Function(Close_Tab_Button));
        Facture_Tab.fire();
        Add_Product_Func();
    }
    @FXML
    private TextField Bar_Code_Insert_Text_Field;
}
