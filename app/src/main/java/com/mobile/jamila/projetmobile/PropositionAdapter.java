package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;
import com.mobile.jamila.projetmobile.dataAccess.PropositionDAO;
import com.mobile.jamila.projetmobile.models.Conducteur;
import com.mobile.jamila.projetmobile.models.Proposition;

import java.util.List;

/**
 * Created by User on 26/05/2017.
 */

public class PropositionAdapter extends RecyclerView.Adapter<PropositionAdapter.MyViewHolder> {
    private List<Proposition> propositionsList;
    Firebase myFireBase;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom_conducteur, classe_conducteur, date_et_temps, itenerire, prix, nombre_de_place;

        public MyViewHolder(View view) {
            super(view);
            nom_conducteur = (TextView) view.findViewById(R.id.conducteurNom);
            classe_conducteur = (TextView) view.findViewById(R.id.conducteurClasse);
            date_et_temps = (TextView) view.findViewById(R.id.oneItemDateEtTemp);
            itenerire = (TextView) view.findViewById(R.id.oneItemIteneraire);
            prix = (TextView) view.findViewById(R.id.oneItemPrix);
            nombre_de_place = (TextView) view.findViewById(R.id.oneItemNbrePlace);
        }


    }


    public PropositionAdapter(List<Proposition> moviesList) {
        this.propositionsList = moviesList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.proposition_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
         Conducteur conducteur = new Conducteur();

        Proposition proposition=propositionsList.get(position);
        myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/conducteurs");
        myFireBase.child(String.valueOf(proposition.getConducteur())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Conducteur c=dataSnapshot.getValue(Conducteur.class);
                //conducteur.setClasse(c.getClasse());
                //conducteur.setNom(c.getNom());
             //   conducteur.setEmail(c.getEmail());
                holder.nom_conducteur.setText(c.getNom().toString());
                Log.d("log",c.getNom().toString());
                holder.classe_conducteur.setText(c.getClasse().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        holder.date_et_temps.setText(proposition.getJourAller()+ " "+ convertMonth(proposition.getMoisAller())+ "  -  " + proposition.getHeureAller()+"h "+proposition.getMinAller()+"min");
        holder.itenerire.setText(proposition.getVilleDep()+" -> "+proposition.getVilleArr());
        holder.prix.setText(proposition.getPrix().toString()+" Dt");
        holder.nombre_de_place.setText(proposition.getNmbrePlace()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.Context context=v.getContext();
                Intent intent =new Intent(context,DetailsProposition.class);

                Log.d("log", propositionsList.get(position).getId().toString());
                PropositionDAO p=new PropositionDAO();
                p.getOneProposition(propositionsList.get(position).getId().toString());
                intent.putExtra("id",propositionsList.get(position).getId().toString());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return propositionsList.size();
    }

    private String convertMonth (int i){
        switch (i)
        {
            case 1: return "Janvier";
            case 2: return  "Février";
            case 3: return "Mars";
            case 4:return "Avril";
            case 5:return "Mai";
            case 6: return "Juin";
            case 7: return  "Juillet";
            case 8: return "Aout";
            case 9:return "Septembre";
            case 10:return "Octobre";
            case 11: return "Novembre";
            case 12:return "Décembre";

            default: return "erreur";

        }
    }


}
