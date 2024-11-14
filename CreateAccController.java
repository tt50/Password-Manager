import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            return "Password should be at least 8 characters";
        } else if (!(password.matches(".*[A-Z].*") && password.matches(".*[a-z].*"))) {
            return "Password should contain both uppercase and lowercase letters.";
        } if (!password.matches(".*[0-9].*")){
            return "Password should contain at least one number.";
        } if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return "Password should contain at least one special character.";
        }
        else {
            return "good";
        }
    }
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label createAccResultLabel;
    @FXML
    private Hyperlink loginLink;

    private final CreateNewAccount createAcc = new CreateNewAccount();

    @FXML
    public void confirmButtonClicked(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check password strength
        String passwordStrengthMessage = StrengthCheck(password);
        if (!passwordStrengthMessage.equals("good")) {
            // Display the password strength message if it doesn't meet requirements
            createAccResultLabel.setText(passwordStrengthMessage);
            return; // Stop the account creation process
        }

        // Proceed with account creation if password meets all requirements
        boolean isAccountCreated = createAcc.CreateNewAcc(username, password);
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
            Stage stage = (Stage) createAccResultLabel.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
