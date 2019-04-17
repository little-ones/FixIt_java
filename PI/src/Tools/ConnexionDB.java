/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author MedAmine
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MedAmine
 */
public class ConnexionDB {
   private static ConnexionDB instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/fixit";
    private String username = "root";
    private String password = "";

    private ConnexionDB() {
        try {
            
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection etablit");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnexionDB getInstance()  {
        if(instance==null)
           instance=new ConnexionDB();
       return instance;
    } 
}

