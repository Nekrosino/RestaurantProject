package com.example.restaurantproject;

import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class MenuTest {

    private Menu menu;
    private Danie danie1;
    private Danie danie2;

    @Before
    public void setUp() {
        menu = new Menu();
        danie1 = new Danie("Pizza Margherita", 12.99);
        danie2 = new Danie("Gulasz", 18.50);

    }

    @Test
    public void testDodajDanie() {
        menu.dodajDanie(danie1);
        assertTrue(menu.getDania().contains(danie1));
    }

    @Test
    public void testUsunDanie() {
        menu.dodajDanie(danie1);
        menu.usunDanie(danie1);
        assertFalse(menu.getDania().contains(danie1));
    }

    @Test
    public void testDodajIUsunDania() {
        menu.dodajDanie(danie1);
        menu.dodajDanie(danie2);
        assertTrue(menu.getDania().contains(danie1));
        assertTrue(menu.getDania().contains(danie2));

        menu.usunDanie(danie1);
        assertFalse(menu.getDania().contains(danie1));
        assertTrue(menu.getDania().contains(danie2));
    }

    @Test
    public void testgetDania() {
        menu.dodajDanie(danie1);
        menu.dodajDanie(danie2);
        menu.usunDanie(danie2);
        menu.dodajDanie(danie2);
        menu.getDania();
        assertTrue(menu.getDania().contains(danie1));
        assertFalse(menu.getDania().contains(null));
    }

    @Test
    public void testWyswietlDania(){
        menu.dodajDanie(danie1);
        menu.dodajDanie(danie2);
        menu.usunDanie(danie2);
        menu.dodajDanie(danie2);
        menu.wyswietlDania();
        assertTrue(menu.getDania().contains(danie1));
        assertFalse(menu.getDania().contains(null));
        //assertTrue(menu.wyswietlDania().(danie1));
    }


}