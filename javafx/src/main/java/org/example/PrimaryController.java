package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Dag;
import main.java.org.example.models.Maaltijd;
import org.bart.services.ServiceContainter;

public class PrimaryController implements Controller {

    @FXML Button maaltijdToevoegenBtn;
    @FXML Label datumLbl;
    @FXML Label goalCalLbl;
    @FXML Label goalKolLbl;
    @FXML Label goalEiwLbl;
    @FXML Label goalVetLbl;
    @FXML Label usedCalLbl;
    @FXML Label usedKolLbl;
    @FXML Label usedEiwLbl;
    @FXML Label usedVetLbl;
    @FXML VBox scrPnVBox;
    @FXML Button instellingenBtn;
    private ServiceContainter serviceContainter;
    private Dag dag;
    private AlertController alertController;



    public PrimaryController(ServiceContainter serviceContainter, AlertController alertController)  {
        try {
            this.serviceContainter = serviceContainter;
            this.alertController = alertController;
            this.dag = this.serviceContainter.getStandaardDagCollectie().getToday();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(throwables.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refresh();

        maaltijdToevoegenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("maaltijdAanmakenPage", new DagMaaltijdAanmakenController(dag,serviceContainter, alertController));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            } catch (SQLException e){
                alertController.sQLExeptionAlert(e);
            }
        });
        instellingenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("instellingenPage", new InstellingenPageController(serviceContainter, alertController));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            } catch (SQLException e) {
                alertController.sQLExeptionAlert(e);
            }
        });

    }

        public Dag getDag() {
        return dag;
    }

    @Override
    public void refresh() {
        scrPnVBox.getChildren().remove(0,scrPnVBox.getChildren().size());
        datumLbl.setText(dag.getDatum().toString());
        DecimalFormat decimalFormat = new DecimalFormat("#####.##");
        goalCalLbl.setText(decimalFormat.format(dag.getTotaalCalorieDoel()));
        goalEiwLbl.setText(decimalFormat.format(dag.getTotaalEiwitDoel()));
        goalKolLbl.setText(decimalFormat.format(dag.getTotaalKoolhydraatDoel()));
        goalVetLbl.setText(decimalFormat.format(dag.getTotaalVetDoel()));
        usedCalLbl.setText(decimalFormat.format(dag.getUsedCalorieen()));
        usedEiwLbl.setText(decimalFormat.format(dag.getUsedKoolhydraten()));
        usedKolLbl.setText(decimalFormat.format(dag.getUsedEiwitten()));
        usedVetLbl.setText(decimalFormat.format(dag.getUsedVetten()));

        for(Maaltijd maaltijd : dag.getMaaltijden()){
            MaaltijdComponent maaltijdComponent = new StandaardMaaltijdComponent(maaltijd, serviceContainter, alertController, this);
            scrPnVBox.getChildren().add(maaltijdComponent);
        }
    }

}
