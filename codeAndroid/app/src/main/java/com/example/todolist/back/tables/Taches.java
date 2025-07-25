package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Taches extends Entite {

    private int id;
    private String created_at;
    private String nom;
    private int parent;
    private boolean repetition;
    private String statut;
    public Taches(){
        this.nomTable = "Taches";
        this.colonne = new String[]{"id","created_at","nom","parent","repetition","statut"};
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
        m.put("id", Integer.toString(id));
        m.put("created_at",created_at);
        m.put("nom",nom);
        m.put("parent",Integer.toString(parent));
        m.put("repetition",Boolean.toString(repetition));
        m.put("statut",statut);

        return m;
    }
}
