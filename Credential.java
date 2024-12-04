import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Credential
{

	private static final String USERID_PATTERN = "USERID: ";
	private static final String NICKNAME_PATTERN = "NICKNAME: ";
	private static final String USERNAME_PATTERN = "USERNAME: ";
	private static final String PASSWORD_PATTERN = "PASSWORD: ";
	private static final String NOTE_PATTERN = "NOTE: ";

	// Variables
	private String userID;

	// 	1) For saving nickname
	private String nickname;

	// 	2) For saving username
	private String username;

	// 	3) For saving encrypted password
	private String encryptedPassword;
	private String password;

	// 	4) For saving encryption key
	private String encryptionKey;		// AES key for encryption/decryption

	// 	5) For saving note
	private String note;

	// Constructors
	public Credential(String userID, String nickname, String username, String password, String note) throws Exception
	{
		this.userID = userID;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.note = note;

		LoginAuthenticationForTextFile parseFileForKey = new LoginAuthenticationForTextFile();
		List<String> AccountInfo;
		AccountInfo = parseFileForKey.parseFile("StoredCredentials.txt", this.userID);
		if (AccountInfo == null || AccountInfo.size() < 3){
			System.out.println("AccountInfo is null");
		}else{
			String AssociatedKey = AccountInfo.get(2);
			System.out.println("Key HERE:" + AssociatedKey);

			// Encrypt the password and store the result along with the encryption key
			PasswordEncryptionForExistingLogin encryptLoginPassword = new PasswordEncryptionForExistingLogin(); // Instance of PasswordEncryptionForExistingLogin

			this.encryptedPassword = encryptLoginPassword.EncryptedLoginPassword(password, AssociatedKey);
			this.encryptionKey = AssociatedKey;
		}
	}

	// Getter methods
	public String getUserID(){return userID; }
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

	private String getText(){
		try{
			Path getTxt = Path.of("UserCredentials.txt");
			String filecontent = Files.readString(getTxt);
			return filecontent;
		} catch(IOException e){
			System.out.println("Error occurred while getting text from file");
			return null;
		}
	}

	// 	Function to save to file
	public void saveToFile(String filename)
	{
		// 'Try' creating a Buffered Writer connected to the provided file name
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
		{
			String text = getText();
			if (text != null && !text.trim().isEmpty()) {
				writer.write("\r\n"); // add newline if the file has content
			}

			String newLine = USERID_PATTERN  + userID + ", " + NICKNAME_PATTERN + nickname + ", " + USERNAME_PATTERN + username + ", " + PASSWORD_PATTERN + encryptedPassword +  ", " + NOTE_PATTERN + note;
			System.out.println(newLine);
			// If successful, write the content into the file...
			writer.write(newLine);
		}
		// 'Catch' any IOExceptions that may be thrown
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}