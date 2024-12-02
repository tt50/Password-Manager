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




}
