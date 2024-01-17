package com.example.restaurantproject;

import java.util.List;
import java.util.ArrayList;

public class Menu {
    String opis;
    double cena;
    private List<Danie> dania;
    public Menu(){
        this.dania = new ArrayList<>();;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

/*
    // Metoda dodająca danie do menu (dostępna dla pracownika)
    public void dodajDanie(SzefKuchni szefkuchni, Danie danie) {
        if (szefkuchni != null) {
            dania.add(danie);
            System.out.println("Danie dodane do menu.");
        } else {
            System.out.println("Brak uprawnień do dodawania dania.");
        }
    }

    // Metoda usuwająca danie z menu (dostępna dla pracownika)
    public void usunDanie(SzefKuchni szefkuchni, Danie danie) {
        if (szefkuchni != null) {
            dania.remove(danie);
            System.out.println("Danie usunięte z menu.");
        } else {
            System.out.println("Brak uprawnień do usuwania dania.");
        }
    }
}

 */


    public List<Danie> getDania() {
        return dania;
    }

    public void dodajDanie(Danie danie) {
        dania.add(danie);
        System.out.println("Danie dodane do menu: " + danie.getNazwa());
    }

    public void usunDanie(Danie danie) {
        dania.remove(danie);
        System.out.println("Danie usunięte z menu: " + danie.getNazwa());
    }

    public void wyswietlDania() {
        for (Danie danie : dania) {
            System.out.println(danie.getNazwa() + " - " + danie.getCena());
        }
    }
}






