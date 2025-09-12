package org.example.tictactoe.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.tictactoe.utils.PageNavigatorService;

import java.io.IOException;

public class InfoController {
    public PageNavigatorService navigator;

    @FXML
    public void handleBack(ActionEvent actionEvent) throws IOException {
        navigator.goBack();
    }

}
