package com.example.todolist.back.bdd;

public class Parametre {
    public String column;
    public String value;
    public Operators operator;

    public Parametre(String column, String value, Operators operator){
        this.column = column;
        this.value = value;
        this.operator = operator;
    }
    public Parametre(String column, String value){
        this.column = column;
        this.value = value;
        this.operator = Operators.EQUAL;
    }
}
