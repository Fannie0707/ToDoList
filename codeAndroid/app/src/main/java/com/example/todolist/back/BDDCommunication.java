package com.example.todolist.back;

import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Taches;
import com.example.todolist.back.tables.Utilisateurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class BDDCommunication {
    private String url = "jdbc:postgresql://postgres.clibtdmmtppmefgurknv:hr:mDd!S3hNQe4'@aws-0-us-east-2.pooler.supabase.com:5432/postgres";
    private String utilisateur = "postgres";
    private String motDePasse = "hr:mDd!S3hNQe4'";
    private String apiKey = "sb_publishable_UQe7gN2ojGRyxFfFuRv1sQ_0oL730XD";
    public boolean connected = false;

    public BDDCommunication(){
        this.connectionBDD();
    }
    private void connectionBDD(){
        try  {
            Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse);
            // Connexion à la base de données
            System.out.println("connection réussie !");
            connected = true;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean insertiondDonne(Entite entite){
        String req = "INSERT INTO "
                +entite.nomTable+ " ("+entite.afficherColonnes()+") "
                +" VALUES ("+entite.afficherValeur()+")";

        return true;
    }
    public void ajoutUtilisateur(Utilisateurs u){
        insertiondDonne(u);
    }
    public void ajoutTache(Taches t){
        insertiondDonne(t);
    }
    public void ajoutCategorie(Categories c){
        insertiondDonne(c);
    }
}
