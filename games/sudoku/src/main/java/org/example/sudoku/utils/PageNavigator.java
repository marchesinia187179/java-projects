package org.example.sudoku.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import org.example.sudoku.controllers.mainpages.GameController;
import org.example.sudoku.models.Cell;
import org.example.sudoku.models.Difficulty;
import org.example.sudoku.models.GameData;
import org.example.sudoku.models.Time;
import org.example.sudoku.service.GameDataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageNavigator {
    public static void setDimensions(Stage stage, int height, int width) {
        stage.setMinHeight(height);
        stage.setHeight(height);
        stage.setMaxHeight(height);
        stage.setMinWidth(width);
        stage.setWidth(width);
        stage.setMaxWidth(width);
    }

    public static FXMLLoader changePage(WindowProperties window, ActionEvent actionEvent, EventHandler<WindowEvent> eventOnCloseRequest) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PageNavigator.class.getResource(window.getPath()));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setTitle(window.getTitle());
        setDimensions(stage, window.getHeight(), window.getWidth());
        stage.show();
        stage.setOnCloseRequest(eventOnCloseRequest);
        return fxmlLoader;
    }

    public static FXMLLoader openPopup(WindowProperties window, EventHandler<WindowEvent> eventOnCloseRequest) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PageNavigator.class.getResource(window.getPath()));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(window.getTitle());
        stage.initModality(window.getModality());
        setDimensions(stage, window.getHeight(), window.getWidth());
        stage.show();
        stage.setOnCloseRequest(eventOnCloseRequest);
        return fxmlLoader;
    }

    public static void startGamePage(WindowProperties window, ActionEvent actionEvent, Difficulty difficultySelected, Boolean isNewGame) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PageNavigator.class.getResource(window.getPath()));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setTitle(window.getTitle());
        setDimensions(stage, window.getHeight(), window.getWidth());
        stage.show();

        GameController gameController = fxmlLoader.getController();
        Cell[][] field = new Cell[9][9];
        List<Button> buttonsCell = new ArrayList<>(List.of(
                gameController.button00, gameController.button01, gameController.button02, gameController.button03, gameController.button04, gameController.button05, gameController.button06, gameController.button07, gameController.button08,
                gameController.button10, gameController.button11, gameController.button12, gameController.button13, gameController.button14, gameController.button15, gameController.button16, gameController.button17, gameController.button18,
                gameController.button20, gameController.button21, gameController.button22, gameController.button23, gameController.button24, gameController.button25, gameController.button26, gameController.button27, gameController.button28,
                gameController.button30, gameController.button31, gameController.button32, gameController.button33, gameController.button34, gameController.button35, gameController.button36, gameController.button37, gameController.button38,
                gameController.button40, gameController.button41, gameController.button42, gameController.button43, gameController.button44, gameController.button45, gameController.button46, gameController.button47, gameController.button48,
                gameController.button50, gameController.button51, gameController.button52, gameController.button53, gameController.button54, gameController.button55, gameController.button56, gameController.button57, gameController.button58,
                gameController.button60, gameController.button61, gameController.button62, gameController.button63, gameController.button64, gameController.button65, gameController.button66, gameController.button67, gameController.button68,
                gameController.button70, gameController.button71, gameController.button72, gameController.button73, gameController.button74, gameController.button75, gameController.button76, gameController.button77, gameController.button78,
                gameController.button80, gameController.button81, gameController.button82, gameController.button83, gameController.button84, gameController.button85, gameController.button86, gameController.button87, gameController.button88
        ));

        int buttonsCellIndex = 0;
        for (int i = 0; i < Constants.Field.ROWS; i++) {
            for (int j = 0; j < Constants.Field.COLUMNS; j++) {
                field[i][j] = new Cell(i, j, buttonsCell.get(buttonsCellIndex), true, new ArrayList<>());
                buttonsCellIndex++;
            }
        }

        GameData gameData;
        if (isNewGame) {
            gameData = new GameData(field, difficultySelected, 0, Constants.GameStatus.IN_PROGRESS, new Time(0, 0, false));
            gameData.setField(field);
            GameDataService.JsonFileWriter(gameData);
        } else {
            gameData = GameDataService.JsonFileReader(field);
            gameData.getFieldHasList().stream()
                    .filter(cell -> !cell.getNotes().isEmpty())
                    .forEach(cell -> cell.button.setFont(Constants.Windows.NOTES_STYLE));
        }

        gameController.gameData = gameData;
        gameController.labelDifficulty.setText(gameData.getDifficulty().getName());
        gameController.labelMistakes.setText(String.valueOf(gameData.getMistakes()));
        gameController.labelTime.setText(gameData.getTime().toString());

        stage.setOnCloseRequest(windowEvent -> {
                    try {
                        GameDataService.JsonFileWriter(gameController.gameData);
                        System.exit(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static void closePage(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}
