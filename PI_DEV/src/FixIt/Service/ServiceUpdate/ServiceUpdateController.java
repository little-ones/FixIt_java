/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Service.ServiceUpdate;

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
 * FXML Controller class
 *
 * @author USER
 */
public class ServiceUpdateController implements Initializable {

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
         ServiceService Service = new ServiceService();
        Service s = new Service(titre.getText(), desc.getText(), "", 1);
        s.setId(Integer.parseInt(StaticData.ServiceId));
        Service.Update(s);
        
         Stage stage = (Stage) saveButton.getScene().getWindow();
    // do what you have to do
        stage.close();
        
        StaticData.ServiceDesc = "";
        StaticData.ServiceCategorie="";
        
    }

    @FXML
    void cancel(ActionEvent event) {
        
        Stage stage = (Stage) cancelButton.getScene().getWindow();
   
        stage.close();
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater (() -> {
               desc.setStyle("-fx-text-fill: #00a1ff;");
            titre.setStyle("-fx-text-fill: #00a1ff;");
           
           
        desc.setText(StaticData.ServiceDesc);
        titre.setText(StaticData.ServiceCategorie);
        
    });
    }    
    
}
