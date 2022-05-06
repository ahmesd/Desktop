/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import entity.Evenement;
import Service.ServiceEvenement;
import utills.DataSource;
import utills.Evenutils;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.util.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.io.*;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author solta
 */
public class AfficherEveController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, Integer> col_id;
    @FXML
    private TableColumn<Evenement, String> col_nom;
    @FXML
    private TableColumn<Evenement, String> col_desc;
    @FXML
    private TableColumn<Evenement, Date> col_debut;
    @FXML
    private TableColumn<Evenement, Date> col_fin;
    @FXML
    private TableColumn<Evenement, Integer> col_nbr;
    ObservableList<Evenement> List = FXCollections.observableArrayList();
    ObservableList<Evenement> dataList;
    @FXML
    private Button supp;
    @FXML
    private TextField tfIdEv;
    @FXML
    private TextField tfNomEv;
    @FXML
    private TextField tfDescEv;
    @FXML
    private DatePicker tfDateDebEv;
    @FXML
    private DatePicker tfDateFinEv;
    @FXML
    private TextField tfNbrEv;
    
    private PreparedStatement pst;
    private Label lb1;
    private Label lb2;
    @FXML
    private TextField tfchercher;
    private Object titre;
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }

    public void setLb1(Label lb1) {
        this.lb1 = lb1;
    }

    public void setLb2(Label lb2) {
        this.lb2 = lb2;
    }

    @FXML
    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        //tfIdEv.setText((col_id.getCellData(index)).toString());
        tfNomEv.setText(col_nom.getCellData(index));
        tfDescEv.setText(col_desc.getCellData(index));
        //tfDateDebEv.setText(col_debut.getCellData(index).toString());
        //tfDateFinEv.setText(col_fin.getCellData(index).toString());
        tfNbrEv.setText(col_nbr.getCellData(index).toString());
    }

    @FXML
    private void supp(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getConnection();
        String sql = "DELETE  FROM evenement where id_ev= ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setString(1, tfIdEv.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "delete");
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            table.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        refresh();
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) {
        ServiceEvenement sp = new ServiceEvenement();
        Evenutils au = new Evenutils();
        String nom = tfNomEv.getText();
        String description = tfDescEv.getText();
        String nbr = tfNbrEv.getText();

        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
        } else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
        } else if (nbr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nombre ne doit pas etre vide");
        } else if (tfDateDebEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de debut ne doit pas etre vide");
        } else if (tfDateFinEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de fin ne doit pas etre vide");
        } else if (!au.testNom(nom)) {
            JOptionPane.showMessageDialog(null, "le nom doit contenir des caracteres");
        } else if (!au.testNom(description)) {
            JOptionPane.showMessageDialog(null, "la description doit contenir des caracteres");
        } else if (!au.testNbr(nbr)) {
            JOptionPane.showMessageDialog(null, "le nombre doit contenir des chiffres");
        } else if (!testdatedeb()){JOptionPane.showMessageDialog(null, "la date de debut doit commencer a partir d aujordhui");}
        else if (!testda()){JOptionPane.showMessageDialog(null, "la date de debut doit inferieure a la date fin");}
        else {
            sp.ajouter(new Evenement(tfNomEv.getText(), tfDescEv.getText(), Date.valueOf(tfDateDebEv.getValue()), Date.valueOf(tfDateFinEv.getValue()), Integer.parseInt(tfNbrEv.getText())));

            JOptionPane.showMessageDialog(null, "Evenement ajoutée !");
            
            refresh();
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        ServiceEvenement sp = new ServiceEvenement();
        Evenutils au = new Evenutils();
        String nom = tfNomEv.getText();
        String description = tfDescEv.getText();
        String nbr = tfNbrEv.getText();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nom ne doit pas etre vide");
        } else if (description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la description ne doit pas etre vide");
        } else if (nbr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le nombre ne doit pas etre vide");
        } else if (tfDateDebEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de debut ne doit pas etre vide");
        } else if (tfDateFinEv.getValue() == null) {
            JOptionPane.showMessageDialog(null, "la date de fin ne doit pas etre vide");
        } else if (!au.testNom(nom)) {
            JOptionPane.showMessageDialog(null, "le nom doit contenir des caracteres");
        } else if (!au.testNom(description)) {
            JOptionPane.showMessageDialog(null, "la description doit contenir des caracteres");
        } else if (!au.testNbr(nbr)) {
            JOptionPane.showMessageDialog(null, "le nombre doit contenir des chiffres");
        } else if (!testdatedeb()){JOptionPane.showMessageDialog(null, "la date de debut doit commencer a partir d aujordhui");}
        else if (!testda()){JOptionPane.showMessageDialog(null, "la date de debut doit inferieure a la date fin");}
        else {

            sp.modifier(new Evenement(Integer.parseInt(tfIdEv.getText()), tfNomEv.getText(), tfDescEv.getText(), Date.valueOf(tfDateDebEv.getValue()), Date.valueOf(tfDateFinEv.getValue()), Integer.parseInt(tfNbrEv.getText())));
            JOptionPane.showMessageDialog(null, "Evenement modifié !");
            
            refresh();
        }
    }

    @FXML
    private void refresh() {
        ObservableList<Evenement> List = FXCollections.observableArrayList();
        try {
            Connection cnx = DataSource.getInstance().getConnection();
           
           
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * from evenement");
            while (rs.next()) {
                List.add(new Evenement(rs.getInt(1), rs.getString("nom_ev"), rs.getString("description"), rs.getDate("date_deb"), rs.getDate("date_fin"), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        //col_jeu.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        table.setItems(List);
        table.refresh();
    }

    @FXML
    private void chercher(ActionEvent event) {
        ObservableList<Evenement> List = FXCollections.observableArrayList();
        try {
            Connection cnx = DataSource.getInstance().getConnection();
            String text = tfchercher.getText();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM evenement where nom_ev='" + text + "'");

            while (rs.next()) {
                List.add(new Evenement(rs.getInt(1), rs.getString("nom_ev"), rs.getString("description"), rs.getDate("date_deb"), rs.getDate("date_fin"), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_ev"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        table.setItems(List);
        table.refresh();
    }

   
    
    @FXML
    private void print(ActionEvent event) {
         
 PrinterJob printerJob = PrinterJob.createPrinterJob();
   if(printerJob.showPrintDialog(null) && printerJob.printPage(table))
       table.setScaleX(0.60);
                        table.setScaleY(0.60);
                        table.setTranslateX(-500);
       printerJob.endJob();
       table.setScaleX(1);
                        table.setScaleY(1);
                        table.setTranslateX(0);
    }

    @FXML
    private void pdf(ActionEvent event) {
        
 PrinterJob job = PrinterJob.createPrinterJob();
 if (job != null) {
     table.setScaleX(0.60);
                        table.setScaleY(0.60);
                        table.setTranslateX(-500);
    boolean success = job.printPage(table);
    
    if (success) {
        
        job.endJob();
        table.setScaleX(1);
                        table.setScaleY(1);
                        table.setTranslateX(0);

    }
 }
    }
    private Boolean testdatedeb(){
    LocalDate now = LocalDate.now();
    if( tfDateDebEv.getValue().compareTo(now)>=0)
    {
        return true;
    }
   
    return false;
    }
    private Boolean testda(){
   
    if(tfDateFinEv.getValue().compareTo(tfDateDebEv.getValue()) > 0){
    return true;
    }
    return false;
    }

 
   
   
}
