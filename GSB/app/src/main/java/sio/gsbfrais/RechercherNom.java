package sio.gsbfrais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import sio.gsbfrais.http.LireMedecin;
import sio.gsbfrais.objet.Medecin;
import sio.gsbfrais.xml.MedecinsSaxParser;

/**
 * Récupère le nom entré par l'utilisateur et s'en sert dans l'url afin d'accéder au fichier xml
 * contenant les médecins dont le nom correspond au nom entré.
 */
public class RechercherNom extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher_nom);
        RecyclerView rv = (RecyclerView)findViewById(R.id.my_recycler_view);
        final EditText rechercheNom = (EditText)findViewById(R.id.editTxtNom);
        Button buttonRecherche = (Button)findViewById(R.id.button);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        MedecinsSaxParser.listeMedecin.clear();

        buttonRecherche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nom = String.valueOf(rechercheNom.getText());
                MedecinsSaxParser.listeMedecin.clear();
                LireMedecin lireMedecin = new LireMedecin();
                lireMedecin.execute("http://172.16.200.21/ws_gsb/index.php/c_webservice/lesmedecins/nom/"+nom);
                try {
                    if (lireMedecin.get()) {
                        Log.i("LireFichier", lireMedecin.listeDesMedecins());
                        MedecinsSaxParser.adapter.notifyDataSetChanged();
                    } else {
                        Log.i("LireFichier", "Problème lecture fichier");
                    }
                } catch (InterruptedException e) {
                    Log.i("LireFichier", "Interruption lecture fichier");
                } catch (ExecutionException e) {
                    Log.i("LireFichier", "Problème exécution");
                }
            }
        });


        rv.setAdapter(MedecinsSaxParser.adapter);
    }

}
