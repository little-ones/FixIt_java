/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.HomePageController;
import Controller.Login;
import Entity.Demande;
import Entity.Pro;
import Entity.Service;
import Service.interfaces.Crud;
import Tools.ConnexionDB;
import java.sql.Date;
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
public class DemandeS implements Crud<Demande> {

    private static DemandeS instance;
    private Statement st;
    private ResultSet rs;

    private DemandeS() {
        ConnexionDB cs = ConnexionDB.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DemandeS getInstance() {
        if (instance == null) {
            instance = new DemandeS();
        }
        return instance;
    }

    @Override
    public void add(Demande d) {

        String req = "insert into demande (user_id,categorie_id,titre,budget,description,date) values "
                + "('" + d.getUserid() + "','" + d.getCategorie_id() + "'"
                + ",'" + d.getTitre() + "','" + d.getBudget() + "','" + d.getDescription() + "','" + d.getDate() + "')";
        try {
            st.executeUpdate(req);
            System.out.println("Demande bien ajouter ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from demande where id=" + id;
        try {
            st.executeUpdate(req);
            System.out.println("Demande bien supprimer ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
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
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
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
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public ArrayList<Demande> showAll() {
        ArrayList<Demande> l = new ArrayList<>();
        String req = "select * from demande where user_id="+Login.id;
        Demande d = new Demande();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                d = new Demande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getString(6), rs.getDate(7));
                System.out.println(d.ToString());
                l.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public Pro showuser(int id) {
        String req = "select * from fos_user where id=" + id;
        Pro p = null;
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                int idpro =rs.getInt(1);
                String username =rs.getString(2);
                String email =rs.getString(4);
                String password =rs.getString(8);
                String role =rs.getString(12);
                String nom =rs.getString(13);
                String prenom =rs.getString(14);
                String adresse= rs.getString(15);
                String tel =rs.getString(16);
                String img =rs.getString(17);
                Double rating = rs.getDouble(18);

                p = new Pro(idpro,username,email,password,role,nom,prenom,adresse,tel,img,rating);
                System.out.println(p.toString()+"--------------------");
                return p;
            }

            System.out.println("Pro by id ok ");
        } catch (SQLException ex) {
            System.out.println("Erreur de chargement de professionels ");
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public ArrayList<Service> showAllServices() {
        ArrayList<Service> l = new ArrayList<>();
        String req = "select * from service";
        Service s = new Service();
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                
                s = new Service(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt("Pro_id"));
                System.out.println(s.toString());
                l.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
 public void updaterating(Pro d1) {
        // String req="select * from demande where id="+d1.getId();
        
        String query = "update fos_user set rating='"+d1.getRating()+ "'where id=" + d1.getId();
        try {
            
            st.executeUpdate(query);
            System.out.println("rating succues ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void updateuser(Pro d1) {
        // String req="select * from demande where id="+d1.getId();
        
       String query = "update fos_user set nom='" + d1.getNom()
                + "',prenom='" + d1.getPrenom() + "',adresse='" + d1.getAdresse() + "',image_name='" + d1.getImage_name()
                + "',password='" + d1.getPassword() +"',telephone='" + d1.getTelephone() + "' where id=" + d1.getId();
        try {
            
            st.executeUpdate(query);
            System.out.println("rating succues ");
        } catch (SQLException ex) {
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public Service showservice(int id) {
        String req = "select * from service where id=" + id;
        Service s = null;
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                int idservice =rs.getInt(1);
                String categorie =rs.getString(2);
                Date date =rs.getDate(3);
                String imgname =rs.getString(4);
                String description =rs.getString(5);
                int proid =rs.getInt(6);
               

                s = new Service(idservice,categorie,date,imgname,description,proid);
                System.out.println(s.toString()+"--------------------");
                return s;
            }

            System.out.println("service by id ok ");
        } catch (SQLException ex) {
            System.out.println("Erreur de chargement des services ");
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
 public Pro showuserByusername(String user,String pwd) {
        String req = "select * from fos_user where username='" + user+"' and password ='"+pwd+"'";
        Pro p = null;
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);

            while (rs.next()) {
                int idpro =rs.getInt(1);
                String username =rs.getString(2);
                String email =rs.getString(4);
                String password =rs.getString(8);
                String role =rs.getString(12);
                String nom =rs.getString(13);
                String prenom =rs.getString(14);
                String adresse= rs.getString(15);
                String tel =rs.getString(16);
                String img =rs.getString(17);
                Double rating = rs.getDouble(18);

                p = new Pro(idpro,username,email,password,role,nom,prenom,adresse,tel,img,rating);
                System.out.println(p.toString()+"--------------------");
                return p;
            }

            System.out.println("Pro by id ok ");
        } catch (SQLException ex) {
            System.out.println("Erreur de chargement de professionels ");
            Logger.getLogger(DemandeS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
