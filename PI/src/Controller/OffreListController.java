/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Offre;
import Entity.Service;
import Service.DemandeS;
import Service.OffreS;
import Test.Fixit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.Parent;
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
public class OffreListController implements Initializable {

    @FXML
    private ImageView produitimg;
    @FXML
    private ImageView proimg;

    @FXML
    private ImageView serviceimg;
    @FXML
    private JFXButton btnadddemande;
    @FXML
    private JFXListView servicelist;
    @FXML
    private JFXListView ListOffre;

    @FXML
    private JFXTextField recherchefield;
    @FXML
    private ImageView searchicon;
    private ObservableList<Service> serviceObservableList;
    private ObservableList<Offre> offreObservableList;
    ArrayList<Service> services;
    ArrayList<Offre> offres;

    public OffreListController() {
        services = DemandeS.getInstance().showAllServices();
        offres = OffreS.getInstance().showAll();
        Collections.reverse(services);
        try {

            this.serviceObservableList = FXCollections.observableArrayList();
            serviceObservableList.addAll(services);
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
        try {

            this.offreObservableList = FXCollections.observableArrayList();
            offreObservableList.addAll(OffreS.getInstance().showAll());
        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*-----------------Image---------------------------------*/
        Image imagepro = new Image(getClass().getResource("/Image/pro.png").toExternalForm());
        proimg.setImage(imagepro);
        Image icon = new Image(getClass().getResource("/Image/recherche.png").toExternalForm());
        searchicon.setImage(icon);
        Image imageservice = new Image(getClass().getResource("/Image/service.png").toExternalForm());
        serviceimg.setImage(imageservice);
        Image imageprod = new Image(getClass().getResource("/Image/produit.png").toExternalForm());
        produitimg.setImage(imageprod);
        /*-----------------------------------------------------------*/
        ListOffre.setItems(offreObservableList);
        ListOffre.setCellFactory(new Callback<ListView<Offre>, ListCell<Offre>>() {
            @Override
            public ListCell<Offre> call(ListView<Offre> offreListView) {
                return new OffreListViewCell() {

                };
            }
        });
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
            loadData();
        });
        btnadddemande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/View/AddDemande.fxml"));
                    Scene scene;
                    try {
                        Stage stage = new Stage();
                        URL url = getClass().getResource("/View/AddDemande.fxml");
                        JFXDecorator decorator = new JFXDecorator(stage, (Parent) fxmlLoader.load(url.openStream()), false, false, true);
                        decorator.setCustomMaximize(false);
                        scene = new Scene(decorator, 774, 500);
                        scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());
                        stage.setTitle("Ajout d'une demande");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    System.out.println("Load interface ajout demande");
                }
            }
        });

    }

    public void loadData() {
        offreObservableList.clear();
        List<Offre> result2 = offres.stream()
                .filter(x -> x.getTitre().toLowerCase().contains(recherchefield.getText().toLowerCase()))
                .collect(Collectors.toList());
        if (result2 != null) {
            //System.out.println(result2.);
            offreObservableList.addAll(result2);
            ListOffre.setItems(offreObservableList);
            //ListViewDemande.getStylesheets().add(Routes.HamburgerCss);
            ListOffre.setCellFactory(soffreListView -> new OffreListViewCell());
            ListOffre.refresh();
        } else {
            offreObservableList.addAll(offres);
            ListOffre.setItems(offreObservableList);
            //ListViewDemande.getStylesheets().add(Routes.HamburgerCss);
            ListOffre.setCellFactory(offreListView -> new OffreListViewCell());
            ListOffre.refresh();
        }
    }

}
