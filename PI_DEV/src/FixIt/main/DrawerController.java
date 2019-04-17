/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.main;

import FixIt.Utils.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class DrawerController implements Initializable {
    
    @FXML
    private ImageView drawerImg;

    @FXML
    private JFXButton Home;

    @FXML
    private JFXButton statistic;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton exit;

    @FXML
    void Home(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void statistique(ActionEvent event) {

        Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.Charts));
                    Scene scene = new Scene(p);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(DrawerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        drawerImg.setImage(new Image(Routes.DrawerImg));
        
    }

}
