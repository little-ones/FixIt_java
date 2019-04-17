/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Service.ServiceSupp;

import FixIt.Offre.OffreSupp.*;
import FixIt.Entities.Service;
import FixIt.Services.ServiceService;
import FixIt.Utils.StaticData;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ConfirmationController implements Initializable{

    @FXML
    private JFXButton ConfirmButton;

    @FXML
    void ConfirmAction(ActionEvent event) {
        
           Service service = new Service();
           service.setId(Integer.parseInt(StaticData.ServiceId));
           ServiceService s= new ServiceService();
                s.Supprimer(service);
                
            Stage stage = (Stage) ConfirmButton.getScene().getWindow();
            stage.close();
            stage.close();
            
            return ;
            
            
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
