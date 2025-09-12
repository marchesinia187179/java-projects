package org.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.example.tictactoe.controls.GameViewController;
import org.example.tictactoe.utils.Constants;
import org.example.tictactoe.utils.PageNavigatorService;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PageNavigatorService navigator = new PageNavigatorService(stage);
        FXMLLoader fxmlLoader = navigator.initMainScene(Constants.Windows.GAME_VIEW);
        GameViewController gameViewController = fxmlLoader.getController();
        gameViewController.navigator = navigator;
    }

    public static void main(String[] args) {
        launch();
    }


}
