package com.example.todolist.back.bdd;

public enum Operators {
    EQUAL("eq.");
    private final String value;
    Operators(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
