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
import services1.ServiceAbonnement;
import dbconnection.Dbconnection;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AfficherAbonnementController implements Initializable {
    @FXML
    private Button retour;
    @FXML
    private TableView<Abonnement> Table;
   
    @FXML
    private TableColumn<Abonnement,String> Prix;
    @FXML
    private TableColumn<Abonnement,String> Type;
    @FXML
    private TableColumn<Abonnement,String> activite;
     ServiceAbonnement sa = new ServiceAbonnement();
    ObservableList<Abonnement> list = FXCollections.observableArrayList(sa.afficher());
    
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ServiceAbonnement sa = new ServiceAbonnement();
        
       list = (ObservableList<Abonnement>) sa.afficher();
       
       
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        activite.setCellValueFactory(new PropertyValueFactory<>("Type_activite"));
        Table.setItems(list);
        
    }    
        // TODO
        
//    @FXML
//    private void retour(ActionEvent event) throws IOException {
//           Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/AcceuilAbonnementAdmin.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//        
//        //This line gets the Stage information
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(tableViewScene);
//        window.show();
//        
//    } 

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AcceuilAbonnementAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
}
