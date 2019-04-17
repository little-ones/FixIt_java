/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Service;
import Service.DemandeS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author MedAmine
 */
public class ServiceListViewCell extends ListCell<Service>{
      
    
    @FXML
    private GridPane gridservice;

    @FXML
    private ImageView imgservice;

    @FXML
    private Label titreservice;

    @FXML
    private Label dateservice;
    
    private FXMLLoader mLLoader;

    ServiceListViewCell(OffreListController aThis) {
        
    }
    ServiceListViewCell() {
        
    }
    @Override
    protected void updateItem(Service service, boolean empty){
         super.updateItem(service, empty);
         if(empty || service == null) {

            setText(null);
            setGraphic(null);

        }else{
             if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/ServiceItem.fxml"));
         
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    System.err.println("ereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuuur");
                    e.printStackTrace();
                }
            } 
             Image image = new Image( getClass().getResource("/Image/services.png").toExternalForm());
             imgservice.setImage(image);
             imgservice.setClip(new Circle(10,10,70));
             titreservice.setText(service.getTitre());
             dateservice.setText(service.getDate().toString());
             
             
             
             setText(null);
             setGraphic(gridservice);
         
        }
    }
    
}
