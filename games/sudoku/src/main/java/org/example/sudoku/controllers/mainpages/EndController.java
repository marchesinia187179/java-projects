package org.example.sudoku.controllers.mainpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.sudoku.service.GameDataService;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML
    public Label labelStatus;
    @FXML
    public Label labelTimeAndDifficulty;

    @FXML
    public void handleBackHome(ActionEvent actionEvent) throws IOException {
        PageNavigator.changePage(Constants.Windows.HOME_PAGE, actionEvent, windowEvent -> {System.exit(0);});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            GameDataService.JsonFileRemover();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
