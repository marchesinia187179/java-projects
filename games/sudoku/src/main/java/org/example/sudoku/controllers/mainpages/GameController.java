package org.example.sudoku.controllers.mainpages;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.example.sudoku.controllers.popups.NewGameController;
import org.example.sudoku.models.Cell;
import org.example.sudoku.models.GameData;
import org.example.sudoku.service.GameDataService;
import org.example.sudoku.utils.Constants;
import org.example.sudoku.utils.PageNavigator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public GameData gameData;
    public List<Button> integers;

    @FXML
    public Button button00;
    @FXML
    public Button button01;
    @FXML
    public Button button02;
    @FXML
    public Button button03;
    @FXML
    public Button button04;
    @FXML
    public Button button05;
    @FXML
    public Button button06;
    @FXML
    public Button button07;
    @FXML
    public Button button08;
    @FXML
    public Button button10;
    @FXML
    public Button button11;
    @FXML
    public Button button12;
    @FXML
    public Button button13;
    @FXML
    public Button button14;
    @FXML
    public Button button15;
    @FXML
    public Button button16;
    @FXML
    public Button button17;
    @FXML
    public Button button18;
    @FXML
    public Button button20;
    @FXML
    public Button button21;
    @FXML
    public Button button22;
    @FXML
    public Button button23;
    @FXML
    public Button button24;
    @FXML
    public Button button25;
    @FXML
    public Button button26;
    @FXML
    public Button button27;
    @FXML
    public Button button28;
    @FXML
    public Button button30;
    @FXML
    public Button button31;
    @FXML
    public Button button32;
    @FXML
    public Button button33;
    @FXML
    public Button button34;
    @FXML
    public Button button35;
    @FXML
    public Button button36;
    @FXML
    public Button button37;
    @FXML
    public Button button38;
    @FXML
    public Button button40;
    @FXML
    public Button button41;
    @FXML
    public Button button42;
    @FXML
    public Button button43;
    @FXML
    public Button button44;
    @FXML
    public Button button45;
    @FXML
    public Button button46;
    @FXML
    public Button button47;
    @FXML
    public Button button48;
    @FXML
    public Button button50;
    @FXML
    public Button button51;
    @FXML
    public Button button52;
    @FXML
    public Button button53;
    @FXML
    public Button button54;
    @FXML
    public Button button55;
    @FXML
    public Button button56;
    @FXML
    public Button button57;
    @FXML
    public Button button58;
    @FXML
    public Button button60;
    @FXML
    public Button button61;
    @FXML
    public Button button62;
    @FXML
    public Button button63;
    @FXML
    public Button button64;
    @FXML
    public Button button65;
    @FXML
    public Button button66;
    @FXML
    public Button button67;
    @FXML
    public Button button68;
    @FXML
    public Button button70;
    @FXML
    public Button button71;
    @FXML
    public Button button72;
    @FXML
    public Button button73;
    @FXML
    public Button button74;
    @FXML
    public Button button75;
    @FXML
    public Button button76;
    @FXML
    public Button button77;
    @FXML
    public Button button78;
    @FXML
    public Button button80;
    @FXML
    public Button button81;
    @FXML
    public Button button82;
    @FXML
    public Button button83;
    @FXML
    public Button button84;
    @FXML
    public Button button85;
    @FXML
    public Button button86;
    @FXML
    public Button button87;
    @FXML
    public Button button88;

    @FXML
    public ToggleButton buttonNotes;
    @FXML
    public Button buttonSelectNumberEight;
    @FXML
    public Button buttonSelectNumberFive;
    @FXML
    public Button buttonSelectNumberFour;
    @FXML
    public Button buttonSelectNumberNine;
    @FXML
    public Button buttonSelectNumberOne;
    @FXML
    public Button buttonSelectNumberSeven;
    @FXML
    public Button buttonSelectNumberSix;
    @FXML
    public Button buttonSelectNumberThree;
    @FXML
    public Button buttonSelectNumberTwo;
    @FXML
    public Button buttonSelectX;

    @FXML
    public Label labelMistakes;
    @FXML
    public Label labelTime;
    @FXML
    public Label labelDifficulty;

    @FXML
    public void checkField(ActionEvent actionEvent) throws IOException {
        if (gameData.isValid() || gameData.getMistakes() == Constants.Field.MAXIMUM_MISTAKES) {
            FXMLLoader loader = PageNavigator.changePage(Constants.Windows.END_GAME, actionEvent, windowEvent -> {System.exit(0);});
            EndController endController = loader.getController();
            endController.labelStatus.setText(gameData.getStatus());
            endController.labelTimeAndDifficulty.setText(gameData.getTime().toString() + " - " + gameData.getDifficulty().getName());
        }

        labelMistakes.setText(String.valueOf(gameData.getMistakes()));
    }

    @FXML
    public void selectNumber(ActionEvent actionEvent) {
        integers.forEach(i -> i.setUnderline(i.equals(actionEvent.getSource())));
    }

    @FXML
    public void setCell(ActionEvent actionEvent) {
        if (buttonNotes.isSelected()) {
            gameData.getFieldHasList().stream()
                    .filter(Cell::isChangeable)
                    .filter(cell -> cell.button.equals(actionEvent.getSource()))
                    .forEach(cell -> {
                                integers.stream()
                                        .filter(Labeled::isUnderline)
                                        .forEach(button -> {
                                                    if (button.getText().equals("X")) {
                                                        cell.removeNotes();
                                                    } else {
                                                        cell.addNote(button.getText());
                                                    }
                                                }
                                        );
                            }
                    );
        } else {
            gameData.getFieldHasList().stream()
                    .filter(Cell::isChangeable)
                    .filter(cell -> cell.button.equals(actionEvent.getSource()))
                    .forEach(cell -> {
                                integers.stream()
                                        .filter(Labeled::isUnderline)
                                        .forEach(button -> {
                                                    if (button.getText().equals("X")) {
                                                        cell.setValue("");
                                                    } else {
                                                        cell.setValue(button.getText());
                                                    }
                                                }
                                        );
                            }
                    );
        }
    }

    @FXML
    public void handlePauseGame(ActionEvent actionEvent) throws IOException {
        GameDataService.JsonFileWriter(gameData);
        gameData.getTime().setPause(true);
        EventHandler<WindowEvent> eventOnCloseRequest = (event) -> gameData.getTime().setPause(false);
        PageNavigator.openPopup(Constants.Windows.GAME_PAUSED, eventOnCloseRequest);
    }

    @FXML
    public void handleNewGame(ActionEvent actionEvent) throws IOException {
        GameDataService.JsonFileWriter(gameData);

        FXMLLoader fxmlLoader = PageNavigator.openPopup(Constants.Windows.NEW_GAME, null);
        NewGameController newGameController = fxmlLoader.getController();
        newGameController.actionEventCaller = actionEvent;
    }

    @FXML
    public void handleInfo(ActionEvent actionEvent) throws IOException {
        PageNavigator.openPopup(Constants.Windows.INFO, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        integers = new ArrayList<>(List.of(
                buttonSelectX, buttonSelectNumberOne, buttonSelectNumberTwo, buttonSelectNumberThree,
                buttonSelectNumberFour, buttonSelectNumberFive, buttonSelectNumberSix, buttonSelectNumberSeven,
                buttonSelectNumberEight, buttonSelectNumberNine
        ));

        //timer
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            gameData.getTime().update();
            labelTime.setText(gameData.getTime().toString());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
