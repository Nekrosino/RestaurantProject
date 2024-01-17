package com.example.restaurantproject;

public class Skladnik {
    String nazwa;
    int ilosc;

    public Skladnik(String nazwa, int ilosc) {
        this.nazwa = nazwa;
        this.ilosc = ilosc;
    }

    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public double getIlosc(){
        return ilosc;
    }

    public void setIlosc(int  ilosc){
        this.ilosc=ilosc;
    }

}
