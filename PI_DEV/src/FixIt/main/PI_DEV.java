/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.main;

import FixIt.Utils.Routes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class PI_DEV extends Application {
  public static  Stage primaryStage;
  public static  Parent parentPage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FixIt !");
        
        parentPage = FXMLLoader.load(getClass().getResource(Routes.Home));
        Scene scene = new Scene(parentPage,1180,670);
        this.primaryStage.setScene(scene );
        this.primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void Launch (){
        
    }
    
}
