import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromUserCredentials {
    private static final String USERNAME_PATTERN = "USER: ";
    private static final String PASSWORD_PATTERN = "PASS: ";
    private static final String NAME_PATTERN = "NAME: ";
    private static final String NOTE_PATTERN = "NOTE: ";


    // need to revise
    public List<String> parseUserCredentialFile(String fileName, String usernameSearched) {
        List<String> accountInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN);
                int passwordStartIndex = line.indexOf(PASSWORD_PATTERN);
                int nameStartIndex = line.indexOf(NAME_PATTERN);
                int noteStartIndex = line.indexOf(NOTE_PATTERN);

                if (passwordStartIndex == 0 && usernameStartIndex > passwordStartIndex && nameStartIndex > usernameStartIndex && noteStartIndex > nameStartIndex) {
                    String password = line.substring(passwordStartIndex + PASSWORD_PATTERN.length(), usernameStartIndex).replace(",","").trim();
                    String username = line.substring(usernameStartIndex + USERNAME_PATTERN.length(), nameStartIndex).replace(",","").trim();
                    String name = line.substring(nameStartIndex + USERNAME_PATTERN.length(), noteStartIndex).replace(",","").trim();
                    String note = line.substring(noteStartIndex + NOTE_PATTERN.length()).replace(",","").trim();

                    System.out.println("Parsed Username: |" + username + "|");
                    System.out.println("Parsed Password: |" + password + "|");
                    System.out.println("Parsed Key: |" + name + "|");
                    System.out.println("Parsed Key: |" + note + "|");

                    if (username.equals(usernameSearched)) {
                        System.out.println("username found");

                        accountInfo.add(username);
                        accountInfo.add(password);
                        accountInfo.add(name);
                        accountInfo.add(note);
                        return accountInfo;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Login Fail, username not found");
        return null; // Username was not found in .txt file
    }
}
