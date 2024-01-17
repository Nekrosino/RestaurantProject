package com.example.restaurantproject;

public class Pracownik {

    int id;
    String imie;
    String nazwisko;
    String stanowisko;
    String numerTelefonu;
    String email;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public void setStanowisko(String stanowisko){
        this.stanowisko=stanowisko;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public String getStanowisko() {
        return stanowisko;
    }

}
