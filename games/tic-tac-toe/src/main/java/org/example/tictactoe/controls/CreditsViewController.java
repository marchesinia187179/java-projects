package org.example.tictactoe.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.tictactoe.utils.Constants;
import org.example.tictactoe.utils.PageNavigatorService;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreditsViewController {
    public PageNavigatorService navigator;

    @FXML
    public void handleGitHubProfileLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(Constants.Links.GITHUB_LINK));
    }

    @FXML
    public void handleBack(ActionEvent actionEvent) {
        navigator.goBack();
    }

}
