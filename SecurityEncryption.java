import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class SecurityEncryption {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "MySuperSecretKey"; // Example secret key



    /**
     * Encrypts a given answer using AES encryption.
     *
     * @param answer the plain text security answer to encrypt
     * @return the encrypted answer as a Base64 string
     * @throws Exception if an encryption error occurs
     */
    public String encryptAnswer(String answer) throws Exception {
        // Generate AES secret key
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        // Initialize cipher for encryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the answer
        byte[] encryptedBytes = cipher.doFinal(answer.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a given encrypted answer.
     *
     * @param encryptedAnswer the encrypted security answer as a Base64 string
     * @return the decrypted plain text answer
     * @throws Exception if a decryption error occurs
     */
    public String decryptAnswer(String encryptedAnswer) throws Exception {
        // Generate AES secret key
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        // Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the answer
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedAnswer);
        return new String(cipher.doFinal(decodedBytes));
    }

    public static void main(String[] args) {
        try {
            SecurityEncryption securityEncryption = new SecurityEncryption();

            // Test the encryption and decryption
            String originalAnswer = "Blue";
            String encryptedAnswer = securityEncryption.encryptAnswer(originalAnswer);
            String decryptedAnswer = securityEncryption.decryptAnswer(encryptedAnswer);

            System.out.println("Original Answer: " + originalAnswer);
            System.out.println("Encrypted Answer: " + encryptedAnswer);
            System.out.println("Decrypted Answer: " + decryptedAnswer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
