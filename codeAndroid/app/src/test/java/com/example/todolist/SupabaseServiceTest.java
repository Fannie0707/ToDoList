package com.example.todolist;

import com.example.todolist.back.bdd.SupabaseService;

import org.junit.Before;

public class SupabaseServiceTest {
    private SupabaseService supa;

    @Before
    public void before(){
        supa = new SupabaseService();
    }
}
