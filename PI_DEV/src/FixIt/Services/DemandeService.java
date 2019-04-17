/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Services;


import FixIt.Entities.Demande;
import FixIt.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MedAmine
 */
public class DemandeService  {

    private static DemandeService instance;
    private Statement st;
    private ResultSet rs;

    private DemandeService() {
        Connection cs = DataSource.getInstance().getConnection();
        try {
            st = cs.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DemandeService getInstance() {
        if (instance == null) {
            instance = new DemandeService();
        }
        return instance;
    }

    public void add(Demande d) {

        String req = "insert into demande (user_id,categorie_id,titre,budget,description,date) values "
                + "('" + d.getUserid() + "','" + d.getCategorie_id() + "'"
                + ",'" + d.getTitre() + "','" + d.getBudget() + "','" + d.getDescription() + "','" + d.getDate() + "')";
        try {
            st.executeUpdate(req);
            System.out.println("Demande bien ajouter ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) {
        String req = "delete from demande where id=" + id;
        try {
            st.executeUpdate(req);
            System.out.println("Demande bien supprimer ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void update(Demande d1) {
        // String req="select * from demande where id="+d1.getId();
        System.out.println("--------->" + d1.getDate());
        String query = "update demande set categorie_id=" + d1.getCategorie_id()
                + ",titre='" + d1.getTitre() + "',budget=" + d1.getBudget() + ",description='" + d1.getDescription()
                + "',date='" + d1.getDate() + "' where id=" + d1.getId();

        /*String req2="insert into demande (categorie_id,titre,budget,description,date) values "
                 + "('"+d1.getCategorie_id()+"'"
                 + ",'"+d1.getTitre()+"','"+d1.getBudget()+"','"+d1.getDescription()+
                 "','"+d1.getDate()+"') where id="+d1.getId();*/
        try {
            /*System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);
            Demande d =new Demande();
            while(rs.next())
              d = new Demande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6),rs.getDate(7));*/
            //System.out.println(d.ToString());

            st.executeUpdate(query);
            System.out.println("Modifier avec succees");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public Demande show(int id) {
        String req = "select * from demande where id=" + id;
        Demande d = new Demande();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Demande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getDate(7));
            }
            System.out.println(d.ToString());
            System.out.println("Demande by id ok ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    
    public ObservableList<Demande> showAll() {
        
        ObservableList<Demande> l = FXCollections.observableArrayList();
        String req = "select * from demande";
        Demande d = new Demande();
        
        try {
            
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Demande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getDate(7));
                l.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public int Count() {
        String req = "SELECT COUNT(*) AS total FROM demande";
        int count = 0;
        try {
           
            rs = st.executeQuery(req);

            rs.next();
            count = rs.getInt("total");
            

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

   

}
