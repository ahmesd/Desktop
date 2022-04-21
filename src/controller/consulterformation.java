/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
 *
 * @author Ahmed
 */
public class consulterformation implements Initializable {
    
    @FXML
    private TableView<Formation> tbl_event;
    @FXML
    private Button supp;
    @FXML
    private Button mod;

    /**
     * Initializes the controller class.
     */
    ObservableList<Formation> data = FXCollections.observableArrayList();
    private TableColumn<Formation, String> nom;
    
 
    @FXML
    private TableColumn<Formation, Date> date_dep;
    @FXML
    private TableColumn<Formation, Date> date_fin;
    private TableColumn<Formation, Integer> prix;
    public static String nom_eventrecup;
    public static String decriptionrecup;
    public static String localisationrecup;
    public static Date date_deprecup;
    public static Date date_finrecup;
    public static int numerorecup;
        public static int id_rec;

 
    @FXML
    private TableColumn<Formation, Integer> id;
    @FXML
    private TableColumn<Formation, String> objectif;
      // private final ListData3 listdata = new ListData3();
               private final ObservableList<Formation> dataList = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private TableColumn<?, ?> nom_event;
    @FXML
    private TableColumn<?, ?> numero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        formationcrud evcrud = new formationcrud();
        ArrayList<Formation> ev = (ArrayList<Formation>) evcrud.displayAllList();
        ObservableList<Formation> obs = FXCollections.observableArrayList(ev);
        //table.setItems(obs);
        nom.setCellValueFactory(new PropertyValueFactory<Formation, String>("nom"));
       // description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        
        date_dep.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_dep"));
        date_fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));
        prix.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("prix"));
        id.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id"));
         
     
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
		sortedData.comparatorProperty().bind(tbl_event.comparatorProperty());

        tbl_event.setItems(sortedData);
    }

    public void setTab(TableView<Formation> table) {
        this.tbl_event = table;
    }

    public void setNom_event(TableColumn<Formation, String> nom) {
        this.nom = nom;
    }

    public void setobjectif(TableColumn<Formation, String> objectif) {
        this.objectif = objectif;
    }

   // public void setlocalisation(TableColumn<Formation, String> localisation) {
      //  this.localisation = localisation;
    //}

    public void setdate_dep(TableColumn<Formation, Date> date_depart) {
        this.date_dep = date_depart;
    }

    public void setdate_fin(TableColumn<Formation, Date> date_fin) {
        this.date_fin = date_fin;
    }

    public void setnumero(TableColumn<Formation, Integer> prix) {
        this.prix = prix;
    }

    public void setid(TableColumn<Formation, Integer> id) {
        this.id = id;
    }
    @FXML
    private void supprimer(ActionEvent event) {
        Formation ev = tbl_event.getSelectionModel().getSelectedItem();
        formationcrud udao = formationcrud.getInstance();
        udao.supprimerevent(ev.getId());
        System.out.println(ev.getEnd_date());
       

        tbl_event.refresh();

    }

    @FXML
    private void modifier(ActionEvent event) {
        Formation ev = tbl_event.getSelectionModel().getSelectedItem();
       consulterformation.nom_eventrecup=ev.getNom();
        
        consulterformation.decriptionrecup=ev.getDescription();
        consulterformation.date_deprecup=ev.getStart_date();
        consulterformation.date_finrecup=ev.getEnd_date();
        consulterformation.numerorecup=ev.getPrix();
              consulterformation.id_rec=ev.getId();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateEvent.fxml"));
            Stage stage = (Stage) mod.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
    private void form(MouseEvent Formation) throws IOException {
        Pidevv m=new Pidevv();
        m.changeScene("/view/AfficherPersonne.fxml");
    }

    @FXML
    private void abon(MouseEvent Formation) throws IOException {
        Pidevv m=new Pidevv();
        m.changeScene("/view/ajouter_event.fxml");
    }

    @FXML
    private void stat(MouseEvent Formation) throws IOException {
        Pidevv m=new Pidevv();
        m.changeScene("/view/stat.fxml");
    }

    @FXML
    private void promo(MouseEvent Formation) throws IOException {
        Pidevv m=new Pidevv();
        m.changeScene("/view/Promotion.fxml");
    }
    
}
