/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pideviheb;

import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.StageStyle;

/**
 *
 * @author solta
 */
public class IhebEv extends Application {
   @Override
    public void start(Stage primaryStage) throws IOException {
        
         
        try {
         //  Parent parent = FXMLLoader.load(getClass().getResource("/views/logInSignUp/logIn.fxml"));
           // Parent parent = FXMLLoader.load(getClass().getResource("/views/logInSignUp/HomePageGameNation.fxml"));
          Parent parent = FXMLLoader.load(getClass().getResource("/views/AfficherEve.fxml"));
         // Parent parent = FXMLLoader.load(getClass().getResource("/views/hamma/commentaireCrud.fxml"));
         
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(IhebEv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
   
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("view/AfficherEve.fxml"));
  //    Parent root = FXMLLoader.load(getClass().getResource("view/Calculatorview.fxml"));
        
