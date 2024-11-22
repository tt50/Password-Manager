
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Credential
{
	// Variables

	// 	1) For saving nickname
	private String nickname;

	// 	2) For saving username
	private String username;

	// 	3) For saving encrypted password
	private String encryptedPassword;

	// 	4) For saving encryption key
	private String encryptionKey;		// AES key for encryption/decryption

	// 	5) For saving note
	private String note;

	// Constructors
	public Credential(String nickname, String username, String password, String note) throws Exception
	{
		this.nickname = nickname;
		this.username = username;
		this.note = note;

		// Encrypt the password and store the result along with the encryption key
		PasswordEncryption.EncryptionResult result = PasswordEncryption.encryptPassword(password);
		this.encryptedPassword = result.getEncryptedText();
		this.encryptionKey = result.getKey();
	}

	// Getter methods
	// 	Function to retrieve nickname
	public String getNickname()
	{
		return nickname;
	}

	// 	Function to retrieve username
	public String getUsername()
	{
		return username;
	}

	// 	Function to retrieve hashed password
	public String getEncryptedPassword()
	{
		return encryptedPassword;
	}

	// 	Function to retrieve the encryption key
	public String getEncryptionKey()
	{
		return encryptionKey;
	}

	// 	Function to get the note
	public String getNote()
	{
		return note;
	}

	// 	Local method to retrieve decrypted password
	public String getDecryptedPassword() throws Exception
	{
		return PasswordDecryption.decryptPassword(this.encryptedPassword, this.encryptionKey);
	}


	// Other Functions
	// 	Function to save to file
	public void saveToFile(String filename)
	{
		// 'Try' creating a Buffered Writer connected to the provided file name
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
		{
			// If successful, write the content into the file...
			writer.write(nickname + ", " + username + ", " + encryptedPassword + ", " + note);
			// And append a new line feed so that saved data isn't one long line
			writer.newLine();
		}
		// 'Catch' any IOExceptions that may be thrown
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	// Function to read from file
	public List<Credential> readFromFile(String fileName)
	{
		// Create a List of type 'Credential' to save all the values from file in

		// Using a try-catch block, write details into the newly created List

		// If unsuccessful, throw an exception


		// If all works out, return the created List with the credentials in it
	}
}
