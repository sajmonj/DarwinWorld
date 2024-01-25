package org.example.visualization;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class ErrorPopUp {
    public static Popup createErrorPopup() {
        Popup popup = new Popup();

        Label exceptionLabel = new Label("Invalid settings");

        exceptionLabel.setStyle("-fx-font-size: 24; -fx-text-fill: #721c24;");

        VBox popupContent = new VBox(10);
        popupContent.getChildren().addAll(exceptionLabel);
        popupContent.setStyle("-fx-background-color: #f8d7da; -fx-padding: 20px; -fx-border-color: #721c24; -fx-border-width: 2px; -fx-border-radius: 10px;");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);");
        borderPane.setCenter(popupContent);

        popup.getContent().add(borderPane);
        popup.setAutoHide(true);

        popupContent.setMinWidth(210);
        popupContent.setMinHeight(80);

        return popup;
    }

}
