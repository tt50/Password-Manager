import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class CreateAccController {
    public static String StrengthCheck(String password) {
        if (password.length() < 8) {
            return "Password should be at least 8 characters.";
        } else if (!(password.matches(".*[A-Z].*") && password.matches(".*[a-z].*"))) {
            return "Password should contain both uppercase and lowercase letters.";
        } else if (!password.matches(".*[0-9].*")) {
            return "Password should contain at least one number.";
        } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return "Password should contain at least one special character.";
        } else {
            return "good";
        }
    }

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField securityQuestion1Field;
    @FXML
    private TextField securityAnswer1Field;
    @FXML
    private TextField securityQuestion2Field;
    @FXML
    private TextField securityAnswer2Field;
    @FXML
    private Label createAccResultLabel;
    @FXML
    private Hyperlink loginLink;

    private final CreateNewAccount createAcc = new CreateNewAccount();

    @FXML
    public void confirmButtonClicked(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String securityQuestion1 = securityQuestion1Field.getText();
        String securityAnswer1 = securityAnswer1Field.getText();
        String securityQuestion2 = securityQuestion2Field.getText();
        String securityAnswer2 = securityAnswer2Field.getText();

        // Check password strength
        String passwordStrengthMessage = StrengthCheck(password);
        if (!passwordStrengthMessage.equals("good")) {
            createAccResultLabel.setText(passwordStrengthMessage);
            return; // Stop the account creation process
        }

        // Validate security questions and answers
        if (securityQuestion1.isBlank() || securityAnswer1.isBlank() ||
                securityQuestion2.isBlank() || securityAnswer2.isBlank()) {
            createAccResultLabel.setText("Please provide both security questions and answers.");
            return;
        }

        // Proceed with account creation
        boolean isAccountCreated = createAcc.CreateNewAcc(username, password,
                securityQuestion1, securityAnswer1,
                securityQuestion2, securityAnswer2);
        if (isAccountCreated) {
            createAccResultLabel.setText("Account was successfully created!");
            switchToLoginScene(event);
        } else {
            createAccResultLabel.setText("Account creation failed, please try again!");
        }
    }

    @FXML
    public void loginLinkClicked(ActionEvent event) {
        // Switch to the login scene
        switchToLoginScene(event);
    }

    private void switchToLoginScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
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
