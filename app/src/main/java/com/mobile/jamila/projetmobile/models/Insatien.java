package com.mobile.jamila.projetmobile.models;

/**
 * Created by User on 24/05/2017.
 */

public class Insatien {
    protected String nom;
    protected String Prenom;
    protected String classe;
    protected int numero_inscription;
    protected String email;

    public Insatien(String nom, String prenom, String classe, String email, int numero_inscription) {
        this.nom = nom;
        Prenom = prenom;
        this.classe = classe;
        this.email = email;
        this.numero_inscription = numero_inscription;
    }

    public Insatien() {

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasse() {
        return classe;
    }

    public long getNumero_inscription() {
        return numero_inscription;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }



    public void setNumero_inscription(int numero_inscription) {
        this.numero_inscription = numero_inscription;
    }


}
