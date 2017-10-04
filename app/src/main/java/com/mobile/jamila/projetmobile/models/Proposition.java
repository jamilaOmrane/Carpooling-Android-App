package com.mobile.jamila.projetmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 22/04/2017.
 */

public class Proposition implements Parcelable {
    String villeDep;
    String villeArr;
    Boolean allerReour;
    //Date dateAller;
    int jourAller;
    int moisAller;
    int heureAller=100;
    int minAller=100;
    //Date dateRetour;
    int jourRetour;
    int moisRetour;
    int heureRetour=100;
    int minRetour=100;
    Double prix;
    int nmbrePlace=-1;
    String precision;
    String tailleBagage;
    String flexibilite;
    String id;
    int conducteur;

    public int getConducteur() {
        return conducteur;
    }

    public void setConducteur(int conducteur) {
        this.conducteur = conducteur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeureAller(int heureAller) {
        this.heureAller = heureAller;
    }

    public void setMinAller(int minAller) {
        this.minAller = minAller;
    }

    public void setHeureRetour(int heureRetour) {
        this.heureRetour = heureRetour;
    }

    public void setMinRetour(int minRetour) {
        this.minRetour = minRetour;
    }
    public void setVilleDep(String villeDep) {
        this.villeDep = villeDep;
    }

    public void setVilleArr(String villeArr) {
        this.villeArr = villeArr;
    }

    public void setAllerReour(Boolean allerReour) {
        this.allerReour = allerReour;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setNmbrePlace(int nmbrePlace) {
        this.nmbrePlace = nmbrePlace;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public void setTailleBagage(String tailleBagage) {
        this.tailleBagage = tailleBagage;
    }

    public void setFlexibilite(String flexibilite) {
        this.flexibilite = flexibilite;
    }

    public void setJourAller(int jourAller) {
        this.jourAller = jourAller;
    }

    public void setMoisAller(int moisAller) {
        this.moisAller = moisAller;
    }

    public void setJourRetour(int jourRetour) {
        this.jourRetour = jourRetour;
    }

    public void setMoisRetour(int moisRetour) {
        this.moisRetour = moisRetour;
    }

    public int getHeureAller() {

        return heureAller;
    }

    public int getMinAller() {
        return minAller;
    }

    public int getHeureRetour() {
        return heureRetour;
    }

    public int getMinRetour() {
        return minRetour;
    }

    public String getVilleArr() {

        return villeArr;
    }

    public Boolean getAllerReour() {
        return allerReour;
    }


    public Double getPrix() {
        return prix;
    }

    public String getPrecision() {
        return precision;
    }

    public int getNmbrePlace() {
        return nmbrePlace;
    }

    public String getTailleBagage() {
        return tailleBagage;
    }

    public String getFlexibilite() {
        return flexibilite;
    }

    public String getVilleDep() {

        return villeDep;
    }

    public int getJourAller() {
        return jourAller;
    }

    public int getMoisAller() {
        return moisAller;
    }

    public int getJourRetour() {
        return jourRetour;
    }

    public int getMoisRetour() {
        return moisRetour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(villeDep);
        dest.writeString(villeArr);

        dest.writeString(allerReour.toString());
        dest.writeInt(jourAller);
        dest.writeInt(moisAller);
        dest.writeInt(heureAller);
        dest.writeInt(minAller);
        dest.writeInt(jourRetour);
        dest.writeInt(moisRetour);
        dest.writeInt(heureRetour);
        dest.writeInt(minRetour);
        /*dest.writeDouble(prix);
        dest.writeInt(nmbrePlace);
        dest.writeString(precision);
        dest.writeString(tailleBagage);*/
    }

    public static final Parcelable.Creator<Proposition> CREATOR = new Parcelable.Creator<Proposition>() {
        public Proposition createFromParcel(Parcel in) {
            return new Proposition(in);
        }

        public Proposition[] newArray(int size) {
            return new Proposition[size];
        }
    };

    public Proposition() {
    }

    public Proposition(String villeDep, String villeArr, Boolean allerReour, int jourAller, int moisAller, int heureAller, int minAller, int jourRetour, int moisRetour, int heureRetour, int minRetour, Double prix, String precision, String tailleBagage, String flexibilite, int nmbrePlace) {

        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.allerReour = allerReour;
        this.jourAller = jourAller;
        this.moisAller = moisAller;
        this.heureAller = heureAller;
        this.jourRetour = jourRetour;
        this.moisRetour = moisRetour;
        this.minAller = minAller;
        this.heureRetour = heureRetour;
        this.minRetour = minRetour;
        this.prix = prix;
        this.precision = precision;
        this.tailleBagage = tailleBagage;
        this.flexibilite = flexibilite;
        this.nmbrePlace = nmbrePlace;
    }

    private Proposition(Parcel in) {
        villeDep = in.readString();
        villeArr = in.readString();
        allerReour=Boolean.valueOf(in.readString());
        jourAller=in.readInt();
        moisAller=in.readInt();
        heureAller=in.readInt();
        minAller=in.readInt();
        jourRetour=in.readInt();
        moisRetour=in.readInt();
        heureRetour=in.readInt();
        minRetour=in.readInt();

    }
}
