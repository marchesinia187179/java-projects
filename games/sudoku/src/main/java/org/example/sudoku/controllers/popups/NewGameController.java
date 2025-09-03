package org.example.sudoku.controllers.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;

public class NewGameController {
    public ActionEvent actionEventCaller;

    @FXML
    public void handleNewGameCancelled(ActionEvent actionEvent) {
        PageNavigator.closePage(actionEvent);
    }

    @FXML
    public void handleNewGameConfirmed(ActionEvent actionEvent) throws IOException {
        PageNavigator.closePage(actionEventCaller);
        PageNavigator.changePage(Constants.Windows.HOME_PAGE, actionEvent, windowEvent -> {System.exit(0);});
    }

}
