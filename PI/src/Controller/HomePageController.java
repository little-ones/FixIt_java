/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author MedAmine
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane acceuil;
    @FXML
    private ImageView logo;

    @FXML
    private JFXTabPane menubar;

    @FXML
    private Tab Accueiltab;

    @FXML
    private Tab offrestab;

    @FXML
    private Tab mesdemandestab;

    @FXML
    private Tab mesreservationtab;

    @FXML
    private Tab professionnelstab;

    @FXML
    private Tab abouttab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Image imalogo = new Image(getClass().getResource("/Image/logo.png").toExternalForm());
            logo.setImage(imalogo);
            AnchorPane MesDemandes = FXMLLoader.load(getClass().getResource("/View/DemandeListView.fxml"));
            AnchorPane Professionnels = FXMLLoader.load(getClass().getResource("/View/prolist.fxml"));
            AnchorPane MesReservations = FXMLLoader.load(getClass().getResource("/View/ReservationListView.fxml"));
            AnchorPane Offres = FXMLLoader.load(getClass().getResource("/View/OffreListView.fxml"));
            AnchorPane Map = FXMLLoader.load(getClass().getResource("/View/Map.fxml"));
            mesdemandestab.setContent(MesDemandes);
            professionnelstab.setContent(Professionnels);
            mesreservationtab.setContent(MesReservations);
            Accueiltab.setContent(Offres);
            abouttab.setContent(Map);

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
