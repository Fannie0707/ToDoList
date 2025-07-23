package com.example.todolist.front;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.back.bdd.Parametre;
import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Utilisateurs;

public class Connexion extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        Button btn_connexion = findViewById(R.id.btn_confirmation);
        EditText nom = findViewById(R.id.nom_e);
        EditText mdp = findViewById(R.id.mdp_e);

        SupabaseService supa = new SupabaseService();

        btn_connexion.setOnClickListener(v -> {
            Parametre[] parametres = new Parametre[]{
                    new Parametre("nom", nom.getText().toString()),
                    new Parametre("mdp", mdp.getText().toString())
            };
            supa.rechercheDonne(Utilisateurs.nomTable, parametres, connexionUtilisateur(nom, mdp));
        });

    }

    private SupabaseCallback connexionUtilisateur(EditText nom, EditText mdp){
        return new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                runOnUiThread(()->{
                    Toast.makeText(Connexion.this,"Utilisateur trouvé",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Connexion.this, HomeActivity.class);
                    startActivity(i);
                });
            }
            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(Connexion.this,"Utilisateur introuvable",Toast.LENGTH_SHORT).show();
                    nom.setText("");
                    mdp.setText("");
                });
            }
        };
    }
}
