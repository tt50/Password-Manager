import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class AddCredentialController {

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextArea notesArea;

    @FXML
    private Label creationLabel;

    public void setCredential(CredentialDetails credential) {
        nicknameField.setText(credential.getNickname());
        usernameField.setText(credential.getUsername());
        passwordField.setText(credential.getPassword());
        notesArea.setText(credential.getNotes());
    }

    String fileName = "UserCredentials.txt";

    @FXML
    public void saveDetails() throws Exception {
        creationLabel.setText("");
        String nickname = nicknameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String notes = notesArea.getText();

        String userID = UserSession.getInstance().getUsername(); // get current logged in user,
        //System.out.println("userID:" + userID);

        // Encrypt the userID
        UsernameEncryption userEncrypt = new UsernameEncryption();
        userID = userEncrypt.EncryptedUsername(userID);

        ReadUserCredential readUserCredential = new ReadUserCredential();
        if (nicknameField.getText() == null || nicknameField.getText().trim().isEmpty()) {
            creationLabel.setText("Nickname is required!");
        } else if (readUserCredential.readCredentials(fileName, userID, nickname)) {
            //System.out.println("existing credential");
            creationLabel.setText("Nickname already exists, please use a different one!");
        } else {
            try {
                Credential credential = new Credential(userID, nickname, username, password, notes);
                credential.saveToFile(fileName);

                System.out.println("Credential saved successfully!");
                System.out.println("UserID: " + userID);
                System.out.println("Nickname: " + nickname);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Notes: " + notes);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error saving credential.");
            }
        }
    }

    @FXML
    public void cancelButton(ActionEvent event) {
        switchToDashboardScene(event);
    }

    private void switchToDashboardScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}