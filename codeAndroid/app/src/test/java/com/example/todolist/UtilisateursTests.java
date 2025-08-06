package com.example.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.todolist.back.bdd.Parametre;
import com.example.todolist.back.bdd.SupabaseCallback;
import com.example.todolist.back.bdd.SupabaseService;
import com.example.todolist.back.tables.Utilisateurs;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.versioning.AndroidVersions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(RobolectricTestRunner.class)
public class UtilisateursTests {
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
    Utilisateurs u = new Utilisateurs( "testUtilisateurs", "utilisateurs");
    @Before
    public void before(){
        latch = new CountDownLatch(1);
    }
    @After
    public void after() throws InterruptedException {
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.supprimerDonne(Utilisateurs.nomTable, p, supa.resultat(latch));
        supa.launch();
        verificationReponse(latch);
    }
    @Test
    public void testInsertionUtilisateur() throws InterruptedException {
        supa.insererDonne(Utilisateurs.nomTable, u, reussiteAttendu);
        supa.launch();
        verificationReponse(latch);
    }
    @Test
    public void testInsertionMemeUtilisateur() throws InterruptedException {
        supa.insererDonne(Utilisateurs.nomTable, u, supa.resultat(latch));
        verificationReponse(latch);

        supa.insererDonne(Utilisateurs.nomTable, u, erreurAttendu);
        verificationReponse(latch);
    }
    @Test
    public void testSupprimerUtilisateur() throws InterruptedException {
        Utilisateurs u = new Utilisateurs( "UtilisateursASupprimer", "utilisateurs");
        supa.insererDonne(Utilisateurs.nomTable, u, supa.resultat(latch));
        verificationReponse(latch);
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.supprimerDonne(Utilisateurs.nomTable, p, reussiteAttendu);
        verificationReponse(latch);
    }
    @Test
    public void testRecuperationUtilisateur() throws InterruptedException {
        CountDownLatch retrieveLatch = new CountDownLatch(1);
        Utilisateurs utilisateur_attendu = new Utilisateurs(null,null);
        supa.insererDonne(Utilisateurs.nomTable, u, supa.resultat(latch));
        Parametre[] p = new Parametre[]{
                new Parametre("nom",u.getNom())
        };
        supa.rechercheDonne(Utilisateurs.nomTable,p,supa.recuperationUtilisateurs(utilisateur_attendu,retrieveLatch));
        supa.launch();
        verificationReponse(latch);
        verificationReponse(retrieveLatch);
        assertEquals(u,utilisateur_attendu);
    }
    public void verificationReponse(CountDownLatch latch) throws InterruptedException, AssertionError{
        boolean fini = latch.await(10, TimeUnit.SECONDS);
        if (!fini) {
            throw new AssertionError("Timeout : le serveur n'a pas répondu à temps");
        }
    }
}
