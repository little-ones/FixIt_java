/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Offre.OffreSupp;

import FixIt.Entities.Offre;
import FixIt.Entities.Service;
import FixIt.Services.OffreService;
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
        
           Offre Offre = new Offre();
           Offre.setId(Integer.parseInt(StaticData.Offreid));
           OffreService s= new OffreService();
                s.Supprimer(Offre);
                
            Stage stage = (Stage) ConfirmButton.getScene().getWindow();
            stage.close();
            
            return ;
            
            
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
