package Org.Main;

import javafx.scene.control.Button;
    public class ButtonCloner {

        public static Button cloneButton(Button originalButton) {
            Button clonedButton = new Button();
            clonedButton.getStyleClass().add("Tab-Button-neutral");
            clonedButton.setPrefWidth(originalButton.getPrefWidth());
            clonedButton.setPrefHeight(originalButton.getPrefHeight());
            return clonedButton;
        }
    }

