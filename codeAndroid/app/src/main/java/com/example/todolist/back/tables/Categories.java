package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Categories extends Entite {

    private String id;
    private String created_at;
    private String nom;
    private String couleur;
    private String utilisateur_id;
    public static final String nomTable = "Categorie";
    public static final String[] colonne =  new String[]{"id","created_at","nom","couleur"};
    public Categories(String created_at, String nom, String couleur, String utilisateur_id){
        this.nom = nom;
        this.couleur = couleur;
        this.created_at = created_at;
    }
    public Categories(String nom, String couleur, String utilisateur_id){
        this.nom = nom;
        this.couleur = couleur;
        this.utilisateur_id = utilisateur_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUtilisateur_id(String utilisateur_id){
        this.utilisateur_id = utilisateur_id;
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
        m.put("nom",nom);
        m.put("couleur",couleur);
        m.put("utilisateur",utilisateur_id);

        return m;
    }
    @Override
    public boolean equals(Object c){
        if (c == null||c.getClass() != this.getClass()){
            return false;
        }
        if (
                Objects.equals(((Categories) c).getNom(), this.getNom()) &&
                Objects.equals(((Categories) c).getCouleur(), this.getCouleur())
        ){
            return true;
        }else{
            return false;
        }
    }
}
