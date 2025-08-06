package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Taches extends Entite {

    private String id;
    private String created_at;
    private String nom;
    private String parent;
    private boolean repetition = false;
    private String statut;
    private String urgence;
    private String type;
    private Utilisateurs utilisateur;
    private Categories categorie;
    public final static String nomTable = "Tache";
    public final static String[] colonne = new String[]{"id","created_at","nom","parent","repetition","statut"};

    public Taches(String nom, Utilisateurs utilisateur){
        this.utilisateur = utilisateur;
        this.nom = nom;
        this.statut = "pas commence";
        this.urgence = "si possible";
        this.type = "journaliere";

    }
    public Taches(){
        this.utilisateur = null;
        this.nom = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
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

    public void setUtilisateur(Utilisateurs utilisateur){
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object t){
        if (t == null||t.getClass() != this.getClass()){
            return false;
        }
        return Objects.equals(((Taches) t).getNom(), this.getNom()) &&
                Objects.equals(((Taches) t).getParent(), this.getParent()) &&
                Objects.equals(((Taches) t).getStatut(), this.getStatut()) &&
                Objects.equals(((Taches) t).isRepetition(), this.isRepetition()) &&
                Objects.equals(((Taches) t).getType(), this.getType()) &&
                Objects.equals(((Taches) t).getUrgence(), this.getUrgence());
    }

    @Override
    public Map<String, String> convertionMap() {
        Map<String,String> m = new HashMap<>();
        m.put("utilisateur", utilisateur.getId());
        m.put("nom",nom);
        m.put("repetition",Boolean.toString(repetition));
        if (categorie != null){
            m.put("categorie",this.categorie.getId());
        }
        if (parent != null){
            m.put("parent",parent);
        }

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

    public void setType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getUrgence(){
        return this.urgence;
    }
}
