package com.example.todolist;

import static org.junit.Assert.assertEquals;

import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Taches;
import com.example.todolist.back.tables.Utilisateurs;

import org.junit.Test;

public class EntitesUnitTest {
    @Test
    public void testAfficherColonnesU(){
        Utilisateurs u = new Utilisateurs();

        String colonnes = u.afficherColonnes();
        assertEquals("id,created_at,nom,mdp",colonnes);
    }
    @Test
    public void testAfficherColonnesT(){
        Taches t = new Taches();

        String colonnes = t.afficherColonnes();
        assertEquals("id,created_at,nom,parent,repetition,statut",colonnes);
    }
    @Test
    public void testAfficherColonnesC(){
        Categories c = new Categories();

        String colonnes = c.afficherColonnes();
        assertEquals("id,created_at,nom,couleur",colonnes);
    }
}
