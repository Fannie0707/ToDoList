package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Taches extends Entite {

    private int id;
    private String created_at;
    private String nom;
    private Integer parent;
    private boolean repetition;
    private String statut;
    private String urgence;
    private String type;
    private Utilisateurs utilisateur;
    private Categories categorie;
    public final static String nomTable = "Tache";
    public final static String[] colonne = new String[]{"id","created_at","nom","parent","repetition","statut"};
    public Taches(boolean repetition, String nom, int parent, Utilisateurs utilisateur){
        this.repetition = repetition;
        this.parent = parent;
        this.statut = "Pas commence";
        this.nom = nom;
        this.utilisateur = utilisateur;

    }
    public Taches(boolean repetition, String nom, Utilisateurs utilisateur){
        this.repetition = repetition;
        this.parent = 0;
        this.statut = "Pas commence";
        this.utilisateur = utilisateur;
        this.nom = nom;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public boolean isRepetition() {
        return repetition;
    }

    public void setRepetition(boolean repetition) {
        this.repetition = repetition;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public Map<String, String> convertionMap() {
        Map<String,String> m = new HashMap<>();
        m.put("utilisateur", "1");
        if (categorie != null){
            m.put("categorie",Integer.toString(this.categorie.getId()));
        }
        if (parent != null){
            m.put("parent",Integer.toString(parent));
        }
        m.put("nom",nom);
        m.put("repetition",Boolean.toString(repetition));
        if (statut != null){
            m.put("statut",statut);
        }
        if (this.urgence != null){
            m.put("urgence",this.urgence);
        }
        if (this.type != null){
            m.put("type",this.type);
        }
        return m;
    }
}
