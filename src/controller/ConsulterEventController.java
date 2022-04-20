/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
 
    @FXML
    private Button ajouterr;
    @FXML
    private TableColumn<Formation,String> imgee;
    @FXML
    private TableColumn<Formation, Date> datefinn;
    private Button ccours;
    @FXML
    private Button imprimmer;
    @FXML
    private Button Stattt;
    @FXML
    private AnchorPane ge;
    @FXML
    private Label st;
    private Button ref;
    @FXML
    private Button act;
    
    
    

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
        date_dep.setCellValueFactory(new PropertyValueFactory<Formation,Date>("datede"));
        datefinn.setCellValueFactory(new PropertyValueFactory<Formation,Date>("datefi"));
        
        prix.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("prix"));
        id.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("id"));
        imgee.setCellValueFactory(new PropertyValueFactory<>("image"));
         
     
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
         Formation ev = tbl.getSelectionModel().getSelectedItem();
        formationcrud udao = formationcrud.getInstance();
        udao.supprimerevent(ev.getNom());
        System.out.println(ev.getStart_date());
        System.out.println(ev.getDescription());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Formation supprimer!");
                        alert.show();

        tbl.refresh();
    }

    @FXML
    private void modifier(ActionEvent Formation) {
         Formation ev = tbl.getSelectionModel().getSelectedItem();
        ConsulterEventController.nom_recup=ev.getNom();
        ConsulterEventController.decriptionrecup=ev.getDescription();
        ConsulterEventController.date_deprecup=ev.getStart_date();
        ConsulterEventController.date_finrecup=ev.getEnd_date();
        ConsulterEventController.prixrecup=ev.getPrix();
                ConsulterEventController.id_rec=ev.getId();

        System.out.println(ev.getId());
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateEvent.fxml"));
            Stage stage = (Stage) mod.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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

    @FXML
    private void ajoutere(ActionEvent Formation) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ajouter_formation.fxml"));
            Stage stage = (Stage) ajouterr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cours(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) ccours.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Imprimmer(ActionEvent event) throws DocumentException, FileNotFoundException {
        Formation service = new Formation();
           Document pdfReport = new Document() ;
           PdfWriter.getInstance(pdfReport, new FileOutputStream("menu.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("Formation"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
          
          PdfPTable my_report_table = new PdfPTable(9);
          
            PdfPCell  tableCellColumn = new PdfPCell(new Phrase("nom"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("description"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("prix"));
            my_report_table.addCell(tableCellColumn);
       
            
            
            double h= 0;
            tbl.getItems().forEach((Formation e) -> {
                
               PdfPCell tableCell = new PdfPCell(new Phrase(e.getPrix()));
                my_report_table.addCell(tableCell);
                
                
                tableCell = new PdfPCell(new Phrase(e.getDescription()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getNom()));
                my_report_table.addCell(tableCell);
                
                
                
                
                
                 
            });
            /* Attach report table to PDF */
            pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            
            pdfReport.close();
            
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: Bureau");
            alertReservation.showAndWait();
    }

    @FXML
    private void Sta(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/PieChartView.fxml"));
            Stage stage = (Stage) ajouterr.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gest(MouseEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterCours.fxml"));
            Stage stage = (Stage) ge.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stt(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/PieChartView.fxml"));
            Stage stage = (Stage) st.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void actt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/consulterEvent.fxml"));
            Stage stage = (Stage) act.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

