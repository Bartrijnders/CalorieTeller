package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Dag;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class MaaltijdComponent extends VBox implements Initializable {

    private final Maaltijd maaltijd;
    @FXML
    Label maaltijdNameLbl;
    @FXML
    Label usedEiwLbl;
    @FXML
    Label goalCalLbl;
    @FXML
    Label goalKolLbl;
    @FXML
    Label usedVetLbl;
    @FXML
    Label usedKolLbl;
    @FXML
    Label goalVetLbl;
    @FXML
    Label usedCalLbl;
    @FXML
    Label goalEiwLbl;
    @FXML
    VBox toevoegingVbox;
    @FXML
    Button addBtn;
    @FXML
    Button editBtn;
    @FXML
    Button deleteBtn;

    private ServiceContainter serviceContainter;
    private AlertController alertController;
    private PrimaryController primaryController;

    public MaaltijdComponent(Maaltijd maaltijd, ServiceContainter serviceContainter, AlertController alertController, PrimaryController primaryController) {
        this.maaltijd = maaltijd;
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
        this.primaryController = primaryController;
        fxmlLoad();

    }

    private void fxmlLoad() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maaltijdComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Maaltijd getMaaltijd() {
        return maaltijd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refresh();
        initAddBtn();
        initDelBtn();
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

    private void initDelBtn(){
        deleteBtn.setOnAction(actionEvent -> {
            try {
                serviceContainter.getStandaardDagCollectie().maaltijdVerwijderen(primaryController.getDag(), maaltijd);
                primaryController.refresh();
            } catch (SQLException e) {
                alertController.sQLExeptionAlert(e);
            }
        });
    }

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
