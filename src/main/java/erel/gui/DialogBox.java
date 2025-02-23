package erel.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and image.
     * Loads the FXML file to set up the UI elements for the dialog box.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image representing the speaker.
     */
    DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Apply circular clipping to display picture
        double radius = 25; // Adjust as needed
        displayPicture.setFitWidth(radius * 2);
        displayPicture.setFitHeight(radius * 2);
        displayPicture.setClip(new javafx.scene.shape.Circle(radius, radius, radius));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text The text to be displayed in the user dialog box.
     * @param img The image representing the user.
     * @return A new DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Duke with the specified text and image.
     * This dialog box is flipped so that the image appears on the left and the text on the right.
     *
     * @param text The text to be displayed in the Duke dialog box.
     * @param img The image representing Duke.
     * @return A new flipped DialogBox for Erel.
     */
    public static DialogBox getErelDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
