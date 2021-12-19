package com.example.brief3_mutuellecentralisee.helpers;

import javafx.scene.control.Alert;

public class alertHelper {

    public static void ShowError(String title,String headerText,String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }
    public static void ShowSuccess(String title,String headerText,String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }
}
