package sio.gsbfrais;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sio.gsbfrais.objet.Medecin;
import sio.gsbfrais.xml.DepartementSaxParser;
import sio.gsbfrais.xml.MedecinsSaxParser;

public class MedecinAdapter extends RecyclerView.Adapter<MedecinAdapter.MedecinViewHolder>  {

    public static class MedecinViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView medecinNom;
        TextView medecinCp;
        TextView medecinAdresse;
        TextView medecinVille;
        TextView medecinNum;
        TextView medecinCoef;

        ImageView medecinIcon;
        TextView medecinLibelle;


        MedecinViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            medecinNom = (TextView)itemView.findViewById(R.id.nom);
            medecinVille = (TextView)itemView.findViewById(R.id.ville);
            medecinAdresse = (TextView)itemView.findViewById(R.id.adresse);
            medecinLibelle = (TextView)itemView.findViewById(R.id.libelle);
            medecinNum = (TextView)itemView.findViewById(R.id.num);
            medecinCoef = (TextView)itemView.findViewById(R.id.coef);

            medecinIcon = (ImageView)itemView.findViewById(R.id.imgView);

        }
    }
   ArrayList<Medecin> filtreMedecin;

    public MedecinAdapter(ArrayList<Medecin> medecins){
        MedecinsSaxParser.listeMedecin = medecins;

    }

    @Override
    public int getItemCount() {
        return  MedecinsSaxParser.listeMedecin .size();
    }

    public MedecinViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_medecin, viewGroup, false);
        MedecinViewHolder pvh = new MedecinViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(final MedecinViewHolder MedecinViewHolder, final int i) {

        MedecinViewHolder.medecinNom.setText( MedecinsSaxParser.listeMedecin .get(i).getNOM()+" "+ MedecinsSaxParser.listeMedecin .get(i).getPRENOM());
        MedecinViewHolder.medecinAdresse.setText("Adresse : " +   MedecinsSaxParser.listeMedecin .get(i).getADRESSE());
        MedecinViewHolder.medecinCoef.setText("Coef Notoriete : " +   MedecinsSaxParser.listeMedecin.get(i).getCOEFNOTORIETE());
        MedecinViewHolder.medecinLibelle.setText("Libelle : " +   MedecinsSaxParser.listeMedecin.get(i).getLIBELLE());
        MedecinViewHolder.medecinVille.setText(  MedecinsSaxParser.listeMedecin .get(i).getCP()+" "+  MedecinsSaxParser.listeMedecin .get(i).getVILLE());
        MedecinViewHolder.medecinNum.setText("Num : " +  MedecinsSaxParser.listeMedecin .get(i).getNUM());
        MedecinViewHolder.medecinIcon.setImageResource(R.drawable.logo);


    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}