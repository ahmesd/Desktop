/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private Button instructor_button;
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Label idf;
    @FXML
    private Label emmm;
    @FXML
    private Label label1;
    @FXML
    private Button button1;
    @FXML
    private Label idf1;
    @FXML
    private Label label3;
    @FXML
    private Button button3;
    @FXML
    private Label idf2;
    @FXML
    private GridPane gird;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void pp(ActionEvent event) {
    }

    @FXML
    private void becomeInstructor(ActionEvent event) {
    }

    @FXML
    private void ffff(MouseEvent event) {
    }
    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/img/menu.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Hello, World", "notification demooooom", TrayIcon.MessageType.INFO);
    }

    @FXML
    private void mes_abo(ActionEvent event) {
    }

    @FXML
    private void panier(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void ajouter2(ActionEvent event) {
    }
    
}
