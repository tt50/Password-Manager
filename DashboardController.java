import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.util.List;

public class DashboardController {
    @FXML
    private Label usernameLabel;
    private String username;

    // Settings Button
    @FXML
    public void settingsButtonClicked(ActionEvent event) {
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
    public void strengthCheckerButtonClicked(ActionEvent event) {
        switchtoPasswordStrengthChecker(event);
    }

    private void switchtoPasswordStrengthChecker(ActionEvent event) {
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
    public void generatorButtonClicked(ActionEvent event) {
        switchToPasswordGenerator(event);
    }

    private void switchToPasswordGenerator(ActionEvent event) {
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
    private ListView<CredentialDetails> credentialsListView;

    @FXML
    private Label detailNicknameLabel;

    @FXML
    private Label detailUsernameLabel;

    @FXML
    private Label detailPasswordLabel;

    @FXML
    private TextArea detailNotesTextArea;

    private final ObservableList<CredentialDetails> credentialsList = FXCollections.observableArrayList();
    private final ReadUserCredential readUserCredential = new ReadUserCredential();

    public void initialize() throws Exception {
        String username = UserSession.getInstance().getUsername();
        if (username == null) {
            throw new IllegalStateException("Invalid user session");
        }
        usernameLabel.setText(username);

        UsernameEncryption userIDEncrypt = new UsernameEncryption();
        String encryptedUserID = userIDEncrypt.EncryptedUsername(username);

        //credentialsList.add(new DisplayCredentialDetails("Temple", "tun58761", "password123", "University account."));
        //credentialsList.add(new DisplayCredentialDetails("Github", "tt50", "eeTesting123!?", "GitHub account."));

        // Read credentials from UserCredentials.txt, find all that are associated with logged in UserID, add to the credential list,
        ObservableList<CredentialDetails> displayList = FXCollections.observableArrayList();
        List<CredentialDetails> credentials = readUserCredential.parseFileForUser("UserCredentials.txt", encryptedUserID);
        if(credentials == null){
            System.out.println("empty");
        }

        // add nicknames and usernames from the credential list to the display list.
        for (CredentialDetails credential : credentials) {
            displayList.add(credential);
        }
        credentialsListView.setItems(displayList);

        // get key
        LoginAuthenticationForTextFile Key = new LoginAuthenticationForTextFile();
        List<String> AccountInfo = Key.parseFile("StoredCredentials.txt", encryptedUserID);
        String AssociatedKey = AccountInfo.get(2);

        // Set custom cell factory
        credentialsListView.setCellFactory(param -> new ListCell<CredentialDetails>() {
            @Override
            protected void updateItem(CredentialDetails item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    VBox vbox = new VBox(5);
                    Label nicknameLabel = null;
                    Label usernameLabel = null;
                    try {
                         nicknameLabel = new Label(PasswordDecryption.decryptPassword(item.getNickname(), AssociatedKey));
                         usernameLabel = new Label(PasswordDecryption.decryptPassword(item.getUsername(), AssociatedKey));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    vbox.getChildren().addAll(nicknameLabel, usernameLabel);
                    setGraphic(vbox);
                } else {
                    setGraphic(null);
                }
            }
        });

        // Lister for if an item on the CredentialDisplay is clicked
        credentialsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayDetails(newValue);
                System.out.println("listen");
            }else{
                System.out.println("not listen");
            }
        });
    }

    @FXML
    private AnchorPane detailsPanel;
    @FXML
    private AnchorPane viewDetailsContainer;

    @FXML
    private AnchorPane addCredentialPanel;
    @FXML
    private AnchorPane addCredentialContainer;

    // open ViewDetails.fxml and displays onto the detailspanel within dashboardscene.fxml.
    private void displayDetails(CredentialDetails selectedCredential) {
        addCredentialPanel.setVisible(false);
        detailsPanel.setVisible(false);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDetails.fxml"));
            Parent root = loader.load();

            ViewDetailsController controller = loader.getController();
            controller.setCredential(selectedCredential);  // Pass the CredentialDetails

            controller.setViewDetailsContainer(viewDetailsContainer); // Pass viewDetails container

            viewDetailsContainer.getChildren().clear();
            viewDetailsContainer.getChildren().add(root);

            detailsPanel.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // searches for selected nickname and username item, then returns the associated credential details.
    private CredentialDetails getCredentialDetails(CredentialDetails selectedCredential) {
        for (CredentialDetails credential : credentialsList) {
            if (credential.getNickname().equals(selectedCredential.getNickname()) &&
                    credential.getUsername().equals(selectedCredential.getUsername())) {
                return credential;
            }
        }
        return null;
    }

    // Display the add credential panel
    @FXML
    private void addNewCredentialButton() {
        detailsPanel.setVisible(false);
        addCredentialPanel.setVisible(true);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCredentials.fxml"));
            Parent root = loader.load();
            AddCredentialController controller = loader.getController();
            addCredentialContainer.getChildren().clear();
            addCredentialContainer.getChildren().add(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this pane is shared with editscene.fxml
    public Pane getContentPane() {
        return viewDetailsContainer;
    }

    @FXML
    private AnchorPane editCredentialPanel;
    @FXML
    private AnchorPane editCredentialContainer;

    public void displayEdit(CredentialDetails selectedCredential) {
        addCredentialPanel.setVisible(false);
        detailsPanel.setVisible(false);
        editCredentialPanel.setVisible(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScene.fxml"));
            Parent root = loader.load();

            EditController controller = loader.getController();
            controller.setCredential(selectedCredential);

            editCredentialContainer.getChildren().clear();
            editCredentialContainer.getChildren().add(root);

            editCredentialPanel.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}