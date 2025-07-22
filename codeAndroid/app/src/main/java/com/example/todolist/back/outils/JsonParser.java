package com.example.todolist.back.outils;

import com.example.todolist.back.tables.Utilisateurs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    public static List<Utilisateurs> parseUtilisateurs(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Utilisateurs>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}