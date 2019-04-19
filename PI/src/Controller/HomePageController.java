/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ProfilController.saveToFileImageNormal;
import Entity.Pro;
import Service.DemandeS;
import com.jfoenix.controls.JFXTabPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

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
    private Tab profiltab;
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
AnchorPane MesDemandes;
AnchorPane Professionnels;
AnchorPane MesReservations;
AnchorPane Offres;
AnchorPane Map;
AnchorPane profil;
public static int id;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Pro p =DemandeS.getInstance().showuser(1);
            id=p.getId();
            System.out.println("Id utilisateur : "+id);
            Image imalogo = new Image(getClass().getResource("/Image/logo.png").toExternalForm());
            logo.setImage(imalogo);
             MesDemandes = FXMLLoader.load(getClass().getResource("/View/DemandeListView.fxml"));
             Professionnels = FXMLLoader.load(getClass().getResource("/View/prolist.fxml"));
             MesReservations = FXMLLoader.load(getClass().getResource("/View/ReservationListView.fxml"));
             Offres = FXMLLoader.load(getClass().getResource("/View/OffreListView.fxml"));
             Map = FXMLLoader.load(getClass().getResource("/View/Map.fxml"));
             profil = FXMLLoader.load(getClass().getResource("/View/ProfilClient.fxml"));
            mesdemandestab.setContent(MesDemandes);
            professionnelstab.setContent(Professionnels);
            mesreservationtab.setContent(MesReservations);
            Accueiltab.setContent(Offres);
            abouttab.setContent(Map);
            profiltab.setContent(profil);

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void Reload(MouseEvent event) throws IOException, SQLException {
        System.out.println("reload ok -------------------------");
        AnchorPane MesD = FXMLLoader.load(getClass().getResource("/View/DemandeListView.fxml"));
        mesdemandestab.setContent(MesD);
        AnchorPane MesR = FXMLLoader.load(getClass().getResource("/View/ReservationListView.fxml"));
        mesreservationtab.setContent(MesR);
        
               
         
        
        
           }
}
