package org.example.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Constants.Windows.HOME_PAGE.path));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Constants.Windows.HOME_PAGE.title);
        stage.setScene(scene);
        PageNavigator.setDimensions(stage, Constants.Windows.HOME_PAGE.height, Constants.Windows.HOME_PAGE.width);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
