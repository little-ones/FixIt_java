/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import com.jfoenix.controls.JFXDecorator;
import static java.awt.Color.white;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MedAmine
 */
public class Fixit extends Application {
    private Stage primaryStage;
    private Parent parentPage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Handyman");
        
        parentPage = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        
       JFXDecorator decorator = new JFXDecorator(primaryStage, parentPage);
        decorator.setCustomMaximize(false);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());
        
        this.primaryStage.setScene(scene);
        this.primaryStage.show(); 
        
    }
    

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
