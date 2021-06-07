package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import main.java.org.example.models.Maaltijd;
import org.bart.services.ServiceContainter;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class InstellingMaaltijdComponent extends MaaltijdComponent {

    private Controller pageController;


    public InstellingMaaltijdComponent(Maaltijd maaltijd, ServiceContainter serviceContainter, AlertController alertController, Controller pageController) {
        super(maaltijd, serviceContainter, alertController);
        this.pageController = pageController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        this.addBtn.setDisable(true);
        this.addBtn.setVisible(false);
        this.scrollPane.setVisible(false);
        this.scrollPane.setDisable(true);
    }

    @Override
    public void refresh() {

        maaltijdNameLbl.setText(maaltijd.getNaam());
        DecimalFormat decimalFormat = new DecimalFormat("#####.##");
        usedCalLbl.setVisible(false);
        usedEiwLbl.setVisible(false);
        usedKolLbl.setVisible(false);
        usedVetLbl.setVisible(false);
        goalCalLbl.setText(decimalFormat.format(maaltijd.getCalorieDoel()));
        goalKolLbl.setText(decimalFormat.format(maaltijd.getKoolhydraatDoel()));
        goalEiwLbl.setText(decimalFormat.format(maaltijd.getEiwitDoel()));
        goalVetLbl.setText(decimalFormat.format(maaltijd.getVetDoel()));
    }

    @Override
    public void initDelBtn() {
        deleteBtn.setOnAction(actionEvent -> {
            try {
                serviceContainter.getInstellingenService().verwijderMaaltijd(this.maaltijd);
                pageController.refresh();
            } catch (SQLException e) {
                alertController.sQLExeptionAlert(e);
            }
        });
    }
}
