package com.example.todolist;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Taches;
import com.example.todolist.back.tables.Utilisateurs;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.TimeUnit;

@RunWith(RobolectricTestRunner.class)
public class BDDTest {
    //TODO refaire les tests en rajoutant la vérification avec CountDownLatch
    SupabaseService supa = new SupabaseService();

    @Test
    public void testInsertionUtilisateur(){
        Utilisateurs u = new Utilisateurs("04/07/2025", "Sonia", "zoro");
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
        Categories c = new Categories("2025-07-30 15:43:03+00","Menage","#EEE");
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
    public void testInsertionTacheRepetV() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Utilisateurs u = new Utilisateurs("04/07/2025", "Sonia", "zoro");
        u.setId(1);
        Taches c = new Taches(true, "Manger", u);
        SupabaseCallback result = new SupabaseCallback()    {
            @Override
            public void onSuccess(String json) {
                System.out.println("Succes");
                System.out.println(json);
                assertTrue(true);
                latch.countDown();
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
                latch.countDown();
                fail();
            }
        };
        supa.insererDonne(Taches.nomTable, c, result);
        boolean terminé = latch.await(10, TimeUnit.SECONDS);
        if (!terminé) {
            throw new AssertionError("Timeout : le serveur n'a pas répondu à temps");
        }
    }
    @Test
    public void testInsertionTacheRepetF(){
        Utilisateurs u = new Utilisateurs("04/07/2025", "Sonia", "zoro");
        u.setId(1);
        Taches c = new Taches(false, "RDV", u);
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
        Utilisateurs u = new Utilisateurs("04/07/2025", "Sonia", "zoro");
        Taches c = new Taches(false, "Faire les pâtes", 1, u);
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
