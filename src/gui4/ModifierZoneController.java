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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Zonedacces;
import services1.Servicezone;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ModifierZoneController implements Initializable {

    @FXML
    private TextField Horaireouverture;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Horairecloture;
    @FXML
    private TextField idzone;
    @FXML
    private Button Modifierzone;
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
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui4/AcceuilZone.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    } 
    @FXML
    private void ModifierZone(ActionEvent event) {
         Servicezone sz = new Servicezone();
        sz.modifier(new Zonedacces(Nom.getText(),Horaireouverture.getText(),Horairecloture.getText()),Integer.valueOf(idzone.getText()));
        JOptionPane.showMessageDialog(null, "Zone modifié");
    
    }
    
}
