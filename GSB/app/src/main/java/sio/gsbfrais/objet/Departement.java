package sio.gsbfrais.objet;

import java.util.ArrayList;

/**
 * Created by ferellec on 04/04/2017.
 * Classe objet des départements
 */
public class Departement {
    public String getNumeroDepartement() {
        return numeroDepartement;
    }

    public void setNumeroDepartement(String numeroDepartement) {
        this.numeroDepartement = numeroDepartement;
    }

    String numeroDepartement;

    public Departement(){


    }

    public String toString() {
        return this.numeroDepartement;
    }

}
