/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Reservation;
import Entity.Service;
import Service.DemandeS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author MedAmine
 */
public class DemandeListController implements Initializable {

    private ObservableList<Demande> demandeObservableList;
    private ObservableList<Service> serviceObservableList;
    @FXML
    private ListView ListViewDemande;

    @FXML
    private ListView servicelist;
    @FXML
    private ImageView produitimg;
    @FXML
    private ImageView proimg;

    @FXML
    private ImageView serviceimg;
    @FXML
    private JFXTextField recherchefield;

    @FXML
    private ImageView searchicon;
    ArrayList<Demande> mesdemandes;

    public DemandeListController() {
        ArrayList<Service> services = DemandeS.getInstance().showAllServices();
        mesdemandes = DemandeS.getInstance().showAll();
        Collections.reverse(services);
        try {

            this.serviceObservableList = FXCollections.observableArrayList();
            serviceObservableList.addAll(services);
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
        try {

            this.demandeObservableList = FXCollections.observableArrayList();
            demandeObservableList.addAll(mesdemandes);
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image icon = new Image(getClass().getResource("/Image/recherche.png").toExternalForm());
        searchicon.setImage(icon);
        /*----------------------------------------------------------------*/
        ListViewDemande.setItems(demandeObservableList);
        ListViewDemande.setCellFactory(demandeListView -> new DemandeListViewCell(this));

        servicelist.setItems(serviceObservableList);
        servicelist.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {
            @Override
            public ListCell<Service> call(ListView<Service> demandeListView) {
                return new ServiceListViewCell() {

                };
            }
        });
        recherchefield.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            loadDataRecherche();
        });

    }

    public void loadData() {
        demandeObservableList.clear();
        demandeObservableList.addAll(DemandeS.getInstance().showAll());
        ListViewDemande.setItems(demandeObservableList);
        //ListViewDemande.getStylesheets().add(Routes.HamburgerCss);
        ListViewDemande.setCellFactory(demandeListView -> new DemandeListViewCell(this));
        ListViewDemande.refresh();
    }

    public void edit(int index) {
        ListViewDemande.edit(index);
    }

    public void delete(int index) {
        ListViewDemande.getItems().remove(index);
    }

    public void loadDataRecherche() {
        demandeObservableList.clear();
        List<Demande> result2 = mesdemandes.stream()
                .filter(x -> x.getTitre().toLowerCase().contains(recherchefield.getText().toLowerCase()))
                .collect(Collectors.toList());
        if (result2 != null) {

            demandeObservableList.addAll(result2);
            ListViewDemande.setItems(demandeObservableList);

            ListViewDemande.setCellFactory(demandeListView -> new DemandeListViewCell());
            ListViewDemande.refresh();
        } else {
            demandeObservableList.addAll(mesdemandes);
            ListViewDemande.setItems(demandeObservableList);

            ListViewDemande.setCellFactory(demandeListView -> new DemandeListViewCell());
            ListViewDemande.refresh();
        }
    }
}
