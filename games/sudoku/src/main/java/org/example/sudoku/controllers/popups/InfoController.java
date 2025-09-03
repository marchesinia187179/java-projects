package org.example.sudoku.controllers.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.sudoku.utils.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoController {
    @FXML
    public void handleGitHubProfileLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(Constants.Links.GITHUB_LINK));
    }

}
