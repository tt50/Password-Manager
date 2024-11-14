import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class UsernameEncryption {
    // All Usernames will utilize the same encryption key, since username is used as the ID for the txt file and the MySQL database.
    // Note that Passwords will use a key associated with individual accounts.

    // Static Base64-encoded AES key
    private static final String ENCODED_KEY = "INqEeOyVRyv2ocfRZ02SHw==";

    // Decode the Base64 key and create a SecretKey
    private static SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(ENCODED_KEY);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // Encrypts plain text usernames with the encoded key, returns the encrypted username
    public String EncryptedUsername(String usernameInput) throws Exception {
        // Get the SecretKey
        SecretKey secretKey = getSecretKey();

        // Initialize Cipher for Encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the Plain Text
        byte[] encryptedBytes = cipher.doFinal(usernameInput.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    public static void printEncodedKey() {
        // Get the encoded key and print it
        System.out.println("Username Encryption Key: " + ENCODED_KEY);
    }

    public static void main(String[] args) throws Exception {
        UsernameEncryption usernameEncryption = new UsernameEncryption();
        String username = "Tiffany";
        System.out.println("Plaintext Username: " + username);

        System.out.println("Username key used: " + ENCODED_KEY);

        // Encrypt the username and print the result
        String encryptedUsername = usernameEncryption.EncryptedUsername(username);
        System.out.println("Encrypted Username: " + encryptedUsername);
    }

    /*
    // Method that was used to generate the username key
    public static void main(String[] args) throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES key size (128, 192, or 256 bits)
        SecretKey usernameKey = keyGen.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(usernameKey.getEncoded());
        System.out.println(encodedKey);
    }
    */
}