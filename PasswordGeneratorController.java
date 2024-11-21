import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.security.SecureRandom;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.Button;

public class PasswordGeneratorController{
    @FXML
    private Label generatorLabel;
    @FXML
    private TextField generatorInput;
    @FXML
    private Button copyButton;
    private String generatedPassword = "";

    @FXML
    public void BackToDashClicked(ActionEvent event){
        switchToDashboardScene(event);
    }

    private void switchToDashboardScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void passwordGeneratorButtonClicked(ActionEvent event){
        passwordGenerator();
    }

    private void passwordGenerator() {
        int defaultLength = 12;
        int length;

        try {
            length = Integer.parseInt(generatorInput.getText());
            if (length <= 0) {
                throw new NumberFormatException("Password length must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            generatorLabel.setText("Invalid input! Using default length of " + defaultLength + ".");
            length = defaultLength;
        }

        // Generate and store the password
        generatedPassword = generateSecurePassword(length);

        // Display the password
        generatorLabel.setText("Generated Password: " + generatedPassword);

        // Enable the copy button
        copyButton.setDisable(false);

        // Debugging: Log the password
        System.out.println("Generated Password: " + generatedPassword);
    }


    private String generateSecurePassword(int length) {
        final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        final String digits = "0123456789";
        final String specialChars = "!@#$%^&*()-_=+<>?";
        final String allChars = upperCase + lowerCase + digits + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure password contains at least one character from each category
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Fill the rest of the password with random characters
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the password to ensure randomness
        return shuffleString(password.toString(), random);
    }

    private String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int j = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
    @FXML
    public void copyToClipboard() {
        if (!generatedPassword.isEmpty()) {
            // Copy the password to the clipboard
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(generatedPassword);
            clipboard.setContent(content);

            generatorLabel.setText("Password copied to clipboard!");
        } else {
            generatorLabel.setText("No password to copy! Please generate one.");
        }

        // Debugging: Print the current password
        System.out.println("Copy attempt with password: " + generatedPassword);
    }

}
