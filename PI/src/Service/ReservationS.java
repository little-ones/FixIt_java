/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Demande;
import Entity.Reservation;
import Service.interfaces.Crud;
import Tools.ConnexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MedAmine
 */
public class ReservationS implements Crud<Reservation> {

    private static ReservationS instance;
    private Statement st;
    private ResultSet rs;

    private ReservationS() {
        ConnexionDB cs = ConnexionDB.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ReservationS getInstance() {
        if (instance == null) {
            instance = new ReservationS();
        }
        return instance;
    }

    @Override
    public void add(Reservation d) {
        String req = "insert into reservation (Client_id,Pro_id,datedebut,datefin,budget,etat,categorie) values "
                + "('" + d.getIdclient() + "','" + d.getIdpro() + "','" + d.getDatedebut() + "','" + d.getDatefin() + "','" + d.getBudget() + "','" + d.getEtat() + "','" + d.getCategorie() + "')";
        try {
            st.executeUpdate(req);
            System.out.println("Réservation bien ajouter ");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from reservation where id=" + id;
        try {
            st.executeUpdate(req);
            System.out.println("Réservation bien supprimer ");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Reservation r) {
        // String req="select * from demande where id="+d1.getId();
        System.out.println("--------->" + r.toString());
        String query = "update reservation set id=" + r.getId()
                + ",datedebut='" + r.getDatedebut() + "',datefin='" + r.getDatefin() + "',budget='" + r.getBudget()
                + "',categorie='" + r.getCategorie()+ "' where id=" + r.getId();

        
        try {
             //System.out.println(d.ToString());

            st.executeUpdate(query);
            System.out.println("Modifier reservation avec succees");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Reservation show(int id) {
        String req = "select * from reservation where id=" + id;
        Reservation d = new Reservation();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
            }
            System.out.println(d.toString());
            System.out.println("Reservation by id ok ");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public ArrayList<Reservation> showAll() {
        ArrayList<Reservation> lr = new ArrayList<>();
        String req = "select * from reservation ";
        Reservation d = new Reservation();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                lr.add(d);
            }
            System.out.println(d.toString());
            System.out.println("Reservation all ok ");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }

}
