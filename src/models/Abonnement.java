/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import services1.ServiceAbonnement;

/**
 *
 *
 */
public class Abonnement {
    ServiceAbonnement sa = new ServiceAbonnement();

    

    private int id ;
    private String prix;
    private String type;
    private int activite_id; 
    private String Type_activite;

    public Abonnement(String text, String text0, int x, Integer valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Abonnement(String text, String text0, int x, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType_activite() {
        return Type_activite;
    }

    public void setType_activite(String Type_activite) {
        this.Type_activite = Type_activite;
    }

    public Abonnement(int id) {
        this.id = id;
    }
    public Abonnement(int id,String prix, String type,int activite_id) {
        
        this.id=id;
        this.prix = prix;
        this.type = type;
        this.activite_id=activite_id;
        this.Type_activite=sa.getNombyid(activite_id);
        
            }


    public Abonnement(String prix, String type,int activite_id) {
        this.prix = prix;
        this.type = type;
        this.activite_id=activite_id;
        this.Type_activite=sa.getNombyid(activite_id);
    }
    
    

    public Abonnement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Abonnement(String text, String text0, String text1, float parseFloat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
    public ServiceAbonnement getSa() {
        return sa;
    }

    public void setSa(ServiceAbonnement sa) {
        this.sa = sa;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(int activite) {
        this.activite_id = activite_id;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "sa=" + sa + ", id=" + id + ", prix=" + prix + ", type=" + type + ", activite_id=" + activite_id + ", Type_activite=" + Type_activite + '}';
    }

    
   
    
    
   
}
