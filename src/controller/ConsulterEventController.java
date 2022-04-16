/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.formationcrud;
import entity.Formation;
import pidevv.Pidevv;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ConsulterEventController implements Initializable {

    @FXML
    private TableColumn<Formation,Date> date_dep;
   
    @FXML
    private TableColumn<Formation,Integer> id;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private TableColumn<Formation, String> nom;
     ObservableList<Formation> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Formation,String> description;
    
     private final ObservableList<Formation> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Formation,Integer> prix;
     public static String nom_recup;
    public static String decriptionrecup;
    public static Date date_deprecup;
    public static Date date_finrecup;
    public static int prixrecup;
        public static int id_rec;
    
    
    @FXML
    private TableView<Formation> tbl;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formationcrud evcrud = new formationcrud();
        ArrayList<Formation> ev = (ArrayList<Formation>) evcrud.readAll();
        ObservableList<Formation> obs = FXCollections.observableArrayList(ev);
        //table.setItems(obs);
      nom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<Formation,String>("description"));
        date_dep.setCellValueFactory(new PropertyValueFactory<Formation,Date>("date_dep"));
        
        prix.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("prix"));
        id.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("id"));
         
     
 FilteredList<Formation> filteredData = new FilteredList<>(FXCollections.observableArrayList(ev), b -> true);
 	// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(events -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (events.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (events.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Formation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tbl.comparatorProperty());

        tbl.setItems(sortedData);
        // TODO
    }    

    public void setDate_dep(TableColumn<Formation,Date> date_dep) {
        this.date_dep = date_dep;
    }

  

    public void setId(TableColumn<Formation,Integer> id) {
        this.id = id;
    }

    public void setNom(TableColumn<Formation,String> nom) {
        this.nom = nom;
    }

    public void setDescription(TableColumn<Formation,String> description) {
        this.description = description;
    }

    public void setPrix(TableColumn<Formation,Integer> prix) {
        this.prix = prix;
    }

    public void setTbl(TableView<Formation> tbl) {
        this.tbl = tbl;
    }
    
    

    @FXML
    private void supprimer(ActionEvent Formation) {
    }

    @FXML
    private void modifier(ActionEvent Formation) {
    }

    @FXML
    private void form(MouseEvent Formation) {
    }

    @FXML
    private void promo(MouseEvent Formation) {
    }

    @FXML
    private void abon(MouseEvent Formation) {
    }

    @FXML
    private void stat(MouseEvent Formation) {
    }
    
}
