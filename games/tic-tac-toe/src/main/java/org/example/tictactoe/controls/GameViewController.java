package org.example.tictactoe.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.tictactoe.models.*;
import org.example.tictactoe.utils.Constants;
import org.example.tictactoe.utils.PageNavigatorService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.random.RandomGenerator;

public class GameViewController implements Initializable {
    @FXML
    public Button button0;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button winnerButton;
    @FXML
    public Button errorButton;

    @FXML
    public ToggleButton singleplayerToggleButton;
    @FXML
    public ToggleButton multiplayerToggleButton;
    @FXML
    public ToggleButton easyModeToggleButton;
    @FXML
    public ToggleButton mediumModeToggleButton;
    @FXML
    public ToggleButton hardModeToggleButton;

    @FXML
    public Label turnLabel;
    @FXML
    public Label dateLabel;

    @FXML
    public HBox singleplayerSpecificationsHBox;
    @FXML
    public VBox startPropertiesVBow;
    @FXML
    public VBox mainVBox;

    @FXML
    public GridPane gridPaneField;

    @FXML
    public ImageView creditsImage;
    @FXML
    public ImageView themeImage;
    @FXML
    public ImageView volumeImage;
    @FXML
    public ImageView infoImage;

    public GameData multiplayerGame;
    public ComputerPlayer singleplayerGame;
    public MediaPlayer mediaPlayer;
    public PageNavigatorService navigator;

    //========== MESSAGE METHODS ==========
    private boolean hasNewGameErrors() throws IOException {
        String errorString = null;
        if (!singleplayerToggleButton.isSelected() && !multiplayerToggleButton.isSelected()) {
            errorString = Constants.Errors.NEW_GAME_MODALITY;
        }

        if (singleplayerToggleButton.isSelected() && singleplayerGame.getDifficulty().equals(Difficulty.NOT_SELECTED)) {
            errorString = Constants.Errors.NEW_GAME_DIFFICULTY;
        }

        if (errorString != null) {
            mainVBox.setDisable(true);
            errorButton.setVisible(true);
            errorButton.setText(errorString);
            return true;
        }

        return false;
    }
    private void showWinner(ActionEvent actionEvent, GameStatus winner) throws IOException {
        mainVBox.setDisable(true);
        winnerButton.setVisible(true);
        winnerButton.setText(winner.getMessage());
    }
    @FXML
    public void handleCloseError(ActionEvent actionEvent) {
        mainVBox.setDisable(false);
        errorButton.setVisible(false);
        errorButton.setText("");
    }

    //========== GAME METHODS ==========
    @FXML
    public void handleRestartGame(ActionEvent actionEvent) {
        singleplayerGame.getField().forEach(c -> c.setMark(""));
        multiplayerGame.getField().forEach(c -> c.setMark(""));
        singleplayerGame.setStatus(GameStatus.ONGOING);
        multiplayerGame.setStatus(GameStatus.ONGOING);
        singleplayerGame.setCurrentPlayer(singleplayerGame.getFirstPlayer());
        multiplayerGame.setCurrentPlayer(multiplayerGame.getFirstPlayer());
        turnLabel.setText("");

        mainVBox.setDisable(false);
        gridPaneField.setDisable(true);
        startPropertiesVBow.setDisable(false);
        winnerButton.setVisible(false);
        winnerButton.setText("");
    }
    @FXML
    public void handleMove(ActionEvent actionEvent) throws IOException {
        if (multiplayerToggleButton.isSelected()) {
            for (Cell c : multiplayerGame.getField()) {
                if (c.getMark().isEmpty() && c.getButton().equals(actionEvent.getSource())) {
                    multiplayerGame.makeMove(c.getIndex(), multiplayerGame.getCurrentPlayer().getMark());
                    multiplayerGame.updateCurrentPlayer();

                    if (!multiplayerGame.getStatus().equals(GameStatus.ONGOING)) {
                        showWinner(actionEvent, multiplayerGame.getStatus());
                    }

                    break;
                }
            }
        }

        if (singleplayerToggleButton.isSelected()) {
            for (Cell c : singleplayerGame.getField()) {
                if (c.getMark().isEmpty() && c.getButton().equals(actionEvent.getSource())) {
                    singleplayerGame.makeMove(c.getIndex(), singleplayerGame.getCurrentPlayer().getMark());
                    singleplayerGame.updateCurrentPlayer();

                    if (singleplayerGame.getStatus().equals(GameStatus.ONGOING)) {
                        singleplayerGame.nextMove(c.getIndex());
                    }

                    if (!singleplayerGame.getStatus().equals(GameStatus.ONGOING)) {
                        showWinner(actionEvent, singleplayerGame.getStatus());
                    }

                    singleplayerGame.updateCurrentPlayer();
                    break;
                }
            }
        }
    }
    @FXML
    public void handleNewGame(ActionEvent actionEvent) throws IOException {
        if (hasNewGameErrors()) {
            return;
        }

        if (multiplayerToggleButton.isSelected()) {
            turnLabel.setText(multiplayerGame.getFirstPlayer().getMessage() + "  -  " + multiplayerGame.getSecondPlayer().getMessage());
        }

        if (singleplayerToggleButton.isSelected()) {
            if (RandomGenerator.getDefault().nextInt(2) == 0) {     //player is first
                singleplayerGame.setComputerAsSecond();
            } else {        //computer is first
                singleplayerGame.setComputerAsFirst();
                singleplayerGame.setFirstComputerMove(RandomGenerator.getDefault().nextInt(Constants.Game.indices.size()));
                singleplayerGame.updateCurrentPlayer();
            }

            turnLabel.setText(singleplayerGame.getFirstPlayer().getMessage() + "  -  " + singleplayerGame.getSecondPlayer().getMessage());
        }

        startPropertiesVBow.setDisable(true);
        gridPaneField.setDisable(false);
    }

    //========== GAME MODALITY METHODS ==========
    @FXML
    public void handleSingleplayerMode(MouseEvent mouseEvent) {
        singleplayerSpecificationsHBox.setVisible(!singleplayerSpecificationsHBox.isVisible());
        multiplayerToggleButton.setSelected(false);
    }
    @FXML
    public void handleMultiplayerMode(ActionEvent actionEvent) {
        singleplayerSpecificationsHBox.setVisible(false);
        singleplayerToggleButton.setSelected(false);
        easyModeToggleButton.setSelected(false);
        mediumModeToggleButton.setSelected(false);
        hardModeToggleButton.setSelected(false);
        singleplayerGame.setDifficulty(Difficulty.NOT_SELECTED);
    }

    //========== GAME DIFFICULTY METHODS ==========
    @FXML
    public void handleEasyMode(MouseEvent mouseEvent) {
        singleplayerGame.setDifficulty(Difficulty.EASY);
        mediumModeToggleButton.setSelected(false);
        hardModeToggleButton.setSelected(false);
    }
    @FXML
    public void handleMediumMode(MouseEvent mouseEvent) {
        singleplayerGame.setDifficulty(Difficulty.MEDIUM);
        easyModeToggleButton.setSelected(false);
        hardModeToggleButton.setSelected(false);
    }
    @FXML
    public void handleHardMode(MouseEvent mouseEvent) {
        singleplayerGame.setDifficulty(Difficulty.HARD);
        easyModeToggleButton.setSelected(false);
        mediumModeToggleButton.setSelected(false);
    }

    //========== MENU BAR METHODS ==========
    @FXML
    public void handleCredits(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = navigator.changePage(Constants.Windows.CREDITS_VIEW, actionEvent, navigator.getCurrentStylesheet());
        CreditsViewController creditsViewController = fxmlLoader.getController();
        creditsViewController.navigator = navigator;
    }
    @FXML
    public void handleTheme(ActionEvent actionEvent) {
        //black theme
        if (themeImage.getImage().equals(Constants.Images.THEME_IMAGE_BLACK)) {
            navigator.setStylesheet(Constants.Stylesheet.BLACK_THEME);

            creditsImage.setImage(Constants.Images.STAR_WHITE);
            themeImage.setImage(Constants.Images.THEME_IMAGE_WHITE);

            if (volumeImage.getImage().equals(Constants.Images.VOLUME_UP_BLACK)) {
                volumeImage.setImage(Constants.Images.VOLUME_UP_WHITE);
            }

            if (volumeImage.getImage().equals(Constants.Images.VOLUME_DOWN_BLACK)) {
                volumeImage.setImage(Constants.Images.VOLUME_DOWN_WHITE);
            }

            infoImage.setImage(Constants.Images.INFO_WHITE);
        } else {    //white theme
            navigator.setStylesheet(Constants.Stylesheet.WHITE_THEME);

            creditsImage.setImage(Constants.Images.STAR_BLACK);
            themeImage.setImage(Constants.Images.THEME_IMAGE_BLACK);

            if (volumeImage.getImage().equals(Constants.Images.VOLUME_UP_WHITE)) {
                volumeImage.setImage(Constants.Images.VOLUME_UP_BLACK);
            }

            if (volumeImage.getImage().equals(Constants.Images.VOLUME_DOWN_WHITE)) {
                volumeImage.setImage(Constants.Images.VOLUME_DOWN_BLACK);
            }

            infoImage.setImage(Constants.Images.INFO_BLACK);
        }
    }
    @FXML
    public void handleVolume(ActionEvent actionEvent) {
        if (volumeImage.getImage().equals(Constants.Images.VOLUME_UP_BLACK)) {
            volumeImage.setImage(Constants.Images.VOLUME_DOWN_BLACK);
            mediaPlayer.pause();
            return;
        }

        if (volumeImage.getImage().equals(Constants.Images.VOLUME_DOWN_BLACK)) {
            volumeImage.setImage(Constants.Images.VOLUME_UP_BLACK);
            mediaPlayer.play();
            return;
        }

        if (volumeImage.getImage().equals(Constants.Images.VOLUME_UP_WHITE)) {
            volumeImage.setImage(Constants.Images.VOLUME_DOWN_WHITE);
            mediaPlayer.pause();
            return;
        }

        if (volumeImage.getImage().equals(Constants.Images.VOLUME_DOWN_WHITE)) {
            volumeImage.setImage(Constants.Images.VOLUME_UP_WHITE);
            mediaPlayer.play();
        }
    }
    @FXML
    public void handleInfo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = navigator.changePage(Constants.Windows.HOW_TO_PLAY_VIEW, actionEvent, navigator.getCurrentStylesheet());
        InfoController infoController = fxmlLoader.getController();
        infoController.navigator = navigator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Cell> field = new ArrayList<>(List.of(
                new Cell(0, button0), new Cell(1, button1), new Cell(2, button2),
                new Cell(3, button3), new Cell(4, button4), new Cell(5, button5),
                new Cell(6, button6), new Cell(7, button7), new Cell(8, button8))
        );

        //game data
        multiplayerGame = new GameData(field, GameTurn.FIRST_PLAYER, GameTurn.SECOND_PLAYER);
        singleplayerGame = new ComputerPlayer(field, GameTurn.COMPUTER_IS_FIRST, GameTurn.PLAYER_IS_SECOND);

        //song
        String musicFile = Objects.requireNonNull(getClass().getResource(Constants.Songs.SONG1)).toExternalForm();
        Media sound = new Media(musicFile);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.volumeProperty().setValue(0.1);
        mediaPlayer.play();

        //date
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            DateTimeFormatter formatter = Constants.DateFormat.DATE_TIME_FORMATTER;
            dateLabel.setText(LocalDateTime.now().format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //images
        creditsImage.setImage(Constants.Images.STAR_BLACK);
        themeImage.setImage(Constants.Images.THEME_IMAGE_BLACK);
        volumeImage.setImage(Constants.Images.VOLUME_UP_BLACK);
        infoImage.setImage(Constants.Images.INFO_BLACK);

        //properties
        singleplayerSpecificationsHBox.setVisible(false);
        winnerButton.setVisible(false);
        errorButton.setVisible(false);
        gridPaneField.setDisable(true);
    }

}
