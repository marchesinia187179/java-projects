package org.example.sudoku.controllers.mainpages;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;

public class DifficultyController {
    @FXML
    public void handleEasyMode(ActionEvent actionEvent) throws IOException {
        PageNavigator.startGamePage(Constants.Windows.GAME, actionEvent, Constants.GameDifficulty.EASY, true);
    }

    @FXML
    public void handleMediumMode(ActionEvent actionEvent) throws IOException {
        PageNavigator.startGamePage(Constants.Windows.GAME, actionEvent, Constants.GameDifficulty.MEDIUM, true);
    }

    @FXML
    public void handleHardMode(ActionEvent actionEvent) throws IOException {
        PageNavigator.startGamePage(Constants.Windows.GAME, actionEvent, Constants.GameDifficulty.HARD, true);
    }

    @FXML
    public void handleImpossibleMode(ActionEvent actionEvent) throws IOException {
        PageNavigator.startGamePage(Constants.Windows.GAME, actionEvent, Constants.GameDifficulty.IMPOSSIBLE, true);
    }

    @FXML
    public void handlePreviousPage(ActionEvent actionEvent) throws IOException {
        PageNavigator.changePage(Constants.Windows.HOME_PAGE, actionEvent, windowEvent -> {System.exit(0);});
    }

}
