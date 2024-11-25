
import java.io.*;
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
		List<Credential> credentials = new ArrayList<>();
		// Using a try-catch block, write details into the newly created List
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			// Variable to save line
			String line;

			// While there are more lines to be read
			while((line = reader.readLine()) != null)
			{
				// add each of the credentials that are of length 4 to credentials
				String[] parts = line.trim().split(", ");

				// If the length of the array is 4, create a Credential object with those values and add it to the ArrayList created above
				if(parts.length == 4)
				{
					try
					{
						Credential credential = new Credential(parts[0], parts[1], parts[2], parts[3]);
						credentials.add(credential);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		// If unsuccessful, throw an exception
		catch(IOException e)
		{
			e.printStackTrace();
		}

		// If all works out, return the created List with the credentials in it
		return credentials;
	}
}
