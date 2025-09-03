package org.example.sudoku.utils;

import javafx.stage.Modality;
import org.example.sudoku.models.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static class Windows {
        public static final int HEIGHT_WINDOW = 630;
        public static final int WIDTH_WINDOW = 490;
        public static final int HEIGHT_POPUP = 200;
        public static final int WIDTH_POPUP = 300;

        public static final javafx.scene.text.Font MAIN_STYLE = new javafx.scene.text.Font("System",18);
        public static final javafx.scene.text.Font NOTES_STYLE = new javafx.scene.text.Font("System",9);

        public static final WindowProperties HOME_PAGE = new WindowProperties("/org/example/sudoku/views/mainpages/HomePageView.fxml", "SUDOKU - Home page", HEIGHT_WINDOW, WIDTH_WINDOW, Modality.NONE);
        public static final WindowProperties GAME = new WindowProperties("/org/example/sudoku/views/mainpages/GameView.fxml", "SUDOKU - Game", HEIGHT_WINDOW, WIDTH_WINDOW, Modality.NONE);
        public static final WindowProperties END_GAME = new WindowProperties("/org/example/sudoku/views/mainpages/EndView.fxml", "SUDOKU - End", HEIGHT_WINDOW, WIDTH_WINDOW, Modality.NONE);
        public static final WindowProperties DIFFICULTY = new WindowProperties("/org/example/sudoku/views/mainpages/DifficultyView.fxml", "SUDOKU - Difficulty", HEIGHT_WINDOW, WIDTH_WINDOW, Modality.NONE);
        public static final WindowProperties NEW_GAME = new WindowProperties("/org/example/sudoku/views/popups/NewGamePopup.fxml", "SUDOKU - New game", HEIGHT_POPUP, WIDTH_POPUP, Modality.APPLICATION_MODAL);
        public static final WindowProperties INFO = new WindowProperties("/org/example/sudoku/views/popups/InfoPopup.fxml", "SUDOKU - Info", HEIGHT_POPUP, WIDTH_POPUP, Modality.APPLICATION_MODAL);
        public static final WindowProperties HOW_TO_PLAY = new WindowProperties("/org/example/sudoku/views/popups/HowToPlayPopup.fxml", "SUDOKU - How to play?", HEIGHT_WINDOW, WIDTH_WINDOW, Modality.APPLICATION_MODAL);
        public static final WindowProperties GAME_PAUSED = new WindowProperties("/org/example/sudoku/views/popups/GamePausedPopup.fxml", "SUDOKU - Game paused", HEIGHT_POPUP, WIDTH_POPUP, Modality.APPLICATION_MODAL);
        public static final WindowProperties ERROR_CONTINUE = new WindowProperties("/org/example/sudoku/views/popups/ErrorContinueGamePopup.fxml", "SUDOKU - Error continue game", HEIGHT_POPUP, WIDTH_POPUP, Modality.APPLICATION_MODAL);
    }

    public static class Field {
        public static final int ROWS = 9;
        public static final int COLUMNS = 9;
        public static final List<String> NUMBERS_TO_SELECT = new ArrayList<>(List.of("1","2","3","4","5","6","7","8","9"));
        public static final int MAXIMUM_MISTAKES = 3;

        /*
         * first array int contains the rows index of k-sector
         * the second array int contains the columns index of k-sector
         */
        public static final List<int[]> SECTOR_1 = new ArrayList<>(List.of(new int[]{0,1,2}, new int[]{0,1,2}));
        public static final List<int[]> SECTOR_2 = new ArrayList<>(List.of(new int[]{0,1,2}, new int[]{3,4,5}));
        public static final List<int[]> SECTOR_3 = new ArrayList<>(List.of(new int[]{0,1,2}, new int[]{6,7,8}));

        public static final List<int[]> SECTOR_4 = new ArrayList<>(List.of(new int[]{3,4,5}, new int[]{0,1,2}));
        public static final List<int[]> SECTOR_5 = new ArrayList<>(List.of(new int[]{3,4,5}, new int[]{3,4,5}));
        public static final List<int[]> SECTOR_6 = new ArrayList<>(List.of(new int[]{3,4,5}, new int[]{6,7,8}));

        public static final List<int[]> SECTOR_7 = new ArrayList<>(List.of(new int[]{6,7,8}, new int[]{0,1,2}));
        public static final List<int[]> SECTOR_8 = new ArrayList<>(List.of(new int[]{6,7,8}, new int[]{3,4,5}));
        public static final List<int[]> SECTOR_9 = new ArrayList<>(List.of(new int[]{6,7,8}, new int[]{6,7,8}));

        public static final List<List<int[]>> SECTORS = new ArrayList<>(List.of(
                SECTOR_1, SECTOR_2, SECTOR_3, SECTOR_4, SECTOR_5, SECTOR_6, SECTOR_7, SECTOR_8, SECTOR_9
        ));
    }

    public static class GameDifficulty {
        public static final Difficulty EASY = new Difficulty("Easy", 46);
        public static final Difficulty MEDIUM = new Difficulty("Medium", 53);
        public static final Difficulty HARD = new Difficulty("Hard", 59);
        public static final Difficulty IMPOSSIBLE = new Difficulty("Impossible", 64);
    }

    public static class GameStatus {
        public static final String IN_PROGRESS = "In progress...";
        public static final String COMPLETED = "Completed";
        public static final String FAILED = "Failed";
    }

    public static class Links {
        public static final String GITHUB_LINK = "https://github.com/marchesinia187179";
    }

}
