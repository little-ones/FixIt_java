/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Service.ServiceList;

import FixIt.Entities.Service;
import FixIt.Offre.OffreList.OffreListController;
import FixIt.Services.ServiceService;
import FixIt.Utils.Routes;
import FixIt.Utils.StaticData;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class ServiceListViewCell extends ListCell<Service> {
    @FXML
    private ImageView image;
    
    ServiceListController Controller ;
    
      @FXML
    private JFXButton Supprimer;

    @FXML
    private JFXButton Modifier;

    
    @FXML
    private GridPane GridPane;
    
    @FXML
    private AnchorPane ServicePane;
    
    @FXML
    private Label categorie;
    
    @FXML
    private Label pro;

    @FXML
    private Label date;
    
    
    private FXMLLoader mLLoader;
    
    
    public ServiceListViewCell(ServiceListController controller){
        Controller=controller;
    }
    
    @Override
    protected void updateItem(Service service, boolean empty) {
        
        super.updateItem(service, empty);
        
        if(empty || service == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
               
                mLLoader = new FXMLLoader(getClass().getResource(Routes.ServiceCell));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                  
                    e.printStackTrace();
                }

            }
           // this.setEditable(true);
            categorie.setText(service.getCategorie());
            date.setText(service.getDate().toString());
            Image Home = new Image(Routes.ServiceCellImg);
            image.setImage(Home);
             
             
             
             
             
             Supprimer.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                 
                Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.ConfirmationServiceSupp));
                    Scene scene = new Scene(p);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 StaticData.ServiceId=""+service.getId();
                
                 
                    stage.setOnHiding((f)->{
                        Controller.loadData();
                        StaticData.ServiceId = "";
                    });
                    
            });
             
             
             Modifier.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                 
                 
                Stage s = new Stage(StageStyle.DECORATED);
                
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.ServiceUpdate));
                    Scene scene = new Scene(p);
                    s.setScene(scene);
                    s.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                 StaticData.ServiceCategorie=""+service.getCategorie();
                 StaticData.ServiceDesc=service.getDescription();
                 StaticData.ServiceId=""+service.getId();
                
                 
                    s.setOnHiding((f)->{
                        Controller.loadData();
                    });
                    
            });
             
            
            //pro.setText(String.valueOf(pro));
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(3.0);
            dropShadow.setOffsetY(3.0);
            dropShadow.setColor(Color.color(0.4, 0.5, 0.5));  

            
            GridPane.setEffect(dropShadow);
            
            setText(null);
            setGraphic(GridPane);
        }
        
        
    }
    
}
