package client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initMainPage();
    }
    public static void main(String[] args) {
        launch();
    }

    @FXML
    public void initMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = Main.class.getResource("fxml/mainPage.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

