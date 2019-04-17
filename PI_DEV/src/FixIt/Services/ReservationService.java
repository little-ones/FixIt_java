/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Services;


import FixIt.Entities.Reservation;
import FixIt.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ReservationService{

    private static ReservationService instance;
    private Statement st;
    private ResultSet rs;

    private ReservationService() {
        Connection cs = DataSource.getInstance().getConnection();
        try {
            st = cs.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    
    public void add(Reservation d) {
        String req = "insert into reservation (Client_id,Pro_id,datedebut,datefin,budget,etat,categorie) values "
                + "('" + d.getIdclient() + "','" + d.getIdpro() + "','" + d.getDatedebut() + "','" + d.getDatefin() + "','" + d.getBudget() + "','" + d.getEtat() + "','" + d.getCategorie() + "')";
        try {
            
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void delete(int id) {
        String req = "delete from reservation where id=" + id;
        try {
            
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void update(Reservation r) {
        
        String query = "update reservation set id=" + r.getId()
                + ",datedebut='" + r.getDatedebut() + "',datefin='" + r.getDatefin() + "',budget='" + r.getBudget()
                + "',categorie='" + r.getCategorie()+ "' where id=" + r.getId();
        
        try {

            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public Reservation show(int id) {
        String req = "select * from reservation where id=" + id;
        Reservation d = new Reservation();
        try {
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    
    public ObservableList<Reservation> showAll() {
        
        ObservableList<Reservation> lr =  FXCollections.observableArrayList();
        String req = "select * from reservation ";
        Reservation d = new Reservation();
        try {
            
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                lr.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }
    
    public void Accepter (int id ){
        
        String Accepter = "Accepté";
        String query = "update reservation set etat ='" +Accepter+"' where id="+id;
               
        try {
            
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int Count() {
        String req = "SELECT COUNT(*) AS total FROM reservation";
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
