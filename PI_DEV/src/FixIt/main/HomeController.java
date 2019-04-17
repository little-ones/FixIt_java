/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.main;

import FixIt.Notifications.Notification;
import FixIt.Service.ServiceList.ServiceListViewCell;
import FixIt.Utils.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author USER
 */
public class HomeController implements Initializable {
  
@FXML
    private StackPane rootPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab HomeTab;

     @FXML
    private AnchorPane Demandes;

    @FXML
    private Tab OffresTab;

    @FXML
    private AnchorPane Offres;
       
    @FXML
    private ImageView serviceimg;

    @FXML
    private ImageView HomeCover;
    @FXML
    private Tab ServicesTab;

    @FXML
    private BorderPane Services;

    @FXML
    private Tab ReservationsTab;
    
      @FXML
    private Label ServicesTitle;

     @FXML
    private ImageView Logo;
    
    @FXML
    private BorderPane Reservations;

    @FXML
    private JFXHamburger hamburger;
    
    
   
    AnchorPane OffresPane, ServicesPane,ReservationsPane,HomePane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
//     ServicesTitle.setTextAlignment(TextAlignment.CENTER);
        
    // for making tab header take full width
    
    Notification n = new Notification();
    
    try {
        n.displayTray("hello from fixit");
    } catch (AWTException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MalformedURLException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
      mainTabPane.widthProperty().addListener((observable, oldValue, newValue) ->
    {
        mainTabPane.setTabMinWidth(mainTabPane.getWidth() / 4.5);
        mainTabPane.setTabMaxWidth(mainTabPane.getWidth() / 4.5);
    });
      
      LoadLists();
       
       
       Image ServiceImage = new Image(Routes.ServiceImg);
       Image Home = new Image(Routes.HomeCover);
       //HomeCover.setImage(Home);
       Logo.setImage(new Image(Routes.Logo));
       Logo.setFitWidth(150.0);
       serviceimg.setImage(ServiceImage);
       
       mainTabPane.getSelectionModel().select(OffresTab);

        VBox box;
    try {
        box = FXMLLoader.load(getClass().getResource(Routes.Drawer));
        drawer.setSidePane(box);
      
            HamburgerBackArrowBasicTransition backTask2;
            backTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            backTask2.setRate(-1);
            mainTabPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (drawer.isShown()){
                drawer.close();
                backTask2.setRate(backTask2.getRate()*-1);
                backTask2.play();
                }
            });
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
                
                backTask2.setRate(backTask2.getRate()*-1);
                backTask2.play();
                
                if (drawer.isShown()){
                    drawer.close();
                }
                else{
                    drawer.open(); 
                }
                
            });
    } catch (IOException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
    }

    
    public  void LoadLists(){
        try {
             OffresPane = FXMLLoader.load(getClass().getResource(Routes.Offres));
             ServicesPane = FXMLLoader.load(getClass().getResource(Routes.Services));
             ReservationsPane = FXMLLoader.load(getClass().getResource(Routes.Reservations));
             //Demandes
            
             HomePane = FXMLLoader.load(getClass().getResource(Routes.Demandes));
            
            // Home.setCenter((Node)HomePane);
            HomeTab.setContent((Node)HomePane);
            OffresTab.setContent((Node)OffresPane);
            OffresTab.getContent().autosize();
            Services.setCenter((Node)ServicesPane);
            Reservations.setCenter((Node)ReservationsPane);
            
            
        } catch (IOException ex) {
            
        }
    }
     @FXML
    void AddOffre(ActionEvent event) {
        
         Stage s = new Stage();
                try {
                    Parent p =  FXMLLoader.load(getClass().getResource(Routes.OffreAdd));
                    Scene scene = new Scene(p);
                    s.setScene(scene);
                    s.show();
                } catch (IOException ex) {
                    Logger.getLogger(ServiceListViewCell.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

   
   

}
