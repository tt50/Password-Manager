import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

public class DashboardController {
    @FXML
    private Label usernameLabel;
    private String username;

    // Display Currently Logged in User
    public void setUsername(String username) throws Exception {
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

    @FXML
    private ListView<CredentialDisplay> credentialsListView;

    public void initialize() {
        // list of credentials, hardcoded test examples
        ObservableList<CredentialDisplay> credentialsList = FXCollections.observableArrayList();
        credentialsList.add(new CredentialDisplay("Temple", "tun58761"));
        credentialsList.add(new CredentialDisplay("Github", "tt50"));
        credentialsList.add(new CredentialDisplay("1", "1"));
        credentialsList.add(new CredentialDisplay("2", "2"));
        credentialsList.add(new CredentialDisplay("3", "3"));
        credentialsList.add(new CredentialDisplay("4", "4"));
        credentialsList.add(new CredentialDisplay("5", "5"));
        credentialsList.add(new CredentialDisplay("6", "6"));

        credentialsListView.setItems(credentialsList);

        // set a custom cell factory
        credentialsListView.setCellFactory(param -> new ListCell<CredentialDisplay>() {
            @Override
            protected void updateItem(CredentialDisplay item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    // each credential detail is displayed in a vbox
                    VBox vbox = new VBox(10);
                    Label nicknameLabel = new Label(item.getNickname());
                    Label usernameLabel = new Label(item.getUsername());
                    vbox.getChildren().addAll(nicknameLabel, usernameLabel);
                    setGraphic(vbox);
                } else {
                    setGraphic(null);
                }
            }
        });

    }

    // set up reading from user credential file, to add to credentialsList

    // set up add new credentials
    // set up deleting credentials

    // set up detailsPanel
    /*
    detail panel will display nickname, username, password, notes
    and include clipboard buttons and a edit button
     */

    @FXML
    private AnchorPane detailsPanel;
}