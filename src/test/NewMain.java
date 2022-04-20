/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import services.ServiceAvis;
import services.ServiceCategory;
import Entities.Avis;
import Entities.Category;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.MyConnection;
/**
 *
 * @author med
 */
public class NewMain {
    
     private Parent gotoRate() {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/rating.fxml"));
           return root;
        } catch (Exception ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void start(Stage primaryStage) throws IOException {

        
        
        
        Parent root =  gotoRate();

        Scene scene = new Scene(root);

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml")); 
        //FXMLController controller = fxmlLoader.<FXMLController>getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    

   public static void main(String[] args)  {
        ServiceAvis su = new ServiceAvis() {};
        //ServiceCategory si = new ServiceCategory() {};
        List<Avis> list=new ArrayList<>();
        //List<Category> list=new ArrayList<>();
        //System.out.println(su.afficher());
//su.addA(new Avis("8","oithgoihezt",",gkren","azerty"));
//su.updateA(new Avis(3,"2","lib","med","AZE"));        
//su.deleteA(new Avis(5));

//si.addC(new Category("java","good time for all"));
//si.updateC(new Category(1,"javaFX","not bad"));        
//si.deleteC(new Category(1));

          launch(args);

    
   }
}
