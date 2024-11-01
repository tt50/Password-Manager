import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordDecryption {

    public static String decryptPassword(String encryptedText, String encodedKey) throws Exception {
        // Step 1: Decode the Base64-encoded AES key
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        // Step 2: Initialize Cipher for Decryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Step 3: Decode the encrypted text from Base64 and decrypt it
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert decrypted bytes to a string and return it
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Example encrypted text and AES key (replace these with actual values)
            String encryptedText = "YourEncryptedTextHere";
            String encryptionKey = "YourEncodedKeyHere";

            // Decrypt the password
            String decryptedPassword = decryptPassword(encryptedText, encryptionKey);

            // Output the decrypted password
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
