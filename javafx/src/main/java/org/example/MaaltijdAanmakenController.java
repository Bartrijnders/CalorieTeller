package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bart.services.ServiceContainter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class MaaltijdAanmakenController implements Initializable {
    protected ServiceContainter serviceContainter;
    protected AlertController alertController;
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

    public MaaltijdAanmakenController(ServiceContainter serviceContainter, AlertController alertController) {
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTextFields();
        initAnnulerenBtn();
        initBevestigBtn();

    }

    protected abstract void initBevestigBtn();

    boolean checkTextFieldContent() {
        List<TextField> textFields = List.of(naamTB, calTB, kolTB, eiwTB, vetTB);
        boolean check = true;
        for (TextField textField : textFields) {
            if (textField.getText() == null || textField.getText().isEmpty()) {
                textField.setStyle("-fx-border-color: #FF0000; -fx-text-fill:  #FF0000");
                check = false;
            } else {
                textField.setStyle(null);
            }
        }
        return check;
    }

    private void initTextFields() {
        List<TextField> textFields = List.of(calTB, kolTB, eiwTB, vetTB);

        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

    }

    protected abstract void initAnnulerenBtn();
}
