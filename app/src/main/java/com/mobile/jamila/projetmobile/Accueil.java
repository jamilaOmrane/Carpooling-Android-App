package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 27/05/2017.
 */

public class Accueil extends AppCompatActivity {

    Button rechercher;
    Button proposer;
    EditText depart;
    EditText arrivee;
    Button inscription;
    Button connexion;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        Firebase.setAndroidContext(this);
        rechercher=(Button)findViewById(R.id.bouttonChercher);
        proposer=(Button)findViewById(R.id.boutonProposer);
        inscription=(Button)findViewById(R.id.bouttonInscription);
        connexion=(Button)findViewById(R.id.bouttonConnexion) ;
        Intent intent=getIntent();
        //boolean connected=intent.getExtras().getBoolean("connected");
       /* if(connected){

        }*/
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            inscription.setClickable(false);
            ViewGroup.LayoutParams params = inscription.getLayoutParams();
            params.width = 450;
            inscription.setLayoutParams(params);
            String email=firebaseAuth.getCurrentUser().getEmail().toString();
            int i=email.indexOf("@");
            String nom=email.substring(0,i);
            inscription.setText(nom);
            connexion.setClickable(false);
            connexion.setText("Déconnecter");
            params = connexion.getLayoutParams();
            params.width = 350;
            connexion.setLayoutParams(params);

        }

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    depart=(EditText)findViewById(R.id.ETDepart);
                    arrivee=(EditText)findViewById(R.id.ETArrivee);
                    if(depart.getText().toString().equals("")||(arrivee.getText().toString().equals("")))
                    {
                        Toast.makeText(Accueil.this, "Veuillez préciser la ville de départ et celle d'arrivée!", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent= new Intent(Accueil.this, ProposistionsListe.class);
                        intent.putExtra("depart",depart.getText().toString());
                        intent.putExtra("arrivee",arrivee.getText().toString());
                        startActivity(intent);

                    }

                }else {
                    Toast.makeText(Accueil.this,"Veuillez connecter!", Toast.LENGTH_LONG).show();
                }


            }
        });

        proposer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    Intent intent = new Intent(Accueil.this, ProposeTrajetActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Accueil.this,"Veuillez connecter!", Toast.LENGTH_LONG).show();
                }

            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser()==null) {
                Intent intent= new Intent(Accueil.this, InscriptionActivity.class);
                startActivity(intent);}

            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser()!=null) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }else {
                    Intent intent= new Intent(Accueil.this, Connexion.class);
                    startActivity(intent);
                }

            }
        });


    }
}
