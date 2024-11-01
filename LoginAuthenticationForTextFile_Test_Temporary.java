import java.io.File;
import java.io.IOException;
public class LoginAuthenticationForTextFile_Test_Temporary {
    // Test for LoginAuthenticationForTextFile
    public static void main(String[] args){
        String username = "tt";
        String password = "temple";
        CreateFile();
        LoginAuthenticationForTextFile auth = new LoginAuthenticationForTextFile(); // Instance of LoginAuthenticationForTextFile

        if(auth.AuthenticationForTextFile(username, password)){
           System.out.println("success");
       }else{
            System.out.println("fail");
       }
    }
    public static void CreateFile() {
        File myFile = new File("StoredCredentals.txt");
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
}