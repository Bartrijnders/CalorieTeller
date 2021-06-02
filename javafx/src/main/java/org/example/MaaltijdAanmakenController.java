package org.example;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderStroke;
import main.java.org.example.models.Dag;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MaaltijdAanmakenController implements Initializable {

    @FXML
    TextField naamTB;
    @FXML
    TextField calTB;
    @FXML
    TextField kolTB;
    @FXML
    TextField eiwTB;
    @FXML
    TextField vetTB;
    @FXML
    Button annulerenBtn;
    @FXML
    Button bevestigBtn;

    private ServiceContainter serviceContainter;
    private Dag dag;
    private AlertController alertController;

    public MaaltijdAanmakenController(Dag dag, ServiceContainter serviceContainter, AlertController alertController) throws SQLException {
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
        this.dag = dag;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTextFields();
        initAnnulerenBtn();
        bevestigBtn.setDefaultButton(true);
        bevestigBtn.setOnAction(actionEvent -> {
            if(checkTextFieldContent()){
                try {
                    serviceContainter.getStandaardDagCollectie().maaltijdAanDagToevoegen(naamTB.getText(),Double.parseDouble(calTB.getText()), Double.parseDouble(kolTB.getText()), Double.parseDouble(eiwTB.getText()), Double.parseDouble(vetTB.getText()),dag);
                    App.setRoot("primary", new PrimaryController(serviceContainter, alertController));
                } catch (SQLException throwables) {
                    alertController.sQLExeptionAlert(throwables);
                } catch (IOException exception) {
                    alertController.ioExeptionAlert(exception);
                }
            } else {
                alertController.infoAlert("Oeps!","Niet alle benodigde gegevens zijn ingevuld.","Tekst velden in het rood dienen ingevult te worden.");
            }
        });

    }

    private boolean checkTextFieldContent() {
        List<TextField> textFields = List.of(naamTB,calTB,kolTB,eiwTB,vetTB);
        boolean check = true;
        for(TextField textField : textFields){
            if ( textField.getText() == null || textField.getText().isEmpty()){
                textField.setStyle("-fx-border-color: #FF0000; -fx-text-fill:  #FF0000");
                check = false;
            }else{
                textField.setStyle(null);
            }
        }
        return check;
    }

    private void initTextFields() {
        List<TextField> textFields = List.of(calTB,kolTB,eiwTB, vetTB);

        for(TextField textField : textFields){
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

    }

    private void initAnnulerenBtn() {
        annulerenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("primary", new PrimaryController(serviceContainter, alertController));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            }
        });
        annulerenBtn.setCancelButton(true);
    }
}
