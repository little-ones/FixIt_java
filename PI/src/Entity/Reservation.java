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
public class Reservation {
    private int id;
    private int idclient;
    private int idpro;
    private Date datedebut;
    private Date datefin;
    private float budget;
    private String categorie;
    private String etat;
    public Reservation(){}
    public Reservation( int id, Date datedebut, Date datefin,float budget, String categorie, String etat, int idclient, int idpro){
        this.id = id;
        this.idclient = idclient;
        this.idpro = idpro;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.budget = budget;
        this.categorie = categorie;
        this.etat = etat;
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
     * @return the idclient
     */
    public int getIdclient() {
        return idclient;
    }

    /**
     * @param idclient the idclient to set
     */
    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    /**
     * @return the idpro
     */
    public int getIdpro() {
        return idpro;
    }

    /**
     * @param idpro the idpro to set
     */
    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    /**
     * @return the datedebut
     */
    public Date getDatedebut() {
        return datedebut;
    }

    /**
     * @param datedebut the datedebut to set
     */
    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    /**
     * @return the datefin
     */
    public Date getDatefin() {
        return datefin;
    }

    /**
     * @param datefin the datefin to set
     */
    public void setDatefin(Date datefin) {
        this.datefin = datefin;
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
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String toString()
    {
        return this.id+" "+this.idclient+" "+this.idpro+" "+this.categorie+" "+this.etat+" "+this.budget;
    }
    
}
