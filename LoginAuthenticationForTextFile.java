import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginAuthenticationForTextFile {
    private static final String USERNAME_PATTERN = "Username:\"";
    private static final String PASSWORD_PATTERN = "Password:\"";
    private static final String KEY_PATTERN = "Key:\"";

    public Boolean AuthenticationForTextFile(String usernameInput, String passwordInput) {
        String UsernameSearched = usernameInput;
        List<String> AssociatedPasswordAndKey;
        AssociatedPasswordAndKey = parseFile("StoredCredentals.txt",UsernameSearched);
        if(AssociatedPasswordAndKey == null ) //|| AssociatedPasswordAndKey.size() < 2)
            return false;
        String AssociatedPassword = AssociatedPasswordAndKey.get(0);
        //String AssociatedKey = AssociatedPasswordAndKey.get(1);
        //String DecryptedPassword;
        //DecryptedPassword = Decrypt(AssociatedPassword,AssociatedKey);
        //return passwordInput.equals(DecryptedPassword);
        return passwordInput.equals(AssociatedPassword);
    }

    public List<String> parseFile(String fileName, String usernameSearched) {
        List<String> PasswordAndKey = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN) + USERNAME_PATTERN.length();
                int usernameEndIndex = line.indexOf("\"", usernameStartIndex);
                String username = line.substring(usernameStartIndex, usernameEndIndex).trim();
                if (username.equals(usernameSearched)) {
                    int passwordStartIndex = line.indexOf(PASSWORD_PATTERN) + PASSWORD_PATTERN.length();
                    int passwordEndIndex = line.indexOf("\"", passwordStartIndex);
                    String password = line.substring(passwordStartIndex, passwordEndIndex).trim();
                    PasswordAndKey.add(password);

                    //int keyStartIndex = line.indexOf(KEY_PATTERN) + KEY_PATTERN.length();
                    //int keyEndIndex = line.indexOf("\"", keyStartIndex);
                   // String key = line.substring(keyStartIndex, keyEndIndex).trim();
                   // PasswordAndKey.add(key);

                    return PasswordAndKey; // Return
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        System.out.println("fail here");
        return null; // Username was not found in .txt file.
    }
}