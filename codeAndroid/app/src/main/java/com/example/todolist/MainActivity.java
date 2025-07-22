package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import com.example.todolist.back.bdd.Parameters;
import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.outils.JsonParser;
import com.example.todolist.back.tables.Utilisateurs;

import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView btnIncription = findViewById(R.id.btnIncription);
        TextView btnConnexion = findViewById(R.id.btnConnexion);
        SupabaseService supa = new SupabaseService();
        Parameters[] parameters = new Parameters[]{new Parameters("nom","Léo")};
        supa.fetchData(Utilisateurs.nomTable,parameters,new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                List<Utilisateurs> utilisateurs = JsonParser.parseUtilisateurs(json);
                System.out.println(json);
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        });

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
