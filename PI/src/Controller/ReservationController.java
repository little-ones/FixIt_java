/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Offre;
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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author MedAmine
 */
public class ReservationController implements Initializable {

    private static Offre offre;

    /**
     * @param aOffre the offre to set
     */
    public static void setOffre(Offre aOffre) {
        offre = aOffre;
    }
    private AnchorPane rootLayout;
    private Stage primaryStage;
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
    private JFXButton btnreserver;

    @FXML
    private JFXButton btnannuler;
    @FXML
    private AnchorPane Reservationoffre;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Image image = new Image(getClass().getResource("/Image/handyman.png").toExternalForm());
            handyman.setImage(image);
            prof.setText(offre.getPro_id() + "");
            titreoffre.setText(offre.getTitre());
            budget.setText(offre.getBudget() + "");

        });
        
        btnreserver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if ((datedebut.getValue() != null && datefin.getValue() != null)) {
                        if(((datedebut.getValue().compareTo(datefin.getValue()))<=0 ) && (datedebut.getValue().compareTo(Date.valueOf(LocalDate.now()).toLocalDate())>=0) )
                        {
                        Reservation r = new Reservation();
                        r.setIdpro(offre.getPro_id());
                        r.setIdclient(1);
                        r.setCategorie(titreoffre.getText());
                        r.setBudget(Float.parseFloat(budget.getText()));
                        r.setDatedebut(Date.valueOf(datedebut.getValue()));
                        r.setDatefin(Date.valueOf(datefin.getValue()));
                        System.out.println("********" + r.toString());
                        ReservationS.getInstance().add(r);
                        showAlertWithoutHeaderText("Demande de réservation bien effecuter");
                        }
                        else{
                            showAlertWithoutHeaderText("Date invalide");
                        }
                    }else{
                        showAlertWithoutHeaderText("Le remplissage du champs date obligatoire");
                    }
                } catch (Exception ex) {
                    showAlertWithoutHeaderText("Le Remplissage des champs est obligatoire");
                }
            }
        });
         btnannuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window stage = Reservationoffre.getScene().getWindow();
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
