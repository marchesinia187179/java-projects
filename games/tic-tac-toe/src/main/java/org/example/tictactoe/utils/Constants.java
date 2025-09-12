package org.example.tictactoe.utils;

import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Constants {
    public static class Stylesheet {
        public static final String BLACK_THEME = Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/styles/black-theme.css")).toExternalForm();
        public static final String WHITE_THEME = Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/styles/white-theme.css")).toExternalForm();

    }

    public static class Windows {
        public static final int WIDTH = 330;
        public static final int HEIGHT = 490 + 30;
        public static final WindowProperties GAME_VIEW = new WindowProperties("/org/example/tictactoe/pages/game-view.fxml", "Tic Tac Toe", WIDTH, HEIGHT, Modality.NONE);
        public static final WindowProperties HOW_TO_PLAY_VIEW = new WindowProperties("/org/example/tictactoe/pages/info-view.fxml", "Tic Tac Toe", WIDTH, HEIGHT, Modality.NONE);
        public static final WindowProperties CREDITS_VIEW = new WindowProperties("/org/example/tictactoe/pages/credits-view.fxml", "Tic Tac Toe", WIDTH, HEIGHT, Modality.NONE);
    }

    public static class Errors {
        public static final String NEW_GAME_MODALITY = "PLEASE SELECT A MODALITY";
        public static final String NEW_GAME_DIFFICULTY = "PLEASE SELECT A DIFFICULTY";
    }

    public static class Game {
        public static final List<Integer> indices = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));

        public static final Set<Integer> indicesFirstRow = new HashSet<>(Set.of(0, 1, 2));
        public static final Set<Integer> indicesSecondRow = new HashSet<>(Set.of(3, 4, 5));
        public static final Set<Integer> indicesThirdRow = new HashSet<>(Set.of(6, 7, 8));
        public static final List<Set<Integer>> indicesRows = new ArrayList<>(List.of(indicesFirstRow, indicesSecondRow, indicesThirdRow));

        public static final Set<Integer> indicesFirstColumn = new HashSet<>(Set.of(0, 3, 6));
        public static final Set<Integer> indicesSecondColumn = new HashSet<>(Set.of(1, 4, 7));
        public static final Set<Integer> indicesThirdColumn = new HashSet<>(Set.of(2, 5, 8));
        public static final List<Set<Integer>> indicesColumns = new ArrayList<>(List.of(indicesFirstColumn, indicesSecondColumn, indicesThirdColumn));

        public static final Set<Integer> indicesFirstDiagonal = new HashSet<>(Set.of(0, 4, 8));
        public static final Set<Integer> indicesSecondDiagonal = new HashSet<>(Set.of(2, 4, 6));

        public static final Set<Integer> indicesCorners = new HashSet<>(Set.of(0, 2, 6, 8));
        public static final Set<Integer> indicesEdges = new HashSet<>(Set.of(1, 3, 5, 7));
        public static final int indexCenter = 4;

        public static final Set<Integer> indicesTopSide = new HashSet<>(Set.of(0, 1, 2));
        public static final Set<Integer> indicesLeftSide = new HashSet<>(Set.of(0, 3, 6));
        public static final Set<Integer> indicesRightSide = new HashSet<>(Set.of(2, 5, 8));
        public static final Set<Integer> indicesBottomSide = new HashSet<>(Set.of(6, 7, 8));
        public static final List<Set<Integer>> indicesSides = new ArrayList<>(List.of(indicesTopSide, indicesLeftSide, indicesRightSide, indicesBottomSide));

        public static final List<Set<Integer>> allTriplets = new ArrayList<>(List.of(
                indicesFirstRow, indicesSecondRow, indicesThirdRow,
                indicesFirstColumn, indicesSecondColumn, indicesThirdColumn,
                indicesFirstDiagonal, indicesSecondDiagonal
        ));

        public static final int NO_MOVE = -1;
    }

    public static class Links {
        public static final String GITHUB_LINK = "https://github.com/marchesinia187179";
    }

    public static class Songs {
        public static final String SONG1 = "/org/example/tictactoe/sounds/song1.mp3";
    }

    public static class Images {
        public static final Image THEME_IMAGE_BLACK = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/theme-black.png")).toExternalForm());
        public static final Image THEME_IMAGE_WHITE = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/theme-white.png")).toExternalForm());
        public static final Image VOLUME_DOWN_BLACK = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/volume-down-black.png")).toExternalForm());
        public static final Image VOLUME_DOWN_WHITE = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/volume-down-white.png")).toExternalForm());
        public static final Image VOLUME_UP_BLACK = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/volume-up-black.png")).toExternalForm());
        public static final Image VOLUME_UP_WHITE = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/volume-up-white.png")).toExternalForm());
        public static final Image INFO_BLACK = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/info-black.png")).toExternalForm());
        public static final Image INFO_WHITE = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/info-white.png")).toExternalForm());
        public static final Image STAR_BLACK = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/star-black.png")).toExternalForm());
        public static final Image STAR_WHITE = new Image(Objects.requireNonNull(Windows.class.getResource("/org/example/tictactoe/images/star-white.png")).toExternalForm());
    }

    public static class DateFormat {
        public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE dd LLL  HH:mm");
    }

}
