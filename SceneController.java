import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import javafx.fxml.FXML;

public class SceneController extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Fields necessary for password strength-checking
    // To save the input password
    @FXML
    private TextField passwordInput;

    // To save the feedback returned by the program
    @FXML
    private Label strengthFeedback;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        this.scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void switchToLoginScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void switchToCreateAccScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CreateAccScene.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboardScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DashboardScene.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettingsScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SettingsScene.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Function to check the strength of the provided password and set the label in the application
    public void checkPasswordStrength()
    {
        // Receive the password from the 'passwordInput' field
        String password = passwordInput.getText();

        // If no password is provided...
        if(password.isEmpty())
        {
            // Display an error message
            strengthFeedback.setText("Password is empty. Please enter a valid password.");
            // Add an empty return so that the function doesn't keep running
            return;
        }

        // Create an instance of storeCredentials to be able to call the password strength checker
        storeCredentials credentialsStored = new storeCredentials("dummy.txt");

        // Call the password strength checker function from storeCredentials
        String messageStrength = credentialsStored.checkPasswordStrength(password);

        // Display the result of the strength check to the user
        strengthFeedback.setText(messageStrength);
    }
}
