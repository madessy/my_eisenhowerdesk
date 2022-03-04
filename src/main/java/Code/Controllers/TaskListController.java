package Code.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Code.Model.Task;
import Code.Model.Tasks;

import java.io.FileNotFoundException;


public class TaskListController {

    @FXML
    public Button edit;

    public Button delete;

    @FXML
    private TableView<Task> ListTask;

    @FXML
    private TableColumn<Task, String> ListTaskName;

    @FXML
    private TableColumn<Task, String> ListCategory;

    @FXML
    public TableColumn<Task,String> ListPriorityLevel;

    @FXML
    private TableColumn<Task, String> ListDeadline;

    //@FXML
    //private TableColumn<History, String> histAchievedDate;

    /**
     *
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    void refresh(ActionEvent event) throws FileNotFoundException {
        initCol();
    }


    /**
     *
     * @throws FileNotFoundException
     */
    private void initCol() throws FileNotFoundException {
        ListTaskName.setCellValueFactory(new PropertyValueFactory<Task,String>("Name"));
        ListCategory.setCellValueFactory(new PropertyValueFactory<Task,String>("Category"));
        ListPriorityLevel.setCellValueFactory(new PropertyValueFactory<Task,String>("PriorityLevel"));
        ListDeadline.setCellValueFactory(new PropertyValueFactory<Task,String>("deadline"));
        //histAchievedDate.setCellValueFactory(new PropertyValueFactory<>("AchievedDate"));
        ObservableList<Task> tasks = Tasks.getTasks();
        ListTask.setItems(tasks);
    }


    /**
     *
     * @param actionEvent
     */
    public void deleteTask(ActionEvent actionEvent) {
        Task deletedTask = ListTask.getSelectionModel().getSelectedItem();
        Tasks.deleteTask(deletedTask);


    }
}
