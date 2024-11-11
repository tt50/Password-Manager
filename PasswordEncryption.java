import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryption {

    // Method to encrypt plain text data and return both encrypted text and AES key
    public static EncryptionResult encryptPassword(String plainText) throws Exception {
        // Step 1: Generate AES Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES key size (128, 192, or 256 bits)
        SecretKey secretKey = keyGen.generateKey();

        // Step 2: Initialize Cipher for Encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Step 3: Encrypt the Plain Text
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        // Step 4: Encode the Key as Base64 for storage/transmission
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Return encrypted text and key
        return new EncryptionResult(encryptedText, encodedKey);
    }

    // Helper class to hold the encrypted text and the encryption key
    public static class EncryptionResult {
        private final String encryptedText;
        private final String key;

        public EncryptionResult(String encryptedText, String key) {
            this.encryptedText = encryptedText;
            this.key = key;
        }

        public String getEncryptedText() {
            return encryptedText;
        }

        public String getKey() {
            return key;
        }
    }

    public static void main(String[] args) {
        try {
            String plainPassword = "mySecurePassword123";

            // Encrypt the password and get the result
            EncryptionResult result = encryptPassword(plainPassword);

            // Output encrypted password and key
            System.out.println("Encrypted Password: " + result.getEncryptedText());
            System.out.println("Encryption Key: " + result.getKey());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
