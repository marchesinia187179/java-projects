package org.example.sudoku.controllers.mainpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.example.sudoku.models.Difficulty;
import org.example.sudoku.models.Time;
import org.example.sudoku.service.GameDataService;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    public Label labelTimeAndDifficulty;

    @FXML
    public void handleNewGame(ActionEvent actionEvent) throws IOException {
        PageNavigator.changePage(Constants.Windows.DIFFICULTY, actionEvent, windowEvent -> {System.exit(0);});
    }

    @FXML
    public void handleContinueGame(ActionEvent actionEvent) throws IOException {
        if (GameDataService.isJsonFileEmpty()) {
            PageNavigator.openPopup(Constants.Windows.ERROR_CONTINUE, null);
        } else {
            PageNavigator.startGamePage(Constants.Windows.GAME, actionEvent, null, false);
        }
    }

    @FXML
    public void handleHowToPlay(ActionEvent actionEvent) throws IOException {
        PageNavigator.openPopup(Constants.Windows.HOW_TO_PLAY, null);
    }

    @FXML
    public void handleInfo(ActionEvent actionEvent) throws IOException {
        PageNavigator.openPopup(Constants.Windows.INFO, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //setting last game string
            Difficulty lastGameDifficulty = GameDataService.JsonFileGetDifficulty();
            Time lastGameTime = GameDataService.JsonFileGetTime();

            if (lastGameDifficulty != null && lastGameTime != null) {
                labelTimeAndDifficulty.setText(lastGameTime.toString() + " - " + lastGameDifficulty.getName());
            } else {
                labelTimeAndDifficulty.setText("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
