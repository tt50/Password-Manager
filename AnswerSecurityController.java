import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnswerSecurityController implements Initializable {
    @FXML
    private Label resultlabel;
    @FXML
    private TextField answer1field;
    @FXML
    private TextField answer2field;
    @FXML
    private Label question1label;
    @FXML
    private Label question2label;

    private static final String USERNAME_PATTERN = "USER: ";
    private static final String KEY_PATTERN = "KEY: ";
    private static final String QUESTION1_PATTERN = "QUESTION1: ";
    private static final String QUESTION2_PATTERN = "QUESTION2: ";
    private static final String ANSWER1_PATTERN = "ANSWER1: ";
    private static final String ANSWER2_PATTERN = "ANSWER2: ";

    private String Answer1encrypted = "";
    private String Answer2encrypted = "";
    private String searchUser = "milky"; //TEMP FOR TESTING (IF YOU SEE THIS REMIND JACK TO FIX)


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        String encryptedLoginUsername = null;
        try {
            UsernameEncryption EncryptUsername = new UsernameEncryption();
            encryptedLoginUsername = EncryptUsername.EncryptedUsername(searchUser);
        }
        catch (Exception e){
            System.err.println("Problem occurred while initializing AnswerSecurity Scene: " + e.getMessage());
        }
        List<String> results = parseFile("StoredCredentials.txt", encryptedLoginUsername);

        String Question1Text = results.get(1);
        String Question2Text = results.get(3);
        Answer1encrypted = results.get(2);
        Answer2encrypted = results.get(4);
        question1label.setText(Question1Text);
        question2label.setText(Question2Text);
    }

    @FXML
    public void goButtonClicked(ActionEvent event) throws Exception{
        String EnteredAnswer1 = answer1field.getText();
        String EnteredAnswer2 = answer2field.getText();
        SecurityEncryption cryptor = new SecurityEncryption();
        String EncryptedAnswer1 = cryptor.encryptAnswer(EnteredAnswer1);
        String EncryptedAnswer2 = cryptor.encryptAnswer(EnteredAnswer2);

        if(Answer1encrypted.equals(EncryptedAnswer1) && Answer2encrypted.equals(EncryptedAnswer2)){
            resultlabel.setText("Correct answers entered, proceeding to password changer");
            switchToPassChangeScene(event);
        }
        else{
            resultlabel.setText("One or more of your answers are incorrect");
        }
    }

    @FXML
    public void dashButtonClicked(ActionEvent event) {
        switchToDashScene(event);
    }

    private void switchToPassChangeScene(ActionEvent event) {
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

    private void switchToDashScene(ActionEvent event) {
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

    public List<String> parseFile(String fileName, String usernameSearched) {
        List<String> accountInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int usernameStartIndex = line.indexOf(USERNAME_PATTERN);
                int keyStartIndex = line.indexOf(KEY_PATTERN);
                int question1StartIndex = line.indexOf(QUESTION1_PATTERN);
                int question2StartIndex = line.indexOf(QUESTION2_PATTERN);
                int answer1StartIndex = line.indexOf(ANSWER1_PATTERN);
                int answer2StartIndex = line.indexOf(ANSWER2_PATTERN);
                if (usernameStartIndex < keyStartIndex && keyStartIndex < question1StartIndex && question1StartIndex < answer1StartIndex && answer1StartIndex < question2StartIndex && question2StartIndex < answer2StartIndex) {
                    System.out.println("|"+usernameSearched+"|");

                    String username = line.substring(usernameStartIndex + USERNAME_PATTERN.length(), keyStartIndex).replace(",","").trim();
                    String key = line.substring(keyStartIndex + KEY_PATTERN.length(), question1StartIndex).replace(",","").trim();
                    String question1 = line.substring(question1StartIndex + QUESTION1_PATTERN.length(), answer1StartIndex).replace(",","").trim();
                    String answer1 = line.substring(answer1StartIndex + ANSWER1_PATTERN.length(), question2StartIndex).replace(",","").trim();
                    String question2 = line.substring(question2StartIndex + QUESTION2_PATTERN.length(), answer2StartIndex).replace(",","").trim();
                    String answer2 = line.substring(answer2StartIndex + ANSWER2_PATTERN.length()).replace(",","").trim();

                    System.out.println("Parsed Username: |" + username + "|");
                    System.out.println("Parsed Key: |" + key + "|");

                    if (username.equals(usernameSearched)) {
                        System.out.println("username found");

                        accountInfo.add(username);
                        accountInfo.add(question1);
                        accountInfo.add(answer1);
                        accountInfo.add(question2);
                        accountInfo.add(answer2);
                        return accountInfo;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Login Fail, username not found");
        return null; // Username was not found in .txt file
    }

}
