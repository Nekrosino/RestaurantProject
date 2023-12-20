package com.example.restaurantproject;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
import java.time.LocalDate;


public class BasketController extends PulpitController implements Initializable{
    private ConnectionManager connectionManager;
    private Scene scene;
    private Stage stage;

    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.toString();


    private String login;
    private String dataRozpoczecia;
    private String dataZakonczenia;
    private String nazwaWycieczki;
    private  String cenaWycieczka;

    private String[] dane;
    private String userID;
    private String userSaldoText;
    private  String idWycieczka;
    private String userNameText;
   private String  userSurnameText;


   private String ubezpieczenie;

    private String parts;
    @FXML
    private Label zakonczenieLabel;
    @FXML
    private Label rozpoczecieLabel;
    @FXML
    private Label nazwaLabel;
    @FXML
    private Label nazwaLabel2;
    @FXML
    private Label cenaLabel;

    @FXML
    private Label ubezpieczenieLabel;

    @FXML
    private Label brakSrodkow;


    Text opis = new Text( "\n");

    Text opis2 = new Text("\n" );

    Text opis3 = new Text("\n");

     Text opis4 = new Text("\n");

     Text opis5 = new Text("\n");

     Text opis6 = new Text("\n");


     Text podstawowy = new Text("\n");
    Text rozszerzony = new Text("\n");

   // Image image1 = new Image(getClass().getResource("/Images/plaza.jpg").toExternalForm());

    //String image1 = "@../../../plaza.jpg";
    //Image newImage;
    //Image image1 = new Image("/src/main/resources/obraz");

    //Image image3 = new Image("src/main/resources/plaza.jpg");
    //Image image4 = new Image("src/main/resources/plaza.jpg");
   // Image image5 = new Image("src/main/resources/plaza.jpg");
    //Image image6 = new Image("src/main/resources/plaza.jpg");




//    String imagePath1 = getClass().getResource("/.jpg").toExternalForm();
//    String imagePath2 = getClass().getResource("/.jpg").toExternalForm();
//    String imagePath3 = getClass().getResource("/.jpg").toExternalForm();
//    String imagePath4 = getClass().getResource("/.jpg").toExternalForm();
//    String imagePath5 = getClass().getResource("/.jpg").toExternalForm();
//    String imagePath6 = getClass().getResource("/.jpg").toExternalForm();
//

    @FXML
    TextFlow descriptionFlow;

    @FXML
    TextFlow ubezpiecznieFlow;

    @FXML
    ImageView imageView;


    public void ReturntoPulpit(ActionEvent e) throws IOException {
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



    public void purchaseWycieczka(ActionEvent e) throws IOException {

        parts = connectionManager.getProfileData(login);
        splitdata();
        float saldoUserFloat = Float.parseFloat(userSaldoText);
        float saldoWycieczkaFloat  = Float.parseFloat(cenaWycieczka);

        if (saldoUserFloat >= saldoWycieczkaFloat) {
            idWycieczka = getIDwycieczka();
            connectionManager.buyWycieczka(userID, idWycieczka, formattedDate);
            connectionManager.paymentWycieczka(cenaWycieczka, login);
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
        else {
            brakSrodkow.setText("BRAK WYSTARCZAJĄCYCH ŚRODKÓW NA KONCIE!");
        }

    }

    public void splitdata() {
        if (parts != null) {
            dane = parts.split(" ");
            userNameText = dane[1];
            userSurnameText = dane[2];
            userSaldoText = dane[3];
            userID = dane[4];
            System.out.println("Otrzymane saldo " + dane[3]);
            System.out.println("Otrzymane id " + dane[4]);

        }
    }

//    public void printLogin() {
//        System.out.println("Login w profilu: " + getLogin());
//        login = getLogin();
//    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionManager = new ConnectionManager();
        login = getLogin();
        nazwaWycieczki=getNazwaWycieczki();
        dataRozpoczecia=getDataRozpoczecia();
        dataZakonczenia=getDataZakonczenia();
        cenaWycieczka=getCenaWycieczka();
        idWycieczka=getIDwycieczka();
        ubezpieczenie=getUbezpieczenieWycieczka();
        descriptionFlow.getChildren().clear();
/*
        if(idWycieczka == "1")
        {
            Image image = new Image(imagePath1);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis);
            ubezpiecznieFlow.getChildren().add(podstawowy);

        }
        else if(idWycieczka == "2")
        {
            Image image = new Image(imagePath2);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis2);
            ubezpiecznieFlow.getChildren().add(rozszerzony);
        }

        else if(idWycieczka == "3")
        {
            Image image = new Image(imagePath3);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis3);
            ubezpiecznieFlow.getChildren().add(rozszerzony);
        }
        else if(idWycieczka == "4")
        {
            Image image = new Image(imagePath4);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis4);
            ubezpiecznieFlow.getChildren().add(podstawowy);
        }
        else if(idWycieczka == "5")
        {
            Image image = new Image(imagePath5);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis5);
            ubezpiecznieFlow.getChildren().add(podstawowy);
        }
        else if(idWycieczka == "6")
        {
            Image image = new Image(imagePath6);
            imageView.setImage(image);
            descriptionFlow.getChildren().add(opis6);
            ubezpiecznieFlow.getChildren().add(rozszerzony);
        }

*/

        nazwaLabel.setText(nazwaWycieczki);
        nazwaLabel2.setText(nazwaWycieczki);
        rozpoczecieLabel.setText(dataRozpoczecia);
        zakonczenieLabel.setText(dataZakonczenia);
        cenaLabel.setText(cenaWycieczka);
        ubezpieczenieLabel.setText(ubezpieczenie);

    }



}
