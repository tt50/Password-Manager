import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class LoginScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a label
        Label helloLabel = new Label("Hello, World!");

        // Create a button to close the application
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> primaryStage.close());

        // Create a vertical box layout and add the label and button
        VBox vbox = new VBox(helloLabel, closeButton);
        vbox.setSpacing(10); // Set spacing between elements

        // Create a scene with the layout
        Scene scene = new Scene(vbox, 300, 200); // Width: 300, Height: 200

        // Set up the primary stage
        primaryStage.setTitle("JavaFX Test Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
