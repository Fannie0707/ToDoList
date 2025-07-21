package com.example.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.todolist.back.BDDCommunication;

import org.junit.Before;
import org.junit.Test;

public class BDDTest {
    private BDDCommunication bdd;
    @Before
    public void before(){
        bdd = new BDDCommunication();
    }
    @Test
    public void testConnectionBDD(){
        BDDCommunication newBDD = new BDDCommunication();
        assertTrue(newBDD.connected);
    }
}
