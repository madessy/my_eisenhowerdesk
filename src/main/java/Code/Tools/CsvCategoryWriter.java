package Code.Tools;

import Code.Model.Category;

import java.io.*;

public class CsvCategoryWriter {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Category";

    /**
     * Create a CSV file with the Categories that have been created by the user
     * @param fileName : csv file where data's gonna be stored
     * @param category : object category created by user
     * @throws IOException : throw exception if there is an error in csv file.
     */
    public static void WriteCsvFile(File fileName, Category category) throws IOException {

        FileWriter fileWriter = new FileWriter(String.valueOf(fileName),true);
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        try {

            //Write the CSV file header
            if(fileReader.readLine()==null){
                fileWriter.append(FILE_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
                System.out.println("CSV file was created successfully !");
            }

            //Write a new category object list to the CSV file
            fileWriter.append(String.valueOf(category.getCategoryName()));
            fileWriter.append(NEW_LINE_SEPARATOR);
            System.out.println("Category successfully added !");

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
