import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasswordStrengthCheckerController {

    @FXML
    private TextField passwordInput;
    @FXML
    private Label passwordStrengthCheckerLabel;

    @FXML
    public void passwordStrengthButtonClicked(ActionEvent event){
        checkPasswordStrength(event);
    }
    @FXML
    private void checkPasswordStrength(ActionEvent event)
    {
        // Receive password from 'passwordInput' field
        String password = passwordInput.getText();

        // If no password is provided...
        if (password.isEmpty()) {
            // Display an error message
            passwordStrengthCheckerLabel.setText("Password field is empty. Please enter a valid password.");
            return;
        }

        // Create an instance of storeCredentials to call the password strength checker
        storeCredentials credentialStorage = new storeCredentials("dummy.txt");

        // Call the password strength checker function from storeCredentials
        String strengthMessage = credentialStorage.checkPasswordStrength(password);

        // Display the result of the strength check to the user
        passwordStrengthCheckerLabel.setText(strengthMessage);
    }

    @FXML
    public void BackToDashClicked(ActionEvent event){
        switchToDashboardScene(event);
    }

    private void switchToDashboardScene(ActionEvent event){
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