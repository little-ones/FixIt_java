/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Offre;
import Entity.Pro;
import Service.DemandeS;
import Test.Fixit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 *
 * @author MedAmine
 */
public class ProListViewCell extends ListCell<ListPro> {

    public static double rating;
    @FXML
    private AnchorPane item;
    @FXML
    private GridPane gridpanepro;
    @FXML
    private HBox hboxprocell;
    @FXML
    private AnchorPane item1;
    @FXML
    private AnchorPane item2;

    @FXML
    private AnchorPane item3;

    @FXML
    private ImageView imgpro;
    @FXML
    private ImageView imgpro1;
    @FXML
    private ImageView imgpro2;

    @FXML
    private Label Namepro;
    @FXML
    private Label Namepro1;
    @FXML
    private Label Namepro2;
    @FXML
    private Label Domaine;
    @FXML
    private Label Domaine1;
    @FXML
    private Label Domaine2;
    @FXML
    private Rating rating1;
    @FXML
    private Rating rating2;
    @FXML
    private Rating rating3;
    @FXML
    private JFXButton btndetail;
    @FXML
    private JFXButton btndetail1;
    @FXML
    private JFXButton btndetail2;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(ListPro pro, boolean empty) {
        super.updateItem(pro, empty);

        if (empty || pro == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/proitems.fxml"));

                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }

            }
            
            Image image = new Image(getClass().getResource("/Image/imgpro.jpg").toExternalForm());
/***********************************************************************************************/  
            if (pro.p1 == null) {
                item1.setVisible(false);
            } else {
                Namepro.setText(pro.p1.getUsername());
                Domaine.setText(pro.p1.getTelephone());
                rating1.setPartialRating(true);
                rating1.setRating(pro.p1.getRating());
               /* rating1.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        Pro p = pro.p1;
                        Double r = Double.parseDouble(newValue.toString());
                        p.setRating((pro.p1.getRating() + r) / 2);
                        rating1.setRating(r);
                        DemandeS.getInstance().updaterating(p);
                    }
                });*/
                imgpro.setImage(image);
                btndetail.setOnAction((event) -> {
                    System.out.println("Button Detail selected ");
                    FXMLLoader Profil = new FXMLLoader();
                    Profil.setLocation(getClass().getResource("/View/profil.fxml"));
                    Scene scene;
                    try {
                        Stage stage = new Stage();
                    URL url = getClass().getResource("/View/profil.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage,(Parent)Profil.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 774, 500);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());    
                        ProfilController controller = Profil.<ProfilController>getController();
                        controller.setId(pro.p1.getId());
                        
                        stage.setTitle("Profil");
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println("Chargement Profil impossible");
                    }
                });
            }
/***********************************************************************************************/            
            if (pro.p2 == null) {
                item2.setVisible(false);
            } else {
               
              /* rating2.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        Pro p = pro.p2;
                        Double r = Double.parseDouble(newValue.toString());
                        p.setRating((pro.p2.getRating() + r) / 2);
                        rating2.setRating(r);
                        DemandeS.getInstance().updaterating(p);
                    }
                });*/
                imgpro1.setImage(image);
                Namepro1.setText(pro.p2.getUsername());
                Domaine1.setText(pro.p2.getTelephone());
                rating2.setRating(pro.p2.getRating());
                btndetail1.setOnAction((event) -> {
                    System.out.println("Button Detail1 selected ");
                    FXMLLoader Profil = new FXMLLoader();
                    Profil.setLocation(getClass().getResource("/View/profil.fxml"));
                    Scene scene;
                    try {
                         Stage stage = new Stage();
                    URL url = getClass().getResource("/View/profil.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage,(Parent)Profil.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 774, 500);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());  
                        
                        ProfilController controller = Profil.<ProfilController>getController();
                        controller.setId(pro.p2.getId());
                        
                        stage.setTitle("Profil");
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println("Chargement Profil impossible");
                    }
                });
            }
            if (pro.p3 == null) {
                item3.setVisible(false);
            } else {
                 System.out.println(pro.p3.getRating()+"------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!");
               /* rating3.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        Pro p = pro.p3;
                        Double r = Double.parseDouble(newValue.toString());
                        p.setRating((pro.p3.getRating() + r) / 2);
                        rating3.setRating(r);
                        DemandeS.getInstance().updaterating(p);
                    }
                });*/
                Namepro2.setText(pro.p3.getUsername());
                Domaine2.setText(pro.p3.getTelephone());
                rating3.setRating(pro.p3.getRating());
                imgpro2.setImage(image);
                btndetail2.setOnAction((event) -> {
                    System.out.println("Button Detail2 selected ");
                    FXMLLoader Profil = new FXMLLoader();
                    Profil.setLocation(getClass().getResource("/View/profil.fxml"));
                    Scene scene;
                    try {
                         Stage stage = new Stage();
                    URL url = getClass().getResource("/View/profil.fxml");
                    JFXDecorator decorator = new JFXDecorator(stage,(Parent)Profil.load(url.openStream()), false, false, true);
                    decorator.setCustomMaximize(false);
                    scene = new Scene(decorator, 774, 500);
                    scene.getStylesheets().add(Fixit.class.getResource("/Styles/styles.css").toExternalForm());  
                        ProfilController controller = Profil.<ProfilController>getController();
                        controller.setId(pro.p3.getId());
                        
                        stage.setTitle("Profil");
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println("Chargement Profil impossible");
                    }
                });
            }
            setText(null);
            setGraphic(gridpanepro);

        }
    }

}
