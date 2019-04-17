/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Offre;
import Entity.Pro;
import Entity.Service;
import Service.ProService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author MedAmine
 */
public class ProlistviewController implements Initializable {

    @FXML
    private AnchorPane item;
    @FXML
    private Label Namepro;
    
    @FXML
    private Label Domaine;

    @FXML
    private JFXButton btndetail;
    @FXML
    private JFXListView Listpro;
    private ObservableList<ListPro> proObservableList;

    public ProlistviewController() {
          ArrayList<Pro> lpro = new ArrayList<>();
          lpro.addAll( ProService.getInstance().showAll());
          List<Pro> result2 = lpro.stream()
                .filter(x -> x.getRoles().equals("pro"))
                .collect(Collectors.toList());
          System.err.println(lpro.toString());
        try {
            proObservableList = FXCollections.observableArrayList();
            
            ListPro Lp = null;
            ListPro Lp1 = null;
            
              for(int i=0; i<result2.size();i++)
              {
                  if (i + 3 <= result2.size())
                  {Lp = new ListPro(result2.get(i), result2.get(i+1), result2.get(i+2));
                   i+=2;
                   proObservableList.addAll(Lp);}
                   else {
                    if (i + 2 <= result2.size()) {
                        Lp = new ListPro(result2.get(i), result2.get(i+1), null);
                         i+=1;
                   proObservableList.addAll(Lp);
                    }else {
                    if (i + 1 <= result2.size()) {
                        Lp = new ListPro(result2.get(i), null, null);
                         
                   proObservableList.addAll(Lp);
                    }
                   }
                   }
              }
            
          
               
           
            

        } catch (Exception ex) {
            System.out.println("Probleme de chargement des professionnels ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Listpro.setItems(proObservableList);
        Listpro.setCellFactory(new Callback<ListView<ListPro>, ListCell<ListPro>>() {
            @Override
            public ListCell<ListPro> call(ListView<ListPro> proListView) {
                return new ProListViewCell() {

                };
            }
        });

    }

}
