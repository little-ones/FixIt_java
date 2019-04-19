/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Offre;
import Entity.Reservation;
import Entity.Service;
import Service.DemandeS;
import Service.ReservationS;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 *
 * @author MedAmine
 */
public class MesReservationListView implements Initializable {

    @FXML
    private JFXListView Listreservation;

    @FXML
    private JFXListView servicelist;
    @FXML
    private JFXTextField recherchefield;

    @FXML
    private ImageView searchicon;

    private ObservableList<Reservation> reservationObservableList;
    private ObservableList<Service> serviceObservableList;
    ArrayList<Reservation> mesreservations;

    public MesReservationListView() {
        mesreservations = ReservationS.getInstance().showAll();
        try {

            this.reservationObservableList = FXCollections.observableArrayList();

            reservationObservableList.addAll(mesreservations);
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des reservations ");
        }
        ArrayList<Service> services = DemandeS.getInstance().showAllServices();
        Collections.sort(services, new Comparator<Service>() {
            public int compare(Service o1, Service o2) {
                if (o1.getDate() == null || o2.getDate() == null) {
                    return 0;
                }
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        Collections.reverse(services);

        try {

            this.serviceObservableList = FXCollections.observableArrayList();
            serviceObservableList.addAll(services);
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Image icon = new Image(getClass().getResource("/Image/recherche.png").toExternalForm());
        searchicon.setImage(icon);
        Listreservation.setItems(reservationObservableList);
        Listreservation.setCellFactory(reservationListView -> new MesreservationListViewCell(this));

        servicelist.setItems(serviceObservableList);
        servicelist.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {
            @Override
            public ListCell<Service> call(ListView<Service> serviceListView) {
                return new ServiceListViewCell() {

                };
            }
        });
        recherchefield.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            loadData();
        });
    }

    public void loadData() {
        reservationObservableList.clear();
        List<Reservation> result2 = mesreservations.stream()
                .filter(x -> x.getCategorie().toLowerCase().contains(recherchefield.getText().toLowerCase()))
                .collect(Collectors.toList());
        if (result2 != null) {

            reservationObservableList.addAll(result2);
            Listreservation.setItems(reservationObservableList);

            Listreservation.setCellFactory(reservationListView -> new MesreservationListViewCell());
            Listreservation.refresh();
        } else {
            reservationObservableList.addAll(mesreservations);
            Listreservation.setItems(reservationObservableList);

            Listreservation.setCellFactory(reservationListView -> new MesreservationListViewCell());
            Listreservation.refresh();
        }
    }
    public void loadDataReservation() {
        reservationObservableList.clear();
        reservationObservableList.addAll(ReservationS.getInstance().showAll());
        Listreservation.setItems(reservationObservableList);
        
        Listreservation.setCellFactory(demandeListView -> new MesreservationListViewCell(this));
        Listreservation.refresh();
    }

}
