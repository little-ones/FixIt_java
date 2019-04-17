/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author MedAmine
 */
public class Service {
    private int id;
    private String titre;
    private Date date;
    private String img;
    private String description;
    private int idpro;
    public Service(int id,String titre,Date date,String img,String description,int idpro){
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.img = img;
        this.description = description;
        this.idpro = idpro;
        
    }

    public Service() {
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
