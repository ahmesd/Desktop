/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;
import java.util.regex.Matcher;
import Service.cons;

/**
 *
 * @author solta
 */
public class Evenutils {
    public boolean testNom(String titre) {

        Matcher matcher = cons.VALID_NAME.matcher(titre);
        return matcher.find();

    }
 public boolean testNbr(String prix) {

        Matcher matcher = cons.VALID_NBR.matcher(prix);
        return matcher.find();
    }
 
}