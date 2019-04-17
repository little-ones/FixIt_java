/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixIt.Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Offre {
    
    private int id;
    
    private int categorie;
    
    private int pro_id;
    
    private Date date ;
    
    private int budget;
    
    private String titre;
    
    private String description;
    
    
    
    public int getId (){
        return this.id;
    }
    public int getCategorie(){
        return this.categorie;
    }
    public String getDescription(){
        return this.description;
    }
    
    public String getTitre(){
        return this.titre;
    }
   
    public int getBudget (){
        return this.budget;
    }
    
    public int getPro_id(){
        return this.pro_id;
    }
    
    public void setCategorie(int categorie){
        this.categorie = categorie;
    }
    public void setDescription(String Desc ){
        this.description = Desc;
    }
    public void setTitre (String titre){
        this.titre = titre;
    }
    public void setPro_Id(int Pro){
        this.pro_id = Pro ;
    }
    public void setBudget(int Budget){
        this.budget=Budget;
    }
    public void setId(int id){
        this.id = id ;
    }
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date ){
        this.date = date ;
    }
    
    public Offre(){
        this.date = Date.valueOf(LocalDate.now());
        
        
    }
    public Offre (int categorie ,String Desc ,String titre , int budget ,int Pro ){
        
        this.categorie = categorie ; 
        this.description = Desc;
        this.titre = titre ; 
        this.budget = budget;
        this.pro_id = Pro;
        this.date = Date.valueOf(LocalDate.now());
    }
    public Offre (int id ,int categorie ,String titre ,String Desc ,String image ,int Pro ,int budget, Date date ){
        this.id=id;
        this.categorie = categorie ; 
        this.description = Desc;
        this.titre = titre ; 
        this.budget = budget;
        this.pro_id = Pro;
        this.date = date;
    }
    
    
}
