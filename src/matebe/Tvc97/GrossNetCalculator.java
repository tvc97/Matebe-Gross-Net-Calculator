package matebe.Tvc97;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Tvc97
 */
public class GrossNetCalculator extends Application {
    
    Parent mainForm;

    public static GrossNetCalculator instance;
    Image matebe_icon = new Image("/res/matebe-logo-icon.png");

    
    @Override
    public void start(Stage primaryStage) {
        try {
            mainForm = FXMLLoader.load(getClass().getResource("/matebe/Tvc97/mainForm.fxml"));
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        primaryStage.setTitle("Gross Net Calculator");
        primaryStage.getIcons().add(matebe_icon);
        primaryStage.setScene(new Scene(mainForm));
        primaryStage.setResizable(false);
        primaryStage.show();
        
        instance = this;
    }
    
    public void openReportForm() {
        try {
            Parent reportForm = FXMLLoader.load(getClass().getResource("/matebe/Tvc97/reportForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Kết quả");
            stage.getIcons().add(matebe_icon);
            stage.setScene(new Scene(reportForm));
            stage.setResizable(false);
            stage.show();
        } catch(Exception exc) {
            
        }
    }
    
    public void openHelpForm() {
        try {
            Parent reportForm = FXMLLoader.load(getClass().getResource("/matebe/Tvc97/helpForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Trợ giúp");
            stage.getIcons().add(matebe_icon);
            stage.setScene(new Scene(reportForm));
            stage.setResizable(false);
            stage.show();
        } catch(Exception exc) {
            
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
