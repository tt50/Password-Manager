import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class AddDetailsController {

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextArea notesArea;

    public void setCredential(DisplayCredentialDetails credential) {

        // Populate fields with credential details
        nicknameField.setText(credential.getNickname());
        usernameField.setText(credential.getUsername());
        passwordField.setText(credential.getPassword());
        notesArea.setText(credential.getNotes());
    }

    @FXML
    public void copyUsername() {
        // Logic to copy username to clipboard
        ClipboardContent content = new ClipboardContent();
        content.putString(usernameField.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void copyPassword() {
        // Logic to copy password to clipboard
        ClipboardContent content = new ClipboardContent();
        content.putString(passwordField.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void editDetails() {
        // Logic to enable editing of details or navigate to an edit page
        nicknameField.setEditable(true);
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        notesArea.setEditable(true);
    }
}
