package org.example;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;

public class AlertController {

    public void sQLExeptionAlert(SQLException exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("SQL error");
        alert.setHeaderText(String.valueOf(exception.getErrorCode()));
        alert.setContentText(exception.getMessage());
        alert.show();
    }

    public void ioExeptionAlert(IOException exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IO error");
        alert.setHeaderText(String.valueOf(exception.getCause()));
        alert.setContentText(exception.getMessage());
        alert.show();
    }

    public void infoAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oeps!");
        alert.setHeaderText("Niet alle benodigde gegevens zijn ingevuld.");
        alert.setContentText("Tekst velden in het rood dienen ingevult te worden.");
        alert.show();
    }
}
