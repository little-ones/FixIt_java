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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author MedAmine
 */
public class ModifierDemandeController implements Initializable {

    private static int id;

    /**
     * @return the id
     */
    public static int getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }

    @FXML
    private AnchorPane ModifierDemande;

    @FXML
    private JFXButton btnModifier;

    @FXML
    private JFXButton btnMannuler;

    @FXML
    private JFXTextField tfMtitre;

    @FXML
    private JFXTextField tfMbudget;

    @FXML
    private JFXTextArea taMdesc;

    @FXML
    private JFXComboBox cbMcat;
    @FXML
    private ImageView imghandy;
    @FXML
    private AnchorPane achropane;
    int indexcat;
    private ObservableList<String> CategorieObservableList;
    ArrayList<Service> services;

    public ModifierDemandeController() {

        try {
            services = DemandeS.getInstance().showAllServices();
            this.CategorieObservableList = FXCollections.observableArrayList();
            for (Service s : services) {
                CategorieObservableList.addAll(s.getTitre());
            }
            Service result2 = services.stream()
                    .filter(x -> id == x.getId())
                    .findAny().get();
            indexcat = services.indexOf(result2);

        } catch (Exception ex) {
            System.out.println("Probleme de chargement des demandes ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Image image = new Image(getClass().getResource("/Image/handy.jpg").toExternalForm());
            imghandy.setImage(image);
            Demande d = new Demande();
            d = DemandeS.getInstance().show(id);
            tfMtitre.setText(d.getTitre());
            tfMbudget.setText((int)d.getBudget() + "");
            cbMcat.getItems().addAll(CategorieObservableList);
            cbMcat.getSelectionModel().select(indexcat);
            taMdesc.setText(d.getDescription());
            btnModifier.setOnAction((event) -> {

                try {
                    if (tfMtitre.validate() && tfMbudget.validate() && taMdesc.validate() && cbMcat.getSelectionModel().getSelectedIndex() >= 0) {
                        Demande d1 = new Demande();
                        d1.setId(id);
                        d1.setUserid(1);
                        d1.setTitre(tfMtitre.getText());
                        System.out.println(tfMtitre.getText()+"titre---------------------------------------------------");
                        d1.setCategorie_id(1);
                        d1.setBudget(Float.parseFloat(tfMbudget.getText()));
                        d1.setDate(Date.valueOf(LocalDate.now()));
                        d1.setDescription(taMdesc.getText());
                        System.out.println("********" + d1.ToString());
                        DemandeS.getInstance().update(d1);
                        showAlertWithoutHeaderText("Demande bien Modifier");
                    }else{
                        showAlertWithoutHeaderText("Vérifier les champs svp!!!");
                    }
                } catch (Exception ex) {
                    System.out.println("Probleme de modification d'une demande");
                }
            });

        });
        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Ce champ doit contenir que des nombres");
        RequiredFieldValidator requeiredvalidator = new RequiredFieldValidator();
        requeiredvalidator.setMessage("Champ obligatoire");
        tfMtitre.getValidators().add(requeiredvalidator);
        tfMtitre.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tfMtitre.validate();
            }
        });
        tfMbudget.getValidators().add(numberValidator);
        tfMbudget.setOnKeyReleased(e
                -> {
            if (!tfMbudget.getText().equals("")) {
                tfMbudget.validate();
            }
        });
        taMdesc.getValidators().add(requeiredvalidator);
        taMdesc.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                taMdesc.validate();
            }
        });
        btnMannuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window stage = ModifierDemande.getScene().getWindow();
                stage.hide();
            }
        });

    }
private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
