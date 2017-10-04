package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by User on 28/05/2017.
 */

public class Connexion extends AppCompatActivity {
    EditText mail;
    EditText password;
    Button connexion;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        Firebase.setAndroidContext(this);

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){

        }


        authListener= new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){

                    Intent intent=new Intent(Connexion.this, Accueil.class);
                    intent.putExtra("connected",true);
                    startActivity(intent);

                }

            }
        };

        mail=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        connexion=(Button)findViewById(R.id.connexionBoutton);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mail.getText().toString().equals("")||password.getText().toString().equals("")){
                    Toast.makeText(Connexion.this,"Veuillez remplir les deux champs.",Toast.LENGTH_LONG).show();
                }else {
                    firebaseAuth.signInWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnCompleteListener(Connexion.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.v("jamila", task.getResult().toString());
                            if(!task.isSuccessful()){
                                Toast.makeText(Connexion.this,"Problème de connexion.",Toast.LENGTH_LONG).show();
                            }else{
                                //Toast.makeText(Connexion.this,"ça marche...",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);

    }

}
