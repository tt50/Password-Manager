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

    public void setCredential(CredentialDetails credential) {
        if(credential != null) {
            this.credential = credential;
            nicknameLabel.setText(credential.getNickname());
            usernameLabel.setText(credential.getUsername());
            passwordLabel.setText(credential.getPassword());
            notesLabel.setText(credential.getNotes());
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
        DashboardController dashboardController = (DashboardController) ((Node) event.getSource()).getScene().getUserData();
        if (dashboardController != null && credential != null) {
            //System.out.println("Switching to edit view");
            dashboardController.displayEdit(credential);
        } else {
            System.out.println("Error: DashboardController or credential is null");
        }
    }
}