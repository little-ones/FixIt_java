/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Service.ServiceAdd;

import FixIt.Entities.Service;
import FixIt.Services.ServiceService;
import FixIt.Utils.StaticData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class ServiceAddController  implements Initializable{
    
    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;
    
     
    @FXML
    void AjouterOffre(ActionEvent event) {
        String Titre = titre.getText();
       // int mID =Integer.parseInt( categorie.getText());
        String description = desc.getText();
        //int Budget = Integer.parseInt( budget.getText());

        Boolean flag = Titre.isEmpty() || description.isEmpty()  ;
        if (flag) {
          
             Stage stage = (Stage) saveButton.getScene().getWindow();
    
                stage.close();
                
            return;
        }
        
        else {
            
            Service service  = new Service(Titre, description, "", 1);
            
            ServiceService Service = new ServiceService();
            
            Service.Ajouter(service);
            
             Stage stage = (Stage) saveButton.getScene().getWindow();
    
                stage.close();
            
          
            
        }
    }
     @FXML
    void cancel(ActionEvent event) {
        
             Stage stage = (Stage) cancelButton.getScene().getWindow();
    
                stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater (() -> {
            desc.setStyle("-fx-text-fill:  #00a1ff;");
            titre.setStyle("-fx-text-fill:  #00a1ff;");
          
       
    });
    }
}
