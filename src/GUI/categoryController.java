/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Entities.Category;
import services.ServiceCategory;
import utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.sql.Connection;

/**
 * FXML Controller class
 *
 * @author med
 */
public class categoryController implements Initializable {
    
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField content;
    @FXML
    private JFXButton ajouter;
   
   
    
    private Connection cnx = MyConnection.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       List<String> myList = new ArrayList<String>();
        String sql = "SELECT * FROM category WHERE category.id is NULL";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                myList.add(rs.getString(3));

            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        // TODO
    }

    @FXML
    private void ajouterCategory(ActionEvent event) throws IOException, SQLException 
    {
        Category p = new Category(name.getText(),content.getText());
        ServiceCategory ps = new ServiceCategory();
        
        if (!name.getText().equals("")&& !content.getText().equals("") )
         {
                   ps.addC(p);
                     new Alert(Alert.AlertType.INFORMATION, "Category ajouté").show();
     
         }
         else
         {
                  new Alert(Alert.AlertType.ERROR, "Ecrivez votre nom et description ").show();
         }
            
        }
    
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategory.fxml"));
   

    
    
/*    @FXML
    private void back(ActionEvent event) throws IOException {

        setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/main.fxml")));    
      
    }*/

}
