import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import java.io.IOException;

public class SceneController extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
