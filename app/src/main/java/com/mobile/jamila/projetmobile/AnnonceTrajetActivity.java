package com.mobile.jamila.projetmobile;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.mobile.jamila.projetmobile.dataAccess.PropositionDAO;
import com.mobile.jamila.projetmobile.models.Insatien;
import com.mobile.jamila.projetmobile.models.Proposition;
import com.mobile.jamila.projetmobile.models.Conducteur;

/**
 * Created by User on 23/04/2017.
 */


public class AnnonceTrajetActivity extends Activity{

    TextView iteneraire;
    EditText prix;
    EditText nbrePlaces;
    EditText precision;
    Spinner tailleBagage;
    Spinner flexibilite;
    Button publier;
    Proposition proposition;
    Firebase myFireBase;
    private FirebaseAuth firebaseAuth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propose_trajet_annonce);
        Intent intent = getIntent();
        proposition = intent.getParcelableExtra("trajet");
        Firebase.setAndroidContext(this);
        myFireBase=new Firebase("https://projetmobile-ed855.firebaseio.com/propositions");


        iteneraire=(TextView)findViewById(R.id.TVitneraire);
        iteneraire.setText(proposition.getVilleDep()+" => "+proposition.getVilleArr());


        tailleBagage=(Spinner)findViewById(R.id.SpinnerTailleBagage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tailleBagage, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tailleBagage.setAdapter(adapter);

        flexibilite=(Spinner)findViewById(R.id.Spinnerflexibilite);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.flexibilite, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flexibilite.setAdapter(adapter);

        publier=(Button)findViewById(R.id.Bpublier);



        preparerConducteur(proposition);

        publier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prix=(EditText)findViewById(R.id.ETprix);
                nbrePlaces=(EditText)findViewById(R.id.ETplaces);
                precision=(EditText)findViewById(R.id.ETdetails);
                proposition.setTailleBagage(tailleBagage.getSelectedItem().toString());


                if ((prix.getText() == null) || (nbrePlaces.getText() == null) ) {
                    Toast.makeText(AnnonceTrajetActivity.this, "Veuillez remplire tous les champs2", Toast.LENGTH_LONG).show();
                }else {
                        if ((Integer.valueOf(nbrePlaces.getText().toString())>4)||
                                (Integer.valueOf(nbrePlaces.getText().toString())<1)){
                            Toast.makeText(AnnonceTrajetActivity.this, "Nombre de places invalide", Toast.LENGTH_LONG).show();
                        }else {
                            proposition.setPrix(Double.valueOf(prix.getText().toString()));
                            proposition.setNmbrePlace(Integer.valueOf(nbrePlaces.getText().toString()));
                            proposition.setPrecision(precision.getText().toString());
                            proposition.setFlexibilite(flexibilite.getSelectedItem().toString());
                            if (precision.getText()==null){
                                proposition.setPrecision("");
                            }
                        }
                    }




                    Log.d("log5",String.valueOf(proposition.getConducteur()));
                    PropositionDAO propositionDAO=new PropositionDAO();
                    propositionDAO.createProposition(proposition);
                    Intent intent1=new Intent(AnnonceTrajetActivity.this,Accueil.class);
                    startActivity(intent1);

                }



        });


    }

    private void preparerConducteur(final Proposition proposition){
        firebaseAuth= FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null) {


            Firebase myFireBase = new Firebase("https://projetmobile-ed855.firebaseio.com/conducteurs");
            myFireBase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.e("Count conducteur", "" + dataSnapshot.getChildrenCount());
                    for (DataSnapshot conducteurSnapshot : dataSnapshot.getChildren()) {
                        Conducteur conducteur = conducteurSnapshot.getValue(Conducteur.class);
                        Log.e("log", conducteur.getNom().toString());
                        String email = firebaseAuth.getCurrentUser().getEmail().toString();
                        Log.e("log", email);
                        Log.e("log2", conducteur.getEmail().toString());

                        if ((conducteur.getEmail().toString().toUpperCase()).equals(email.toString().toUpperCase())) {
                            Log.e("log3", conducteur.getNom().toString());
                            Log.e("log4",String.valueOf(conducteur.getNumero_inscription()));
                            proposition.setConducteur(conducteur.getNumero_inscription());

                        }

                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }





    }
}
