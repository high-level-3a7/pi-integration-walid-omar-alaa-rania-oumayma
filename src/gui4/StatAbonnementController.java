/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui4;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import dbconnection.Dbconnection;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class StatAbonnementController implements Initializable {

    
    Connection cnx = Dbconnection.getInstance().getCnx();
    Statement stm, stm1, stm2, stm3;
    private PieChart piechart;
   
    private final ObservableList <PieChart.Data> details =FXCollections.observableArrayList();
    @FXML
    private BorderPane pane1;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            float nbrAbonnement = 0;
            float nbrGold =0;
            float nbrPremium =0;
            float nbrKid =0;
            float nbrPINK =0;
            
            
            String req = "SELECT * FROM `abonnement`";
            stm = cnx.createStatement();
            ResultSet result = stm.executeQuery(req);
            while (result.next()) {
                nbrAbonnement +=1;
            }
            String req1 = "SELECT * FROM `abonnement` WHERE `type` = 'GOLD'";
            stm1 = cnx.createStatement();
            ResultSet result1 = stm1.executeQuery(req1);
            while (result1.next()) {
                nbrGold +=1;
            }
            String req2 = "SELECT * FROM `abonnement` WHERE `type` = 'Premium'";
            stm2 = cnx.createStatement();
            ResultSet result2 = stm2.executeQuery(req2);
            while (result2.next()) {
                nbrPremium +=1;
            }
            String req3 = "SELECT * FROM `abonnement` WHERE `type` = 'KID'";
            stm3 = cnx.createStatement();
            ResultSet result3 = stm3.executeQuery(req3);
            while (result3.next()) {
                nbrKid +=1;
            }
            String req4 = "SELECT * FROM `abonnement` WHERE `type` = 'PINK'";
            stm3 = cnx.createStatement();
            ResultSet result4 = stm3.executeQuery(req4);
            while (result4.next()) {
                nbrPINK +=1;
            }
            System.out.println(nbrGold);
            System.out.println(nbrPremium);
            System.out.println(nbrKid);
            System.out.println(nbrPINK);
            
            details.addAll(new PieChart.Data("GOLD\n"+(nbrGold/(nbrAbonnement))*100+"%",nbrGold ),new PieChart.Data("Premium\n"+(nbrPremium/(nbrAbonnement))*100+"%",nbrPremium ),new PieChart.Data("KID\n"+(nbrKid/(nbrAbonnement))*100+"%",nbrKid ),new PieChart.Data("PINK\n"+(nbrPINK/(nbrAbonnement))*100+"%",nbrPINK ))  ;
            piechart=new PieChart();
            piechart.setData(details);
            piechart.setTitle("statistique des abonnements");
            piechart.setLabelsVisible(true);
                
            pane1.setCenter(piechart);
        } catch (SQLException ex) {
            Logger.getLogger(StatAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
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
}
