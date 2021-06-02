package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bart.services.ServiceContainter;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        ServiceContainter serviceContainter = new ServiceContainter();
        AlertController alertController = new AlertController();
        scene = new Scene(loadFXML("primary", new PrimaryController(serviceContainter, alertController)));
        stage.setScene(scene);
        stage.setTitle("Calorie Teller");
        stage.show();
    }


    static void setRoot(String fxml, Object controller) throws IOException {
        scene.setRoot(loadFXML(fxml, controller));

    }

    private static Parent loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}