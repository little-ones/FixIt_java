/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author MedAmine
 */
import Entity.Demande;
import Entity.Offre;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.Date;
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
 * @author USER
 */
public class OffreS {

    private static OffreS instance;
    private ResultSet rs;
    private Statement st;

    public OffreS() {
        ConnexionDB cs = ConnexionDB.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(OffreS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OffreS getInstance() {
        if (instance == null) {
            instance = new OffreS();
        }
        return instance;
    }

    public Offre show(int id) {
        String req = "select * from offre where id=" + id;
        Offre o = new Offre();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                
                int ido = rs.getInt("id");
                int categorie = rs.getInt("service_id");
                String Titre = rs.getString("titre");
                int Budget = rs.getInt("budget");
                String description = rs.getString("description");
                Date date = rs.getDate("date_ajout");
                int pro_id = rs.getInt("user_id");
                o =new Offre(ido, categorie, pro_id, date, Budget, Titre, description);
            }
            System.out.println(o.toString());
            System.out.println("Offre by id ok ");
        } catch (SQLException ex) {
            Logger.getLogger(OffreS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

   
    public ArrayList<Offre> showAll() {
        ArrayList<Offre> l = new ArrayList<>();
        String req = "select * from offre";
        Offre o = new Offre();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                int id = rs.getInt("id");
                int categorie = rs.getInt("service_id");
                String Titre = rs.getString("titre");
                int Budget = rs.getInt("budget");
                String description = rs.getString("description");
                Date date = rs.getDate("date_ajout");
                int pro_id = rs.getInt("user_id");
                o =new Offre(id, categorie, pro_id, date, Budget, Titre, description);
                System.out.println(o.toString());
                l.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    
}
