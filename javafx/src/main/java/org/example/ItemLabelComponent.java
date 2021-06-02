package org.example;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.org.example.models.Item;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemLabelComponent extends Label implements Initializable {

    private Item item;

    public ItemLabelComponent(Item item) {
        this.item = item;
        fxmlLoad();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setText(item.toString());
    }

    public Item getItem() {
        return item;
    }

    private void fxmlLoad() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemLbl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
