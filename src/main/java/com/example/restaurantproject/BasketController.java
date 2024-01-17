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

    private String[] dane;
    private String userID;
    private String userNameText;
   private String  userSurnameText;

    private String parts;

    @FXML
    private Label nazwaLabel;
    @FXML
    private Label nazwaLabel2;
    @FXML
    private Label cenaLabel;



    @FXML
    TextFlow descriptionFlow;


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


    public void kupDanie(ActionEvent e) throws IOException{
        parts = connectionManager.getProfileData(login);


    }





//    public void printLogin() {
//        System.out.println("Login w profilu: " + getLogin());
//        login = getLogin();
//    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionManager = new ConnectionManager();
        login = getLogin();
        descriptionFlow.getChildren().clear();


    }



}
