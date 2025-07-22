package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class Categories extends Entite {
    public Categories(){
        this.nomTable = "Categories";
        this.colonne = new String[]{"id","created_at","nom","couleur"};
    }

}
