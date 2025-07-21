package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

public class Utilisateurs extends Entite {
    public Utilisateurs() {
        this.nomTable = "Utilisateurs";
        this.colonne = new String[]{"id", "created_at","nom", "mdp"};
    }
}
