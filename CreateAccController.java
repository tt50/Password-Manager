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

        boolean isAccountCreated = createAcc.CreateNewAcc(username, password);
        if (isAccountCreated) {
            createAccResultLabel.setText("Account was sucessfully created!");
            switchToLoginScene(event);
        } else {
            createAccResultLabel.setText("Login account creation failed, please try again!");
        }
    }

    @FXML
    public void loginLinkClicked(ActionEvent event) {
        // Switch to the create account scene
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
