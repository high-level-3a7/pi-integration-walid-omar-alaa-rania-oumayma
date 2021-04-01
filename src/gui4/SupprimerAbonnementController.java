/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Abonnement;
import org.controlsfx.control.Notifications;
import services1.ServiceAbonnement;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class SupprimerAbonnementController implements Initializable {

    @FXML
    private TextField fxID;
    @FXML
    private Button fxsupprimer;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void SupprimerAbonnemnt(ActionEvent event) {
        ServiceAbonnement sa = new ServiceAbonnement();
        sa.supprimer(new Abonnement( Integer.valueOf(fxID.getText())));
        Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text(" Abonnement supprimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(3));
               n.darkStyle();
               n.show();
    }
    
}
