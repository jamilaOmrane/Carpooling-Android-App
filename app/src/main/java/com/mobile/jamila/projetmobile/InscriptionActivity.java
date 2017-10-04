package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobile.jamila.projetmobile.dataAccess.ConducteurDAO;
import com.mobile.jamila.projetmobile.dataAccess.InsatienDAO;
import com.mobile.jamila.projetmobile.models.Conducteur;
import com.mobile.jamila.projetmobile.models.Insatien;

/**
 * Created by User on 27/05/2017.
 */

public class InscriptionActivity extends AppCompatActivity {

    EditText inscri;
    EditText email;
    EditText motdepasse;
    EditText prenom;
    EditText nom;
    EditText niveauFiliere;
    CheckBox conducteur;
    EditText voiture;
    Button bouttonInscri;
    private FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        auth = FirebaseAuth.getInstance();

        inscri=(EditText)findViewById(R.id.inscri);
        email=(EditText)findViewById(R.id.mail);
        motdepasse=(EditText)findViewById(R.id.password);
        prenom=(EditText)findViewById(R.id.prenom);
        nom=(EditText)findViewById(R.id.nom);
        niveauFiliere=(EditText)findViewById(R.id.niveauFiliere);
        conducteur=(CheckBox) findViewById(R.id.CBconducteur);
        voiture=(EditText)findViewById(R.id.voiture);
        bouttonInscri=(Button)findViewById(R.id.bouttonInscri);

        conducteur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!conducteur.isChecked()){
                    voiture.setVisibility(View.INVISIBLE);
                }else {
                    voiture.setVisibility(View.VISIBLE);
                }
            }
        });

        bouttonInscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conducteur.isChecked()){
                    if(inscri.getText().toString().equals("")||email.getText().toString().equals("")|| motdepasse.getText().toString().equals("")||
                            prenom.getText().toString().equals("")||
                            nom.getText().toString().equals("")||niveauFiliere.getText().toString().equals("")||voiture.getText().toString().equals(""))
                    {
                        Toast.makeText(InscriptionActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                    }else {
                       // String nom, String prenom, String classe, String email, int numero_inscription, String voiture
                        /*Insatien insatien=new Insatien(nom.getText().toString(), prenom.getText().toString(),niveauFiliere.getText().toString(),
                                email.getText().toString(), Integer.parseInt(inscri.getText().toString()));
                        InsatienDAO insatienDAO=new InsatienDAO();
                        insatienDAO.createInsatien(insatien);*/

                        Conducteur conducteur=new Conducteur(nom.getText().toString(), prenom.getText().toString(),niveauFiliere.getText().toString(),
                                email.getText().toString(), Integer.parseInt(inscri.getText().toString()), voiture.getText().toString());
                        ConducteurDAO conducteurDAO=new ConducteurDAO();
                        conducteurDAO.createConducteur(conducteur);
                        auth.createUserWithEmailAndPassword(email.getText().toString(),motdepasse.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(InscriptionActivity.this, "Echec.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent=new Intent(InscriptionActivity.this, Connexion.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });



                    }
                }else {
                    if(inscri.getText().toString().equals("")||email.getText().toString().equals("")|| motdepasse.getText().toString().equals("")||
                            prenom.getText().toString().equals("")||
                            nom.getText().toString().equals("")||niveauFiliere.getText().toString().equals(""))
                    {
                        Toast.makeText(InscriptionActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                    }else {
                        //String nom, String prenom, String classe, String cin, long numero_inscription
                        Insatien insatien=new Insatien(nom.getText().toString(), prenom.getText().toString(),niveauFiliere.getText().toString(),
                                email.getText().toString(), Integer.parseInt(inscri.getText().toString()));
                        InsatienDAO insatienDAO=new InsatienDAO();
                        insatienDAO.createInsatien(insatien);
                        auth.createUserWithEmailAndPassword(email.getText().toString(),motdepasse.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(InscriptionActivity.this, "Echec.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent=new Intent(InscriptionActivity.this, Connexion.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });

                    }

                }

            }
        });
    }
}
