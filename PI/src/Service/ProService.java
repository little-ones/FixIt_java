/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Demande;
import Entity.Pro;
import Entity.Reservation;
import Service.interfaces.Crud;
import Tools.ConnexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author MedAmine
 */
public class ProService {
    
    private static ProService instance;
    private Statement st;
    private ResultSet rs;
    private ProService() {
        ConnexionDB cs=ConnexionDB.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ProService getInstance(){
        if(instance==null) 
            instance=new ProService();
        return instance;
    }

 public ArrayList<Pro> showAll() {
        ArrayList<Pro> lr= new ArrayList<>();
        String req="select * from fos_user";
        Pro  d = null ;
        try {
            System.out.println("okkkkkkkkkkkkkkkkkkk ");
            rs = st.executeQuery(req);
            
            while(rs.next())
            {
                 d = new Pro(rs.getInt(1),rs.getString(2),rs.getString(4),
                         rs.getString(8),rs.getString(12),rs.getString(13),
                         rs.getString(14),rs.getString(15),rs.getString(16)
                         ,rs.getString(17),rs.getDouble(18));
                lr.add(d);
                
            }
            
            
            System.out.println("user all ok ");
        } catch (SQLException ex) {
            Logger.getLogger(ProService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }
   
}
