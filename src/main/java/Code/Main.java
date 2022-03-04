package Code;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Code.Controllers.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application{
    private Stage primaryStage;

    private static Logger log = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) {

        log.info("Application started.");

        this.primaryStage = primaryStage;

        loadMainWindow();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void loadMainWindow() {

        primaryStage.setTitle("EisenhowerDesk");

        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/FXML/main.fxml"));
            log.debug("Root FXML filepath: " + loader.getLocation().toString());
            Parent root = loader.load();

            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setScene(scene);
            primaryStage.show();

            MainController controller = loader.getController();
            //controller.setPrimaryStage(primaryStage);//to change name

        } catch (IOException exception) {
            log.error("Something is wrong with the loading of the main window.");
        }
    }
}
