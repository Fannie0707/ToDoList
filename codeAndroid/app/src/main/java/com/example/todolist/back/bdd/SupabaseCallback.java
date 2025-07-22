package com.example.todolist.back.bdd;

public interface SupabaseCallback {
    void onSuccess(String json);
    void onError(Exception e);
}
