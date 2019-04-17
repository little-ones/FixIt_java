/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Service.DemandeS;
import Test.Fixit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author MedAmine
 */
public class DemandeListViewCell extends ListCell<Demande> {
    public DemandeListController controllerd;
    public DemandeListController controllermd;
    @FXML
    private GridPane ItemGridPane;
    @FXML
    private ImageView demandeimg;

    @FXML
    private Label titredemande;

    @FXML
    private Label categorie;
    @FXML
    private Label datedemande;

    
    @FXML
    private JFXButton btnsupprimerdemande;
    @FXML
    private AnchorPane ModifierDemande1;
    @FXML
    private JFXButton btnmodifierdemande;
    @FXML
    private ImageView updatebtn;
    @FXML
    private ImageView btndeleicon;
    private FXMLLoader mLLoader;

    public DemandeListViewCell(DemandeListController controllerd) {
       this.controllerd =controllerd;
    }

    DemandeListViewCell() {
        
    }

    @Override
    protected void updateItem(Demande demande, boolean empty) {
        super.updateItem(demande, empty);
        if (empty || demande == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/DemandeListeItem.fxml"));

                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    System.err.println("ereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeuuuuuuuuuuuuuuuuur");
                    e.printStackTrace();
                }
            }
            Image image = new Image(getClass().getResource("/Image/templates.png").toExternalForm());
            demandeimg.setImage(image);
            demandeimg.setClip(new Circle(20, 20, 70));
            titredemande.setText(demande.getTitre());
            categorie.setText(DemandeS.getInstance().showservice(demande.getCategorie_id()).getTitre() + "  "
                    +demande.getBudget()+"TND");
            datedemande.setText(demande.getDate().toString());
            
            
            btnsupprimerdemande.setOnAction((event) -> {
                try {
                    DemandeS.getInstance().delete(demande.getId());
                    this.controllerd.loadData();
                    System.out.println("Demande bien supprimer");

                } catch (Exception ex) {
                    System.out.println("Probleme de suppression d'une demande ");
                }
            });
            btnmodifierdemande.setOnAction((event) -> {
                
                FXMLLoader ModifierDemande = new FXMLLoader();
                ModifierDemande.setLocation(getClass().getResource("/View/UpdateDemande.fxml"));
                  
                Scene scene;
                try {
                    
                    ModifierDemandeController controller = ModifierDemande.<ModifierDemandeController>getController();
                    controller.setId(demande.getId());
                    Stage stage = new Stage();
                    URL url = getClass().getResource("/View/UpdateDemande.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage,(Parent)ModifierDemande.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 774, 500);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());
                    stage.setTitle("Modification demandes");
                    stage.setScene(scene);
                    stage.show();
                    stage.setOnHiding((f)->{
                        this.controllerd.loadData();
                        stage.close();
                    });
                } catch (IOException ex) {
                    System.out.println("Chargement UpdateDemande impossible");
                }
            });
            
            setText(null);
            setGraphic(ItemGridPane);

        }
    }

  

}
