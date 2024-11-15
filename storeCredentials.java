import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class storeCredentials {

    private final String filename;
    String[] tokens = null;
    String updated = null;

    public storeCredentials(String file, String password, String user, String key){ //used if no text file exists
        filename = file;
        String contents = "PASS: " + password + "," + "USER: " + user + "," + "KEY: " + key;
        createFile();
        try { //writes content to file
            FileWriter fw = new FileWriter(filename, true);

            // if file is not empty add a newline
            String text = getText();
            if (text != null && !text.trim().isEmpty()) {
                fw.write("\r\n"); // add newline if the file has content
            }

            fw.write(contents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while creating file contents");
        }

    }

    public storeCredentials(String file){ //used if text file exists and certain contents need updating
        filename = file;
    }

    public void printFile(){
        createFile(); //makes sure that the text file exists, and creates it if not
        String text = getText(); //gets all content from text file
        System.out.println("[File Content] = " + "[" + text + "]");
    }

    private boolean createFile(){
        try{
            File file = new File(filename);
            file.createNewFile();
            return true;
        } catch(IOException e){
            System.out.println("Error occurred during file creation");
            return false;
        }
    }

    private String getText(){
        try{
            Path getTxt = Path.of(filename);
            String filecontent = Files.readString(getTxt);
            return filecontent;
        } catch(IOException e){
            System.out.println("Error occurred while getting text from file");
            return null;
        }
    }

    private boolean writeUpdate(String updated){
        try { //writes new content to file
            FileWriter fw = new FileWriter(filename);

            // if file is not empty add a newline
            String text = getText();
            if (text != null && !text.trim().isEmpty()) {
                fw.write("\r\n"); // add newline if the file has content
            }

            fw.write(updated);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred while storing either password, username, or key");
            return false;
        }
    }

    public boolean storePass(String password) {
        createFile(); //makes sure that the text file exists, and creates it if not
        String text = getText(); //gets all content from text file

        try { //breaks down content on file by password, user, key and stores in array
            tokens = text.split(",");
            password = "PASS: " + password + ","; //prepares the new password for storage
            tokens[0] = password;
            updated = tokens[0] + tokens[1]+ "," + tokens[2]; //updates entire contents of file
        } catch (NullPointerException e) {
            System.out.println("Error occurred while splitting text file contents");
            return false;
        }

        return writeUpdate(updated);
    }

    public boolean storeUser(String user){
        createFile(); //makes sure that the text file exists, and creates it if not
        String text = getText(); //gets all content from text file
        
        try { //breaks down content on file by password, user, key and stores in array
            tokens = text.split(",");
            user = "USER: " + user + ","; //prepares the new username for storage
            tokens[1] = user;
            updated = tokens[0] + "," + tokens[1] + tokens[2];
        } catch (NullPointerException e) {
            System.out.println("Error occurred while splitting text file contents");
            return false;
        }

        return writeUpdate(updated);
    }

    public boolean storeKey(String key){
        createFile(); //makes sure that the text file exists, and creates it if not
        String text = getText(); //gets all content from text file

        try { //breaks down content on file by password, user, key and stores in array
            tokens = text.split(",");
            key = "KEY: " + key; //prepares the new key for storage
            tokens[2] = key;
            updated = tokens[0] + "," + tokens[1] + "," + tokens[2];
        } catch (NullPointerException e) {
            System.out.println("Error occurred while splitting text file contents");
            return false;
        }

        return writeUpdate(updated);
    }

    /**
     * @param password The password to check.
     * @return A message indicating the password strength.
     * @code Checks the strength of the given password.
     */
    public String checkPasswordStrength(String password)
    {
        // Check the password's length...
        if(password.length() < 8)
        {
            // And if not long enough, return a message saying so
            return "Weak: Password must be at least 8 characters.";
        }
        // Check if the password contains any capital letters...
        if(!password.matches(".*[A-Z].*"))
        {
            // And if not, return a message saying so
            return "Weak: Password must contain at least one uppercase letter.";
        }
        // Check if the password contains any lowercase letters...
        if(!password.matches(".*[a-z].*"))
        {
            // And if not, return a message saying so
            return "Weak: Password must contain at least one lower case letter.";
        }
        // Check if the password contains any digits...
        if(!password.matches(".*\\d.*"))
        {
            // And if not, return a message saying so
            return "Weak: Password must contain at least one digit.";
        }
        // Check if the password contains any special characters...
        if(!password.matches(".*[!@#$%^&*()].*"))
        {
            // And if not, return a message recommending them
            return "Moderate: Add a special character for more strength.";
        }
        // Otherwise, simple return that it is a strong password
        return "String: Good password!";
    }

    public static void main(String[] args) { //an example of how the class works
        storeCredentials file = new storeCredentials("test.txt", "password", "user", "key");
        System.out.println("Content prior after file is created: \n");
        file.printFile();

        storeCredentials update_file = new storeCredentials("StoredCredentials.txt");

        update_file.storePass("newPass");
        System.out.println("\nContent after updating password: \n");
        file.printFile();

        update_file.storeUser("newUser");
        System.out.println("\nContent after updating username: \n");
        file.printFile();

        update_file.storeKey("newKey");
        System.out.println("\nContent after updating key: \n");
        file.printFile();
    }
}



