/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Reservation.ReservationList;

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
public class ReservationListViewCell extends ListCell<Reservation> {
   
    ReservationListController Controller ;
         
    private FXMLLoader mLLoader;
    
     @FXML
    private GridPane GridPane;

    @FXML
    private AnchorPane OffrePane;

    @FXML
    private JFXButton Accepter;

    @FXML
    private ImageView image;

    @FXML
    private Label titre;

    @FXML
    private Label DateFin;

    @FXML
    private Label budget;

    @FXML
    private Label categorie;

    @FXML
    private Label DateDebut;

    @FXML
    private Label Etat;

    
     ServiceService s;
             
    public ReservationListViewCell(ReservationListController controller){
        Controller=controller;
       s = new ServiceService();
    }
    
    
    @Override
    protected void updateItem(Reservation reservation, boolean empty) {
        
        super.updateItem(reservation, empty);
        
        if(empty || reservation == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
               
                mLLoader = new FXMLLoader(getClass().getResource(Routes.ReservationCell));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                  
                    e.printStackTrace();
                    
                }

            }
            
            titre.setText(reservation.getCategorie());
            budget.setText(reservation.getBudget()+"");
            
            DateDebut.setText(reservation.getDatedebut().toString());
            DateFin.setText(reservation.getDatefin().toString());
            
            Accepter.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
               Stage stage = new Stage(StageStyle.DECORATED);
                try {
                       StaticData.RservationId = ""+reservation.getId();
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.ResAcceptation));
                    Scene scene = new Scene(p);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 //StaticData.Offreid=""+Offre.getId();
                
                 
                    stage.setOnHiding((f)->{
                        Controller.loadData();
                        StaticData.Offreid = "";
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
