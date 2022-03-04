package Code.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Code.Model.Task;
import Code.Model.Tasks;
import Code.Tools.TaskButton;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * Controller of the main.fxml file, it uses the Task, Tasks and the TaskButton file too.
 *
 * */
public class MainController {

    private static Logger log = LogManager.getLogger(MainController.class.getName());


    /**
     *
     * @param event
     */
    @FXML
    void addTaskPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/addTask.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage newTask = new Stage();
            newTask.setScene(new Scene(root1));
            newTask.show();
        } catch (Exception e) {
            log.error(e.getMessage() + " in add task page");
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    void getHistory(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/taskList.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage history = new Stage();
            history.setMinWidth(500);
            history.setMinHeight(500);
            history.setScene(new Scene(root1));
            history.show();
        } catch (Exception e) {
            log.error(e.getMessage() + "in get history");
        }
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    void addCategoryPage(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/createCategory.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage history = new Stage();
            history.setScene(new Scene(root1));
            history.show();
        } catch (Exception e) {
            log.error(e.getMessage() + "in add category page");

        }
    }

    /**
     * Draw the matrix with the task(s)
     * @param scene
     */
    public void redraw(Scene scene)
    {
        //charger les données a afficher
        log.debug("creating the main scene");
        AnchorPane pane =  (AnchorPane)scene.lookup("#anchor");
        double width = pane.getWidth();
        double height = pane.getHeight();
        int margin = 40;
        int right_margin = 10;
        ObservableList<Task> list = Tasks.getTasks();
        if(list.size()<1) {
            return;
        }

        //algorithme permettant de trouver la date minimum et maximum d'une tâche
        LocalDate no_urgence= null;
        LocalDate urgence = null;
        log.debug("finding the max date for a task");
        for (Task t : list) {

            if(no_urgence==null) {
                no_urgence = t.getDeadlineDate();
            }
            if(urgence== null) {
                urgence = t.getDeadlineDate();
                continue;
            }

            if(t.getDeadlineDate().compareTo(urgence)<0) {
                urgence = t.getDeadlineDate(); //nouvelle date la plus urgente
            }
            if(t.getDeadlineDate().compareTo(no_urgence)>0) {
                no_urgence =  t.getDeadlineDate();
            }

        }
        //Drawing Matrix
        log.debug("drawing the matrix");
        Line horizontal_axe = (Line)scene.lookup("#line_h");

        horizontal_axe.setStartX(margin);
        horizontal_axe.setStartY(height/2);
        horizontal_axe.setEndX(width-right_margin);
        horizontal_axe.setEndY(height/2);

        Line vertical_axe = (Line)scene.lookup("#line_v");
        vertical_axe.setStartX(width/2);
        vertical_axe.setStartY(margin);
        vertical_axe.setEndX(width/2);
        vertical_axe.setEndY(height-margin);

        Line arrow_h_below = (Line)scene.lookup("#arrow_h_below");
        arrow_h_below.setStartX(horizontal_axe.getEndX());
        arrow_h_below.setStartY(height/2);
        arrow_h_below.setEndX(width-right_margin-30);
        arrow_h_below.setEndY((height/2)+10);

        Line arrow_h_up = (Line)scene.lookup("#arrow_h_up");
        arrow_h_up.setStartX(horizontal_axe.getEndX());
        arrow_h_up.setStartY(height/2);
        arrow_h_up.setEndX(width-right_margin-30);
        arrow_h_up.setEndY((height/2)-10);

        Line arrow_v_right= (Line)scene.lookup("#arrow_v_right");
        arrow_v_right.setStartX(vertical_axe.getStartX());
        arrow_v_right.setStartY(margin);
        arrow_v_right.setEndX((width/2)+10);
        arrow_v_right.setEndY(margin+30);

        Line arrow_v_left= (Line)scene.lookup("#arrow_v_left");
        arrow_v_left.setStartX(vertical_axe.getStartX());
        arrow_v_left.setStartY(margin);
        arrow_v_left.setEndX((width/2)-10);
        arrow_v_left.setEndY(margin+30);

        Rectangle top_right = (Rectangle) scene.lookup("#top_right");
        top_right.setX(vertical_axe.getStartX()+10);
        top_right.setY(margin);
        top_right.setWidth(horizontal_axe.getEndX()-(horizontal_axe.getEndX()/2)-20);
        top_right.setHeight(vertical_axe.getEndY()-vertical_axe.getEndY()/2-30);
        top_right.setFill(Color.rgb(255, 87, 51));

        Rectangle below_left = (Rectangle) scene.lookup("#below_left");
        below_left.setX(horizontal_axe.getStartX());
        below_left.setY(height/2+10);
        below_left.setWidth(vertical_axe.getStartX()-50);
        below_left.setHeight(vertical_axe.getEndY()-horizontal_axe.getStartY());
        below_left.setFill(Color.rgb(51, 255, 65  ));


        Rectangle top_left = (Rectangle) scene.lookup("#top_left");
        top_left.setX(horizontal_axe.getStartX());
        top_left.setY(vertical_axe.getStartY());
        top_left.setWidth(vertical_axe.getStartX()-50);
        top_left.setHeight(horizontal_axe.getStartY()-50);
        top_left.setFill(Color.rgb(51, 227, 255));


        Rectangle below_right = (Rectangle) scene.lookup("#below_right");
        below_right.setX(vertical_axe.getStartX()+10);
        below_right.setY(horizontal_axe.getStartY()+10);
        below_right.setWidth(horizontal_axe.getEndX()-(horizontal_axe.getEndX()/2)-20);
        below_right.setHeight(vertical_axe.getEndY()-horizontal_axe.getStartY());
        below_right.setFill(Color.rgb(250, 21, 253));

        long days = ChronoUnit.DAYS.between(urgence, no_urgence);
        double pixByDay = (horizontal_axe.getEndX())/days;


        for (Task t:list) {

            String id = t.getName().replace(" ","");
            TaskButton task_button =(TaskButton)scene.lookup("#"+id);
            if(task_button==null) {
                task_button = new TaskButton();
                task_button.setText(t.getName());
                task_button.setId(id);
                pane.getChildren().add(task_button);
            }


            task_button.setLayoutY(height - (height/8 * t.getPriorityLevel()));
            task_button.setTask(t);
            task_button.setOnMouseClicked(e->{
                try {
                    Task clickedTask = ((TaskButton)e.getSource()).getTask();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/taskView.fxml"));
                    Parent root1 = fxmlLoader.load();
                    TaskViewController tvc = fxmlLoader.getController();
                    tvc.setValuesFromTask(clickedTask);
                    Stage newTask = new Stage();
                    newTask.setScene(new Scene(root1));
                    newTask.show();

                } catch (Exception ex) {
                    log.error(ex.getMessage());

                }

            });

            //place x coordinate of the button on the matrix
            double d = ChronoUnit.DAYS.between(no_urgence,t.getDeadlineDate());
            double x = Math.abs(d*pixByDay);
            if(x+task_button.getWidth()> width-right_margin) {
                x = x - (horizontal_axe.getEndX() - x+task_button.getWidth());
            }
            if(x-task_button.getWidth()<=0){
                x = x + (margin);
            }
            task_button.setLayoutX(x);

        }


    }
}

