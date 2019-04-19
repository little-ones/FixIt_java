/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Pro;
import Service.DemandeS;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author MedAmine
 */
public class ProfiClientController implements Initializable {

    @FXML
    private ImageView imgClient;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField telephone;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Button btnmodifier;
    String img;
    String username;
    Image image;
    Pro p;
    int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {

            Pro p = DemandeS.getInstance().showuser(HomePageController.id);
            if (p.getImage_name() == null) {
                image = new Image(getClass().getResource("/Image/user.jpg").toExternalForm());
                imgClient.setImage(image);
            } else {
                image = new Image(getClass().getResource("/uplodedimg/" + p.getImage_name()).toExternalForm());
                imgClient.setImage(image);
            }
            System.out.println(p.toString());
            id=p.getId();
            username=p.getUsername();
            nom.setText(p.getNom());
            prenom.setText(p.getPrenom());
            telephone.setText(p.getTelephone());
            email.setText(p.getEmail());
            adresse.setText(p.getAdresse());
            password.setText(p.getAdresse());

        });

        btnmodifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Pro p1  = new Pro();
                    p1.setId(id);
                    p1.setUsername(username);
                    System.out.println("username : "+p1.getUsername());
                    p1.setNom(nom.getText());
                    System.out.println("Nom : "+nom.getText());
                    p1.setAdresse(adresse.getText());
                    System.out.println("Adresse : "+adresse.getText());
                    p1.setTelephone(telephone.getText());
                    System.out.println("telephone : "+telephone.getText());
                    p1.setPrenom(prenom.getText());
                    System.out.println("prenom : "+prenom.getText());
                    p1.setPassword(password.getText());
                    System.out.println("password : "+password.getText());
                    p1.setImage_name( img);
                    System.out.println("image name  : "+img);
                    p1.setRating(0);
                    System.out.println(p1.toString());
        
                    
                    System.out.println(p1.toString());
                    DemandeS.getInstance().updateuser(p1);
                } catch (Exception ex) {
                    System.out.println("Modification user problème ----------");
                    ex.printStackTrace();
                }
            }
        });
    }

    public static String saveToFileImageNormal(Image image) throws SQLException, IOException {
        String ext = "jpg";
        File dir = new File("C:/Users/MedAmine/Documents/NetBeansProjects/PI/src/uplodedimg");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
         
        return name;
    }

    @FXML
    private void addImage(MouseEvent event) throws IOException, SQLException {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgClient.setImage(image);
            img =saveToFileImageNormal(imgClient.getImage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
