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

public class SettingsController {
    @FXML
    private Label usernameLabel;
    private String username;

    public void initialize() {
        if (usernameLabel != null) {
            String username = UserSession.getInstance().getUsername();
            if (username != null) {
                usernameLabel.setText(username);
            }
        }
    }

    @FXML
    public void dashboardButtonClicked(ActionEvent event){
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
    @FXML
    public void changeToSecurityCheck(ActionEvent event){
        switchToAnswerSecurityScene(event);
    }

    private void switchToAnswerSecurityScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnswerSecurityScene.fxml"));
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

    // Password Change Button
    /*@FXML
    public void PasswordChangeButtonClicked(ActionEvent event){ //NEW
        switchtoPassChangeScene(event);
    }

    private void switchtoPassChangeScene(ActionEvent event){ //NEW
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordChangeScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
