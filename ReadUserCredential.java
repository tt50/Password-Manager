import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadUserCredential {
    private static final String USERID_PATTERN = "USERID: ";
    private static final String NICKNAME_PATTERN = "NICKNAME: ";
    private static final String USERNAME_PATTERN = "USERNAME: ";
    private static final String PASSWORD_PATTERN = "PASSWORD: ";
    private static final String NOTE_PATTERN = "NOTE: ";

    public boolean readCredentials(String filename, String userIDSearched, String nicknameSearched) {
        if (userIDSearched == null || userIDSearched.isEmpty() || nicknameSearched == null || nicknameSearched.isEmpty()) {
            System.out.println("Error: userID or nickname is null or empty");
            return false;
        }

        List<String> accountInfo = parseFile(filename, userIDSearched, nicknameSearched);
        if (accountInfo == null || accountInfo.isEmpty()) {
            return false;
        }

        return true;
    }

    // Check if username and nickname already exist in files to determine if add credential can be allowed to be written
    public List<String> parseFile(String fileName, String userIDSearched, String nicknameSearched) {
        List<String> accountInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int userIDStartIndex = line.indexOf(USERID_PATTERN);
                int nicknameStartIndex = line.indexOf(NICKNAME_PATTERN);
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN);
                int passwordStartIndex = line.indexOf(PASSWORD_PATTERN);
                int noteStartIndex = line.indexOf(NOTE_PATTERN);

                if (usernameStartIndex >= 0 && passwordStartIndex > usernameStartIndex && userIDStartIndex >= 0 && nicknameStartIndex >= 0) {

                    String userID = line.substring(userIDStartIndex + USERID_PATTERN.length(), nicknameStartIndex).replace(",", "").trim();
                    String nickname = line.substring(nicknameStartIndex + NICKNAME_PATTERN.length(), usernameStartIndex).replace(",", "").trim();
                    String username = line.substring(usernameStartIndex + USERNAME_PATTERN.length(), passwordStartIndex).replace(",", "").trim();
                    String password = line.substring(passwordStartIndex + PASSWORD_PATTERN.length(), noteStartIndex).replace(",", "").trim();
                    String note = line.substring(noteStartIndex + NOTE_PATTERN.length()).replace(",", "").trim();

                    if (userID.equals(userIDSearched) && nickname.equals(nicknameSearched)) {
                        accountInfo.add(userID);
                        accountInfo.add(nickname);
                        accountInfo.add(username);
                        accountInfo.add(password);
                        accountInfo.add(note);
                        return accountInfo;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Username and nickname not found");
        return null; // Return null if not found
    }

    // Read file to find all associated credentials with the UserID, then add to the credential display list
    public List<CredentialDetails> parseFileForUser(String fileName, String userIDSearched) {
        List<CredentialDetails> credentialsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int userIDStartIndex = line.indexOf(USERID_PATTERN);
                int nicknameStartIndex = line.indexOf(NICKNAME_PATTERN);
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN);
                int passwordStartIndex = line.indexOf(PASSWORD_PATTERN);
                int noteStartIndex = line.indexOf(NOTE_PATTERN);

                if (usernameStartIndex >= 0 && passwordStartIndex > usernameStartIndex && userIDStartIndex >= 0 && nicknameStartIndex >= 0) {

                    String userID = line.substring(userIDStartIndex + USERID_PATTERN.length(), nicknameStartIndex).replace(",", "").trim();
                    String nickname = line.substring(nicknameStartIndex + NICKNAME_PATTERN.length(), usernameStartIndex).replace(",", "").trim();
                    String username = line.substring(usernameStartIndex + USERNAME_PATTERN.length(), passwordStartIndex).replace(",", "").trim();
                    String password = line.substring(passwordStartIndex + PASSWORD_PATTERN.length(), noteStartIndex).replace(",", "").trim();
                    String note = line.substring(noteStartIndex + NOTE_PATTERN.length()).replace(",", "").trim();


                    System.out.println(" UserID: " + userID +"|");
                    System.out.println(" Nickname: " + nickname+"|");
                    System.out.println(" Username: " + username+"|");
                    System.out.println(" Password: " + password+"|");
                    System.out.println(" Note: " + note+"|");

                    System.out.println("|"+userID+"|"+userIDSearched+"|");
                    if (userID.equals(userIDSearched)) {
                        CredentialDetails credential = new CredentialDetails(nickname, username, password, note);
                        credentialsList.add(credential);
                        //System.out.println("Credential added: " + credential.toString());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return credentialsList;
    }

}