package com.example.restaurantproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa PulpitController rozszerzająca klasę BiuroPodrozyController i implementująca interfejs Initializable
 */
public class PulpitController extends RestaurantController implements Initializable {
    private ConnectionManager connectionManager;
    @FXML
    private Label helloLabel;

    @FXML
    private Label nazwaWycieczki;

    @FXML
    private Label poczatekWycieczki;

    @FXML
    private Label koniecWycieczki;
    private Stage stage;
    private Scene scene;
    protected String login;
    public String password;

    public String parts;

    public String daneWycieczka;

    public String idWycieczka;


    public String[] wycieczkaTab;

    public String nazwa;
    public String dataRozpoczecia;
    public String dataZakonczenia;
    public String cenaWycieczka;

    public String ubezpieczenieWycieczka; //dodane

    @FXML
    private Label Stegna;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;


    /**
     * Funkcja przekazująca informacje na temat loginu użytkownika do aplikacji
     */
    public void printLogin() {
        System.out.println("Login w pulpicie: " + getLogin());
        login = getLogin();
    }

    /**
     * Funkcja przekazująca informacje na temat hasła użytkownika do aplikacji
     */
    public void printPassword() {
        password = getPassword();
    }

    /**
     * Setter, służący do ustawienia wartości pola parts
     *
     * @param parts zmienna przechowująca dane użytkownika
     */
    public void setParts(String parts) {
        this.parts = parts;
    }

    /**
     * Getter, służący do pobrania informacji o danych użytkownika
     *
     * @return zwraca dane użytkownika
     */
    public String getParts() {
        return parts;
    }

    public void setNazwaWycieczki(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwaWycieczki() {
        return nazwa;
    }

    public void setDataRozpoczecia(String dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public String getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataZakonczenia(String dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public String getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setCenaWycieczka(String cenaWycieczka) {
        this.cenaWycieczka = cenaWycieczka;
    }

    public String getCenaWycieczka() {
        return cenaWycieczka;
    }

    public void setIDwycieczka(String idWycieczka) {
        this.idWycieczka = idWycieczka;
    }

    public String getIDwycieczka() {

        return idWycieczka;
    }

    public void setUbezpieczenie(String ubezpieczenieWycieczka) { ///DODANE
        this.ubezpieczenieWycieczka = ubezpieczenieWycieczka;
    }

    public String getUbezpieczenieWycieczka() {   ///DODANE
        return ubezpieczenieWycieczka;
    }


    /**
     * Metoda przełączająca scenę na ekran logowania.
     *
     * @param e obsługa zdarzeń na przycisku
     * @throws IOException
     */
    public void switchToLoginScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
        Parent root = loader.load();
        RestaurantController restaurantController = loader.getController();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    /**
     * Metoda obsługująca przycisk profilu użytkownika. Przełącza scenę na ekran profilu użytkownika i przekazuje login, hasło i dane profilu.
     *
     * @param e obsługa zdarzeń na przycisku
     * @throws IOException
     */
    public void onProfileButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
        Parent root = loader.load();
        UserProfileController userProfileController = loader.getController();
        userProfileController.setLogin(login);
        userProfileController.setPassword(password);
        System.out.println("Login wyslany" + login);
        System.out.println("Haslo wyslane" + password);
//        String parts = connectionManager.getProfileData(login,password);
        String parts = connectionManager.getProfileData(login);
        userProfileController.setParts(parts);
        userProfileController.initialize(null, null); // Manually call the initialize method

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

    /**
     * Metoda obsługująca wylogowanie użytkownika, wywołuje metody logout() i disconnect() z ConnectionManager,
     * następnie przełącza scenę na ekran logowania.
     *
     * @param e obsługa zdarzeń na przycisku
     * @throws IOException
     */
    public void logout(ActionEvent e) throws IOException {


        connectionManager.logout();
        connectionManager.disconnect();
        printLogin();
        switchToLoginScene(e);
    }


    /**
     * Metoda obsługująca zdarzenie najechania myszą na obrazek. Pobiera dane wycieczki i wyświetla je na etykietach.
     *
     * @param e obsługa zdarzeń na myszce
     * @throws IOException
     */
    public void print(MouseEvent e) throws IOException {
        idWycieczka = "1";
        getDaneWycieczka();

        nazwa = wycieczkaTab[1];
        dataRozpoczecia = wycieczkaTab[2];
        dataZakonczenia = wycieczkaTab[3];

        nazwaWycieczki.setText(nazwa);
        poczatekWycieczki.setText(dataRozpoczecia);
        koniecWycieczki.setText(dataZakonczenia);

    }

    /**
     * Metoda pobierająca dane wycieczki z bazy danych
     *
     * @throws IOException
     */
    public void getDaneWycieczka() throws IOException {
        daneWycieczka = connectionManager.getWycieczka(idWycieczka);
        wycieczkaTab = daneWycieczka.split(" ");

    }

    /**
     * Metoda czyszcząca zawartośc etykiet, po zjechaniu myszą z obiektu
     *
     * @param e obsługa zdarzenia
     * @throws IOException
     */

    public void dprint(MouseEvent e) throws IOException {
        nazwaWycieczki.setText(" ");
        poczatekWycieczki.setText(" ");
        koniecWycieczki.setText(" ");
    }


    public void addToBasket(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Basket.fxml"));
        Parent root = loader.load();
        BasketController basketController = loader.getController();



        //userProfileController.setPassword(password);
       // System.out.println("Login wyslany"+login);
      //  System.out.println("Haslo wyslane"+password);
      //  String parts = connectionManager.getProfileData(login,password);
      //  userProfileController.setParts(parts);

        /*
        Button button = (Button) e.getSource();
        if(button.getId().equals("button1"))
        {
            idWycieczka ="1";
            //wysylamy nazwe wycieczki
            //wysylamy zdjecie
            //wysylamy cene
            //wysylamy opis
            //date rozpoczecia
            //date zakonczenia
            getDaneWycieczka();


        }
        else if(button.getId().equals("button2"))
        {
            idWycieczka = "2";
            getDaneWycieczka();

        }
        else if(button.getId().equals("button3"))
        {
            idWycieczka ="3";
            getDaneWycieczka();
        }
        else if(button.getId().equals("button4"))
        {
            idWycieczka="4";
            getDaneWycieczka();
        }
        else if(button.getId().equals("button5"))
        {
            idWycieczka="5";
            getDaneWycieczka();
        }
        else if(button.getId().equals("button6"))
        {
            idWycieczka="6";
            getDaneWycieczka();
        }
        nazwa = wycieczkaTab[1];
        dataRozpoczecia = wycieczkaTab[2];
        dataZakonczenia=wycieczkaTab[3];
        cenaWycieczka=wycieczkaTab[4];
        ubezpieczenieWycieczka=wycieczkaTab[5];


        basketController.setLogin(login);
        basketController.setNazwaWycieczki(nazwa);
        basketController.setDataRozpoczecia(dataRozpoczecia);
        basketController.setDataZakonczenia(dataZakonczenia);
        basketController.setCenaWycieczka(cenaWycieczka);
       basketController.setIDwycieczka(idWycieczka);
       basketController.setUbezpieczenie(ubezpieczenieWycieczka);

       */
        basketController.initialize(null, null); // Manually call the initialize method


        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();


    }


//
//     *  Metoda inicjalizująca kontroler. Ustawia obiekt ConnectionManager, wywołuje metody printLogin() i printPassword()
//     *  oraz ustawia tekst etykiety helloLabel.
//     * @param url
//     * @param resourceBundle

    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionManager = new ConnectionManager();
        printLogin();
        printPassword();
        helloLabel.setText("Hello " + login);

    }
}

