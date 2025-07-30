package com.example.todolist.front;

import android.app.Activity;
import android.os.Bundle;

import com.example.todolist.R;

public class ToDoList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_todo);
    }
}