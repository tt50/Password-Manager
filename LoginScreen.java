import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Manager");

        // Create a label
        Label TitleLabel = new Label("Password Manager");
        Label UsernameLabel = new Label("Username:");
        Label PasswordLabel = new Label("Password:");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create login button
        Button LoginButton = new Button("Login");

        LoginButton.setOnAction(event -> {
            String UsernameInput = usernameField.getText();
            String PasswordInput = passwordField.getText();

            // add checks here
            /*
            if(){
                resultLabel.setText("Login successful!");
            }else{
                resultLabel.setText("Login failed. Please check your credentials.");
            }
             */
        });

        // Create a layout (VBox) to arrange the elements.
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(UsernameLabel, usernameField, PasswordLabel, passwordField, LoginButton, resultLabel);

        // Create a scene with the layout
        Scene scene = new Scene(vbox, 300, 200); // Width: 300, Height: 200
        primaryStage.setScene(scene);

        // Title of application
        primaryStage.setTitle("Password Manager Application");

        // Show window
        primaryStage.show();
    }
}