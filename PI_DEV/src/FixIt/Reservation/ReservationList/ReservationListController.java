/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Reservation.ReservationList;

import FixIt.Entities.Offre;
import FixIt.Entities.Reservation;
import FixIt.Offre.OffreList.OffreListViewCell;
import FixIt.Services.OffreService;
import FixIt.Services.ReservationService;
import FixIt.Utils.Routes;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationListController implements Initializable {
    @FXML
    private JFXListView<Reservation> Reservations; 
    
    ReservationService Reservation;
    
    private ObservableList<Reservation> ServiceObservableList;
    
     public ReservationListController(){
         
        Reservation = ReservationService.getInstance();
        ServiceObservableList = Reservation.showAll();
       
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reservations.setItems(ServiceObservableList);
        Reservations.getStylesheets().add(Routes.HamburgerCss);
        Reservations.setCellFactory( serviceListView -> new ReservationListViewCell(this));   
        Reservations.refresh();
    }    
    public void loadData(){
        ServiceObservableList = Reservation.showAll();
        Reservations.setItems(ServiceObservableList);
        Reservations.getStylesheets().add(Routes.HamburgerCss);
        Reservations.setCellFactory( serviceListView -> new ReservationListViewCell(this));   
        Reservations.refresh();
    }
    
}
