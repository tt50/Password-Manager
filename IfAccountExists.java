import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IfAccountExists {
    private static final String USERNAME_PATTERN = "USER: ";

    public boolean checkIfAccExists(String usernameInput){
        return parseFileForUsername("StoredCredentials.txt", usernameInput);
    }

    // Locate username in the txt file, return if username matches usernameInput
    public boolean parseFileForUsername(String fileName, String usernameSearched) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN) + USERNAME_PATTERN.length();

                if (usernameStartIndex - USERNAME_PATTERN.length() != -1) {
                    int usernameEndIndex = line.indexOf(",", usernameStartIndex);
                    if (usernameEndIndex == -1) {
                        usernameEndIndex = line.length();
                    }

                    String username = line.substring(usernameStartIndex, usernameEndIndex).replace(",","").trim();
                    if (username.equals(usernameSearched)) {
                        return true; // Username was found
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false; // Username was not found
    }
}
