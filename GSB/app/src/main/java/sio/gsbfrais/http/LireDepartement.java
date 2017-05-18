package sio.gsbfrais.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sio.gsbfrais.objet.Departement;
import sio.gsbfrais.objet.Medecin;
import sio.gsbfrais.xml.DepartementSaxParser;
import sio.gsbfrais.xml.MedecinsSaxParser;

/**
 * Created by ferellec on 04/04/2017.
 * Instancie la classe DepartementSaxParser qui ce chargera de recuperer la liste des départements disponible via l'url fournis
 */
public class LireDepartement extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String...urls ) {
        URL url;
        try {
            url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            try {
                DepartementSaxParser DepartementSaxParser = new DepartementSaxParser();
                DepartementSaxParser.parse(inputStream);
            } catch (Exception e) {
                Log.i("LireXml", "erreur");
            }

        }
        catch (MalformedURLException e) {
            return false;
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Renvoie la chaîne de caractère contenant la liste des numéros des départements
     * @return
     */
    public String listeDesDepartements() {
        String liste = "";
        for (Departement departement : DepartementSaxParser.listeDepartement) {
            liste = liste + departement.getNumeroDepartement() + "\n";
        }
        return liste;
    }
}
