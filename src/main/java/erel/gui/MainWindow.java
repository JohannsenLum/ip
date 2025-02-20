package erel.gui;

import erel.ui.Erel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Erel erel;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image erelImage = new Image(this.getClass().getResourceAsStream("/images/Erel.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Erel instance */
    public void setErel(Erel e) {
        erel = e;
        dialogContainer.getChildren().add(
                DialogBox.getErelDialog(erel.greet(), erelImage) // Show greeting on startup
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = erel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getErelDialog(response, erelImage)
        );
        userInput.clear();
    }
}
