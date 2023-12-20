package com.example.restaurantproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *  Klasa BiuroPodrozyController jest kontrolerem interfejsu użytkownika w aplikacji biura podróży.
 *  Odpowiada za obsługę zdarzeń i logikę związaną z logowaniem i rejestracją użytkowników.
 */
public class RestaurantController implements Initializable
 {
     private ConnectionManager connectionManager;

     private Stage stage;
    private Scene scene;


    @FXML
    private Label loginText;

    @FXML
    private ImageView image;

    @FXML
    private Button Register;

    @FXML
    private Button Login;


    @FXML
    private TextField LoginInput;

    @FXML
    private PasswordField PasswordInput;

     public String login;
     private String password;

     /**
      * Konstruktor aplikacji Biura Podrozy
      */
     public RestaurantController() {
     }
     public String getLogin() {
         return login;
     }

     /**
      *  Setter, służący do ustawienia wartości pola login
      * @param login zmienna przechowująca login
      */
     public void setLogin(String login) {
         this.login = login;
     }

     /**
      * Getter, służacy do pozyskania informacji na temat hasła
      * @return zwraca hasło
      */
     public String getPassword(){
         return password;
     }

     /**
      *  Setter, służący do ustawienia wartości pola hasło
      * @param password zmienna przechowująca hasło
      */
     public void setPassword(String password){
         this.password=password;
     }


     /**
      * Zmienna przechowująca wiadomość dla ConnectionManagera
      */
     public String Message;


     /**
      *  Metoda obsługująca próbę połączenia z serwerem.
      * @param e obsługa zdarzenia na przycisku
      * @throws IOException wyrzucenie wyjątku w przypadku niepowodzenia
      */
     public void connectToServer(ActionEvent e) throws IOException {

             // Tworzenie połączenia z serwerem
                setLogin(LoginInput.getText());
                System.out.println("Powinnobyc:"+login);
                setPassword(PasswordInput.getText());
         if(LoginInput.getText().isEmpty())
         {
           login = "shdjhsdkj";
         }

         if(PasswordInput.getText().isEmpty())
         {
             password = "shkjhsadjhdsajkds";
         }

    /**
    * Pozyskanie ID sesji zalogowanego użytkownika
    */
         String sessionId = connectionManager.connectToServer(login,password);

         if (sessionId != null) {
             Message = login;
             System.out.println("Dane wyslane do managera"+Message);
             switchToLoggedScene(e);

         }


         }


    String css=this.getClass().getResource("style.css").toExternalForm();

     /**
      * : Metoda testowa do przełączania na scenę logowania.
      * @param e obsługa zdarzenia na przycisku
      * @throws IOException wyrzucenie wyjątku w przypadku niepowodzenia
      */
     public void test(MouseEvent e) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
         stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
         scene = stage.getScene();
         scene.setRoot(root);
         scene.getStylesheets().add(css);
         stage.show();
     }

     /**
      * Metoda przełączająca na scenę rejestracji
      * @param e obsługa zdarzenia na przycisku
      * @throws IOException wyrzucenie wyjątku w przypadku niepowodzenia
      */
    public void switchToRegisterScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rejestracja.fxml"));
        Parent root = loader.load();
        RegisterController registerController = loader.getController();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = stage.getScene();
        scene.setRoot(root);
        stage.show();
    }

     /**
      * Metoda przełączająca na scenę zalogowanego pulpitu
      * @param e obsługa zdarzenia na przycisku
      * @throws IOException wyrzucenie wyjątku w przypadku niepowodzenia
      */
     public void switchToLoggedScene(ActionEvent e) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Pulpit_cli.fxml"));
    Parent root = loader.load();
    PulpitController pulpitController = loader.getController();
    pulpitController.setLogin(login);  // Ustawienie wartości pola login
    pulpitController.setPassword(password);
    pulpitController.initialize(null, null); // Manually call the initialize method



    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = stage.getScene();
    scene.setRoot(root);
    stage.show();


}


     /**
      * Metoda inicjalizująca kontroler po załadowaniu sceny.
      * @param url
      * @param resourceBundle
      */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connectionManager = new ConnectionManager();





    }

}

