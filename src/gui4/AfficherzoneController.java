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
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Abonnement;
import models.Zonedacces;
import services1.ServiceAbonnement;
import services1.Servicezone;
import dbconnection.Dbconnection;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AfficherzoneController implements Initializable {
     @FXML
    private Button retour;
    @FXML
    private TableView<Zonedacces> Table;
   
    @FXML
    private TableColumn<Zonedacces,String> nom;
    @FXML
    private TableColumn<Zonedacces,String> horaireouverture;
    @FXML
    private TableColumn<Zonedacces,String> horairecloture;
    
    ObservableList<Zonedacces> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM Zonedacces";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Zonedacces(rs.getString(2), rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Servicezone sz = new Servicezone();
       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        horaireouverture.setCellValueFactory(new PropertyValueFactory<>("horaireouverture"));
        horairecloture.setCellValueFactory(new PropertyValueFactory<>("Horairecloture"));
        Table.setItems(list);
        
    }    
      @FXML
    private void retour(ActionEvent event) throws IOException {
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AcceuilZone.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    } 
    
// TODO
    }    
    

