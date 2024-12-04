import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ViewDetailsController {

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label notesLabel;
    private AnchorPane viewDetailsContainer;  // container reference holder
    private CredentialDetails credential;

    public void setCredential(CredentialDetails credential) throws Exception {
        if(credential != null) {
            // get userID
            String username = UserSession.getInstance().getUsername();
            UsernameEncryption userIDEncrypt = new UsernameEncryption();
            String encrytedUserID = userIDEncrypt.EncryptedUsername(username);

            // get key
            LoginAuthenticationForTextFile Key = new LoginAuthenticationForTextFile();
            List<String> AccountInfo = Key.parseFile("StoredCredentials.txt", encrytedUserID);
            String AssociatedKey = AccountInfo.get(2);

            this.credential = credential;
            nicknameLabel.setText(PasswordDecryption.decryptPassword(credential.getNickname(), AssociatedKey));
            usernameLabel.setText(PasswordDecryption.decryptPassword(credential.getUsername(), AssociatedKey));
            passwordLabel.setText(credential.getPassword());
            notesLabel.setText(PasswordDecryption.decryptPassword(credential.getNotes(), AssociatedKey));
        }else{
            System.out.println("Credential is null");
        }
    }

    // set Pane
    public void setViewDetailsContainer(AnchorPane viewDetailsContainer) {
        this.viewDetailsContainer = viewDetailsContainer;
    }

    @FXML
    public void copyUsername() {
        ClipboardContent content = new ClipboardContent();
        content.putString(usernameLabel.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void copyPassword() {
        ClipboardContent content = new ClipboardContent();
        content.putString(passwordLabel.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void editButtonClicked(ActionEvent event) {
        if (credential != null) {
            System.out.println("Credential: " + credential.toString());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            DashboardController dashboardController = (DashboardController) scene.getUserData();
            if (dashboardController == null) {
                System.out.println("DashboardController is null.");
                dashboardController = (DashboardController) stage.getUserData();
            }
            if (dashboardController != null) {
                dashboardController.displayEdit(credential);
            } else {
                System.out.println("Error: DashboardController is null");
            }
        } else {
            System.out.println("Error: Credential is null");
        }
    }

    @FXML
    public void viewPassword(ActionEvent actionEvent) throws Exception {
        String encryptedPassword = credential.getPassword();
        System.out.println("Encrypted password in viewPassword: " + encryptedPassword);

        if (credential != null && credential.getPassword().equals(passwordLabel.getText())) {
            // Get user id and key again to decrypt password if needed
            String username = UserSession.getInstance().getUsername();
            UsernameEncryption userIDEncrypt = new UsernameEncryption();
            String encryptedUserID = userIDEncrypt.EncryptedUsername(username);

            // Get key
            LoginAuthenticationForTextFile key = new LoginAuthenticationForTextFile();
            List<String> accountInfo = key.parseFile("StoredCredentials.txt", encryptedUserID);
            String associatedKey = accountInfo.get(2);

            String decryptedCredentialPassword = PasswordDecryption.decryptPassword(credential.getPassword(), associatedKey);
            System.out.println("Decrypted password: " + decryptedCredentialPassword);
            passwordLabel.setText(decryptedCredentialPassword);
        } else {
            System.out.print("Error or already decrypted");
        }

    }

}