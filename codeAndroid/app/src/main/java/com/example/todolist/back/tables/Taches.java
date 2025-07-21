package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

public class Taches extends Entite {

    public Taches(){
        this.nomTable = "Taches";
        this.colonne = new String[]{"id","created_at","nom","parent","repetition","statut"};
    }
}
