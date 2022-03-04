package Code.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Code.Model.Category;
import Code.Model.Task;
import Code.Model.Tasks;

import java.io.FileNotFoundException;

public class TaskViewController {

    @FXML
    private TextField taskName;

    @FXML
    private DatePicker newDeadline;

    @FXML
    private CheckBox achieved;

    @FXML
    private ComboBox<String> newCategory;

    @FXML
    private ComboBox<Integer> newPriority;

    @FXML
    private Button saveBtn;

    private Task currentTask;

    /**
     *
     */
    public TaskViewController(){

    }

    /**
     *
     * @param event
     */

    @FXML
    void deleteTask(ActionEvent event) {
        Tasks.deleteTask(currentTask);
    }

    /**
     *
     * @param event
     */

    @FXML
    void getFieldsEnabled(ActionEvent event) {
        taskName.setDisable(false);
        newDeadline.setDisable(false);
        newCategory.setDisable(false);
        newPriority.setDisable(false);
        newDeadline.setDisable(false);

    }

    /**
     * Save the modified task into the dataList. If the checkbox achieved is active it will delete the task
     * @param event
     */

    @FXML
    void saveEdit(ActionEvent event) {
        if (achieved.isSelected()){
            Tasks.deleteTask(currentTask);
        }else{
            Tasks.deleteTask(currentTask);
            currentTask = new Task(taskName.getText(),newCategory.getValue(),newPriority.getValue(),String.valueOf(newDeadline.getValue()));
            Tasks.addTask(currentTask);
        }

    }

    /**
     *
     * @param t
     */
    public void setValuesFromTask(Task t)
    {
        this.currentTask=t;
        ObservableList<String> categories= null;
        try {
            categories = Category.readCsvCategoryFile();
            taskName.setText(t.getName());
            taskName.setDisable(true);
            ObservableList<String> cat = FXCollections.observableArrayList(categories);
            newCategory.setItems(cat);
            newCategory.setDisable(true);
            newCategory.getSelectionModel().select(t.getCategory());
            newPriority.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7));
            newPriority.getSelectionModel().select(t.getPriorityLevel());
            newPriority.setDisable(true);
            newDeadline.setValue(t.getDeadlineDate());
            newDeadline.setDisable(true);


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public void isAchieved(ActionEvent actionEvent) {

    }
}
