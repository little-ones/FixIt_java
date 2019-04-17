/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Demande.DemandeDetails;

import FixIt.Utils.Routes;
import FixIt.Utils.StaticData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DemandeDetailsController implements Initializable {

    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label titre;

    @FXML
    private Label budget;

    @FXML
    private Label date;

    @FXML
    private Label client;

    @FXML
    private Label description;

    @FXML
    private JFXButton saveAsPdf;
    
    @FXML
    private Label categorie;
    
    @FXML
    private ImageView DemandeDetailsImg;
    
    @FXML
    void ExportToPdf(ActionEvent event) {
                    System.out.println("To Printer!");
                    Stage stage = (Stage) saveAsPdf.getScene().getWindow();
                    PrinterJob job = PrinterJob.createPrinterJob();
                    
                          if(job != null){
                                job.showPrintDialog(stage); 
                                job.printPage(root);
                                job.endJob();
                          }else {
                              System.out.println("problem with job printer ");
                          }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Platform.runLater(()->{
        
            
            titre.setText(StaticData.DemandeTitre);
            description.setText(StaticData.DemandeDesc);
            budget.setText(StaticData.DemandeBudget);
            date.setText(StaticData.DemandeDate);
            categorie.setText(StaticData.DemandeCategorie);
            //titre.setText(StaticData.DemandeTitre);
            
            Image ServiceImage = new Image(Routes.ServiceImg);
            
            DemandeDetailsImg.setImage(ServiceImage);
            
        });
    }    
    
}
