/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Offre;
import Entity.Pro;
import Entity.Service;
import Service.DemandeS;
import Test.Fixit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author MedAmine
 */
public class OffreListViewCell extends ListCell<Offre> {

    private FXMLLoader mLLoader;
    @FXML
    private Label ProNom;

    @FXML
    private Label Categorie;

    @FXML
    private JFXButton btnreserver;

    @FXML
    private JFXButton btnannuler;

    @FXML
    private Label etat;
    @FXML
    private GridPane gridpaneoffre;
    @FXML
    private ImageView icreserver;
        @FXML
    private ImageView offreimg;
    @Override
    protected void updateItem(Offre offre, boolean empty) {
        super.updateItem(offre, empty);
        if (empty || offre == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/OffreListViewItem.fxml"));

                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    System.err.println("ereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuuur");
                    e.printStackTrace();
                }
            }
            btnreserver.setOnAction((event) -> {
                FXMLLoader ReserverunOffre = new FXMLLoader();
                ReserverunOffre.setLocation(getClass().getResource("/View/ReservationOffre.fxml"));
                Scene scene;
                try {
                    Stage stage = new Stage();
                    URL url = getClass().getResource("/View/ReservationOffre.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage, (Parent) ReserverunOffre.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 774, 500);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());

                    ReservationController controller = ReserverunOffre.<ReservationController>getController();
                    controller.setOffre(offre);

                    stage.setTitle("Réservation d'un offre");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("Chargement UpdateDemande impossible");
                }
            });
            Image imageoffre = new Image(getClass().getResource("/Image/offre.png").toExternalForm());
            offreimg.setImage(imageoffre);
            Image image = new Image(getClass().getResource("/Image/reservation.png").toExternalForm());
            icreserver.setImage(image);
            Pro p = DemandeS.getInstance().showuser(offre.getPro_id());
            ProNom.setText(p.getNom());
            Categorie.setText(offre.getTitre() + " " + offre.getBudget() + "TND");
            etat.setText("Disponible");
            gridpaneoffre.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                FXMLLoader Offrepopup = new FXMLLoader();
                Offrepopup.setLocation(getClass().getResource("/View/Offrepopup.fxml"));
                Scene scene;
                try {
                    Stage stage = new Stage();
                    URL url = getClass().getResource("/View/Offrepopup.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage,(Parent)Offrepopup.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 610, 449);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm()); 
                    
                    PopupOffreController controller = Offrepopup.<PopupOffreController>getController();
                    controller.setO(offre);
                    
                    stage.setTitle("Information offre");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("Chargement information offre impossible");
                    ex.printStackTrace();
                }
            });
            setText(null);
            setGraphic(gridpaneoffre);

        }
    }

}
