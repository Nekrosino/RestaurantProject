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
 * Klasa PulpitController rozszerzająca klasę RestaurantController i implementująca interfejs Initializable
 */
public class PulpitController extends RestaurantController implements Initializable {
    private ConnectionManager connectionManager;
    @FXML
    private Label helloLabel;

    private Stage stage;
    private Scene scene;
    protected String login;
    public String password;

    public String parts;

    public String daneZamowDanie;
    public String idWycieczka;

    public String idDanie;

    public String[] danieTab;

    public String nazwa;


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


    public void getDaneZamowDanie() throws IOException {
        System.out.println(" 1 etap " + idDanie);
        daneZamowDanie = connectionManager.zamowDanie(idDanie);
        danieTab = daneZamowDanie.split(" ");


    }


    /**
     * Metoda czyszcząca zawartośc etykiet, po zjechaniu myszą z obiektu
     *
     * @param e obsługa zdarzenia
     * @throws IOException
     */



    public void addToBasket(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Basket.fxml"));
        Parent root = loader.load();
        BasketController basketController = loader.getController();



        //userProfileController.setPassword(password);
       // System.out.println("Login wyslany"+login);
      //  System.out.println("Haslo wyslane"+password);
      //  String parts = connectionManager.getProfileData(login,password);
      //  userProfileController.setParts(parts);


        Button button = (Button) e.getSource();
        if(button.getId().equals("button1"))
        {
            idDanie ="1";
            getDaneZamowDanie();




        }
        else if(button.getId().equals("button2"))
        {
            idDanie = "2";
            getDaneZamowDanie();

        }
        else if(button.getId().equals("button3"))
        {
            idDanie ="3";

            getDaneZamowDanie();
        }
        else if(button.getId().equals("button4"))
        {
            idDanie="4";
            getDaneZamowDanie();
        }
        else if(button.getId().equals("button5"))
        {
            idDanie="5";
            getDaneZamowDanie();
        }
        else if(button.getId().equals("button6"))
        {
            idDanie="6";
            getDaneZamowDanie();
        }
        nazwa = danieTab[1];





        System.out.println("test" + nazwa);



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

