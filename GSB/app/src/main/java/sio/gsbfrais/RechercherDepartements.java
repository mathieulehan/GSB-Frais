package sio.gsbfrais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sio.gsbfrais.http.LireDepartement;
import sio.gsbfrais.http.LireMedecin;
import sio.gsbfrais.objet.Departement;
import sio.gsbfrais.objet.Medecin;
import sio.gsbfrais.xml.DepartementSaxParser;
import sio.gsbfrais.xml.MedecinsSaxParser;

import static sio.gsbfrais.objet.Departement.*;

public class RechercherDepartements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher_departements);
        final Spinner spinnerDpt = (Spinner) findViewById(R.id.spinner);
        RecyclerView rv = (RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        MedecinsSaxParser.listeMedecin.clear();
        LireDepartement lireDepartement = new LireDepartement();
        lireDepartement.execute("http://172.16.200.21/ws_gsb/index.php/c_webservice/lesdepartements");
        try {
           if(lireDepartement.get()){
               Log.i("departement",lireDepartement.listeDesDepartements());

           }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Departement> dp = DepartementSaxParser.listeDepartement;
        ArrayAdapter<Departement> adapter = new ArrayAdapter<Departement>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,dp);
        spinnerDpt.setAdapter(adapter);
        spinnerDpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * Met à jour les données de l'adapter : il obtiendra les médecins appartenant
             * au département choisi à l'aide du Spinner
             * @onItemSelected
             */
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String numDep = spinnerDpt.getSelectedItem().toString();
                MedecinsSaxParser.listeMedecin.clear();
                Log.d("d", numDep);
                LireMedecin lireMedecin = new LireMedecin();
                lireMedecin.execute("http://172.16.200.21/ws_gsb/index.php/c_webservice/lesmedecinsdudepartement/numdep/"+numDep);
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

            @Override
            /**
             * Vide l'adapter si on ne sélectionne pas de département
             * @onNothingSelected
             */
            public void onNothingSelected(AdapterView<?> parentView) {
                MedecinsSaxParser.listeMedecin.clear();
                MedecinsSaxParser.adapter.notifyDataSetChanged();
            }

        });




        rv.setAdapter( MedecinsSaxParser.adapter);    }
}
