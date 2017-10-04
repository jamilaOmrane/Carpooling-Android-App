package com.mobile.jamila.projetmobile.models;

/**
 * Created by User on 24/05/2017.
 */

public class Conducteur /*extends Insatien */{
    protected String nom;
    protected String Prenom;
    protected String classe;
    protected int numero_inscription;
    protected String email;
    String voiture;

    /*public Conducteur(String nom, String prenom, String classe, String email, int numero_inscription, String voiture) {
        super(nom, prenom, classe, email, numero_inscription);
        this.voiture=voiture;
    }*/
    public Conducteur(String nom, String prenom, String classe, String email, int numero_inscription, String voiture) {
        super();
        this.nom=nom;
        this.Prenom=prenom;
        this.classe=classe;
        this.email=email;
        this.numero_inscription=numero_inscription;

        this.voiture=voiture;
    }

    public Conducteur() {
    }

    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }


    public int getNumero_inscription() {
        return numero_inscription;
    }


    public void setNumero_inscription(int numero_inscription) {
        this.numero_inscription = numero_inscription;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public String getVoiture() {

        return voiture;
    }
}
