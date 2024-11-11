public class CreateNewAccount {
    public static boolean CreateNewAcc(String usernameInput, String passwordInput) throws Exception {
        if (usernameInput == null || passwordInput == null) {
            System.out.println("Error: empty username or password");
            return false;
        }

        UsernameEncryption EncryptUsername = new UsernameEncryption(); // Instance of UsernameEncryption
        PasswordEncryption EncryptPassword = new PasswordEncryption(); // Instance of PasswordEncryption
        LoginAuthenticationForTextFile LoginAuthentication = new LoginAuthenticationForTextFile(); // Instance of LoginAuthenticationForTextFile
        IfAccountExists ExistingAccCheck = new IfAccountExists(); // Instance of IfAccountExists

        // Encrypt the username input
        String encryptedUsername = EncryptUsername.EncryptedUsername(usernameInput);

        // Compare the newly encrypted username input to existing encrypted usernames
        if(ExistingAccCheck.checkIfAccExists(encryptedUsername)) {
            // return an existing account message on the create account page
            return false; // Return error message if account exists
        }else{ // Username was not an existing username, new account can be created
            //Encrypt the password input and get result
            PasswordEncryption.EncryptionResult passwordResult = PasswordEncryption.encryptPassword(passwordInput);

            // Save encryptedPassword and passwordKey
            String encryptedPassword = passwordResult.getEncryptedText();
            String passwordKey = passwordResult.getKey();

            // Testing output values for encrypting usernames and passwords
            System.out.println("Plaintext Username: " + usernameInput);
            System.out.println("Plaintext Password: " + passwordInput);
            System.out.println("Encrypted Username: " + encryptedUsername); // If you test on the same username, the encrypted value should not change
            System.out.println("Encrypted Password: " + encryptedPassword);
            System.out.println("Encryption Key for account/password: " + passwordKey);
            UsernameEncryption.printEncodedKey(); // Should equal to "INqEeOyVRyv2ocfRZ02SHw=="

            storeCredentials store = new storeCredentials("StoredCredentials.txt", encryptedPassword, encryptedUsername, passwordKey);
            return true; // Return success message
        }
    }

    public static void main(String[] args) throws Exception {
        CreateNewAcc("Tiffany","Temple");
    }

}