package com.example.restaurantproject;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class DanieTest {

    private Danie danie;

    @Before
    public void setUp() {
        danie = new Danie("Testowe danie", 15.99);
    }

    @Test
    public void testGetNazwa() {
        assertEquals("Testowe danie", danie.getNazwa());
    }

    @Test
    public void testGetCena() {
        assertEquals(15.99, danie.getCena(), 0.001);
    }

    @Test
    public void testSetNazwa() {
        danie.setNazwa("Zmienione danie");
        assertEquals("Zmienione danie", danie.getNazwa());
    }

    @Test
    public void testSetCena() {
        danie.setCena(20.50);
        assertEquals(20.50, danie.getCena(), 0.001);
    }

}
