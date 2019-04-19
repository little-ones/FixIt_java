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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 *
 * @author MedAmine
 */
public class ModifierReservationController implements Initializable {

    private static int id;

    /**
     * @return the id
     */
    public static int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }
    @FXML
    private AnchorPane modifierreservation;
    @FXML
    private ImageView handyman;

    @FXML
    private JFXTextField prof;

    @FXML
    private JFXTextField titreoffre;

    @FXML
    private JFXTextField budget;

    @FXML
    private JFXDatePicker datedebut;

    @FXML
    private JFXDatePicker datefin;

    @FXML
    private JFXButton btnmodif;

    @FXML
    private JFXButton btnannuler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Image image = new Image(getClass().getResource("/Image/handy.jpg").toExternalForm());
            handyman.setImage(image);
            Reservation r = new Reservation();
            r = ReservationS.getInstance().show(id);
            Pro p = DemandeS.getInstance().showuser(r.getIdpro());
            System.err.println(r.toString() + "<-----------------------------");
            prof.setText(p.getNom() + "");
            titreoffre.setText(r.getCategorie());
            datedebut.setValue(r.getDatedebut().toLocalDate());
            datefin.setValue(r.getDatefin().toLocalDate());
            budget.setText(r.getBudget() + "");
            btnmodif.setOnAction((event) -> {
                try {
                    if ((datedebut.getValue() != null && datefin.getValue() != null)) {
                        if (((datedebut.getValue().compareTo(datefin.getValue())) <= 0) && (datedebut.getValue().compareTo(Date.valueOf(LocalDate.now()).toLocalDate()) >= 0)) {
                            Reservation r1 = new Reservation();
                            r1.setId(id);
                            r1.setCategorie(titreoffre.getText());
                            r1.setDatedebut(Date.valueOf(datedebut.getValue()));
                            r1.setDatefin(Date.valueOf(datefin.getValue()));
                            r1.setBudget(Float.parseFloat(budget.getText()));

                            System.out.println("********" + r1.toString());
                            ReservationS.getInstance().update(r1);
                        }else{
                            showAlertWithoutHeaderText("Date invalide");
                        }
                    }else{
                        showAlertWithoutHeaderText("Le remplissage du date début et date fin  obligatoire");
                    }
                } catch (Exception ex) {
                    System.out.println("Probleme de modification d'une reservation");
                    ex.printStackTrace();
                }
            });

        });
        btnannuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window stage = modifierreservation.getScene().getWindow();
                stage.hide();
            }
        });
    }
    private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
