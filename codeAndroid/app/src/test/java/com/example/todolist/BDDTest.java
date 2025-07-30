package com.example.todolist;


import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Taches;
import com.example.todolist.back.tables.Utilisateurs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class BDDTest {
    SupabaseService supa = new SupabaseService();

    @Test
    public void testInsertionUtilisateur(){
        Utilisateurs u = new Utilisateurs(3,"04/07/2025", "Sonia", "zoro");
        SupabaseCallback result = new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        supa.insererDonne(Utilisateurs.nomTable, u, result);
    }
    @Test
    public void testInsertionCategorie(){
        //TODO rajouter les permissions d'insertions sur les tables
        Categories c = new Categories("07/08/2025","Ménage","#FFF");
        SupabaseCallback result = new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        supa.insererDonne(Categories.nomTable, c, result);
    }
    @Test
    public void testInsertionTacheRepetV(){
        Taches c = new Taches(true, "07/08/2025", "Manger");
        SupabaseCallback result = new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        supa.insererDonne(Taches.nomTable, c, result);
    }
    @Test
    public void testInsertionTacheRepetF(){
        Taches c = new Taches(false, "07/08/2025", "Manger");
        SupabaseCallback result = new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        supa.insererDonne(Taches.nomTable, c, result);
    }
    @Test
    public void testInsertionTacheParent(){
        Taches c = new Taches(false, "07/08/2025", "Manger", 0);
        SupabaseCallback result = new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        supa.insererDonne(Taches.nomTable, c, result);
    }
}
