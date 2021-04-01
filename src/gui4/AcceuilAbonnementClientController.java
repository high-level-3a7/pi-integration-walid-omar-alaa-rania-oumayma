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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
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
public class AcceuilAbonnementClientController implements Initializable {

    @FXML
    private TableView<Abonnement> Table;
    @FXML
    private TableColumn<Abonnement,String> prix;
    @FXML
    private TableColumn<Abonnement,String> type;
    @FXML
    private TableColumn<Abonnement,Integer> activite;
    @FXML
    private TextField fxrecherche;
    @FXML
    private Button retour;
    ObservableList<Abonnement> list = FXCollections.observableArrayList();
    @FXML
    private Button gotouser;
    @FXML
    private Button gotoreclamation;
    @FXML
    private Button gotomateriel;
    @FXML
    private Button gotoemploi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM abonnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Abonnement(rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceAbonnement sa = new services1.ServiceAbonnement();
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        activite.setCellValueFactory(new PropertyValueFactory<>("Type_activite"));
    
        
     
        Table.setItems(list);
                
               fxrecherche.getText();
        
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        activite.setCellValueFactory(new PropertyValueFactory<>("Type_activite"));
       
       
        Table.setItems(list);
        
        FilteredList<Abonnement> filteredData = new FilteredList<>(list, b -> true);
        fxrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Abonnement -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Abonnement.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Abonnement.getType()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Abonnement.getActivite_id()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                               
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Abonnement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Table.comparatorProperty());
        Table.setItems(sortedData);
        } 
    
      @FXML
    private void retour(ActionEvent event) throws IOException {
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AcceuilBoutons.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    @FXML
    private void gotouser(MouseEvent event) {
         
    }

    @FXML
    private void gotoreclamation(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui1/Accueil.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }


    

    @FXML
    private void gotomateriel(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui/GestionMateriel.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
    
   

    @FXML
    private void gotoboutique(MouseEvent event) throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui2/Boutique.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }


    @FXML
    private void gotoemploi(MouseEvent event) throws IOException {
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui3/Acc1.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }
    
   
    }    
    

