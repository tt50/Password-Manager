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
import javafx.scene.Node;

public class SettingsController {
    @FXML
    private Label userLabel;
    private String username;

    // Display Currently Logged in User
    public void setUsername(String username){
        userLabel.setText(username);
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

    // Password Change Button
    @FXML
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
    }
}
