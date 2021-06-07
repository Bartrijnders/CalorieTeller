package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import main.java.org.example.models.Maaltijd;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class MaaltijdComponent extends VBox implements Initializable {
    protected ServiceContainter serviceContainter;
    protected AlertController alertController;
    protected Maaltijd maaltijd;
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
    ScrollPane scrollPane;
    @FXML
    Button addBtn;
    @FXML
    Button editBtn;
    @FXML
    Button deleteBtn;

    public MaaltijdComponent(Maaltijd maaltijd, ServiceContainter serviceContainter, AlertController alertController) {
        this.serviceContainter = serviceContainter;
        this.alertController = alertController;
        this.maaltijd = maaltijd;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
        initDelBtn();
    }

    public abstract void refresh();

    public abstract void initDelBtn();
}
