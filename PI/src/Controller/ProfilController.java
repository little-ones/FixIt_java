/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Pro;
import Service.DemandeS;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.Rating;

/**
 *
 * @author MedAmine
 */
public class ProfilController implements Initializable {

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
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label telephone;

    @FXML
    private Label email;

    @FXML
    private Label adresse;

    @FXML
    private Rating ratingprofil;

    @FXML
    private Label lbrating;
    
    @FXML
    private ImageView userimg;
    public ProfilController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
           Image image = new Image(getClass().getResource("/Image/user.jpg").toExternalForm());
            userimg.setImage(image);

            Pro p = DemandeS.getInstance().showuser(id);
            System.out.println(p.toString());
            nom.setText("Nom : " + p.getNom());
            prenom.setText("Prenom : " + p.getPrenom());
            telephone.setText("Téléphone : " + p.getTelephone());
            email.setText("Email : " + p.getEmail());
            adresse.setText("Adresse : " + p.getAdresse());
            ratingprofil.setRating(p.getRating());
            
            ratingprofil.ratingProperty().addListener(new ChangeListener<Number>() {
                  

               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                  Pro p1 = p;
                        Double r = Double.parseDouble(newValue.toString());
                        p1.setRating((p1.getRating() + r) / 2);
                        ratingprofil.setRating(r);
                        DemandeS.getInstance().updaterating(p1);
               }
                });
            
            
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
            userimg.setImage(image);
            String s =saveToFileImageNormal(userimg.getImage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
