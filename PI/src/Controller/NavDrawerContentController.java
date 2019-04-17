/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author MedAmine
 */
public class NavDrawerContentController implements Initializable{
    @FXML
    private ImageView navimg;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
          Image image = new Image( getClass().getResource("/Image/navimg.jpg").toExternalForm());
          navimg.setImage(image);
         });
    }
    
}
