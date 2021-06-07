package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Dag;
import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.Maaltijd;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InstellingenPageController implements Controller {

    @FXML
    TextField kolTB;
    @FXML
    TextField calTB;
    @FXML
    TextField eiwTB;
    @FXML
    TextField vetTB;
    @FXML
    Button bevestigBtn;
    @FXML
    Button annulerenBtn;
    @FXML
    VBox scrPnVBox;
    @FXML
    Button maaltijdToevoegenBtn;

    private ServiceContainter serviceContainter;
    private AlertController alertController;
    private DagInstellingen placeHolder;

    public InstellingenPageController(ServiceContainter serviceContainter, AlertController alertController) throws SQLException {
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
        placeHolder = serviceContainter.getInstellingenService().getInstellingen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
        initBtns();
    }

    @Override
    public void refresh() {
        try {
            if(scrPnVBox.getChildren().size() > 0){
                scrPnVBox.getChildren().remove(0, scrPnVBox.getChildren().size());
            }
            DagInstellingen dagInstellingen = serviceContainter.getInstellingenService().getInstellingen();
            calTB.setText(Double.toString(dagInstellingen.getCalorieDoel()));
            kolTB.setText(Double.toString(dagInstellingen.getKoolhydraatDoel()));
            eiwTB.setText(Double.toString(dagInstellingen.getEiwitDoel()));
            vetTB.setText(Double.toString(dagInstellingen.getVetDoel()));

            for(Maaltijd maaltijd : placeHolder.getMaaltijden()){
                MaaltijdComponent maaltijdComponent = new InstellingMaaltijdComponent(maaltijd, serviceContainter, alertController, this);
                scrPnVBox.getChildren().add(maaltijdComponent);
            }

        } catch (SQLException e) {
            alertController.sQLExeptionAlert(e);
        }
    }

    public DagInstellingen getPlaceHolder() {
        return placeHolder;
    }

    private void initBtns() {
        maaltijdToevoegenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("maaltijdAanmakenPage", new InstellingMaaltijdAanmakenController(serviceContainter, alertController, this));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            }
        });

        annulerenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("primary", new PrimaryController(serviceContainter,alertController));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            }
        });

        bevestigBtn.setOnAction(actionEvent -> {
            if(checkTextFieldContent()){
                try {
                    serviceContainter.getInstellingenService().updateInstellingen(placeHolder);
                    App.setRoot("primary", new PrimaryController(serviceContainter, alertController));
                } catch (SQLException e) {
                    alertController.sQLExeptionAlert(e);
                } catch (IOException e) {
                    alertController.ioExeptionAlert(e);
                }
            }
            else{
                alertController.infoAlert("Oeps!","Niet alle benodigde gegevens zijn ingevuld.","Tekst velden in het rood dienen ingevult te worden.");
            }
        });
    }

    boolean checkTextFieldContent() {
        List<TextField> textFields = List.of(calTB, kolTB, eiwTB, vetTB);
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

}
