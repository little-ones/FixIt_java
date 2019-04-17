/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Charts;

import FixIt.Services.DemandeService;
import FixIt.Services.OffreService;
import FixIt.Services.ReservationService;
import FixIt.Services.ServiceService;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FixItChartsController implements Initializable {

    @FXML
    private BarChart<?, ?> BarChart;
    
     @FXML
    private JFXTabPane mainTab;

    @FXML
    private CategoryAxis XAxis;

    @FXML
    private PieChart PieChart;
    
    @FXML
    private NumberAxis YAxis;

    DemandeService demande; 
    OffreService Offre; 
    ServiceService Service; 
    ReservationService Reservation; 
    
    public FixItChartsController(){
        demande = DemandeService.getInstance();
        Reservation = ReservationService.getInstance();
        Offre = new OffreService();
        Service = new ServiceService();
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mainTab.widthProperty().addListener((observable, oldValue, newValue) ->
       {
           mainTab.setTabMinWidth(mainTab.getWidth() / 2.5);
           mainTab.setTabMaxWidth(mainTab.getWidth() / 2.5);
       });
        
        LoadCharts();
        
    }    
    
    public void LoadCharts(){
        
        System.out.println("Excuting Load charts");
        
        XYChart.Series S  = new XYChart.Series<>();
        
        S.getData().add(new XYChart.Data("Offres",Offre.Count() ));
        S.getData().add(new XYChart.Data("Demandes",demande.Count() ));
        S.getData().add(new XYChart.Data("Reservations",Reservation.Count() ));
        S.getData().add(new XYChart.Data("Service",Service.Count() ));
        
        BarChart.getData().addAll(S);
        
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        pieChart.add(new PieChart.Data("Offres",Offre.Count()));
        pieChart.add(new PieChart.Data("Demandes",demande.Count() ));
        pieChart.add(new PieChart.Data("Reservations",Reservation.Count()));
        pieChart.add(new PieChart.Data("Service",Service.Count()));
        
        PieChart.setData(pieChart);
        
    }
    
    
}
