package erel.gui;

import java.io.IOException;

import erel.ui.Erel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Erel JavaFX application.
 */
public class Main extends Application {

    private Erel erel = new Erel();

    /**
     * Starts the JavaFX application by loading the FXML layout for the main window.
     * It injects an instance of Erel into the controller and sets the scene for the application window.
     *
     * @param stage The primary stage for the application, which represents the main window.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Erel");
            fxmlLoader.<MainWindow>getController().setErel(erel); // inject the Erel instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
