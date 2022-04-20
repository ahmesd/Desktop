/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Duration;
import Entities.Avis;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import services.ServiceAvis;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Chahnez Sardouk
 */
public class ListratingController implements Initializable {

    @FXML
    private TableColumn<Avis, String>rating;
    @FXML
    private TableColumn<Avis, String> title;
    @FXML
    private TableColumn<Avis, String> category;
    @FXML
    private TableColumn<Avis, String> content;
    @FXML
    private Button supprimer;
  
    @FXML
    private TableView<Avis> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ServiceAvis sa = new ServiceAvis();
        ArrayList arrayList = (ArrayList) sa.afficher();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
       rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
       title.setCellValueFactory(new PropertyValueFactory<>("title"));
      category.setCellValueFactory(new PropertyValueFactory<>("category"));
      content.setCellValueFactory(new PropertyValueFactory<>("content"));
        // TODO
    }    

   

    @FXML
    private void supprimer(ActionEvent event) {
        
               if (table.getSelectionModel().isEmpty()) {
             new Alert(Alert.AlertType.ERROR, "The table is empty ").show();
        } else {
            ObservableList<Avis> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete this rate");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceAvis().deleteA(bon.get(0));
                System.out.println(bon.get(0).getId());
            }
        }
        list();
        
        
    }
    
       public void list(){
        ServiceAvis sa = new ServiceAvis();
        ArrayList arrayList = (ArrayList) sa.afficher();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        

}
   
    
    
    
}
