/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Pro;
import Entity.Reservation;
import Service.DemandeS;
import Service.ReservationS;
import Test.Fixit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author MedAmine
 */
public class MesreservationListViewCell extends ListCell<Reservation> {

    public MesReservationListView controllerd;

    @FXML
    private GridPane gridpanereservation;

    @FXML
    private JFXButton btnsupp;

    @FXML
    private JFXButton btnmodifier;

    @FXML
    private Label pro;

    @FXML
    private Label categoriprix;

    @FXML
    private Label datedebutfin;

    private FXMLLoader mLLoader;

    public MesreservationListViewCell(MesReservationListView controllerd) {
        this.controllerd = controllerd;
    }

    MesreservationListViewCell() {

    }
public void refresh()
{
    this.controllerd.loadDataReservation();
}
    @Override
    protected void updateItem(Reservation reservation, boolean empty) {
        super.updateItem(reservation, empty);
        
        if (empty || reservation == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/ReservationItem.fxml"));

                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    System.err.println("ereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuuur");
                    e.printStackTrace();
                }
            }
            btnsupp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ReservationS.getInstance().delete(reservation.getId());
                        refresh();
                    } catch (Exception ex) {
                        System.out.println("Probleme de suppression de'une réserrvation");
                    }
                }
            });
            btnmodifier.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader ModifierReservation = new FXMLLoader();
                    ModifierReservation.setLocation(getClass().getResource("/View/ModifierReservation.fxml"));
                    Scene scene;
                    try {
                        Stage stage = new Stage();
                        URL url = getClass().getResource("/View/ModifierReservation.fxml");
                        JFXDecorator decorator = new JFXDecorator(stage, (Parent) ModifierReservation.load(url.openStream()), false, false, true);
                        decorator.setCustomMaximize(false);
                        scene = new Scene(decorator, 774, 500);
                        scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());

                        ModifierReservationController controller = ModifierReservation.<ModifierReservationController>getController();
                        controller.setId(reservation.getId());

                        stage.setTitle("Modification Reservation");
                        stage.setScene(scene);
                        stage.show();
                        stage.setOnHiding((f) -> {
                           refresh();

                            stage.close();
                        });
                        
                    } catch (IOException ex) {
                        System.out.println("Chargement ModifierReservation impossible");
                    }
                }
            });
            Pro p = DemandeS.getInstance().showpro(reservation.getIdpro());
            pro.setText(p.getNom());
            categoriprix.setText(reservation.getCategorie() + " " + reservation.getBudget() + " TND");
            datedebutfin.setText("de " + reservation.getDatedebut() + " jusqu'a " + reservation.getDatefin());
            setText(null);
            setGraphic(gridpanereservation);

        }
    }

    /**
     * @return the controllerd
     */
    public MesReservationListView getControllerd() {
        return controllerd;
    }
}
