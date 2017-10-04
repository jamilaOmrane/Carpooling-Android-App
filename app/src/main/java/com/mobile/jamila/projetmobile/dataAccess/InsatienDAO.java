package com.mobile.jamila.projetmobile.dataAccess;

import com.firebase.client.Firebase;
import com.mobile.jamila.projetmobile.models.Insatien;
import com.mobile.jamila.projetmobile.models.Proposition;

/**
 * Created by User on 28/05/2017.
 */

public class InsatienDAO {
    Firebase myFireBase;


    public InsatienDAO() {
        this.myFireBase =new Firebase("https://projetmobile-ed855.firebaseio.com/insatiens");

    }

    public boolean createInsatien(Insatien insatien)
    {
        try {
            //String propositionId = myFireBase.push().getKey();

            myFireBase.child(String.valueOf(insatien.getNumero_inscription())).setValue(insatien);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
