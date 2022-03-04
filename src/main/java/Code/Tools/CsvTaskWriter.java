package Code.Tools;
import Code.Model.Task;
import Code.Model.Tasks;

import java.io.*;


public class CsvTaskWriter {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "Name,Category,PriorityLevel,Deadline";


    /**
     *
     * @param fileName
     * @throws IOException
     */
    public static void writeCsvFile(File fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(fileName));
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        try {
            if (fileReader.readLine()!=null){
                fileName.delete();
                File CSVFile = new File(System.getProperty("user.home")+"/taskBD.csv");
            }else{
                fileWriter.append(FILE_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
                System.out.println("CSV file was created successfully !");
            }
            for(Task task :Tasks.getTasks())
            {
                //Write a new student object list to the CSV file
                fileWriter.append(String.valueOf(task.getName()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(task.getCategory()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(task.getPriorityLevel()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(task.getDeadline());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(task.getId()));
                fileWriter.append(NEW_LINE_SEPARATOR);
                System.out.println("Task successfully added !");
            }


        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !");
                e.printStackTrace();
            }

        }

    }

}
