/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui3;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dbconnection.Dbconnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import services.ServiceEmploi;
import models.Emploi;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class IEAfficherController implements Initializable {

    @FXML
    private TableView<Emploi> TVE;
    @FXML
    private TableColumn<Emploi, Integer> ColumnEm;
    @FXML
    private TableColumn<Emploi, Integer> ColumnSe;
    @FXML
    private TableColumn<Emploi, Integer> Columnzo;
    
    @FXML
    private TableColumn<Emploi, String> ColumnD;
    
    @FXML
    private Button butA;
    ObservableList<Emploi> list = FXCollections.observableArrayList();
    @FXML
    private Button btnm;
    @FXML
    private Button btnSE;
    @FXML
    private Button btnSE1;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TextField tfSE;
    @FXML
    private Button telecharger;
    @FXML
    private Button mail;
    @FXML
    private Button retourp;
    @FXML
    private Button gotouser;
    @FXML
    private Button gotoreclamation;
    @FXML
    private Button gotocommande;
    @FXML
    private Button gotoabonnement;
    @FXML
    private Button gotoemploi;
    @FXML
    private Button gotomateriel;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceEmploi em = new services.ServiceEmploi();
          
        list = (ObservableList<Emploi>) em.afficher();
        
        ColumnEm.setCellValueFactory(new PropertyValueFactory<>("IdEmploi"));
        ColumnSe.setCellValueFactory(new PropertyValueFactory<>("IdSeance"));
        Columnzo.setCellValueFactory(new PropertyValueFactory<>("idzone"));
        ColumnD.setCellValueFactory(new PropertyValueFactory<>("DateE"));
        
     
        TVE.setItems(list);
                
               tfrecherche.getText();
        
        ColumnEm.setCellValueFactory(new PropertyValueFactory<>("IdEmploi"));
        ColumnSe.setCellValueFactory(new PropertyValueFactory<>("IdSeance"));
        Columnzo.setCellValueFactory(new PropertyValueFactory<>("idzone"));
        ColumnD.setCellValueFactory(new PropertyValueFactory<>("DateE"));
       
        TVE.setItems(list);
        
        FilteredList<Emploi> filteredData = new FilteredList<>(list, b -> true);
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Emploi -> {
                            if (newValue == null || newValue.isEmpty()) {
					return true;
                        }
        String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Emploi.getIdEmploi()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Emploi.getIdSeance()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Emploi.getIdzone()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(Emploi.getDateE()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				     else  
				    	 return false; 
                                });
                        
		});
        
        SortedList<Emploi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TVE.comparatorProperty());
        TVE.setItems(sortedData);
        } 
                
                
                
                
             

        private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();   
        } catch (IOException e) {
        }}
        
        

   

    @FXML
    private void SuppE(ActionEvent event) {
         ServiceEmploi ec = new ServiceEmploi();
        ec.supprimer(new Emploi((int) Float.parseFloat(tfSE.getText())));
Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Emploi du temps supprimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
    }
    @FXML
    private void AffichE(MouseEvent event) {
        try {
             list .clear();
            Connection cnx = Dbconnection.getInstance().getCnx();
             String requete = "SELECT * FROM emploi_du_temps ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Emploi(rs.getInt("IdEmploi"), rs.getInt("IdSeance"), rs.getInt("idzone"), rs.getString("DateE")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @FXML
    private void modifB(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/IEModifier.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void AjoutB(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui3/IEAjout.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    


    

    @FXML
    private void telecharger(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException {
    
        try {
            String file_name="D:\\PDF\\Emploi.pdf"; 
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            Connection cnx = Dbconnection.getInstance().getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from emploi_du_temps"; 
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("IdEmploi"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("IdSeance"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("idzone"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("DateE"));
            t.addCell(c4);
            t.setHeaderRows(1);
            while(rs.next()){
                t.addCell(rs.getString(1));
                t.addCell(rs.getString(2));
                t.addCell(rs.getString(3));
                t.addCell(rs.getString(4));
                document.add(t);
            }
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  PDF téléchargé vérifier votre dossier")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
        //JOptionPane.showMessageDialog(null,"PDF téléchargée vérifier votre dossier");
        //Notification.showNotif("Pdf Téléchargé","vérifier votre dossier :D");
    }

    @FXML
    private void mailB(MouseEvent event) throws SQLException {
        Connection cnx = Dbconnection.getInstance().getCnx();
        try {
            ServiceEmploi ep = new services.ServiceEmploi();
            String requete = "SELECT * FROM emploi_du_temps";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ep.sendmail(new Emploi(rs.getInt("IdEmploi"), rs.getInt("IdSeance"), rs.getInt("idzone"), rs.getString("DateE")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Mail envoyé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show(); 
    }

    @FXML
    private void retourp(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == retourp) {
            loadStage("/gui3/Acc2.fxml");
         }
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
    private void gotoabonnement(MouseEvent event) throws IOException {
        Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui4/Accueil.fxml"));
            Scene sceneacc2 = new Scene(parentacc2);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(sceneacc2);
            stage.show();
    }

    @FXML
    private void gotoemploi(MouseEvent event)   throws IOException {
         Parent parentacc2 = FXMLLoader.load(getClass().getResource("../gui3/Acc2.fxml"));
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

    }


    
    
