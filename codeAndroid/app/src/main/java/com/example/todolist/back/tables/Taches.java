package com.example.todolist.back.tables;

import com.example.todolist.back.Entite;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class Taches extends Entite {

    public Taches(){
        this.nomTable = "Taches";
        this.colonne = new String[]{"id","created_at","nom","parent","repetition","statut"};
    }
}
