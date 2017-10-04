package com.mobile.jamila.projetmobile.dataAccess;

import com.firebase.client.Firebase;
import com.mobile.jamila.projetmobile.models.Conducteur;
import com.mobile.jamila.projetmobile.models.Insatien;

/**
 * Created by User on 28/05/2017.
 */

public class ConducteurDAO {

    Firebase myFireBase;


    public ConducteurDAO() {
        this.myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/conducteurs");

    }

    public boolean createConducteur(Conducteur conducteur)
    {
        try {
            //String propositionId = myFireBase.push().getKey();

            myFireBase.child(String.valueOf(conducteur.getNumero_inscription())).setValue(conducteur);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
