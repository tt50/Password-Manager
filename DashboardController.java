import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

public class DashboardController {
    @FXML
    private TextField plainTextData;
    @FXML
    private Label encryptionTestLabel;
    @FXML
    public void settingButtonClicked(ActionEvent event){
        switchToSettingsScene(event);
    }

    @FXML
    private TextField passwordInput;
    @FXML
    private Label strengthResult;

    @FXML
    public void checkPasswordStrength(ActionEvent event)
    {
        // Receive password from 'passwordInput'

        // If no password is provided...

            // Set the result of the check to be an error message to the user

        // Call the password strength checker function using the 'PasswordStrengthChecker' module

        // Display the result of the strength to the user of the function
        
    }

    private final UsernameEncryption EncryptUsername = new UsernameEncryption();

    // feature interface test
    // returns the value of an encrypted username
    @FXML
    public void encryptionTestButtonClicked(ActionEvent event) throws Exception {
        String plainText = plainTextData.getText();
        if(plainText != null){
            String encryptedUsername = EncryptUsername.EncryptedUsername(plainText);
            encryptionTestLabel.setText("Original username: '" + plainText + "'  Encrypted Username: '" + encryptedUsername +"'");
        } else{
            encryptionTestLabel.setText("Invalid input");
        }
    }

    private void switchToSettingsScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}