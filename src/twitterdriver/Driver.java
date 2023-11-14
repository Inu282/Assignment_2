package twitterdriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

        AdminUI adminPanel=AdminUI.getInstance();
        HBox adminView=adminPanel.getAdminPanel();
        
        Scene scene=new Scene(adminView, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Control Panel");
        primaryStage.show();
    }        

    
    
}