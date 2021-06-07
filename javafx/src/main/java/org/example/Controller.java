package org.example;

import javafx.fxml.Initializable;
import main.java.org.example.models.Dag;

import java.net.URL;
import java.util.ResourceBundle;

public interface Controller extends Initializable {
    @Override
    void initialize(URL url, ResourceBundle resourceBundle);

    void refresh();

}
