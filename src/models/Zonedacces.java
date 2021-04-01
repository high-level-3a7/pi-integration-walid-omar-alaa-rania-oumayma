/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ouma
 */
public class Zonedacces {
    private int idzone ;
    private String nom;
    private String horaireouverture ;
    private String horairecloture ;
    
    
    public Zonedacces(int idzone){
        this.idzone=idzone;
    }
    
    public Zonedacces(int idzone,String nom,String horaireouverture,String horairecloture){
    this.idzone=idzone ;
    this.nom=nom;
    this.horaireouverture=horaireouverture;
    this.horairecloture=horairecloture ; }
    
    public Zonedacces(String nom, String horaireouverture,String horairecloture){
    this.nom=nom;    
    this.horaireouverture=horaireouverture;
    this.horairecloture=horairecloture;
    }

    public int getIdzone() {
        return idzone;
    }

    public void setIdzone(int idzone) {
        this.idzone = idzone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getHoraireouverture() {
        return horaireouverture;
    }

    public void setHoraireouverture(String horaireouverture) {
        this.horaireouverture = horaireouverture;
    }

    public String getHorairecloture() {
        return horairecloture;
    }

    public void setHorairecloture(String horairecloture) {
        this.horairecloture = horairecloture;
    }

    @Override
    public String toString() {
        return "Zonedacces{" + "idzone=" + idzone + ", nom=" + nom + ", horaireouverture=" + horaireouverture + ", horairecloture=" + horairecloture + '}';
    }

   
    
    
  
    
}
