package org.example;

import main.java.org.example.models.DagInstellingen;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.sql.SQLException;

public class InstellingMaaltijdAanmakenController extends MaaltijdAanmakenController{

    private InstellingenPageController instellingenPageController;
    public InstellingMaaltijdAanmakenController(ServiceContainter serviceContainter, AlertController alertController, InstellingenPageController instellingenPageController) {
        super(serviceContainter, alertController);
        this.instellingenPageController = instellingenPageController;
    }

    @Override
    protected void initBevestigBtn() {
        this.bevestigBtn.setOnAction(actionEvent -> {
            if(this.checkTextFieldContent()){
                try {
                     instellingenPageController.getPlaceHolder().addMaaltijd(naamTB.getText(), Double.parseDouble(calTB.getText()), Double.parseDouble(kolTB.getText()), Double.parseDouble(eiwTB.getText()), Double.parseDouble(vetTB.getText()));
                    App.setRoot("instellingenPage", instellingenPageController);
                } catch (IOException e) {
                    alertController.ioExeptionAlert(e);
                }
            } else {
                alertController.infoAlert("Oeps!","Niet alle benodigde gegevens zijn ingevuld.","Tekst velden in het rood dienen ingevult te worden.");
            }
        });

    }

    @Override
    protected void initAnnulerenBtn() {
      this.annulerenBtn.setOnAction( actionEvent -> {
          try {
              App.setRoot("instellingenPage", new InstellingenPageController(serviceContainter, alertController));
          } catch (IOException e) {
              alertController.ioExeptionAlert(e);
          } catch (SQLException e) {
              alertController.sQLExeptionAlert(e);
          }
      });
    }
}
