/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui4;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Abonnement;
import org.controlsfx.control.Notifications;
import services1.ServiceAbonnement;
import dbconnection.Dbconnection;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AjoutAbonnementController implements Initializable {

    @FXML
    private TextField fxprix;
    @FXML
    private TextField fxtype;
    @FXML
    private Button btn;
    @FXML
    private Button retour;
    
    @FXML
    private ComboBox<String> combo_activite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcombo();
        // TODO
    }    
    @FXML
    private void retour(ActionEvent event) throws IOException {
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AcceuilAbonnementAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    @FXML
    private void AjouterAbonnement(ActionEvent event) {
        ServiceAbonnement sa = new ServiceAbonnement();
        int x = sa.getIdByNom(combo_activite.getValue());
        sa.ajouter(new Abonnement(fxprix.getText(),fxtype.getText(), x));
         Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text(" Abonnement ajouté")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();

    }
    public void fillcombo(){
            Connection cnx = Dbconnection.getInstance().getCnx();
          ObservableList<String> comboString= FXCollections.observableArrayList();
      // comboString = FXCollections.observableArrayList(); //Declared somewhere else
           String requete = "select nom  from  activite   ";
              try {
                  
            
            PreparedStatement pst = cnx.prepareStatement(requete);
                // pst.setInt(1, m.getId());
                ResultSet rs = pst.executeQuery();
          
           while(rs.next()){
             
comboString.add(rs.getString("nom"));
              
                       }
              
           combo_activite.setItems(comboString);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
     
}
  