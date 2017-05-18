package sio.gsbfrais.objet;

import java.util.ArrayList;

import sio.gsbfrais.MedecinAdapter;

/**
 * Created by ferellec on 21/03/2017.
 * Classe objet des Medecins
 */
public class Medecin {
    private int NUM;
    private String NOM;
    private String PRENOM;
    private String ADRESSE;
    private String CP;
    private String VILLE;
    private String LIBELLE;
    private double COEFNOTORIETE;


    public Medecin (){

    }


    public double getCOEFNOTORIETE() {
        return COEFNOTORIETE;
    }

    public void setCOEFNOTORIETE(double COEFNOTORIETE) {
        this.COEFNOTORIETE = COEFNOTORIETE;
    }

    public String getLIBELLE() {
        return LIBELLE;
    }

    public void setLIBELLE(String LIBELLE) {
        this.LIBELLE = LIBELLE;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public int getNUM() {
        return NUM;
    }

    public void setNUM(int NUM) {
        this.NUM = NUM;
    }

    public String getVILLE() {
        return VILLE;
    }

    public void setVILLE(String VILLE) {
        this.VILLE = VILLE;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }
}
