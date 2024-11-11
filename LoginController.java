import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginResultLabel;
    @FXML
    private Hyperlink createAccountLink;

    private final LoginAuthenticationForTextFile auth = new LoginAuthenticationForTextFile();

    @FXML
    public void loginButtonClicked(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAuthenticated = auth.AuthenticationForTextFile(username, password);
        if (isAuthenticated) {
            loginResultLabel.setText("Login successful!");
            switchToDashboardScene(event);
        } else {
            loginResultLabel.setText("Login failed. Try again.");
        }
    }

    @FXML
    public void createAccLinkClicked(ActionEvent event) {
        // Switch to the create account scene
        switchToCreateAccScene(event);
    }

    private void switchToDashboardScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScene.fxml"));
            Stage stage = (Stage) loginResultLabel.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToCreateAccScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccScene.fxml"));
            Stage stage = (Stage) loginResultLabel.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
