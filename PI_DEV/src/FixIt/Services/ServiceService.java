/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Services;

import FixIt.Entities.Service;
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
public class ServiceService {
    Connection  connection  = null ;
    
    
    public ServiceService(){
         connection = DataSource.getInstance().getConnection();
    }
    
    public void Ajouter (Service Service){
        String req = "INSERT INTO `service` (`categorie`, `date`,`image_name`,`description`,`Pro_id`) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedstatement  = connection.prepareStatement(req);
            preparedstatement.setString(1,Service.getCategorie());
            preparedstatement.setDate(2, Service.getDate());
            preparedstatement.setString(3, Service.getImage());
            preparedstatement.setString(4, Service.getDescription());
            preparedstatement.setInt(5, Service.getPro_id());
            
            preparedstatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Supprimer (Service Service ){
         String req ="DELETE FROM `service` WHERE id= ?";
         
         PreparedStatement statement;
        try {
            
            statement = connection.prepareStatement(req);
            statement.setInt(1, Service.getId());
         statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Update(Service Service){
        
            String query = "update service set categorie = ? where id = ?";
        try {
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Service.getCategorie());
            statement.setInt(2, Service.getId());
            System.out.println(Service.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Service> Afficher (){
          String query = "SELECT * FROM service";
          ObservableList<Service> ServiceObservableList = FXCollections.observableArrayList();;
        try {
            Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query);
              while (rs.next())
      {
        int id = rs.getInt("id");
        String categorie = rs.getString("categorie");
        String description = rs.getString("description");
        Date date = rs.getDate("date");
        int pro_id = rs.getInt("Pro_id");
        Service s = new Service(categorie, description, "", pro_id , date);
        s.setId(id);
        ServiceObservableList.add(s);
        
      }
      st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServiceObservableList;
    }
    
    public Service getCategorie(int id ){
          String query = "SELECT * FROM service where id="+id;
          Service s = new Service();
        try {
            Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query);
              if (rs.next()){
                  String categorie = rs.getString("categorie");
        
                  s.setCategorie(categorie);
              }
      
        st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
     public int Count() {
        String req = "SELECT COUNT(*) AS total FROM service";
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
