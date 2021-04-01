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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Abonnement;
import models.Zonedacces;
import services1.Servicezone;
import dbconnection.Dbconnection;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AcceuilZoneController implements Initializable {

    @FXML
    private TableView<Zonedacces> Table;
    @FXML
    private TableColumn<Zonedacces,String> nom;
    @FXML
    private TableColumn<Zonedacces,String> horaireouverture;
    @FXML
    private TableColumn<Zonedacces,String> horairecloture;
    @FXML
    private TextField fxrecherche;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Afficher;
    @FXML
    private Button Supprimer;
    @FXML
    private Button retour;
    
    ObservableList<Zonedacces> list = FXCollections.observableArrayList();
    private Window primaryStage;
    @FXML
    private Button gotouser;
    @FXML
    private Button gotoreclamation;
    @FXML
    private Button gotocommande;
    @FXML
    private Button gotomateriel;
    @FXML
    private Button gotoemploi;
    @FXML
    private Button BPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM zonedacces";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Zonedacces(rs.getString("nom"), rs.getString("horaireouverture"), rs.getString("horairecloture")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Servicezone em = new Servicezone();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        horaireouverture.setCellValueFactory(new PropertyValueFactory<>("horaireouverture"));
        horairecloture.setCellValueFactory(new PropertyValueFactory<>("horairecloture"));
    
        
     
        Table.setItems(list);
                
               fxrecherche.getText();
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        horaireouverture.setCellValueFactory(new PropertyValueFactory<>("horaireouverture"));
        horairecloture.setCellValueFactory(new PropertyValueFactory<>("horairecloture"));
       
       
        Table.setItems(list);
        
        FilteredList<Zonedacces> filteredData = new FilteredList<>(list, b -> true);
        fxrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Zonedacces -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Zonedacces.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Zonedacces.getHoraireouverture()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Zonedacces.getHorairecloture()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                               
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Zonedacces> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Table.comparatorProperty());
        Table.setItems(sortedData);
        } 
    
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();   
        } catch (IOException e) {
        }
        // TODO
    }    
    @FXML    
    private void BPDF(ActionEvent event) {
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.Table;
           job.printPage(root);
           job.endJob(); }}
    
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
    private void Ajouter(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AjouterZonedacces.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
      
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/ModifierZone.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/Afficherzone.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
       
    }

    @FXML
    private void Supprimer(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/SupprimerZone.fxml"));
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
    private void gotocommande(MouseEvent event) throws IOException {
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
