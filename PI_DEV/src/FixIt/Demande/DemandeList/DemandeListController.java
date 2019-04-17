/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Demande.DemandeList;

import FixIt.Entities.Demande;
import FixIt.Entities.Reservation;
import FixIt.Reservation.ReservationList.ReservationListViewCell;
import FixIt.Services.DemandeService;
import FixIt.Services.ReservationService;
import FixIt.Utils.Routes;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DemandeListController implements Initializable {

    
    @FXML
    private ImageView HomeImg;

    @FXML
    private JFXListView<Demande> Demandes;
    
    DemandeService DemandeService ;
    
    private ObservableList<Demande> ServiceObservableList;
    
     public DemandeListController(){
         
        DemandeService = DemandeService.getInstance();
        ServiceObservableList = DemandeService.showAll();
       
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Demandes.setItems(ServiceObservableList);
        Demandes.getStylesheets().add(Routes.HamburgerCss);
        Demandes.setCellFactory( serviceListView -> new DemandeListViewCell(this));   
        Demandes.refresh();
    }    
      public void loadData(){
        ServiceObservableList = DemandeService.showAll();
        Demandes.setItems(ServiceObservableList);
        Demandes.getStylesheets().add(Routes.HamburgerCss);
        Demandes.setCellFactory( serviceListView -> new DemandeListViewCell(this));   
        Demandes.refresh();
    }
}
