package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.HashMap;
import java.util.Map;

public class Utilisateurs extends Entite {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    private String created_at;
    private String nom;
    private String mdp;
    public static final String nomTable = "Utilisateurs";
    public static final String[] colonne =  new String[]{"id", "created_at","nom", "mdp"};
    public Utilisateurs(String created_at, String nom, String mdp) {
        this.created_at = created_at;
        this.nom = nom;
        this.mdp = mdp;
    }

    public int getId() { return id; }
    public String getCreated_at() { return created_at; }
    public String getNom() { return nom; }
    public String getMdp() { return mdp; }

    @Override
    public Map<String,String> convertionMap() {
        Map<String, String> m = new HashMap<>();
        m.put("nom",nom);
        m.put("mdp",mdp);
        return m;
    }
}
