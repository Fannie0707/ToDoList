package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utilisateurs extends Entite {
    private String id;

    public void setId(String id) {
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
    public Utilisateurs(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }

    public String getId() { return id; }
    public String getCreated_at() { return created_at; }
    public String getNom() { return nom; }
    public String getMdp() { return mdp; }

    @Override
    public boolean equals(Object u){
        if (u == null||u.getClass() != this.getClass()){
            return false;
        }
        if (Objects.equals(((Utilisateurs) u).getNom(), this.getNom()) && Objects.equals(((Utilisateurs) u).getMdp(), this.getMdp())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Map<String,String> convertionMap() {
        Map<String, String> m = new HashMap<>();
        m.put("nom",nom);
        m.put("mdp",mdp);
        return m;
    }
}
