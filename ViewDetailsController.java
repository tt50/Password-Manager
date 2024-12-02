import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class ViewDetailsController {

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label notesLabel;

    public void setCredential(DisplayCredentialDetails credential) {
        nicknameLabel.setText(credential.getNickname());
        usernameLabel.setText(credential.getUsername());
        passwordLabel.setText(credential.getPassword());
        notesLabel.setText(credential.getNotes());
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
    public void editButtonClicked(ActionEvent event){
        switchToEditScene(event);
    }

    private void switchToEditScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScene.fxml"));
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