package com.mobile.jamila.projetmobile.dataAccess;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mobile.jamila.projetmobile.models.Proposition;

/**
 * Created by User on 27/04/2017.
 */

public class PropositionDAO {
    Firebase myFireBase;
    Proposition proposition;

    public PropositionDAO() {
        this.myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/propositions");

    }

    public Proposition readProposition()
    {
        return proposition;
    }

    public void retrievePropositions() {

    }

    public boolean createProposition(Proposition proposition)
    {
        try {
            String propositionId = myFireBase.push().getKey();
            proposition.setId(propositionId);
            myFireBase.child(propositionId).setValue(proposition);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public void getOneProposition(final String proposisionId){

        //Firebase pFirebase=myFireBase.child(proposisionId);
        myFireBase.child(proposisionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("log",String.valueOf(dataSnapshot.getChildrenCount()));
                Proposition p=dataSnapshot.getValue(Proposition.class);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public boolean updateProposition(Proposition proposition)
    {
        return true;
    }

    public boolean deleteProposition(Proposition proposition)
    {
        return true;
    }

}
