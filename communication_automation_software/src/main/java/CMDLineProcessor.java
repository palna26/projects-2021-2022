import java.io.BufferedReader;

public class CMDLineProcessor {

    private static final int REQUIRED_ARGS = 7;
    private static final String EMAIL_FLAG = "--email";
    private static final String EMAIL_TEMPLATE_FLAG = "--email-template";
    private static final String OUTPUT_DIRECTORY_FLAG = "--output-dir";
    private static final String CSV_FILE_FLAG = "--csv-file";

    public CMDLineProcessor() {
    }

    /**
     * @param args
     * Method uses helper methods to take in a String array and return the necessary names of files
     * @return String[] - array of strings that includes the names of the email Template, output Directory, and email
     * @throws CMDLineProcessorException
     */
    public String[] process(String[] args) throws CMDLineProcessorException {
        String emailTemplate;
        String outputDirectory;
        String csvFile;

        if(!this.checkNumParameters(args)) {
            // Throw an exception if the number arguments provided is incorrect
            throw new CMDLineProcessorException("Incorrect number of arguments received!");
        } else {
            emailTemplate = findEmailTemplate(args);
            // Throw an exception if the email command is provided but no email template
            if (findEmail(args) && emailTemplate == null) {
                throw new CMDLineProcessorException("--email provided but no --email-template was given.");
            }
            outputDirectory = findOutputDirectory(args);
            csvFile = findCSVFile(args);
        }
        return new String[] {emailTemplate, outputDirectory, csvFile};
    }

    /**
     * @param args
     * Method takes in the arguments and finds the argument after csv file to get the name of the csv file
     * @return String csvFile - name of csv file
     */
    private String findCSVFile(String[] args) {
        String csvFile = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(CSV_FILE_FLAG)){
                csvFile = args[i+ 1];
                break;
            }
        }
        return csvFile;
    }

    /**
     * @param args
     * Method takes in the arguments and finds the argument after output Directory to get the name of the
     * output directory
     * @return String outputDirectory - name of outputDirectory
     */
    private String findOutputDirectory(String[] args) {
        String outputDirectory = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(OUTPUT_DIRECTORY_FLAG)){
                outputDirectory = args[i+ 1];
                break;
            }
        }
        return outputDirectory;
    }

    /**
     * @param args
     * @return
     */
    private String findEmailTemplate(String[] args) {
        String emailTemplate = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(EMAIL_TEMPLATE_FLAG)){
                emailTemplate = args[i+ 1];
                break;
            }
        }
        return emailTemplate;
    }

    /**
     * @param args
     * Method checks if the email command is provided
     * @return Boolean flag indicating if the argument includes email command
     */
    private Boolean findEmail(String[] args) {
        String email = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(EMAIL_FLAG)){
                return true;
            }
        }
        return false;
    }

    /**
     * @param args
     * Method checks if the number of arguments provided is correct
     * @return Boolean flag indicating if the number of arguments is correct
     */
    private Boolean checkNumParameters(String[] args) {
        if(args.length == REQUIRED_ARGS)
            return Boolean.TRUE;
        else return Boolean.FALSE;
    }

}
