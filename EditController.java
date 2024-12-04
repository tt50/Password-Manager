import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextArea notesArea;

    private CredentialDetails credential;
    private AnchorPane viewDetailsContainer;  // container reference holder

    // set Pane
    public void setViewDetailsContainer(AnchorPane viewDetailsContainer) {
        this.viewDetailsContainer = viewDetailsContainer;
    }

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
            nicknameField.setText(PasswordDecryption.decryptPassword(credential.getNickname(), AssociatedKey));
            usernameField.setText(PasswordDecryption.decryptPassword(credential.getUsername(),AssociatedKey));
            passwordField.setText(PasswordDecryption.decryptPassword(credential.getPassword(), AssociatedKey));
            notesArea.setText(PasswordDecryption.decryptPassword(credential.getNotes(), AssociatedKey));
        }else{
            System.out.println("Credential is null");
        }
    }

    @FXML
    public void saveDetails() {
        String nickname = nicknameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String notes = notesArea.getText();

        System.out.println("Nickname: " + nickname);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Notes: " + notes);
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
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            DashboardController dashboardController = loader.getController();
            dashboardController.initialize();

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