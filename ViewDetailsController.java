import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
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
    private DisplayCredentialDetails credential;

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

    @FXML
    public void switchToEditScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScene.fxml"));
            Parent editRoot = loader.load();

            DashboardController dashboardController = (DashboardController) ((Node) event.getSource()).getScene().getUserData();
            Pane contentPane = dashboardController.getContentPane();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(editRoot);

            EditController editController = loader.getController();
            editController.setCredential(this.credential);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}