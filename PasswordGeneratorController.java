import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PasswordGeneratorController{
    @FXML
    private Label generatorLabel;
    @FXML
    private TextField generatorInput;

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
        passwordGenerator(event);
    }

    private void passwordGenerator(ActionEvent event){

    }
}
