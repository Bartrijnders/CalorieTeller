package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;
import org.bart.services.ServiceContainter;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ToevoegingsComponent extends VBox implements Initializable {


    private Toevoeging toevoeging;
    private ServiceContainter serviceContainter;
    private MaaltijdComponent maaltijdComponent;
    @FXML Label itemNameLbl;
    @FXML Label calValueLbl;
    @FXML Label kolValueLbl;
    @FXML Label eiwValueLbl;
    @FXML Label vetValueLbl;
    @FXML Label hoeveelheidLbl;
    @FXML Button deleteBtn;



    public ToevoegingsComponent(Toevoeging toevoeging, ServiceContainter serviceContainter, MaaltijdComponent maaltijdComponent) {
        this.toevoeging = toevoeging;
        this.serviceContainter = serviceContainter;
        this.maaltijdComponent = maaltijdComponent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("toevoegingsComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DecimalFormat decimalFormat = new DecimalFormat("#####.##");
        itemNameLbl.setText(toevoeging.getItem().getNaam());
        hoeveelheidLbl.setText(decimalFormat.format(toevoeging.getHoeveelheidInGram()) + "g");
        calValueLbl.setText(decimalFormat.format(toevoeging.getCalorieWaarde()));
        kolValueLbl.setText(decimalFormat.format(toevoeging.getKoolhydraatWaarde()));
        eiwValueLbl.setText(decimalFormat.format(toevoeging.getEiwitWaarde()));
        vetValueLbl.setText(decimalFormat.format(toevoeging.getVetWaarde()));

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    serviceContainter.getMaaltijdService().removeItem(maaltijdComponent.getMaaltijd(), toevoeging);
                    maaltijdComponent.refresh();
                } catch (SQLException throwables) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(throwables.getSQLState());
                    alert.setHeaderText(throwables.getSQLState());
                    alert.setContentText(throwables.getMessage());
                    alert.show();
                }
            }
        });
    }


}
