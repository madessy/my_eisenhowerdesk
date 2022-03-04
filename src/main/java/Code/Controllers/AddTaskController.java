package Code.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Code.Model.Category;
import Code.Model.Task;
import Code.Model.Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Controller for the adding task window
 * @version 1.02
 * */
public class AddTaskController implements Initializable {

    private static Logger log = LogManager.getLogger(MainController.class.getName());

    File task = new File(System.getProperty("user.home")+"/taskBD.csv");

    @FXML
    public TextField taskName;
    public ComboBox<String> categoryChoice;
    public ComboBox<Integer> priorityLevelChoice;
    public DatePicker deadLine;
    public Button addBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            ObservableList<String> categories= Category.readCsvCategoryFile();
            categoryChoice.setItems(FXCollections.observableArrayList(categories));
            priorityLevelChoice.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws IOException
     */
    @FXML
    private void addTask() throws IOException {

        Task newTask = new Task(taskName.getText(),categoryChoice.getValue(),priorityLevelChoice.getValue(),String.valueOf(deadLine.getValue()));
        Tasks.addTask(newTask);
        taskName.clear();
        categoryChoice.getSelectionModel().clearSelection();
        priorityLevelChoice.getSelectionModel().clearSelection();
        deadLine.getEditor().clear();
        log.debug("adding a task");
    }

    /**
     *
     * @param actionEvent
     */
    public void setCategory(ActionEvent actionEvent) {
        categoryChoice.getSelectionModel().getSelectedItem();
    }

    /**
     *
     * @param actionEvent
     */
    public void setPriorityLevel(ActionEvent actionEvent) {
        priorityLevelChoice.getSelectionModel().getSelectedItem();
    }
}
