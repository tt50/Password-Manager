import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

public class DashboardController {

    @FXML
    private Label usernameLabel;
    private String username;

    // Display Currently Logged in User
    public void setUsername(String username){
        this.username=username;
        usernameLabel.setText(username);
    }

    // Settings Button
    @FXML
    public void settingsButtonClicked(ActionEvent event){
        switchToSettingsScene(event);
    }
    private void switchToSettingsScene(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsScene.fxml"));
                Parent root = loader.load();

                //Pass the username to the settings controller
                SettingsController newSettingsController = loader.getController();
                newSettingsController.setUsername(username);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // Strength Checker Button
    @FXML
    public void strengthCheckerButtonClicked(ActionEvent event){
        switchtoPasswordStrengthChecker(event);
    }
    private void switchtoPasswordStrengthChecker(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordStrengthCheckerScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Password Generator Button
    @FXML
    public void generatorButtonClicked(ActionEvent event){
        switchToPasswordGenerator(event);
    }
    private void switchToPasswordGenerator(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordGeneratorScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logoutClicked(ActionEvent event) {
        // Switch to the login scene
        switchToLoginScene(event);
    }

    private void switchToLoginScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}