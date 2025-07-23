package com.example.todolist.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.todolist.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView btnIncription = findViewById(R.id.btnIncription);
        TextView btnConnexion = findViewById(R.id.btnConnexion);

        btnIncription.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Incription.class);
            startActivity(i);
        }
        );

        btnConnexion.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Connexion.class);
             startActivity(i);
         }
        );
    }
}
