public class CreateNewAccount {
    public static boolean CreateNewAcc(String usernameInput, String passwordInput,
                                       String securityQuestion1, String securityAnswer1,
                                       String securityQuestion2, String securityAnswer2) throws Exception {
        if (usernameInput == null || passwordInput == null ||
                securityQuestion1 == null || securityAnswer1 == null ||
                securityQuestion2 == null || securityAnswer2 == null) {
            System.out.println("Error: Empty username, password, or security question/answer");
            return false;
        }

        UsernameEncryption EncryptUsername = new UsernameEncryption(); // Instance of UsernameEncryption
        PasswordEncryption EncryptPassword = new PasswordEncryption(); // Instance of PasswordEncryption
        SecurityEncryption EncryptSecurity = new SecurityEncryption(); // Instance of SecurityEncryption
        IfAccountExists ExistingAccCheck = new IfAccountExists(); // Instance of IfAccountExists

        // Encrypt the username input
        String encryptedUsername = EncryptUsername.EncryptedUsername(usernameInput);

        // Compare the newly encrypted username input to existing encrypted usernames
        if (ExistingAccCheck.checkIfAccExists(encryptedUsername)) {
            // Return an error if account exists
            System.out.println("Error: Account with this username already exists.");
            return false;
        } else {
            // Encrypt the password input and get result
            PasswordEncryption.EncryptionResult passwordResult = PasswordEncryption.encryptPassword(passwordInput);

            // Encrypt the security answers
            String encryptedAnswer1 = EncryptSecurity.encryptAnswer(securityAnswer1);
            String encryptedAnswer2 = EncryptSecurity.encryptAnswer(securityAnswer2);

            // Save encrypted password and password key
            String encryptedPassword = passwordResult.getEncryptedText();
            String passwordKey = passwordResult.getKey();

            // Testing output values for encryption
            System.out.println("Plaintext Username: " + usernameInput);
            System.out.println("Plaintext Password: " + passwordInput);
            System.out.println("Encrypted Username: " + encryptedUsername);
            System.out.println("Encrypted Password: " + encryptedPassword);
            System.out.println("Encryption Key for Password: " + passwordKey);
            System.out.println("Security Question 1: " + securityQuestion1);
            System.out.println("Security Answer 1 (Encrypted): " + encryptedAnswer1);
            System.out.println("Security Question 2: " + securityQuestion2);
            System.out.println("Security Answer 2 (Encrypted): " + encryptedAnswer2);
            UsernameEncryption.printEncodedKey();

            // Store credentials in the file
            storeCredentials store = new storeCredentials("StoredCredentials.txt",
                    encryptedPassword, encryptedUsername,
                    passwordKey, securityQuestion1, encryptedAnswer1,
                    securityQuestion2, encryptedAnswer2);
            return true; // Return success message
        }
    }

    public static void main(String[] args) throws Exception {
        // Example usage for testing
        CreateNewAcc("Tiffany", "Temple",
                "What is your favorite color?", "Blue",
                "What is your mother's maiden name?", "Smith");
    }
}
