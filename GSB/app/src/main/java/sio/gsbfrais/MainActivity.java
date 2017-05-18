package sio.gsbfrais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Cette activité contient deux bouton qui permettent de chercher la liste des médecins correspondant
 * à un certain nom ou appartenant à un certain département.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button searchByName = (Button) findViewById(R.id.searchByName);
        final Button searchByDep = (Button) findViewById(R.id.searchByDep);
        searchByName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RechercherNom.class);
                startActivity(intent);


            }
        });
        searchByDep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RechercherDepartements.class);
                startActivity(intent);            }
        });
    }
}
