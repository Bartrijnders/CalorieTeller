package org.example;


import main.java.org.example.models.Dag;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.sql.SQLException;

public class DagMaaltijdAanmakenController extends MaaltijdAanmakenController {

    private Dag dag;

    public DagMaaltijdAanmakenController(Dag dag, ServiceContainter serviceContainter, AlertController alertController) throws SQLException {
        super(serviceContainter, alertController);
        this.dag = dag;

    }

    @Override
    protected void initBevestigBtn() {
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

    @Override
    protected void initAnnulerenBtn() {
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
