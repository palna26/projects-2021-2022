import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BufferFileReader {
    public static final String EMAIL_TARGET = "[[email]]";
    public static final String FIRST_NAME_TARGET = "[[first_name]]";
    public static final String LAST_NAME_TARGET = "[[last_name]]";
    public static final String CSV_FIRST_NAME = "first_name";
    public static final String CSV_LAST_NAME = "last_name";
    public static final String CSV_EMAIL = "email";

    /**
     * @param args
     * Method takes in commands from the command line and calls helper method to process commands
     * It then reads files and produces an appropriate output file
     */
    public static void main(String[] args) {

        try {
            CMDLineProcessor cmdLineProcessor = new CMDLineProcessor();
            String[] inputs = cmdLineProcessor.process(args);
            String emailTemplate = inputs[0];
            String outputDirectory = inputs[1];
            String csvFile = inputs[2];
            BufferedReader inputFile =new BufferedReader(new FileReader(csvFile));
            BufferedReader templateFile = new BufferedReader(new FileReader(emailTemplate));

            // Read the lines in the email template and make the lines into an array of strings
            String[] templateLines = templateFile.lines().toArray(String[]::new);

            // Read the header of the csv file
            String firstLine = inputFile.readLine();
            String removedQuotes = firstLine.substring(1, firstLine.length() -1);
            // Split the header into columns
            String[] headers = removedQuotes.split("\",\"");
            Integer firstNameIndex = -1;
            Integer lastNameIndex = -1;
            Integer emailIndex = -1;
            // Find the index of each column that contains the first name, last name, and email
            for(int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
                if (headers[i].equals(CSV_FIRST_NAME)) {
                    firstNameIndex = i;
                } else if (headers[i].equals(CSV_LAST_NAME)) {
                    lastNameIndex = i;
                } else if (headers[i].equals(CSV_EMAIL)) {
                    emailIndex = i;
                }
            }

            // Declare variable line as a string
            String line;

            // Go through a while loop for the entire csv file until the last line which is null
            while ((line = inputFile.readLine()) != null) {
                // Take out quotations from the first and last index
                String cleanedLine = line.substring(1, line.length() - 1);
                // Split the columns into an array
                String[] columns = cleanedLine.split("\",\"");
                // Set the firstName, lastName, and email variables to replace
                String firstName = columns[firstNameIndex];
                String lastName = columns[lastNameIndex];
                String email = columns[emailIndex];


                String outputFileName = outputDirectory + "/" + firstName + "_" + lastName + ".txt";
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName));
                // Rewrite the email template but replace the targets with the variables defined above
                for (int i = 0; i < templateLines.length; i++) {
                    String currentLine = templateLines[i];
                    currentLine = currentLine.replace(EMAIL_TARGET, email);
                    currentLine = currentLine.replace(FIRST_NAME_TARGET, firstName);
                    currentLine = currentLine.replace(LAST_NAME_TARGET, lastName);
                    outputFile.write(currentLine + System.lineSeparator());
                }
                outputFile.close();
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
            ioe.printStackTrace();
        } catch (CMDLineProcessorException e) {
            System.out.println("Something went wrong! : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
