import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class LoginScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginAuthenticationForTextFile auth = new LoginAuthenticationForTextFile(); // Instance of LoginAuthenticationForTextFile
        primaryStage.setTitle("Password Manager Application");

        // Create elements for the login page
        Label titleLabel = new Label("Password Manager");
        Label login_usernameLabel = new Label("Username:");
        Label login_passwordLabel = new Label("Password:");
        TextField login_usernameField = new TextField();
        PasswordField login_passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label resultLabel = new Label();

        // Hyperlink to create a new account
        Hyperlink createAccountLink = new Hyperlink("Create New Account");

        // Login button action
        loginButton.setOnAction(event -> {
            String usernameInput = login_usernameField.getText();
            String passwordInput = login_passwordField.getText();

            // Add login checks here
            try {
                boolean LoginCheck = auth.AuthenticationForTextFile(usernameInput, passwordInput);
                if (LoginCheck) {
                    resultLabel.setText("Login successful!");
                } else {
                    resultLabel.setText("Login failed.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Layout for the login page
        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(titleLabel, login_usernameLabel, login_usernameField, login_passwordLabel, login_passwordField, loginButton, createAccountLink, resultLabel);
        Scene loginScene = new Scene(loginLayout, 800, 600);

        // Create elements for the account creation page
        Label newAccountLabel = new Label("Create a New Account");
        Label createAcc_usernameLabel = new Label("Username:");
        Label createAcc_passwordLabel = new Label("Password:");
        TextField createAcc_usernameField = new TextField();
        PasswordField createAcc_passwordField = new PasswordField();
        Button createAcc_button = new Button("Create Account");

        Label successLabel = new Label();
        Label errorLabel = new Label();

        Hyperlink loginPageLink = new Hyperlink("Back to Login Page");

        // create account button action
        createAcc_button.setOnAction(event -> {
            String CreateAcc_usernameInput = createAcc_usernameField.getText();
            String CreateAcc_passwordInput = createAcc_passwordField.getText();
            try {
                boolean isAccountCreated = CreateNewAccount.CreateNewAcc(CreateAcc_usernameInput, CreateAcc_passwordInput);
                if(isAccountCreated) {
                    // Account was created
                    successLabel.setText("Account successfully created!");
                    successLabel.setStyle("-fx-text-fill: green;");
                    errorLabel.setText(""); // Clear previous message
                } else {
                    // Account already exists
                    errorLabel.setText("Account already exists!");
                    errorLabel.setStyle("-fx-text-fill: red;");
                    successLabel.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("An error occurred while creating the account.");
                errorLabel.setStyle("-fx-text-fill: red;");
                successLabel.setText("");
            }
        });

        // Layout for the new account page
        VBox newAccountLayout = new VBox(10);
        newAccountLayout.getChildren().addAll(newAccountLabel, createAcc_usernameLabel, createAcc_usernameField, createAcc_passwordLabel, createAcc_passwordField, createAcc_button, successLabel, errorLabel, loginPageLink);
        Scene accountCreationScene = new Scene(newAccountLayout, 800, 600);

        // Hyperlink actions to switch scenes
        createAccountLink.setOnAction(e -> primaryStage.setScene(accountCreationScene));
        loginPageLink.setOnAction(e -> primaryStage.setScene(loginScene));

        // Set the initial scene and show the stage
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}

// create dashboard page
/*
//Layout for the dashboard
vBox dashboardLayout = new Vbox(10);
dashboardLayout.
Scene dashboardScene = new Scene(dashboardLayout, 800, 600);

            // Set the initial scene and show the stage
            primaryStage.setScene(loginScene);
            primaryStage.show();
 */