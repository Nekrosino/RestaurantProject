package com.example.restaurantproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Klasa UserProfileController rozszerza klasę PulpitController i implementuje interfejs Initializable.
 * Jest odpowiedzialna za obsługę ekranu profilu użytkownika.
 */
public class UserProfileController extends PulpitController implements Initializable{
    private ConnectionManager connectionManager = new ConnectionManager();
    private Scene scene;
    private Stage stage;

    @FXML
    private Label helloLabel;

    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label saldoLabel;
    protected String login;

    protected  String parts;
    private String[] dane;

    String userSaldoText;
    String userNameText;
    String userSurnameText;

    /**
     * Metoda wyświetlająca login użytkownika w konsoli.
     */
    public void printLogin() {
        System.out.println("Login w profilu: " + getLogin());
        login = getLogin();
    }

    /**
     *  Metoda wyświetlająca części danych o użytkowniku w konsoli.
     */
    public void printParts()
    {
        parts = getParts();
    }

    /**
     * Metoda rozdzielająca dane o użytkowniku na poszczególne fragmenty (imię, nazwisko, saldo) i przypisująca je do odpowiednich pól.
     */
    public void splitdata()
    {
        if (parts != null) {
            dane = parts.split(" ");
            userNameText = dane[1];
            userSurnameText = dane[2];
            userSaldoText = dane[3];
            System.out.println("Otrzymane imie "+dane[1]);
            System.out.println("Otrzymane nazwisko "+dane[2]);
            System.out.println("Otrzymane saldo "+dane[3]);
        }

    }

    /**
     *  Metoda obsługująca przycisk przejścia do pulpitu użytkownika.
     *  Przełącza scenę na ekran pulpitu i przekazuje login użytkownika do kontrolera pulpitu.
     * @param e obsługa zdarzenia
     * @throws IOException
     */
    public void onDashboardButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pulpit_cli.fxml"));
        Parent root = loader.load();
        PulpitController pulpitController = loader.getController();
        pulpitController.setLogin(login);
        pulpitController.initialize(null, null); // Manually call the initialize method
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }


    /**
     *  Metoda inicjalizująca kontroler. Wywoływana przy inicjalizacji widoku.
     *  Inicjalizuje obiekty, pobiera dane o użytkowniku, rozdziela je i wyświetla odpowiednie informacje w interfejsie.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connectionManager = new ConnectionManager();

        printLogin();
        printParts();
        splitdata();
        System.out.println("DOSTAŁEM "+parts);
        nameLabel.setText(userNameText);
        surnameLabel.setText(userSurnameText);
        saldoLabel.setText(userSaldoText);

        helloLabel.setText("Hello " + login);


    }
}
