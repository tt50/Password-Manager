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

    public void setCredential(CredentialDetails credential) {
        nicknameField.setText(credential.getNickname());
        usernameField.setText(credential.getUsername());
        passwordField.setText(credential.getPassword());
        notesArea.setText(credential.getNotes());
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
    public void initialize() {
        nicknameField.setEditable(true);
        usernameField.setEditable(true);
        passwordField.setEditable(true);
        notesArea.setEditable(true);
    }
}