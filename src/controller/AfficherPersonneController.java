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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherPersonneController implements Initializable {

    @FXML
    private Label admin;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private HBox ooooh;
    @FXML
    private Button smsbut;
    @FXML
    private TableView<?> personsTable;
    @FXML
    private TableColumn<?, ?> prenomColonne;
    @FXML
    private TableColumn<?, ?> nomColonne;
    @FXML
    private TableColumn<?, ?> usernameColonne1;
    @FXML
    private TableColumn<?, ?> emailColonne;
    @FXML
    private TableColumn<?, ?> passColonne;
    @FXML
    private TableColumn<?, ?> roleColonne;
    @FXML
    private TableColumn<?, ?> icColonne;
    @FXML
    private Button ref;
    @FXML
    private Button b;
    @FXML
    private Button imprimer;
    @FXML
    private TextField filterField;
    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField role;
    @FXML
    private TextField id;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private Button mod;
    @FXML
    private Button sup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deco(MouseEvent event) {
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void sendd(ActionEvent event) {
    }

    @FXML
    private void oooohOut(MouseEvent event) {
    }

    @FXML
    private void oooohIn(MouseEvent event) {
    }

    @FXML
    private void reclam(MouseEvent event) {
    }

    @FXML
    private void demande(MouseEvent event) {
    }
    
}
