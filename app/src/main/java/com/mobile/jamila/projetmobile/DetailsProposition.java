package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mobile.jamila.projetmobile.models.Conducteur;
import com.mobile.jamila.projetmobile.models.Proposition;

/**
 * Created by User on 29/05/2017.
 */

public class DetailsProposition extends AppCompatActivity  {

    TextView depart;
    TextView arrivee;
    TextView dateDepart;
    TextView dateRetour;
    TextView bagage;
    TextView retard;
    Firebase myFireBase;
    TextView nom;
    TextView prenom;
    TextView filiere;
    TextView email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_proposition);
        Firebase.setAndroidContext(this);

        depart=(TextView)findViewById(R.id.villeDepart);
        arrivee=(TextView)findViewById(R.id.villeArrivee);
        dateDepart=(TextView)findViewById(R.id.dateDepart);
        dateRetour=(TextView)findViewById(R.id.dateRetour);
        bagage=(TextView)findViewById(R.id.bag);
        retard=(TextView)findViewById(R.id.retard);
        nom=(TextView)findViewById(R.id.nom);
        prenom=(TextView)findViewById(R.id.prenom);
        filiere=(TextView)findViewById(R.id.niveauF);
        email=(TextView)findViewById(R.id.email);

        Intent intent=getIntent();
        final String proposisionId=intent.getStringExtra("id");
        myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/propositions");
        myFireBase.child(proposisionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Proposition p=dataSnapshot.getValue(Proposition.class);
                depart.setText(p.getVilleDep().toString());
                arrivee.setText(p.getVilleArr().toString());
                dateDepart.setText(p.getJourAller()+" / "+p.getMoisAller()+ " -- "+p.getHeureAller()+"h "+p.getMinAller()+"min.");
                if(p.getJourRetour()!=0){
                    dateRetour.setText(p.getJourRetour()+" / "+p.getMoisRetour()+" -- "+p.getHeureRetour()+"h "+p.getMinRetour()+"min.");
                }else {
                    dateRetour.setText("SANS RETOUR!");
                }
                bagage.setText(p.getTailleBagage().toString());
                retard.setText(p.getFlexibilite().toString());

                myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/conducteurs");
                myFireBase.child(String.valueOf(p.getConducteur())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Conducteur c=dataSnapshot.getValue(Conducteur.class);
                        nom.setText(c.getNom().toString());
                        prenom.setText(c.getPrenom().toString());
                        filiere.setText(c.getClasse().toString());
                        email.setText(c.getEmail().toString());

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }

}
