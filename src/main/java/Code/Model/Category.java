package Code.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Category {
    private String CategoryName;

    /**
     *
     * @param categoryName
     */
    public Category(String categoryName){
        this.CategoryName = categoryName;
    }

    /**
     *
     * @return
     */
    public String getCategoryName() {
        return CategoryName;
    }

    /**
     *
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    /**
     *
     * @return dataList
     * @throws FileNotFoundException
     */
    public static ObservableList<String> readCsvCategoryFile() throws FileNotFoundException {
        String csvFile = System.getProperty("user.home")+"/categoryBD.csv";
        BufferedReader fileReader;
        String fieldDelimiter = ",";
        ObservableList<String> dataList = FXCollections.observableArrayList();

        try {
            fileReader = new BufferedReader(new FileReader(csvFile));
            String line;
            boolean isFirstLine = true;
            while ((line = fileReader.readLine()) != null && !line.isEmpty()) {
                if(isFirstLine)
                {
                    isFirstLine=false;
                    continue;
                }
                String[] fields = line.split(fieldDelimiter, -1);
                String record = fields[0];
                dataList.add(record);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
}
