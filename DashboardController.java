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

    // Settings Button
    @FXML
    public void settingsButtonClicked(ActionEvent event){
        switchToSettingsScene(event);
    }
    private void switchToSettingsScene(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsScene.fxml"));
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
    private ListView<CredentialDisplay> credentialsListView;

    @FXML
    private AnchorPane detailsPanel;

    @FXML
    private Label detailNicknameLabel;

    @FXML
    private Label detailUsernameLabel;

    @FXML
    private Label detailPasswordLabel;

    @FXML
    private TextArea detailNotesTextArea;

    private final ObservableList<DisplayCredentialDetails> credentialsList = FXCollections.observableArrayList();

    public void initialize() {
        if (usernameLabel != null) {
            String username = UserSession.getInstance().getUsername();
            if (username != null) {
                usernameLabel.setText(username);
            }
        }

        credentialsList.add(new DisplayCredentialDetails("Temple", "tun58761", "password123", "University account."));
        credentialsList.add(new DisplayCredentialDetails("Github", "tt50", "securepass456", "GitHub development account."));
        ObservableList<CredentialDisplay> displayList = FXCollections.observableArrayList();
        for (DisplayCredentialDetails credential : credentialsList) {
            displayList.add(new CredentialDisplay(credential.getNickname(), credential.getUsername()));
        }
        credentialsListView.setItems(displayList);

        // Set custom cell factory
        credentialsListView.setCellFactory(param -> new ListCell<CredentialDisplay>() {
            @Override
            protected void updateItem(CredentialDisplay item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    VBox vbox = new VBox(5);
                    Label nicknameLabel = new Label(item.getNickname());
                    Label usernameLabel = new Label(item.getUsername());
                    vbox.getChildren().addAll(nicknameLabel, usernameLabel);
                    setGraphic(vbox);
                } else {
                    setGraphic(null);
                }
            }
        });

        credentialsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayDetails(newValue);
            }
        });

        detailsPanel.setVisible(false);
    }

    private void displayDetails(CredentialDisplay selectedCredential) {
        for (DisplayCredentialDetails credential : credentialsList) {
            if (credential.getNickname().equals(selectedCredential.getNickname()) &&
                    credential.getUsername().equals(selectedCredential.getUsername())) {

                // Populate details
                detailNicknameLabel.setText(credential.getNickname());
                detailUsernameLabel.setText(credential.getUsername());
                detailPasswordLabel.setText(credential.getPassword());
                detailNotesTextArea.setText(credential.getNotes());

                // Make the details panel visible
                detailsPanel.setVisible(true);
                return;
            }
        }
    }

}