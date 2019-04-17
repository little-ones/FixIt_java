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
public class Service {
    
    private int id;
    
    private Date date ;
    
    private String categorie;
    
    private String description;
    
    private String image;
    
    private int pro_id;
    
    public int getId (){
        return this.id;
    }
    public String getCategorie(){
        return this.categorie;
    }
    public String getDescription(){
        return this.description;
    }
    public String getImage(){
        return  this.image;
    }
    public int getPro_id(){
        return this.pro_id;
    }
    public void setCategorie(String categorie){
        this.categorie = categorie;
    }
    public void setDescription(String Desc ){
        this.description = Desc;
    }
    public void setImage (String Image){
        
    }
    public void setPro_Id(int Pro){
        this.pro_id = Pro ;
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
    
    public Service(){
        this.date = Date.valueOf(LocalDate.now());
        
        
    }
    public Service (String categorie ,String Desc ,String image ,int Pro ){
        
        this.categorie = categorie ; 
        this.description = Desc;
        this.image = image ; 
        this.pro_id = Pro;
        this.date = Date.valueOf(LocalDate.now());
    }
    public Service (String categorie ,String Desc ,String image ,int Pro , Date date ){
        
        this.categorie = categorie ; 
        this.description = Desc;
        this.image = image ; 
        this.pro_id = Pro;
        this.date = date;
    }
    @Override
    public String toString() {
        return this.getCategorie();
    }
    
}
