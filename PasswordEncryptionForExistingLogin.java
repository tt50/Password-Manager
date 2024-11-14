import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryptionForExistingLogin {
    // Note that Passwords will use a key associated with individual accounts.

    // Method to encrypt plain text password with the key associated with the account and return encrypted password
    public String EncryptedLoginPassword(String usernameInput, String encodedKey) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        // Initialize Cipher for Encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the Plain Text
        byte[] encryptedBytes = cipher.doFinal(usernameInput.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}