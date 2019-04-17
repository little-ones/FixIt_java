/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Services;

import FixIt.Entities.Offre;
import FixIt.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class OffreService {
    Connection  connection  = null ;
    
    
    public OffreService(){
         connection = DataSource.getInstance().getConnection();
    }
    
    public void Ajouter (Offre Offre){
        String req = "INSERT INTO `offre` (`user_id`, `date_ajout`,`budget`,`titre`,`description`,`service_id`) values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedstatement  = connection.prepareStatement(req);
         
            preparedstatement.setInt(1, 1);
            preparedstatement.setDate(2, Offre.getDate());
            preparedstatement.setInt(3, Offre.getBudget());
            preparedstatement.setString(4, Offre.getTitre());
            preparedstatement.setString(5, Offre.getDescription());
            preparedstatement.setInt(6, Offre.getCategorie());
            
            preparedstatement.execute();
            System.out.println("Offre addded");
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Supprimer (Offre Offre ){
         String req ="DELETE FROM `offre` WHERE id= ?";
         
         PreparedStatement statement;
        try {
            
            statement = connection.prepareStatement(req);
            statement.setInt(1, Offre.getId());
         statement.execute();
            System.out.println("Offre deleted ");
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void Update(Offre Offre){
        
            String query = "update Offre set service_id = ? , date_ajout=?, budget =? , titre=? , description=? where id = ?";
        try {
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Offre.getCategorie());
            statement.setDate(2, Offre.getDate());
            statement.setInt(3, Offre.getBudget());
            statement.setString(4, Offre.getTitre());
            statement.setString(5, Offre.getDescription());
            statement.setInt(6, Offre.getId());
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<Offre> Afficher (){
          String query = "SELECT * FROM offre";
          ObservableList<Offre> ServiceObservableList = FXCollections.observableArrayList();;
        try {
            Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query);
              while (rs.next())
      {
        int id = rs.getInt("id");
        int categorie = rs.getInt("service_id");
        String Titre = rs.getString("titre");
        int Budget= rs.getInt("budget");
        String description = rs.getString("description");
        Date date = rs.getDate("date_ajout");
        int pro_id = rs.getInt("user_id");
        
        ServiceObservableList.add(new Offre(id,categorie,Titre, description, "", pro_id ,Budget, date));
        
      }
      st.close();
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServiceObservableList;
    }
    public int Count() {
        String req = "SELECT COUNT(*) AS total FROM offre";
        int count = 0;
        try {
           Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(req);

            rs.next();
            count = rs.getInt("total");
            

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
