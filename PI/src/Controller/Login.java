/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Pro;
import Service.DemandeS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class Login implements Initializable {

    @FXML
    private ImageView loginimg;
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton btnconnecter;

    @FXML
    private JFXPasswordField password;
    public static int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResource("/Image/login.png").toExternalForm());
        loginimg.setImage(image);
        btnconnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String user = username.getText();
                String pwd = password.getText();
                Pro u = DemandeS.getInstance().showuserByusername(user, pwd);
                if (u != null) {
                    id = u.getId();
                    if (u.getRoles().equals("client")) {
                        FXMLLoader Homepage = new FXMLLoader();
                        Homepage.setLocation(getClass().getResource("/View/HomePage.fxml"));

                        Scene scene;
                        try {

                            Stage stage = new Stage();
                            URL url = getClass().getResource("/View/HomePage.fxml");
                            JFXDecorator decorator = new JFXDecorator(stage, (Parent) Homepage.load(url.openStream()), false, false, true);
                            decorator.setCustomMaximize(false);
                            scene = new Scene(decorator, 835, 680);
                            scene.getStylesheets().add(Login.class.getResource("/Styles/styles.css").toExternalForm());
                            stage.setTitle("");
                            stage.setScene(scene);
                            stage.show();

                            Window stage1 = LoginPane.getScene().getWindow();
                            stage1.hide();
                        } catch (IOException ex) {
                            System.out.println("Chargement HomePage impossible");
                        }
                    }
                } else {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");

                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Nom d'utilisateur ou mot de passe incorrect ");

                    alert.showAndWait();

                }
            }

        });
    }

}
