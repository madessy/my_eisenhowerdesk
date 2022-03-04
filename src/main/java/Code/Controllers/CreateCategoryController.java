package Code.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Code.Model.Category;
import Code.Tools.CsvCategoryWriter;

import java.io.File;
import java.io.IOException;


public class CreateCategoryController {
    File FileCategory = new File(System.getProperty("user.home")+"/categoryBD.csv");

    static ObservableList<String> result = FXCollections.observableArrayList();
    @FXML
    public TextField catFieldName;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void confirm(ActionEvent actionEvent) throws IOException {
        Category category = new Category(catFieldName.getText());
        CsvCategoryWriter.WriteCsvFile(FileCategory,category);
        result.add(category.getCategoryName());
    }
}
