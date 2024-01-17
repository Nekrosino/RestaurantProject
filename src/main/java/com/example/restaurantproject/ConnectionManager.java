package com.example.restaurantproject;


import java.io.*;
import java.net.*;

import java.io.IOException;

/**
 * Klasa ConnectionManager odpowiedzialna za zarządzanie połączeniem między klientem a serwerem.
 */
public class ConnectionManager {
    private String sessionId;
    private Socket clientSockett;
    private PrintWriter out;
    private BufferedReader in;
    private String data;

    private String saldo;

    /**
     *  Metoda nawiązuje połączenie z serwerem, wysyła żądanie logowania z danymi użytkownika (login i hasło) i odbiera odpowiedź od serwera
     * @param login login wpisany przez użytkownika
     * @param password hasło wpisane przez użytkownika
     * @return zwraca ID sesji gdy logowanie powiodło się, w innym przypadku zwraca NULL
     * @throws IOException wyrzucenie wyjątku w przypadku niepowodzenia
     */
    public String connectToServer(String login, String password) throws IOException {
        // Tworzenie połączenia z serwerem
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        // Wysyłanie żądania logowania
        out.println("LOGIN " + login + " " + password);
        System.out.println("Wysłane dane: " + login + " " + password);
        data = login;

        String response = in.readLine();
        // Jeżeli logowanie powiodło się, otrzymujemy identyfikator sesji
        if (response.startsWith("SESSION_ID")) {
            sessionId = response.split(" ")[1];
            System.out.println("Zalogowano pomyślnie. SESSION ID: " + sessionId);
            return sessionId;

            // Wywołanie innych operacji związanych z połączeniem
        } else {
            System.out.println("Błąd logowania");
            return null;
        }
    }

    /**
     *  Metoda wysyła żądanie wylogowania do serwera. Jeśli wylogowanie powiedzie się, metoda ustawia identyfikator sesji na null.
     * @throws IOException
     */
    public void logout() throws IOException {
        // Sprawdzenie, czy klient jest zalogowany
        if (sessionId != null) {
            // Wysłanie żądania wylogowania
            out.println("LOGOUT " + sessionId);
            String response = in.readLine();

            if (response.equals("LOGOUT_SUCCESS")) {
                // Wylogowanie zakończone pomyślnie
                sessionId = null;


                System.out.println("Wylogowano pomyślnie");
            } else {
                System.out.println("Błąd wylogowania");
            }
        } else {
            System.out.println("Klient nie jest zalogowany");
        }
    }

    /**
     * Zamknięcie połączenia z serwerem
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if (clientSockett != null) {
            clientSockett.close();
        }
    }

    /**
     *  Metoda wysyła żądanie pobrania danych profilu użytkownika do serwera, przekazując dane logowania.
     *  Jeśli żądanie powiedzie się, metoda zwraca odpowiedź od serwera, która zawiera dane profilu użytkownika
     * @param login login użytkownika
     * @return zwraca uzyskane dane przy sukcesie lub w przeciwnym wypadku zwraca NULL
     * @throws IOException
     */
    public String getProfileData(String login) throws IOException
    {
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        // Wysyłanie żądania danych
//        out.println("PROFILEDATA " + login + " " + password);
            out.println("PROFILEDATA " + login);
//        System.out.println("Wysłane dane: " + login + " " + password);
         System.out.println("Wysłane dane: " + login);



        String response = in.readLine();
        // Jeżeli logowanie powiodło się, otrzymujemy identyfikator sesji
        if (response.startsWith("PROFILEDATA")) {
            String parts = response;
            System.out.println("Otrzymano pomyślnie dane dla użytkownika: "+parts);
            return parts;

        } else {
            System.out.println("Błąd pobrania danych");
            return null;
        }
    }

    /**
     * Metoda ta wysyła żądanie rejestracji nowego użytkownika do serwera, przekazując wszystkie niezbędne dane rejestracyjne.
     * @param Imie Imie podane przez użytkownika
     * @param Nazwisko Nazwisko podane przez użytkownika
     * @param Adres Adres podany przez użytkownika
     * @param Numer Numer telefonu podany przez użytkownika
     * @param email E-mail podany przez użytkownika
     * @param login Login podany przez użytkownika
     * @param haslo Hasło podane przez użytkownika
     * @param portfel Portfel podany przez użytkownika
     * @throws IOException
     */
    public String registerUser(String idKlient,String Imie,String Nazwisko,String Adres,String Numer,String email,String login,String haslo,String portfel ) throws IOException
    {
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        //wyslanie żądania
        out.println("REGISTERUSER "+idKlient+" "+Imie+" "+Nazwisko+" "+Adres+" "+Numer+" "+email+" "+login+" "+haslo+" "+portfel);
        System.out.println("Dane uzytkownika do rejestracji: "+idKlient+" "+Imie+" "+Nazwisko+" "+Adres+" "+Numer+" "+email+" "+login+" "+haslo+" "+portfel );
        String response = in.readLine();
        if(response.startsWith("REGISTERSUCCESS"))
        {
            return "SUCCESS";
        }
        else if (response.startsWith("REGISTERFAILED"))
        {
            return  "FAILED";
        }
        else
        {
            return "FAILED";
        }


    }

    public String getLastID() throws IOException {
        clientSockett = new Socket("localhost", 1234);
        in = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
        out = new PrintWriter(clientSockett.getOutputStream(), true);

        out.println("GETLASTID ");

        String response = in.readLine();
        if(response.startsWith("GETLASTID"))
        {
            String[] parts = response.split(" ");
            String lastID = parts[1];
            System.out.println("Ostatnie IDMANAGER "+lastID);
            return lastID;
        }
        else{
            System.out.println("Błąd pobrania danych");
            return null;
        }
    }


    public String zamowDanie(String idDanie) throws IOException{
        clientSockett = new Socket("localhost", 1234);
        out = new PrintWriter(clientSockett.getOutputStream(), true);
        out.println("ZAMOWDANIE "+idDanie);

        String response = in.readLine();
        if (response.startsWith("ZAMOWDANIE")) {
            String parts = response;
            System.out.println("Otrzymano pomyślnie dane dla użytkownika: "+parts);
            return parts;

        } else {
            System.out.println("Błąd pobrania danych");
            return null;
        }

    }

}
