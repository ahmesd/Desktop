/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Date;

/**
 *
 * @author solta
 */
public class Evenement {
    int id_ev;
    String nom_ev;
    String description;
    Date date_deb;
    Date date_fin;
    int nbr;
   

    public Evenement(int id_ev, String nom_ev, String description,Date date_deb,Date date_fin,int nbr ) {
        this.id_ev = id_ev;
        this.nom_ev = nom_ev;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nbr = nbr;
    }

    public Evenement( String nom_ev, String description,Date date_deb,Date date_fin,int nbr) {
        this.nom_ev = nom_ev;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nbr = nbr;
    }

public Evenement( int id_ev) {
        this.id_ev = id_ev;
       
    }

    public Evenement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
   



    public int getId_ev() {
        return id_ev;
    }

    public void setId_ev(int id) {
        this.id_ev = id_ev;
    }

    public String getNom_ev() {
        return nom_ev;
    }

    public void setNom_ev(String nom) {
        this.nom_ev = nom_ev;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = description;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb() {
        this.date_deb = date_deb;
    }
    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin() {
        this.date_fin = date_fin;
    }
    public int getNbr() {
        return nbr;
    }

    public void setNbr(int prenom) {
        this.nbr = nbr;
    }
    @Override
    public String toString() {
        return "evenement{" + ",son nom est " + nom_ev + ",la description est " + description + ", date de debut=" + date_deb +", date fin=" + date_fin +", nombre de places=" + nbr +'}';
    }
    

   
}
