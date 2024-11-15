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
    private Label userLabel;

    private String username;

    public void setUsername(String username){
        this.username = username;
        userLabel.setText("Welcome "+ username);
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