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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * Klasa RegisterController implementuje interfejs Initializable i jest odpowiedzialna za obsługę rejestracji użytkownika w aplikacji.
 */
public class RegisterController implements Initializable {

    private ConnectionManager connectionManager = new ConnectionManager();
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField LoginField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField AdresField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField SurnameField;
    @FXML
    private TextField RepeatPasswordField;
    @FXML
    private TextField PhoneField;

    @FXML
    private TextField EmailField;


    private String Login;
    private String Password;
    private String RepeatPassword;
    private String Name;
    private String Surname;
    private String Phone;
    private String Adres;
    private String Email;
    private String idKlient;

    @FXML
    private Label ErrorMessage;


    public void getID() throws IOException {
        idKlient = connectionManager.getLastID();
        System.out.println("ID "+idKlient);
    }
    /**
     *  Metoda obsługująca przycisk przejścia do ekranu logowania. Sprawdza poprawność wprowadzonych danych rejestracyjnych,
     *  następnie wywołuje metodę registerUser() z ConnectionManager w celu zarejestrowania użytkownika.
     *  Jeśli rejestracja powiedzie się, przełącza scenę na ekran logowania.
     * @param e obsługa zdarzenia
     * @throws IOException
     */
    public void switchToLoginScene(ActionEvent e) throws IOException {

        fillForm();


        if (!Name.isEmpty() && !Surname.isEmpty() && !Login.isEmpty() && !Password.isEmpty() && !RepeatPassword.isEmpty() && !Phone.isEmpty() && !Adres.isEmpty() && !Email.isEmpty())
        {
            if (Password.equals(RepeatPassword))
            {
                if (Phone.matches("\\d{9}"))
                {
                    if(Email.contains("@"))
                    {
                        getID();
                        System.out.println("Otrzymane id " + idKlient);
                        System.out.println("Pomyślnie zatwierdzono");
                        System.out.println("Wypelnione dane: " + idKlient + " " + Name + " " + Surname + " " + Adres + " " + Phone + " " + Login + " " + Password + " " + Email);
                        String registrationResult = connectionManager.registerUser(idKlient, Name, Surname, Adres, Phone, Email, Login, Password, "99999.9");
                        if(registrationResult.equals("SUCCESS")) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
                            Parent root = loader.load();
                            RestaurantController restaurantController = loader.getController();
                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            scene = stage.getScene();
                            scene.setRoot(root);
                            stage.show();
                        }
                        else
                        {
                            ErrorMessage.setText("Nazwa uzytkownika w uzyciu");
                            System.out.println("Nazwa uzytkownika w uzyciu");
                        }
                    }
                    else
                    {
                        ErrorMessage.setText("Podaj adres e-mail");
                    }
                }
                else ErrorMessage.setText("Numer telefonu musi mieć dokładnie 9 cyfr");
            }
            else
            {
                ErrorMessage.setText("Hasla sie roznia!");
            }
        }
        else
        {
            ErrorMessage.setText("Wypełnij wszystkie pola!");
        }

    }

    /**
     * Metoda obsługująca przycisk powrotu do ekranu logowania. Przełącza scenę na ekran logowania.
     * @param e obsługa zdarzenia
     * @throws IOException
     */
    public void returnToLoginScene(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Restaurant.fxml"));
        Parent root = loader.load();
        RestaurantController restaurantController = loader.getController();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();


    }

    /**
     *  Metoda pobierająca wprowadzone dane rejestracyjne z pól tekstowych i zapisująca je do odpowiednich pól.
     */
    public void fillForm()
    {
        Name = NameField.getText();
        Surname = SurnameField.getText();
        Login = LoginField.getText();
        Password = PasswordField.getText();
        RepeatPassword = RepeatPasswordField.getText();
        Phone = PhoneField.getText();
        Adres = AdresField.getText();
        Email = EmailField.getText();

    }

    /**
     * Metoda inicjalizująca kontroler.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connectionManager = new ConnectionManager();
    }
}
