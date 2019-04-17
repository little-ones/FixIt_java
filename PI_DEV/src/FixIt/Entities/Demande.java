/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;

/**
 *
 * @author MedAmine
 */
public class Demande {
    private int id;
    private int userid;
    private int categorie_id;
    private String titre;
    private float budget;
    private String description;
    private Date date;
    public Demande(){}
    public Demande(int id,int userid,int catid,String titre,float budget,String desc,Date date){
        this.id = id;
        this.userid = userid;
        this.categorie_id = catid;
        this.titre = titre;
        this.budget = budget;
        this.description = desc;
        this.date = date;
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
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the categorie_id
     */
    public int getCategorie_id() {
        return categorie_id;
    }

    /**
     * @param categorie_id the categorie_id to set
     */
    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
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
     * @return the budget
     */
    public float getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
    public String ToString()
    {
        return this.id+" "+this.userid+" "+this.categorie_id+" "+this.titre+" "+this.budget+" "+this.description;
    }
    
}
