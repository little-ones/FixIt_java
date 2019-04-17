/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Offre.OffreAdd;

import FixIt.Entities.Offre;
import FixIt.Entities.Service;
import FixIt.Services.OffreService;
import FixIt.Services.ServiceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author USER
 */
public class OffreAddController implements Initializable{
     @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextField categorie;

    @FXML
    private ComboBox<String> categories;
    
    ObservableList<Service> ServiceObservableList;
    
    @FXML
    private JFXTextField budget;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    ServiceService service;
    
    public OffreAddController(){
        service = new ServiceService();
    }
    
    
    @FXML
    void AjouterOffre(ActionEvent event) {

        String Titre = titre.getText();
        String description = desc.getText();
        int Budget = Integer.parseInt( budget.getText());

        Boolean flag = Titre.isEmpty() || description.isEmpty() || budget.getText().isEmpty() ;
        if (flag) {
         
            
        }
        
        else {
            
            Offre offre  = new Offre(ServiceObservableList.get(categories.getSelectionModel().getSelectedIndex()).getId(), Titre, description,  Budget, 1);
            
            OffreService OffreService = new OffreService();
            
            OffreService.Ajouter(offre);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
    
            stage.close();
            
            
        }
        
    }

    @FXML
    void cancel(ActionEvent event) {

        Stage stage = (Stage) saveButton.getScene().getWindow();
         
                stage.close();
                
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            desc.setStyle("-fx-text-fill: #00a1ff;");
            titre.setStyle("-fx-text-fill: #00a1ff;");
            budget.setStyle("-fx-text-fill: #00a1ff;");
            
            ServiceObservableList = service.Afficher();
            
            for (Service s : ServiceObservableList){
                categories.getItems().add(s.getCategorie());
            }
    }

}
