package com.example.restaurantproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ZamowienieTest{

    private Menu menu;
    private Danie danie1;
    private Danie danie2;
    private Stolik stolik1;
    private Zamowienie zamowienie1;

    @Before
    public void setUp() {
        menu = new Menu();
        danie1 = new Danie("Pizza Margherita", 12.99);
        danie2 = new Danie("Gulasz", 18.50);
        stolik1 = new Stolik();
        zamowienie1 = new Zamowienie(stolik1);
    }
    @Test
    public void testDodajZamowienie() {
        zamowienie1.dodajZamowienie(danie1);
        assertTrue(zamowienie1.getDania().contains(danie1));
    }

    @Test
    public void testUsunZamowienie() {
        zamowienie1.dodajZamowienie(danie1);
        zamowienie1.usunZamowienie(danie1);
        assertFalse(menu.getDania().contains(danie1));
    }
}