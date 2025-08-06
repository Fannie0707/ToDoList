package com.example.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.todolist.back.bdd.Parametre;
import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Utilisateurs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(RobolectricTestRunner.class)
public class CategoriesTests {
    SupabaseService supa = new SupabaseService();
    Utilisateurs u = new Utilisateurs( "testCategories", "categories");

    @Before
    public void before() throws InterruptedException {
        CountDownLatch insert_latch = new CountDownLatch(1);
        CountDownLatch retrieveLatch = new CountDownLatch(1);
        supa.insererDonne(Utilisateurs.nomTable, u, supa.resultat(insert_latch));
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.rechercheDonne(Utilisateurs.nomTable,p,supa.recuperationUtilisateurs(u,retrieveLatch));
        supa.launch();
        verificationReponse(insert_latch);
        verificationReponse(retrieveLatch);
    }
    @After
    public void after() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.supprimerDonne(Utilisateurs.nomTable, p, supa.resultat(latch));
        supa.launch();
        verificationReponse(latch);
    }
    @Test
    public void testInsertionCategorie() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Categories c = new Categories("Menage","#EEE", u.getId());
        supa.insererDonne(Categories.nomTable, c, reussiteAttendu(latch));
        supa.launch();
        verificationReponse(latch);
    }

    @Test
    public void testRecuperationCategorie() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch retrieveLatch = new CountDownLatch(1);
        Categories c = new Categories("Menage","#EEE", u.getId());
        Categories categorieAttendue = new Categories("","","","");
        supa.insererDonne(Categories.nomTable, c, reussiteAttendu(latch));
        Parametre[] p = new Parametre[]{
                new Parametre("nom",c.getNom()),
                new Parametre("utilisateur",u.getId())
        };
        supa.rechercheDonne(Categories.nomTable,p,supa.recuperationCategorie(categorieAttendue,retrieveLatch));
        supa.launch();
        verificationReponse(latch);
        verificationReponse(retrieveLatch);
        assertEquals(c,categorieAttendue);
    }
    public void verificationReponse(CountDownLatch latch) throws InterruptedException, AssertionError{
        boolean fini = latch.await(10, TimeUnit.SECONDS);
        if (!fini) {
            throw new AssertionError("Timeout : le serveur n'a pas répondu à temps");
        }
    }
    public SupabaseCallback reussiteAttendu(CountDownLatch latch){
        return new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
                latch.countDown();
                assertTrue(true);
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
                latch.countDown();
                fail();
            }
        };
    }
    public SupabaseCallback erreurAttendu(CountDownLatch latch) { return new SupabaseCallback() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json);
                latch.countDown();
                fail();
            }
            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
                latch.countDown();
                assertTrue(true);
            }
        };
    }
}
