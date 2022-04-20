/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import Entities.Category;
import services.ServiceCategory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Chahnez Sardouk
 */
public class ListCategoryController implements Initializable {
    static Stage stageModifier;
    @FXML
    private TableView<Category> listcategory;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, String> content;
    
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    static Category d;
    @FXML
    private AnchorPane anchorpaneAfficherCategorie;
    @FXML
    private TextField filtre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affiche();
    }

    public void affiche() {
        ServiceCategory ServiceCategory = new ServiceCategory();
        ArrayList arrayList = (ArrayList) ServiceCategory.showC();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategory.setItems(observableList);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        

      /*  Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Category, String>, TableCell<Category, String>>() {
            @Override
            public TableCell call(final TableColumn<Category, String> param) {
                final TableCell<Category, String> cell = new TableCell<Category, String>() {

                    //TO DO
                };
                return cell;
            }
        };*/

        

    }

    public void list() {
        ServiceCategory ServiceCategory = new ServiceCategory();
        ArrayList arrayList = (ArrayList) ServiceCategory.showC();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategory.setItems(observableList);

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/category.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
        
        
        
       

    }

    private void setNode(Node node) {
        anchorpaneAfficherCategorie.getChildren().clear();
        anchorpaneAfficherCategorie.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (listcategory.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "The list is empty ").show();
        } else {
            List<Category> category = listcategory.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete this category");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceCategory().deleteC(category.get(0));
                System.out.println(category.get(0).getId());
            }
        }
        list();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {

        if (listcategory.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Choix invalide ").show();
                    
        } else {
            d = listcategory.getSelectionModel().getSelectedItem();

            
            
          
//        Stage stageModifier = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/updatecategory.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
            
            
           // setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ModifierCategorie.fxml")));
        }
    }

    @FXML
    private void search(KeyEvent event) {
        ServiceCategory ServiceCategory = new ServiceCategory();
        ArrayList arrayList = (ArrayList) ServiceCategory.searchName(filtre.getText());


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategory.setItems(observableList);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        

        

        
    }

    private void searchK(KeyEvent event) {
        ServiceCategory ServiceCategory = new ServiceCategory();
        ArrayList arrayList = (ArrayList) ServiceCategory.showC();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategory.setItems(observableList);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
       
       
        
    }


  /*  @FXML
    private void back(ActionEvent event) throws IOException {
                    setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/main.fxml")));

    }*/
    
    
}
