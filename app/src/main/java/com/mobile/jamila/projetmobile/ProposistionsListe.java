package com.mobile.jamila.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mobile.jamila.projetmobile.models.Proposition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 26/05/2017.
 */

public class ProposistionsListe extends AppCompatActivity {
    private List<Proposition> propositionList=new ArrayList<>();
    private RecyclerView recyclerView;
    private PropositionAdapter pAdapter;
    private String depart;
    private String arrivee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_proposition);
        Firebase.setAndroidContext(this);
        Intent intent=getIntent();
        depart=intent.getStringExtra("depart");
        arrivee=intent.getStringExtra("arrivee");

        recyclerView=(RecyclerView)findViewById(R.id.listePropositon);
        pAdapter=new PropositionAdapter(propositionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        prepareMovieData();


    }

    private void prepareMovieData() {
        Firebase myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/propositions");
        myFireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*final Map<String, Proposition> messageMap = new LinkedHashMap<String, Proposition>();
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        HashMap<String,Proposition> propositionHashMap = (HashMap<String, Proposition>) postSnapshot.getValue();
                        Collection<Proposition> propositionItem = messageMap.values() ;
                        propositionItem.addAll(propositionItem);
                        pAdapter.notifyDataSetChanged();
                    }
                }*/
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot propositionSnapshot: dataSnapshot.getChildren()) {
                    Proposition proposition = propositionSnapshot.getValue(Proposition.class);
                    if(proposition.getVilleDep().toUpperCase().toString().equals(depart.toUpperCase().toString())&&
                            (proposition.getVilleArr().toUpperCase().toString().equals(arrivee.toUpperCase().toString()))){
                        propositionList.add(proposition);
                        pAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });







       /* Proposition p=new Proposition();
        p.setDateAller(new Date(2001,12,20));
        p.setHeureAller(15);
        p.setVilleDep("a");
        p.setVilleArr("z");
        p.setNmbrePlace(2);
        p.setPrix(14.0);
        propositionList.add(p);
        pAdapter.notifyDataSetChanged();*/
      /* Firebase myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/propositions");
        myFireBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int size= (int) dataSnapshot.getChildrenCount();

                    Proposition value = (Proposition) dataSnapshot.getChildren();
                    propositionList.add(value);
                    pAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
    }


}
