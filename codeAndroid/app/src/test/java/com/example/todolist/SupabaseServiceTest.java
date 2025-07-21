package com.example.todolist;

import com.example.todolist.back.tables.SupabaseService;

import org.junit.Before;
import org.junit.Test;

public class SupabaseServiceTest {
    private SupabaseService supa;
    @Before
    public void before(){
        supa = new SupabaseService();
    }
    @Test
    public void testGetTache(){
        supa.fetchTasks();
    }
}
