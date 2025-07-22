package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

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
    public Utilisateurs(int id, String created_at, String nom, String mdp) {
        this.id = id;
        this.created_at = created_at;
        this.nom = nom;
        this.mdp = mdp;
    }
    public Utilisateurs() {
    }
    public int getId() { return id; }
    public String getCreated_at() { return created_at; }
    public String getNom() { return nom; }
    public String getMdp() { return mdp; }
}
