package Code.Model;

import Code.Tools.CsvTaskWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tasks {

    private static ObservableList<Task> dataList = null;
    private static final File CSVFile = new File(System.getProperty("user.home")+"/taskBD.csv");
    private static int lastId=0;


    /**
     * Add a new object task to the ObservableList
     * @param t : the task that need to be added
     */
    public static void addTask(Task t)
    {
        dataList.add(t);
        t.setId(++lastId);

        try {
            Tasks.persist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a task from the Observable dataList then rewrite a new csv file without this task
     * @param t : the task that need to be deleted
     */
    public static void deleteTask(Task t){
        dataList.remove(t);

        try {
            Tasks.persist();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    /**
     * store the file data's from the csv file read
     * @return : an ObservableList<Task> list with all the tasks read
     */
    public static ObservableList<Task> getTasks(){
        if(dataList == null) {
            try {
                dataList = readCsvFile();
            }
            catch (FileNotFoundException e) {
                dataList= FXCollections.observableArrayList();
            }
        }
        return dataList;
    }


    /**
     * convert string date value to a LocalDate value then return the LocalDate
     * @param date : the date that need to be converted
     * @return : the string date converted to LocalDate
     */
    public static LocalDate convertStringToDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Method that store the data's into the csv file
     * @throws IOException : throw exception if file unreadable or not found
     */
    public static void persist() throws IOException {

        CsvTaskWriter.writeCsvFile(CSVFile);

    }

    /**
     * read a file and store the datas in an ObservableList<Task>
     * @return : a new observable list with the whole object read in the file
     * @throws FileNotFoundException : Unable to find the file
     */
    private static ObservableList<Task> readCsvFile() throws FileNotFoundException {
        ObservableList<Task> list = FXCollections.observableArrayList();
        String csvFile = System.getProperty("user.home")+"/taskBD.csv";
        File f = new File(csvFile);
        if(!f.exists())
        {
            return list;
        }
        BufferedReader fileReader;
        String fieldDelimiter = ",";

        try {
            fileReader = new BufferedReader(new FileReader(csvFile));
            String line;
            boolean isFirstLine = true;
            while ((line = fileReader.readLine()) != null && !line.isEmpty()) {
                if(isFirstLine) {
                    isFirstLine=false;
                    continue;
                }
                String[] fields = line.split(fieldDelimiter, -1);

                Task t = new Task();
                t.setName(fields[0]);
                t.setCategory(fields[1]);
                t.setPriorityLevel(Integer.parseInt(fields[2]));
                t.setDeadline(fields[3]);
                int tid = Integer.parseInt(fields[4]);
                t.setId(tid);
                if(tid>Tasks.lastId)
                {
                    lastId=tid;
                }
                list.add(t);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}

