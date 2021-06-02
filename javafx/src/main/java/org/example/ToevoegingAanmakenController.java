package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.java.org.example.models.Item;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ToevoegingAanmakenController implements Initializable {

    @FXML
    ListView<ItemLabelComponent> itemsListView;
    @FXML
    Button annulerenBtn;
    @FXML
    Button bevestigBtn;
    @FXML
    TextField hoeveelheidTF;
    private ServiceContainter serviceContainter;
    private List<Item> items;
    private AlertController alertController;
    private MaaltijdComponent maaltijdComponent;



    public ToevoegingAanmakenController(ServiceContainter serviceContainter, AlertController alertController , MaaltijdComponent maaltijdComponent) {
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
        this.maaltijdComponent = maaltijdComponent;
        try {
            this.items = serviceContainter.getFoodItemCollectie().getItems();
        } catch (SQLException throwables) {
            alertController.sQLExeptionAlert(throwables);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Item item : items){
            itemsListView.getItems().add(new ItemLabelComponent(item));
        }
        initButtons();
    }

    private void initButtons(){
        annulerenBtn.setOnAction(actionEvent -> {
            try {
                App.setRoot("primary", new PrimaryController(serviceContainter, alertController));
            } catch (IOException exception) {
                alertController.ioExeptionAlert(exception);
            }
        });
        bevestigBtn.setOnAction(actionEvent -> {
            try {
                if(checkFields()){
                    serviceContainter.getMaaltijdService().addItem(maaltijdComponent.getMaaltijd(), itemsListView.getSelectionModel().getSelectedItem().getItem(),Double.parseDouble(hoeveelheidTF.getText()));
                    App.setRoot("primary", new PrimaryController(serviceContainter, alertController));
                } else {
                    alertController.infoAlert("","niet alle gegevens ingevuld", "alle rode gegevens moeten ingvuld zijn");
                }
            } catch (IOException exception) {
                alertController.ioExeptionAlert(exception);
            } catch (SQLException e){
                alertController.sQLExeptionAlert(e);
            }
        });
    }

    private boolean checkFields(){
        boolean check = true;
        hoeveelheidTF.setStyle(null);
        itemsListView.setStyle(null);
        if(hoeveelheidTF.getText() == null || hoeveelheidTF.getText().isEmpty()){
            hoeveelheidTF.setStyle("-fx-border-color: #FF0000; -fx-text-fill:  #FF0000");
            check = false;
        } else if(itemsListView.getSelectionModel().getSelectedItem() == null){
            itemsListView.setStyle("-fx-border-color: #FF0000; -fx-text-fill:  #FF0000");
            check = false;
        }
        System.out.println(check);
        return check;
    }


}
