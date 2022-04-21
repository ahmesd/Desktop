/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Formation;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidevv.Datasource;
import service.formationcrud;
import utils.DataValidationUtils;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class Ajouter_formationController implements Initializable {

    @FXML
    private AnchorPane show;
    @FXML
    private TextField nom;
    @FXML
    private TextField numero;
    @FXML
    private TextField location;
    @FXML
    private Text nomfor;
    @FXML
    private Text price;
    @FXML
    private DatePicker date_depart;
    @FXML
    private DatePicker date_fin;
    @FXML
    private Text descri;
    @FXML
    private Button ajouter;
    @FXML
    private Button consulter;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    
     PreparedStatement preparedStatement;
    private Statement st;
    private ResultSet rs;
  
    Connection cnx = Datasource.getInstance().getConnection();
    formationcrud eventcru = formationcrud.getInstance();
    //PromotionCrud pr = PromotionCrud.getInstance()
    @FXML
    private Button tfphoto;
    @FXML
    private Text imge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_event(ActionEvent event) {
        if (nom.getText().isEmpty() || numero.getText().isEmpty() || location.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Check your fields! ");
                alert.show();
        
               System.out.println("Fields Are Empty");
            // show.setText("Enter all details");

        }else 
        if (!DataValidationUtils.isNomValid(nom.getText().replaceAll("\\s", ""))) {
                nom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                System.out.println("Formation name is invalid");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("invalid product name!");
                alert.show();
        }else 
        if (!DataValidationUtils.isDescriptionValid(location.getText().replaceAll("\\s", ""))) {
                location.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                System.out.println("Description is invalid");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("invalid product name!");
                alert.show();
        }else 
        if (!DataValidationUtils.price(numero.getText().replaceAll("\\s", ""))) {
                numero.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                System.out.println("Prix is invalid");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("invalid product name!");
                alert.show();}

        
        else {
            String nom_event = nom.getText();
            int num = parseInt(numero.getText());
            String phot=tfphoto.getText();
           
            String loc = location.getText();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            //String date_dep = date_depart.getValue().toString();
            Date date_depar = java.sql.Date.valueOf( date_depart.getValue());
           // String date_f = date_fin.getValue().toString();
            Date date_fin1 = java.sql.Date.valueOf(date_fin.getValue());
            System.out.println(" date depare de type date " + date_depar);
            //System.out.println("date de depare de type string" + date_dep);
          //  System.out.println("date fin de type sting" + date_f);
            System.out.print("date fin de type date" + date_fin1);

            Formation ev = new Formation(nom_event, loc,phot, num,date_depar,date_fin1);
            eventcru.ajouterFormationPST(ev);
              
            
            
               System.err.println("Added Seccessfully");
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Formation added successfuly!");
                        alert.show();
                        nom.setText("");
                        numero.setText("");
                        location.setText(" ");
                        


            

        }
    }

    @FXML
    private void consulter_event(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) consulter.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void uploadphoto(ActionEvent event) {
         FileChooser F = new FileChooser();
        F.setTitle("Choisir une image");
        F.getExtensionFilters().addAll();
        File f = F.showOpenDialog(slider.getScene().getWindow());
        if(f != null){
            tfphoto.setText(f.toString());
        }
    }
    
}
