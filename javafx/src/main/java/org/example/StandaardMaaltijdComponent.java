package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class StandaardMaaltijdComponent extends MaaltijdComponent {



    private final PrimaryController primaryController;

    public StandaardMaaltijdComponent(Maaltijd maaltijd, ServiceContainter serviceContainter, AlertController alertController, PrimaryController primaryController) {
        super(maaltijd, serviceContainter, alertController);
        this.primaryController = primaryController;


    }

    public Maaltijd getMaaltijd() {
        return maaltijd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        initAddBtn();
    }

    private void initAddBtn() {
        addBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("toevoegingAanmakenPage", new ToevoegingAanmakenController(serviceContainter, alertController, this));
            } catch (IOException e) {
                alertController.ioExeptionAlert(e);
            }
        });
    }

    @Override
    public void initDelBtn(){
        deleteBtn.setOnAction(actionEvent -> {
            try {
                serviceContainter.getStandaardDagCollectie().maaltijdVerwijderen(primaryController.getDag(), maaltijd);
                primaryController.refresh();
            } catch (SQLException e) {
                alertController.sQLExeptionAlert(e);
            }
        });
    }

    @Override
    public void refresh() {
        DecimalFormat decimalFormat = new DecimalFormat("#####.##");
        toevoegingVbox.getChildren().remove(0,toevoegingVbox.getChildren().size());
        usedCalLbl.setText(decimalFormat.format(maaltijd.getGebruikteCalorieen()));
        usedKolLbl.setText(decimalFormat.format(maaltijd.getGebruikteKoolhydraten()));
        usedEiwLbl.setText(decimalFormat.format(maaltijd.getGebruikteEiwitten()));
        usedVetLbl.setText(decimalFormat.format(maaltijd.getGebruikteVetten()));
        goalCalLbl.setText(decimalFormat.format(maaltijd.getCalorieDoel()));
        goalKolLbl.setText(decimalFormat.format(maaltijd.getKoolhydraatDoel()));
        goalEiwLbl.setText(decimalFormat.format(maaltijd.getEiwitDoel()));
        goalVetLbl.setText(decimalFormat.format(maaltijd.getVetDoel()));
        maaltijdNameLbl.setText(maaltijd.getNaam());
        for (Toevoeging toevoeging : maaltijd.getToevoegingen()){
            ToevoegingsComponent toevoegingsComponent = new ToevoegingsComponent(toevoeging,serviceContainter, this);
            toevoegingVbox.getChildren().add(toevoegingsComponent);
        }
    }
}
