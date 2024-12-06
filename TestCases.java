import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCases {

    // Checks value of encrypted username using username input
    @Test
    public void testUsernameEncryption() throws Exception {
        UsernameEncryption usernameEncryption = new UsernameEncryption();
        String username = "Tiffany";
        String encryptedUsername = usernameEncryption.EncryptedUsername(username);
        assertEquals(encryptedUsername, "SOWPJOcQ16KYBcTNvwFT1w==");
    }

    // Checks value of encrypted password using password and key input
    @Test
    public void testLoginPasswordEncryption() throws Exception {
        PasswordEncryptionForExistingLogin encryptLoginPassword = new PasswordEncryptionForExistingLogin();
        String password = "Temple123!";
        String AssociatedKey = "yyGyQU8JdwnET3+P8N7aDQ==";
        String encryptedLoginPassword = encryptLoginPassword.EncryptedLoginPassword(password, AssociatedKey);
        assertEquals(encryptedLoginPassword, "AW2/LGyrpmrqsCqglhEj5g==");
    }

    // Checks value of an encrypted password that gets decrypted
    @Test
    public void testPasswordDecryption() throws Exception {
        PasswordDecryption decryptPass = new PasswordDecryption();
        String encryptedPassword = "AW2/LGyrpmrqsCqglhEj5g==";
        String AssociatedKey = "yyGyQU8JdwnET3+P8N7aDQ==";
        String decryptedPassword = decryptPass.decryptPassword(encryptedPassword,AssociatedKey);
        assertEquals(decryptedPassword, "Temple123!");
    }

    // Checks that Password Manager account is authenticated successfully
    @Test
    public void testLoginAuthentication() throws Exception {
        LoginAuthenticationForTextFile auth = new LoginAuthenticationForTextFile();
        String user = "tiffany";
        String pass = "Truong123!";
        boolean isAuthenticated = auth.AuthenticationForTextFile(user, pass);
        TestCase.assertTrue(isAuthenticated);
    }

    // Checks that Password was changed successfully
    @Test
    public void testPasswordChange() throws Exception {
        String user = "tiffany";
        String oldpass = "Temple123!";
        String newpass = "Truong123!";
        Boolean result = PasswordChanger.PasswordChange(user, oldpass, newpass);
        TestCase.assertTrue(result);
    }

    // Checks if account exists in the StoredCredentials.txt file
    @Test
    public void testIfAccountExists() throws Exception {
        String user = "tiffany";
        UsernameEncryption encrypt = new UsernameEncryption();
        String encryptedUsername = encrypt.EncryptedUsername(user);
        IfAccountExists acc = new IfAccountExists();
        Boolean result = acc.checkIfAccExists(encryptedUsername);
        TestCase.assertTrue(result);
    }

    // Checks if new account can be created
    // this user already exists, so this test checks if it returns false
    @Test
    public void createNewAccount() throws Exception {
        CreateNewAccount acc = new CreateNewAccount();
        boolean result;
        result = CreateNewAccount.CreateNewAcc("Tiffany", "Temple",
                "What is your favorite color?", "Blue",
                "What is your mother's maiden name?", "Smith");

        TestCase.assertFalse(result);
    }


    }