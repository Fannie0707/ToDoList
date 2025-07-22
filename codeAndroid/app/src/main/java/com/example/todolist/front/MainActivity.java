package com.example.todolist.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.todolist.R;
import com.example.todolist.back.bdd.Parameters;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Utilisateurs;

import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView btnIncription = findViewById(R.id.btnIncription);
        TextView btnConnexion = findViewById(R.id.btnConnexion);
        SupabaseService supa = new SupabaseService();
        Parameters[] parameters = new Parameters[]{new Parameters("nom","TEST")};
        Utilisateurs u = new Utilisateurs(3,"2025-07-21T13:43:52+00:00", "TEST", "TEST");
        btnIncription.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this, Incription.class);
                 startActivity(i);
             }
        }
        );

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Connexion.class);
                 startActivity(i);
             }
        }
        );
    }
}
