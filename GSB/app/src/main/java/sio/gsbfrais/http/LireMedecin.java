package sio.gsbfrais.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sio.gsbfrais.objet.Medecin;
import sio.gsbfrais.xml.MedecinsSaxParser;

/**
 * Created by ferellec on 30/03/2017.
 * Créé une chaîne de caractères listeDesMedecins et la remplit à l'aide de la classe MedecinsSaxParser
 *  Instancie la classe MedecinSaxParser qui ce chargera de recuperer la liste des medecins disponible via l'url fournis
 */
public class LireMedecin extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String...urls ) {
        URL url;
        try {
            url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            try {
                MedecinsSaxParser MedecinsSaxParser = new MedecinsSaxParser();
                MedecinsSaxParser.parse(inputStream);
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
     * Renvoie la chaîne de caractère contenant la liste de tous les médecins (numéro + prénom)
     * @return
     */
    public String listeDesMedecins() {
        String liste = "";
        for (Medecin medecin :  MedecinsSaxParser.listeMedecin ) {
            liste = liste + medecin.getNUM() + " " + medecin.getNOM() + "\n";
        }
        return liste;
    }
}
