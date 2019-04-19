/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MedAmine
 */
public class Pro {
  private int id;
  private String username;
  private String email;
  private String password;
  private String  roles;
  private String nom; 
  private String prenom; 
  private String adresse;
  private String telephone;
  private String image_name;
  private double rating; 
    public Pro(){}
 public Pro(int id,String username,String email,String password,String roles,String nom,String prenom,String adresse,String telephone,String image_name,double rating){
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.roles = roles;
      this.nom = nom;
      this.prenom = prenom;
      this.adresse = adresse;
      this.telephone = telephone;
      this.image_name = image_name;
      this.rating = rating;
      
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the image_name
     */
    public String getImage_name() {
        return image_name;
    }

    /**
     * @param image_name the image_name to set
     */
    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

 public String toString()
 {
     return this.username+" ---------------------------------------------"+this.id+"Imagename:"+this.image_name;
 }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
    

   

   
 
   
}
