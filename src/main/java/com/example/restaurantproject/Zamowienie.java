package com.example.restaurantproject;

import java.util.ArrayList;
import java.util.List;

public class Zamowienie {
   // private List<Danie> zamowioneDania;
    private List<Danie> dania;
    private int ilosc;
    private Stolik stolik;


    public Zamowienie(Stolik stolik) {
        this.stolik = stolik;
       // this.zamowioneDania = new ArrayList<>();
        this.dania = new ArrayList<>();;
        // inne inicjalizacje...
    }

    public List<Danie> getDania() {
        return dania;
    }

    public void dodajZamowienie(Danie danie) {
        dania.add(danie);
        System.out.println("Zamowione danie: " + danie.getNazwa());
    }

    public void usunZamowienie(Danie danie) {
        dania.remove(danie);
        System.out.println("Usuniete danie z zamowienia: " + danie.getNazwa());
    }

    public void wyswietlDania() {
        for (Danie danie : dania) {
            System.out.println(danie.getNazwa() + " - " + danie.getCena());
        }
    }


}
