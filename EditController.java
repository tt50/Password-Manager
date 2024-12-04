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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EditController {

    String fileName = "UserCredentials.txt";
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextArea notesArea;
    @FXML
    private Label AlertLabel;
    private CredentialDetails credential;
    private AnchorPane viewDetailsContainer;  // container reference holder
    private String oldNickname;

    private String oldLine;
    // set Pane
    public void setViewDetailsContainer(AnchorPane viewDetailsContainer) {
        this.viewDetailsContainer = viewDetailsContainer;
    }

    public void setCredential(CredentialDetails credential) throws Exception {
        this.credential = credential;

        if (credential != null) {
            System.out.println("Credential received: " + credential.toString());

            // get userID
            String username = UserSession.getInstance().getUsername();
            UsernameEncryption userIDEncrypt = new UsernameEncryption();
            String encrytedUserID = userIDEncrypt.EncryptedUsername(username);

            oldLine = "USERID: " +  encrytedUserID + "," + "NICKNAME: " + credential.getNickname() + "," + "USERNAME: " + credential.getUsername() + "," + "PASSWORD: " + credential.getPassword() + "," + "NOTE: " + credential.getNotes();


            // get key
            LoginAuthenticationForTextFile Key = new LoginAuthenticationForTextFile();
            List<String> AccountInfo = Key.parseFile("StoredCredentials.txt", encrytedUserID);
            String AssociatedKey = AccountInfo.get(2);
            try {
                this.credential = credential;
                nicknameField.setText(PasswordDecryption.decryptPassword(credential.getNickname(), AssociatedKey));
                usernameField.setText(PasswordDecryption.decryptPassword(credential.getUsername(), AssociatedKey));
                passwordField.setText(PasswordDecryption.decryptPassword(credential.getPassword(), AssociatedKey));
                notesArea.setText(PasswordDecryption.decryptPassword(credential.getNotes(), AssociatedKey));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credential is null edit controller");
        }
    }

    @FXML
    public void saveDetails() throws Exception {
        String nickname = nicknameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String notes = notesArea.getText();

        if (nickname == null || nickname.trim().isEmpty()) {
            AlertLabel.setText("Nickname is required!");
        }else {
            System.out.println("Nickname: " + nickname);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Notes: " + notes);

            String userID = UserSession.getInstance().getUsername(); // get current logged in user,
            UsernameEncryption userEncrypt = new UsernameEncryption();
            userID = userEncrypt.EncryptedUsername(userID);

            // get key
            LoginAuthenticationForTextFile Key = new LoginAuthenticationForTextFile();
            List<String> AccountInfo = Key.parseFile("StoredCredentials.txt", userID);
            String AssociatedKey = AccountInfo.get(2);

            // Encrypt data using associated account key
            PasswordEncryptionForExistingLogin detailEncrypt = new PasswordEncryptionForExistingLogin();
            nickname = detailEncrypt.EncryptedLoginPassword(nickname, AssociatedKey);
            username = detailEncrypt.EncryptedLoginPassword(username, AssociatedKey);
            password = detailEncrypt.EncryptedLoginPassword(password, AssociatedKey);
            notes = detailEncrypt.EncryptedLoginPassword(notes, AssociatedKey);

            // replace existing line
            replaceLine(fileName, userID, nickname, username, password, notes);
        }
    }

    // Replace line in the file when nickname matches oldNickname
    private void replaceLine(String filename,String UserID, String nickname, String username, String password, String notes) throws IOException {
        // Create a new line with updated details
        String newLine = "USERID: " + UserID + ","+  "NICKNAME: " + nickname + "," + "USERNAME: " + username + "," + "PASSWORD: " + password + "," + "NOTE: " + notes;

        System.out.println("oldline: "+ oldLine);
        System.out.println("newline: "+newLine);

        if (!PasswordChanger.replaceLine(filename, oldLine, newLine)) {
            System.out.println("Line replacement failed.");
        } else {
            System.out.println("Line replaced successful.");
        }
    }

    private void switchToViewDetailsScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDetails.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = (DashboardController) ((Node) event.getSource()).getScene().getUserData();
            Pane contentPane = dashboardController.getContentPane();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);

        } catch (Exception e) {
            e.printStackTrace();
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
            DashboardController dashboardController = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setUserData(dashboardController);

            dashboardController.initialize();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        nicknameField.setEditable(true);
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        notesArea.setEditable(true);
    }

}