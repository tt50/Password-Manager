import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordManager extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the Login Scene from FXML
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Scene loginScene = new Scene(root);
            loginScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Password Manager");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}