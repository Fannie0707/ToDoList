package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;

public class Categories extends Entite {
    public Categories(){
        this.nomTable = "Categories";
        this.colonne = new String[]{"id","created_at","nom","couleur"};
    }
}
