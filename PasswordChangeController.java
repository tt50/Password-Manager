import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class PasswordChangeController {
    @FXML
    private TextField username; // Username input for security check
    @FXML
    private PasswordField oldpassword;
    @FXML
    private PasswordField newpassword;
    @FXML
    private Label PassChangeResult;

    /*
    @FXML
    private Label usernameLabel;
    private String usernameDisplay = UserSession.getInstance().getUsername();

    // Displays currently logged in username
    public void setUsername(String username) {
        this.usernameDisplay = username;
        usernameLabel.setText(username);
    }

     */

    @FXML
    public void PasswordChangeButtonClicked(ActionEvent event) throws Exception{
        String user = username.getText();
        String oldpass = oldpassword.getText();
        String newpass = newpassword.getText();

        Boolean result = PasswordChanger.PasswordChange(user, oldpass, newpass);
        if(result == true){
            PassChangeResult.setText("Password Changed");
        }
        else if(result == false){
            PassChangeResult.setText("Couldn't Change Password");
        }
        else{
            PassChangeResult.setText("Something went wrong");
        }
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

//PASSWORD: aB99999999!
