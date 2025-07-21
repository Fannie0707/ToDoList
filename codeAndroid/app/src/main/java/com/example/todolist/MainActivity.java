package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView btnIncription = findViewById(R.id.btnIncription);
        TextView btnConnexion = findViewById(R.id.btnConnexion);

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
