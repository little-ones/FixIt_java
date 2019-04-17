/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Offre.OffreList;

import FixIt.Entities.Offre;
import FixIt.Service.ServiceList.*;
import FixIt.Entities.Service;
import FixIt.Services.OffreService;
import FixIt.Services.ServiceService;
import FixIt.Utils.Routes;
import FixIt.Utils.StaticData;
import FixIt.main.HomeController;
import FixIt.main.PI_DEV;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
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
public class OffreListViewCell extends ListCell<Offre> {
   
    OffreListController Controller ;
         
    private FXMLLoader mLLoader;
    
     @FXML
    private GridPane GridPane;

    @FXML
    private AnchorPane OffrePane;

    @FXML
    private JFXButton Supprimer;

    @FXML
    private JFXButton Modifier;

    @FXML
    private ImageView image;

    @FXML
    private Label titre;

    @FXML
    private Label pro;

    @FXML
    private Label date;

    @FXML
    private Label budget;

    @FXML
    private Label categorie;
    
     ServiceService s;
             
    public OffreListViewCell(OffreListController controller){
        Controller=controller;
       s = new ServiceService();
    }
    
    
    @Override
    protected void updateItem(Offre Offre, boolean empty) {
        
        super.updateItem(Offre, empty);
        
        if(empty || Offre == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
               
                mLLoader = new FXMLLoader(getClass().getResource(Routes.OffreCell));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                  
                    e.printStackTrace();
                    
                }

            }
            
            titre.setText(Offre.getTitre());
            budget.setText(Offre.getBudget()+"");
            categorie.setText(s.getCategorie(Offre.getCategorie()).getCategorie());
            date.setText(Offre.getDate().toString());
            
            Supprimer.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
               Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.ConfirmationOffreSupp));
                    Scene scene = new Scene(p);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 StaticData.Offreid=""+Offre.getId();
                
                 
                    stage.setOnHiding((f)->{
                        Controller.loadData();
                        StaticData.Offreid = "";
                    });
                 
            });
             Modifier.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                 Stage s = new Stage();
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.OffreUpdate));
                    Scene scene = new Scene(p);
                    s.setScene(scene);
                    s.show();
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                s.setOnHiding((f)->{
                        Controller.loadData();
                });
                 
                
                 StaticData.OffreCategorie=""+Offre.getCategorie();
                 StaticData.OffreBudget=""+Offre.getBudget();
                 StaticData.OffreDesc=Offre.getDescription();
                 StaticData.OffreTitre=Offre.getTitre();
                 StaticData.Offreid=""+Offre.getId();
                 System.out.println(StaticData.Offreid);
                 
                 
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
