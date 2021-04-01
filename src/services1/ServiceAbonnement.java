/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Abonnement;
import models.Zonedacces;
import dbconnection.Dbconnection;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tests.MainGui;
/**
 *
 * @author ouma
 */

public class ServiceAbonnement implements IService<Abonnement> {

    Connection cnx = Dbconnection.getInstance().getCnx();
    private Statement ste ;
    private ResultSet result;

    @Override
    public void ajouter(Abonnement t) {
        try {
            String requete = "INSERT INTO Abonnement (prix,type,activite_id) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getPrix());
            pst.setString(2, t.getType());
            pst.setInt(3, t.getActivite_id());
            pst.executeUpdate();
            System.out.println("Abonnement ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Abonnement t) {
        try {
            String requete = "DELETE FROM abonnement WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Abonnement supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public void modifier(Abonnement t, int id) {
        try {
            String requete = "UPDATE abonnement SET  prix=?,type=?, activite_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getPrix());
            pst.setString(2, t.getType());
            pst.setInt(3, t.getActivite_id());
            pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Abonnement modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
   @Override
    public ObservableList<Abonnement> afficher() {
        ObservableList<Abonnement> list =FXCollections.observableArrayList();;

        try {
            String requete = "SELECT * FROM abonnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Abonnement (rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

        } catch 
 (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
    
   public String getNombyid(int activite_id){
        
          
           String ch="";
            try {
                   String requete = "SELECT nom FROM activite where id=? ";
            
            PreparedStatement pst = cnx.prepareStatement( requete);
            pst.setInt(1, activite_id);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
              
               ch = rs.getString("Nom");
}
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        int x = 0;
        
       return ch ;
    }
//        int x = 0;
        
    
   
   public int getIdByNom(String ch){
        
        int x = 0;
        try {
            String requete = "SELECT id FROM activite where nom=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                x=rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x ;}
     



    
    
  /*  public Abonnement getabonnement(int id) throws SQLException {
        Abonnement a = null;
        String req = "SELECT * FROM abonnement WHERE id= '" + id + "'";
        ste =cnx.createStatement();
        result = ste.executeQuery(req);
        while (result.next()) {
            a = new Abonnement(result.getString("prix"), result.getString("type"), result.getString("activite"),
                    result.getInt("id"));
        }
        return a;  
    }

    public boolean abonnement(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
    public List<Abonnement> readAllAbonnementSortedByType() {

        List<Abonnement> lu = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery("select id from Abonnement order by type  ");
            while (rs.next()) {
                int id = rs.getInt("id");
                
                Abonnement t = new Abonnement(id);
                lu.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lu;
    }

    public void modifier(Abonnement abonnement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
//    public List<Abonnement> Search(String charac) {
//           String requete="select * from abonnement where id LIKE '%"+charac+"%'or prix LIKE '%"+charac+"%' or type LIKE '%"+charac+"%' or activite LIKE '%"+charac+ "'"  ;
//           
//           List<Abonnement> Abonnement = new ArrayList<>();
//        try {
//            Statement stm=cnx.createStatement();
//            ResultSet rst=stm.executeQuery(requete);           
//     while(rst.next()) 
//    {       
//        //System.out.println("Event : "+rst.getString("description")+"\tMedia :"+rst.getString("source")+"\tNombre de J'aime :"+rst.getInt("nbrlikes") );
// 
//            Abonnement result = new Abonnement();
//            
//            result.setPrix(rst.getString("prix"));
//            result.setType(rst.getString("type"));
//            result.setActivite(rst.getString("activite"));
//            Abonnement.add(result);
//          
//    }
//        } catch (SQLException ex) {
//            System.out.println("No abonnement Available !");
//        } 
//        return Abonnement;   
//    }
    
    
   

   

   

