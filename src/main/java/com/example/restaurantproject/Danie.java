package com.example.restaurantproject;

import java.util.ArrayList;
import java.util.List;

public class Danie {
    String nazwa;
    double cena;
    private List<Skladnik> skladniki;

    public Danie(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.skladniki = new ArrayList<>();
    }

    public String getNazwa(){
        return nazwa;
    }

    public double getCena(){
        return cena;
    }

    public void setNazwa(String nazwa){
        this.nazwa=nazwa;
    }

    public void setCena(double cena){
        this.cena=cena;
    }

/*
    Danie danie1 = new Danie();
    danie1 = new Danie("Pizza",15.99);

*/

}
