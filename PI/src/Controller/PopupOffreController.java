/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Offre;
import Service.DemandeS;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author MedAmine
 */
public class PopupOffreController implements Initializable{
    private static Offre o;

    /**
     * @return the o
     */
    public static Offre getO() {
        return o;
    }

    /**
     * @param aO the o to set
     */
    public static void setO(Offre aO) {
        o = aO;
    }
    @FXML
    private ImageView img;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextField budget;

    @FXML
    private JFXTextField datepublication;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField pro;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
        Image image = new Image(getClass().getResource("/Image/handyman.png").toExternalForm());
        img.setImage(image);
        titre.setText(o.getTitre());
        budget.setText(o.getBudget()+" TND");
        datepublication.setText(o.getDate()+"");
        description.setText(o.getDescription());
        pro.setText(DemandeS.getInstance().showuser(o.getPro_id()).getNom());
        });
    }
    
    
}
