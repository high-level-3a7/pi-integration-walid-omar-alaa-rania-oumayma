
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
import models.Zonedacces;
import dbconnection.Dbconnection;
import java.sql.Statement;

/**
 *
 * @author ouma
 */
       
public class Servicezone implements IService<Zonedacces> {
    
    Connection cnx = Dbconnection.getInstance().getCnx();
    private Statement ste ;
    private ResultSet result;

    @Override
    public void ajouter(Zonedacces t) {
        try {
            String requete = "INSERT INTO Zonedacces (nom,horaireouverture,horairecloture) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getHoraireouverture());
            pst.setString(3, t.getHorairecloture());
            pst.executeUpdate();
            System.out.println("Zonedacces ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Zonedacces t) {
        try {
            String requete = "DELETE FROM Zonedacces WHERE idzone=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdzone());
            pst.executeUpdate();
            System.out.println("Zone d'acces supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public void modifier(Zonedacces t,int idzone) {
        try {
            String requete = "UPDATE zonedacces SET Nom=?,Horaireouverture=?,Horairecloture=? WHERE idzone=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getHoraireouverture());
            pst.setString(3, t.getHorairecloture());
            pst.setInt(4, t.getIdzone());
            
            pst.executeUpdate();
            System.out.println("zonedacces modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Zonedacces> afficher() {
        List<Zonedacces> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Zonedaccees";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Zonedacces(rs.getString(2),rs.getString(3), rs.getString("horairecloture")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
    public Zonedacces getzonedacces(String idzone) throws SQLException {
        Zonedacces z = null;
        String req = "SELECT * FROM zonedacces WHERE idzone = '" + idzone + "'";
        ste = (cnx.createStatement());
        result = ste.executeQuery(req);
        while (result.next()) {
            z = new Zonedacces(result.getInt("idzone"), result.getString("nom"),result.getString("horaireouverture"),result.getString("horairecloture"));
        }
        return z;  
    }
}
