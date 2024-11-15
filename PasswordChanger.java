import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PasswordChanger {
    private static final String USERNAME_PATTERN = "USER: ";
    private static final String PASSWORD_PATTERN = "PASS: ";
    private static final String KEY_PATTERN = "KEY: ";
    private final UsernameEncryption EncryptUsername = new UsernameEncryption(); // Instance of UsernameEncryption
    private final PasswordEncryptionForExistingLogin encryptLoginPassword = new PasswordEncryptionForExistingLogin(); // Instance of PasswordEncryptionForExistingLogin

    public static boolean PasswordChange(String username, String oldPassword, String newPassword) throws Exception {
        LoginAuthenticationForTextFile Auth = new LoginAuthenticationForTextFile();
        if(username == null || oldPassword == null || newPassword == null || !Auth.AuthenticationForTextFile(username, oldPassword))
            return false;

        UsernameEncryption EncryptUsername = new UsernameEncryption(); // Instance of UsernameEncryption
        PasswordEncryptionForExistingLogin encryptLoginPassword = new PasswordEncryptionForExistingLogin(); // Instance of PasswordEncryptionForExistingLogin

        List<String> AccountInfo;

        // Encrypt the username input
        String encryptedLoginUsername = EncryptUsername.EncryptedUsername(username);

        // parse the txt file for the username
        AccountInfo = Auth.parseFile("StoredCredentials.txt", encryptedLoginUsername);
        if (AccountInfo == null || AccountInfo.size() < 3)
            return false;
        String AssociatedUsername = AccountInfo.get(0);
        String AssociatedPassword = AccountInfo.get(1);
        String AssociatedKey = AccountInfo.get(2);

        String oldLine = "PASS: " + AssociatedPassword + "," + "USER: " + AssociatedUsername + "," + "KEY: " + AssociatedKey;

        // Encrypt the new password
        String encryptedNewPassword = encryptLoginPassword.EncryptedLoginPassword(newPassword, AssociatedKey);

        String newLine = "PASS: " + encryptedNewPassword + "," + "USER: " + AssociatedUsername + "," + "KEY: " + AssociatedKey;
        String filename = "StoredCredentials.txt";

        try {
            if(!replaceLine(filename, oldLine, newLine))
                return false;
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return true;
    }

    // Replaces line using random access file
    public static boolean replaceLine(String filename, String oldLine, String newLine) throws IOException {
        if (newLine.length() != oldLine.length()) {
            throw new IllegalArgumentException("New line content must be the same length as the old line content");
        }
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            String line;
            long pointerPosition = 0;

            while ((line = file.readLine()) != null) {
                if (line.equals(oldLine)) {
                    // Move pointer to the start of this line
                    file.seek(pointerPosition);
                    // Overwrite with new line content
                    file.writeBytes(newLine);
                    return true; // old line was replaced with new line
                }
                // Update pointer position to the beginning of the next line
                pointerPosition = file.getFilePointer();
            }
            return false; // old line not found
        }
    }

    public static void main(String[] arg) throws Exception {
        if(PasswordChange("Tiffany", "Hello123!","Temple123!")){
            System.out.println("password change passed");
            LoginAuthenticationForTextFile Auth = new LoginAuthenticationForTextFile();
            if(Auth.AuthenticationForTextFile("Tiffany","Temple123!"))
                System.out.println("auth passed");
        }else{
            System.out.println("Test failed");
        }
    }
}