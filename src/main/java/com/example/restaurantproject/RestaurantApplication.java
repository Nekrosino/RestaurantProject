package com.example.restaurantproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *  Klasa główna aplikacji RestaurantApplication, która rozszerza klasę Application.
 *  Jest to część kodu aplikacji sieci restauracji, która odpowiada za uruchomienie interfejsu graficznego użytkownika.
 */
public class RestaurantApplication extends Application {

    /**
     * Inicjalizacja interfejsu graficznego aplikacji.
     * @param stage dostosowywanie okna aplikacji
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));

        Scene loginScene = new Scene( root);
        stage.setTitle("Restaurant Management System");
        stage.getIcons().add(new Image(("restaurant.png")));
        String css=this.getClass().getResource("style.css").toExternalForm();
        loginScene.getStylesheets().add(css);
        loginScene.getStylesheets().add(css);
        stage.setScene(loginScene);
        stage.show();
    }

    /**
     * Funkcja main, uruchamiająca aplikacje w trybie Desktop
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}

