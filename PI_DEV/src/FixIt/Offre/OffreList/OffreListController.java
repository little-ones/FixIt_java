/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Offre.OffreList;

import FixIt.Entities.Offre;
import FixIt.Entities.Service;
import FixIt.Service.ServiceList.ServiceListViewCell;
import FixIt.Services.OffreService;
import FixIt.Utils.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class OffreListController implements Initializable{
     @FXML
    private JFXListView<Offre> Offres;
     
    OffreService Offre;
     @FXML
    private ImageView offreImg;

    @FXML
    private JFXButton Ajouter;
    
    private ObservableList<Offre> ServiceObservableList;
    
     public OffreListController(){
         
        Offre = new OffreService();
        ServiceObservableList = Offre.Afficher();
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Image OffreImage = new Image(Routes.OffreImg);
        offreImg.setImage(OffreImage);
        
       // Offres.getCenter().autosize();
        Offres.setItems(ServiceObservableList);
        Offres.getStylesheets().add(Routes.HamburgerCss);
        Offres.setCellFactory( OffreListView -> new OffreListViewCell(this));
        Offres.refresh();
    
    }
    public void edit(int index) {
        Offres.edit(index);
    }

    public void delete(int index) {
        Offres.getItems().remove(index);
    }
    public void loadData(){
        ServiceObservableList = Offre.Afficher();
        Offres.setItems(ServiceObservableList);
        Offres.getStylesheets().add(Routes.HamburgerCss);
        Offres.setCellFactory( serviceListView -> new OffreListViewCell(this));   
        Offres.refresh();
    }
    
    
    @FXML
    void AjouterOffre(ActionEvent event) {
        
         Stage s = new Stage(StageStyle.DECORATED);
                try {
                    
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.OffreAdd));
                    Scene scene = new Scene(p);
                    s.setScene(scene);
                    s.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                
                s.setOnHiding((f)->{
                         loadData();
                         s.close();
                });
                    
    }
}
