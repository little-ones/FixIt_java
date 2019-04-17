/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Service.ServiceList;

import FixIt.Entities.Service;
import FixIt.Services.OffreService;
import FixIt.Services.ServiceService;
import FixIt.Utils.Routes;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class ServiceListController implements Initializable{
    
     @FXML
    private JFXListView<Service> Services;
    
    ServiceService Service ;
    
    private ObservableList<Service> ServiceObservableList;
    
    public ServiceListController(){
        Service = new ServiceService();
        ServiceObservableList = Service.Afficher();
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Services.setItems(ServiceObservableList);
        Services.getStylesheets().add(Routes.HamburgerCss);
        Services.setCellFactory( serviceListView -> new ServiceListViewCell(this));
        Services.refresh();
        
        
      
    }
    public void edit(int index) {
        Services.edit(index);
    }

    public void delete(int index) {
        Services.getItems().remove(index);
        Services.refresh();
    }
    
    public void loadData(){
        ServiceObservableList = Service.Afficher();
        Services.setItems(ServiceObservableList);
        Services.getStylesheets().add(Routes.HamburgerCss);
        Services.setCellFactory( serviceListView -> new ServiceListViewCell(this));   
        Services.refresh();
    }
    
     @FXML
    void AjouterSertvice(ActionEvent event) {
        
         Stage s = new Stage(StageStyle.DECORATED);
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.ServiceAdd));
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
