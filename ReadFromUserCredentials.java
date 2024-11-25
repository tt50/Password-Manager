import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromUserCredentials {
    private static final String USERID_PATTERN = "USERID: ";
    private static final String NAME_PATTERN = "NAME: ";
    private static final String USERNAME_PATTERN = "USERNAME: ";
    private static final String NOTE_PATTERN = "NOTE: ";
    private final UsernameEncryption EncryptUsername = new UsernameEncryption(); // Instance of UsernameEncryption
    private final PasswordEncryptionForExistingLogin encryptLoginPassword = new PasswordEncryptionForExistingLogin(); // Instance of PasswordEncryptionForExistingLogin
    public boolean ReadUserCredentials(String usernameInput) throws Exception {
        if (usernameInput == null ) {
            System.out.println("Error: empty username or password ");
            return false;
        }

        List<String> AccountInfo;

        // Encrypt the username input
        String encryptedLoginUsername = EncryptUsername.EncryptedUsername(usernameInput);
        System.out.println("Encrypted Username: " + encryptedLoginUsername);

        // parse the txt file for the username
        AccountInfo = parseUserCredentialFile("TestFile.txt", encryptedLoginUsername);
        if (AccountInfo == null || AccountInfo.size() < 4)
            return false;
        String userID = AccountInfo.get(0); //user id
        String Nickname = AccountInfo.get(1);
        String Username = AccountInfo.get(2);
        String Note = AccountInfo.get(3);

        //Testing output
        System.out.println("userID: " + userID); // user id
        System.out.println("Nickname: " + Nickname);
        System.out.println("Username: " + Username);
        System.out.println("Note: " + Note);

        return userID.equals(encryptedLoginUsername);
    }


    // need to revise
    public List<String> parseUserCredentialFile(String fileName, String usernameSearched) {
        List<String> accountInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int useridStartIndex = line.indexOf(USERID_PATTERN);
                int nameStartIndex = line.indexOf(NAME_PATTERN);
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN);
                int noteStartIndex = line.indexOf(NOTE_PATTERN);

                if (useridStartIndex == 0 && nameStartIndex > useridStartIndex && usernameStartIndex > nameStartIndex && noteStartIndex > usernameStartIndex) {
                    String userID = line.substring(useridStartIndex + USERID_PATTERN.length(), usernameStartIndex).replace(",","").trim();
                    String name = line.substring(nameStartIndex + NAME_PATTERN.length(), nameStartIndex).replace(",","").trim();
                    String username = line.substring(nameStartIndex + USERNAME_PATTERN.length(), noteStartIndex).replace(",","").trim();
                    String note = line.substring(noteStartIndex + NOTE_PATTERN.length()).replace(",","").trim();

                    System.out.println("userID:  |" + userID + "|");
                    System.out.println("Nickname: |" + name + "|");
                    System.out.println("Username:  |" + username + "|");
                    System.out.println("Note: |" + note + "|");

                    if (username.equals(usernameSearched)) {
                        System.out.println("username found");

                        accountInfo.add(userID);
                        accountInfo.add(name);
                        accountInfo.add(username);
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
