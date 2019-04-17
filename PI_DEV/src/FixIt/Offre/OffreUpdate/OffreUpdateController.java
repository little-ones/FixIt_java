/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Offre.OffreUpdate;


import FixIt.Entities.Offre;
import FixIt.Entities.Reservation;
import FixIt.Entities.Service;
import FixIt.Services.OffreService;
import FixIt.Services.ServiceService;
import FixIt.Utils.StaticData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
public class OffreUpdateController implements Initializable {
    
    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextField budget;
    
    @FXML
    private ComboBox<String> categories;
    
    ObservableList<Service> ServiceObservableList;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXButton saveButton;
    
    @FXML
    private JFXButton cancelButton;
    
    ServiceService service;
    
    public OffreUpdateController(){
        service = new ServiceService();
    }

    @FXML
    void AjouterOffre(ActionEvent event) {
        OffreService Offre = new OffreService();
        Offre o = new Offre(ServiceObservableList.get(categories.getSelectionModel().getSelectedIndex()).getId(), titre.getText(),desc.getText(), Integer.parseInt(budget.getText()), 0);
        o.setId(Integer.parseInt(StaticData.Offreid));
        Offre.Update(o);
         Stage stage = (Stage) saveButton.getScene().getWindow();
    
         stage.close();
                
        StaticData.OffreDesc="";
        StaticData.OffreCategorie="";
        StaticData.OffreTitre="";
        StaticData.OffreBudget="";
    }

    @FXML
    void cancel(ActionEvent event) {
        
             Stage stage = (Stage) saveButton.getScene().getWindow();
    
                stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater (() -> {
            
            desc.setStyle("-fx-text-fill: #00a1ff;");
            titre.setStyle("-fx-text-fill: #00a1ff;");
            budget.setStyle("-fx-text-fill: #00a1ff;");
            ServiceObservableList = service.Afficher();
            
            for (Service s : ServiceObservableList){
                
                categories.getItems().add(s.getCategorie());
            } 
            
            desc.setText(StaticData.OffreDesc);
            titre.setText(StaticData.OffreTitre);
            budget.setText(StaticData.OffreBudget);
            
            
    });
    }
    
}
