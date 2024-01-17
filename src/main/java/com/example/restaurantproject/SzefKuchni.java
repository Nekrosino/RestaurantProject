package com.example.restaurantproject;

public class SzefKuchni extends Pracownik {
    private Menu menu;

    public SzefKuchni(Menu menu) {
        this.menu = menu;
    }

    public void dodajDanieDoMenu(Danie danie) {
        menu.dodajDanie(danie);
        System.out.println("Danie dodane do menu przez szefa kuchni: " + danie.getNazwa());
    }

    public void usunDanieZMenu(Danie danie) {
        menu.usunDanie(danie);
        System.out.println("Danie usunięte z menu przez szefa kuchni: " + danie.getNazwa());
    }
/*
    public void dodajPozycjeMenu(this, Danie danie){


    }


    public void usunPozycjeMenu(){

    }
*/
}
