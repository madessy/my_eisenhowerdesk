package Code;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Code.Controllers.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;


public class Main_app extends Application {

    private static final Logger log = LogManager.getLogger(Main.class.getName());
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        log.info("application started");
        this.primaryStage = primaryStage;
        LoadMainWindow();
    }

    public void LoadMainWindow(){
        try {
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(Main_app.class.getResource("/main/java/Code/FXML/main.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            primaryStage.setTitle("EISENHOWER");
            primaryStage.setMinHeight(700);
            primaryStage.setMinWidth(700);
            MainController mc = loader.getController();
            primaryStage.setScene(new Scene(root));
            primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    mc.redraw( primaryStage.getScene());
                }
            });
            primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    mc.redraw( primaryStage.getScene());
                }
            });
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
