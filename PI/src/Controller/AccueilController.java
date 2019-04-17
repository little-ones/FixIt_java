/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

/**
 *
 * @author MedAmine
 */
public class AccueilController implements Initializable {
    @FXML
    private ImageView produitimg;
    @FXML
    private ImageView proimg;

    @FXML
    private ImageView serviceimg;
    @FXML
    private ImageView logo;
    @FXML
    private AnchorPane achropane;

    @FXML
    private JFXHamburger humburger;

    @FXML
    private JFXDrawer navdrawer;
    @FXML
    private AnchorPane MesProffessionnels;
    @FXML
    private AnchorPane MesReservations;
    @FXML
    private JFXButton btnadddemande;
    AnchorPane ListOffrePane;
    AnchorPane MesDemandes;

    public AccueilController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*************************************************************/
        Image imagepro = new Image(getClass().getResource("/Image/pro.png").toExternalForm());
        proimg.setImage(imagepro);
        Image imageservice = new Image(getClass().getResource("/Image/service.png").toExternalForm());
        serviceimg.setImage(imageservice);
        Image imageprod = new Image(getClass().getResource("/Image/produit.png").toExternalForm());
        produitimg.setImage(imageprod);
        /*****************************************************************/
        Image image = new Image(getClass().getResource("/Image/navimg.jpg").toExternalForm());
       Image imalogo = new Image(getClass().getResource("/Image/logo.png").toExternalForm());
        logo.setImage(imalogo);
        HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(humburger);
        burgerTask2.setRate(-1);
        humburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            if (navdrawer.isOpened()) {
                navdrawer.close();
            } else {
                navdrawer.open();
            }
        });
        try {

            VBox box = FXMLLoader.load(getClass().getResource("/View/NavDrawerContent.fxml"));
            AnchorPane AjouterDemande = FXMLLoader.load(getClass().getResource("/View/AddDemande.fxml"));

            MesDemandes = FXMLLoader.load(getClass().getResource("/View/DemandeListView.fxml"));

            navdrawer.setSidePane(box);
            setNode(MesDemandes);
            for (Node node : box.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        switch (node.getAccessibleText()) {

                            case "button1":

                                try {
                                    MesDemandes = FXMLLoader.load(getClass().getResource("/View/DemandeListView.fxml"));
                                    setNode(MesDemandes);
                                } catch (IOException ex) {
                                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                            case "button2":
                                try {
                                    ListOffrePane = FXMLLoader.load(getClass().getResource("/View/OffreListView.fxml"));
                                    setNode(ListOffrePane);
                                } catch (IOException ex) {
                                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case "button3":
                                /*FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/View/AddDemande.fxml"));
                                 Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader.load(), 774, 471);
                                    Stage stage = new Stage();
                                    stage.setTitle("New Window");
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                */
                                try {
                                    MesReservations = FXMLLoader.load(getClass().getResource("/View/ReservationListView.fxml"));
                                    setNode(MesReservations);
                                } catch (IOException ex) {
                                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;

                            case "button4":
                                try {
                                    MesProffessionnels = FXMLLoader.load(getClass().getResource("/View/prolist.fxml"));
                                    setNode(MesProffessionnels);
                                } catch (IOException ex) {
                                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                                }


                                break;

                        }

                    });
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        btnadddemande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/View/AddDemande.fxml"));
                                 Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader.load(), 774, 471);
                                    Stage stage = new Stage();
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

    public void setNode(Node node) {
        achropane.getChildren().clear();
        achropane.getChildren().add((Node) node);
    }

}
