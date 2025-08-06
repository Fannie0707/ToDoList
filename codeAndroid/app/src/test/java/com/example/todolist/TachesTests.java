package com.example.todolist;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.todolist.back.bdd.Parametre;
import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Categories;
import com.example.todolist.back.tables.Taches;
import com.example.todolist.back.tables.Utilisateurs;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.versioning.AndroidVersions;

import java.util.concurrent.TimeUnit;

@RunWith(RobolectricTestRunner.class)
public class TachesTests {
    SupabaseService supa = new SupabaseService();
    CountDownLatch latch = new CountDownLatch(1);
    SupabaseCallback reussiteAttendu = new SupabaseCallback() {
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
    SupabaseCallback erreurAttendu = new SupabaseCallback() {
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
    Utilisateurs u = new Utilisateurs("testTacheUtilisateur","taches");
    Categories c = new Categories("testTachesCategories","#FFF", u.getId());
    Taches t = new Taches("testTache",u);
    @Before
    public void before() throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        CountDownLatch latch4 = new CountDownLatch(1);
        supa.insererDonne(Utilisateurs.nomTable, u, supa.resultat(latch1));
        Parametre[] p1 = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.rechercheDonne(Utilisateurs.nomTable,p1,supa.recuperationUtilisateurs(u,latch2));
        supa.launch();
        verificationReponse(latch1);
        verificationReponse(latch2);

        c.setUtilisateur_id(u.getId());
        supa.insererDonne(Categories.nomTable, c, supa.resultat(latch3));
        Parametre[] p2 = new Parametre[]{
                new Parametre("nom",c.getNom())
        };
        supa.rechercheDonne(Categories.nomTable,p2,supa.recuperationCategorie(c,latch4));
        supa.launch();
        verificationReponse(latch3);
        verificationReponse(latch4);
    }
    @After
    public void after() throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.supprimerDonne(Utilisateurs.nomTable, p, supa.resultat(latch1));
        supa.launch();
        verificationReponse(latch1);
    }
    @Test
    public void testInsertionTacheRepetV() throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        t.setRepetition(true);
        supa.insererDonne(Taches.nomTable, t, reussiteAttendu);
        supa.launch();
        verificationReponse(latch1);
    }
    @Test
    public void testInsertionTacheRepetF() throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        supa.insererDonne(Taches.nomTable, t, reussiteAttendu);
        supa.launch();
        verificationReponse(latch1);
    }
    @Test
    public void testRecuperationTache() throws InterruptedException {
        Taches tacheAttendu = new Taches();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        t.setUtilisateur(u);
        supa.insererDonne(Taches.nomTable, t, supa.resultat(latch1));
        Parametre[] p = new Parametre[]{
                new Parametre("nom",t.getNom())
        };
        supa.rechercheDonne(Taches.nomTable,p,supa.recuperationTache(tacheAttendu,latch2));
        supa.launch();
        verificationReponse(latch1);
        verificationReponse(latch2);
        assertEquals(t,tacheAttendu);
    }
    @Test
    public void testInsertionTacheParent() throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        Taches t_parent = new Taches("testTacheParent",u);
        supa.insererDonne(Taches.nomTable, t_parent, supa.resultat(latch1));
        Parametre[] p = new Parametre[]{
                new Parametre("nom",t.getNom())
        };
        supa.rechercheDonne(Taches.nomTable, p, supa.recuperationTache(t_parent,latch2));
        t.setParent(t_parent.getId());
        supa.insererDonne(Taches.nomTable, t, supa.resultat(latch3));
        supa.launch();
        verificationReponse(latch1);
        verificationReponse(latch2);
    }

    public void verificationReponse(CountDownLatch latch) throws InterruptedException, AssertionError{
        boolean fini = latch.await(10, TimeUnit.SECONDS);
        if (!fini) {
            throw new AssertionError("Timeout : le serveur n'a pas répondu à temps");
        }
    }
}
