/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Demande;
import Entity.Service;
import Service.DemandeS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.Color;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 *
 * @author MedAmine
 */
public class AjouterDemandeController implements Initializable {

    /**
     * ***********Ajouter une Demande*****************
     */
    @FXML
    private AnchorPane AjouterDemande;

    @FXML
    private JFXButton btnajouter;

    @FXML
    private JFXButton btnannuler;

    @FXML
    private JFXTextField tftitre;

    @FXML
    private JFXTextField tfbudget;

    @FXML
    private JFXTextArea tadesc;

    @FXML
    private JFXComboBox<String> cbcat;
    @FXML
    private ImageView imghandy;

    public AjouterDemandeController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Service> services = DemandeS.getInstance().showAllServices();
        Platform.runLater(() -> {
            Image image = new Image(getClass().getResource("/Image/handy.jpg").toExternalForm());
            imghandy.setImage(image);
            for (Service s : services) {
                cbcat.getItems().add(s.getTitre());
            }

        });
        NumberValidator numberValidator = new NumberValidator();
        
        numberValidator.setMessage("Ce champ doit contenir que des nombres");
        RequiredFieldValidator requeiredvalidator = new RequiredFieldValidator();
        requeiredvalidator.setMessage("Champ obligatoire");
        tftitre.getValidators().add(requeiredvalidator);
        tftitre.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tftitre.validate();
            }
        });
        tfbudget.getValidators().add(numberValidator);
        tfbudget.setOnKeyReleased(e
                -> {
            if (!tfbudget.getText().equals("")) {
                tfbudget.validate();
            }
        });
        tadesc.getValidators().add(requeiredvalidator);
        tadesc.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tadesc.validate();
            }
        });
        btnajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (tftitre.validate() && tfbudget.validate() && tadesc.validate() && cbcat.getSelectionModel().getSelectedIndex() >= 0) {
                        Demande d = new Demande();
                        d.setTitre(tftitre.getText());
                        d.setUserid(1);

                        d.setCategorie_id(services.get(cbcat.getSelectionModel().getSelectedIndex()).getId());
                        d.setBudget(Float.parseFloat(tfbudget.getText()));
                        d.setDate(Date.valueOf(LocalDate.now()));
                        d.setDescription(tadesc.getText());
                        System.out.println("********" + d.ToString());
                        DemandeS.getInstance().add(d);
                        showAlertWithoutHeaderText("Demande bien ajouter");
                      
                    } else {
                        showAlertWithoutHeaderText("Vérifier les champs svp");
                    }
                } catch (Exception ex) {
                    System.out.println("Probleme d'ajout d'une demande");
                }
            }
        });
        btnannuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window stage = AjouterDemande.getScene().getWindow();
                
                stage.hide();
            }
        });

    }

    private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

}
