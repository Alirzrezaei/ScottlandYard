package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Alireza
 */
public class gameGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("gui/FXMLDocument.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Scottlang Yard");
        stage.setMinHeight(900);
        stage.setMinWidth(1400);
        stage.setHeight(900);
        stage.setWidth(1400);

        stage.show();

        // 1. statement in Application.start():
// Exit the application if an exception has not been caught.
        Thread.currentThread().setUncaughtExceptionHandler((Thread th, Throwable ex) -> {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Exception");
            alert.setContentText("Sorry, that should not be happening!");
            alert.showAndWait();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
