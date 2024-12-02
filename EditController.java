import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class EditController {

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextArea notesArea;

    public void setCredential(DisplayCredentialDetails credential) {
        nicknameField.setText(credential.getNickname());
        usernameField.setText(credential.getUsername());
        passwordField.setText(credential.getPassword());
        notesArea.setText(credential.getNotes());
    }

    @FXML
    public void saveDetails(){
        // call method to update file
    }

    public void intialize() {
        nicknameField.setEditable(true);
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        notesArea.setEditable(true);
    }
}