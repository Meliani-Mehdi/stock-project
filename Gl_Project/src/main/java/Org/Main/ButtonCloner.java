package Org.Main;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ButtonCloner {

        public static Button cloneButton(Button originalButton) {
            Button clonedButton = new Button();
            if (originalButton.getGraphic() instanceof ImageView) {
                ImageView originalImageView = (ImageView) originalButton.getGraphic();
                // Create a new ImageView with the same image
                ImageView clonedImageView = new ImageView(originalImageView.getImage());
                // Set the new size for the cloned image
                clonedImageView.setFitWidth(originalImageView.getFitWidth());
                clonedImageView.setFitHeight(originalImageView.getFitHeight());

                clonedButton.setGraphic(clonedImageView);
            }
            clonedButton.setPrefWidth(originalButton.getPrefWidth());
            clonedButton.setPrefHeight(originalButton.getPrefHeight());
            return clonedButton;
        }
    }

