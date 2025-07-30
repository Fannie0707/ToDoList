package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Categories extends Entite {

    private int id;
    private String created_at;
    private String nom;
    private String couleur;
    public static final String nomTable = "Categorie";
    public static final String[] colonne =  new String[]{"id","created_at","nom","couleur"};
    public Categories( String created_at, String nom, String couleur){
        this.nom = nom;
        this.couleur = couleur;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public Map<String, String> convertionMap() {
        Map<String,String> m = new HashMap<>();
        m.put("id", Integer.toString(id));
        m.put("created_at",created_at);
        m.put("nom",nom);
        m.put("couleur",couleur);

        return m;
    }
}
