/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Demande.DemandeList;

import FixIt.Entities.Demande;
import FixIt.Reservation.ReservationList.*;
import FixIt.Offre.OffreList.*;
import FixIt.Entities.Offre;
import FixIt.Entities.Reservation;
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
public class DemandeListViewCell extends ListCell<Demande> {
   
    DemandeListController Controller ;
         
    private FXMLLoader mLLoader;
    
    @FXML
    private GridPane GridPane;

    @FXML
    private AnchorPane OffrePane;

    @FXML
    private JFXButton Details;

    @FXML
    private JFXButton Contacter;

    @FXML
    private ImageView image;

    @FXML
    private Label titre;

    @FXML
    private Label client;

    @FXML
    private Label date;

    @FXML
    private Label categorie;

    
     ServiceService service;
             
    public DemandeListViewCell(DemandeListController controller){
       Controller=controller;
       service = new ServiceService();
    }
    
    
    @Override
    protected void updateItem(Demande demande, boolean empty) {
        
        super.updateItem(demande, empty);
        
        if(empty || demande == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
               
                mLLoader = new FXMLLoader(getClass().getResource(Routes.DemandeCell));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                  
                    e.printStackTrace();
                    
                }

            }
            
            titre.setText(demande.getTitre());
            categorie.setText(demande.getCategorie_id()+"");
            
            
            date.setText(demande.getDate().toString());
            
            GridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
               Stage s = new Stage();
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.DemandeDetails));
                    Scene scene = new Scene(p);
                    s.setScene(scene);
                    s.show();
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                s.setOnHiding((f)->{
                        Controller.loadData();
                });
                 
                
                 StaticData.DemandeCategorie=""+service.getCategorie(demande.getCategorie_id()).getCategorie();
                 StaticData.DemandeBudget=""+demande.getBudget();
                 StaticData.DemandeDesc=demande.getDescription();
                 StaticData.DemandeTitre=demande.getTitre();
                 StaticData.Demandeid=""+demande.getId();
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
