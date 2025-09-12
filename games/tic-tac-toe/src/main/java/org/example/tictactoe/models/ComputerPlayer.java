package org.example.tictactoe.models;

import lombok.*;
import org.example.tictactoe.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
public class ComputerPlayer extends GameData {
    private String computerMark;
    private String playerMark;
    private boolean defensive;
    private int firstComputerMove;
    private Difficulty difficulty;

    public ComputerPlayer(List<Cell> field, GameTurn firstPlayer, GameTurn secondPlayer) {
        super(field, firstPlayer, secondPlayer);
        this.computerMark = firstPlayer.getMark();
        this.playerMark = secondPlayer.getMark();
        this.defensive = false;
        this.firstComputerMove = Constants.Game.NO_MOVE;
        this.difficulty = Difficulty.NOT_SELECTED;
    }

    //========== SETTING METHODS ==========
    public void setComputerAsFirst() {
        setFirstPlayer(GameTurn.COMPUTER_IS_FIRST);
        setComputerMark(GameTurn.COMPUTER_IS_FIRST.getMark());
        setSecondPlayer(GameTurn.PLAYER_IS_SECOND);
        setPlayerMark(GameTurn.PLAYER_IS_SECOND.getMark());
        setCurrentPlayer(getFirstPlayer());
        setDefensive(false);
    }
    public void setComputerAsSecond() {
        setFirstPlayer(GameTurn.PLAYER_IS_FIRST);
        setPlayerMark(GameTurn.PLAYER_IS_FIRST.getMark());
        setSecondPlayer(GameTurn.COMPUTER_IS_SECOND);
        setComputerMark(GameTurn.COMPUTER_IS_SECOND.getMark());
        setCurrentPlayer(getFirstPlayer());
        setDefensive(true);
    }
    public void setDifficulty(Difficulty difficulty) {
        if (difficulty.equals(getDifficulty())) {
            this.difficulty = Difficulty.NOT_SELECTED;
        } else {
            this.difficulty = difficulty;
        }
    }

    //========== MOVE METHODS ==========
    public void setFirstComputerMove(int firstComputerMove) {
        this.firstComputerMove = firstComputerMove;
        makeMove(firstComputerMove, getComputerMark());
    }
    public void nextMove(int playerMove) {
        if (isFieldFilled()) {
            checkStatus();
            return;
        }

        //probability to choosing the correct move based on the difficulty
        if (RandomGenerator.getDefault().nextInt(0,100) >= getDifficulty().getValue()) {
            makeMove(getRandomSelectableCell(Constants.Game.indices.stream()), getComputerMark());
            return;
        }

        //check if the computer can win
        if (makeMove(findWinningOrBlockingMove(getComputerMark()), getComputerMark())) {
            return;
        }

        //check if the player can win
        if (makeMove(findWinningOrBlockingMove(getPlayerMark()), getComputerMark())) {
            return;
        }

        boolean isUploaded = false;
        //offensive move
        if (!isDefensive()) {
            switch (getMoveCase(getFirstComputerMove())) {
                case 0 -> isUploaded = makeMove(centerCaseOffensive(playerMove), getComputerMark());
                case 1 -> isUploaded = makeMove(edgeCaseOffensive(playerMove), getComputerMark());
                case 2 -> isUploaded = makeMove(cornerCaseOffensive(playerMove), getComputerMark());
            }

            if (isUploaded) {
                return;
            }
        }

        //defensive move
        if (isDefensive()) {
            switch (getMoveCase(playerMove)) {
                case 0 -> isUploaded = makeMove(centerCaseDefensive(), getComputerMark());
                case 1 -> isUploaded = makeMove(edgeCaseDefensive(playerMove), getComputerMark());
                case 2 -> isUploaded = makeMove(cornerCaseDefensive(), getComputerMark());
            }

            if (isUploaded) {
                return;
            }
        }

        //random move
        makeMove(getRandomSelectableCell(Constants.Game.indices.stream()), getComputerMark());
    }

    //========== STRATEGY METHODS ==========
    private int findWinningOrBlockingMove(String mark) {
        for (Set<Integer> set : Constants.Game.allTriplets) {
            boolean setHasTwoMarks = countMarksInSet(set.stream(), mark) == 2;
            boolean setHasOneEmptyCell = set.stream().filter(this::isCellEmpty).count() == 1;

            if (setHasTwoMarks && setHasOneEmptyCell) {
                return set.stream().filter(this::isCellEmpty).toList().getFirst();
            }
        }
        return Constants.Game.NO_MOVE;
    }

    //offensive strategies

    /*
     * first computer move: center + first player move: edge
     * > next computer move: random empty corner, otherwise it changes in defensive mode
     */
    private int centerCaseOffensive(int playerMove) {
        if (countMarksInSet(Constants.Game.indices.stream(), getComputerMark()) == 1) {
            if (Constants.Game.indicesCorners.contains(playerMove)) {
                setDefensive(true);
                return Constants.Game.NO_MOVE;
            }

            //if the player chooses an edge
            return getRandomSelectableCell(Constants.Game.indicesCorners.stream());
        }

        return findForkMove();
    }

    /*
     * first computer move: edge + first player move: different column & different row
     * > next computer move: center, otherwise it changes in defensive mode
     * >> next computer move: fork move
     */
    private int edgeCaseOffensive(int playerMove) {
        if (countMarksInSet(Constants.Game.indices.stream(), getComputerMark()) == 1) {
            if (getRowAndColumnSet(getFirstComputerMove()).contains(playerMove)) {
                setDefensive(true);
                return Constants.Game.NO_MOVE;
            }

            return (isCellEmpty(Constants.Game.indexCenter)) ? Constants.Game.indexCenter : Constants.Game.NO_MOVE;
        }

        return findForkMove();
    }

    /*
     * first computer move: corner + first player move: edge or corner
     * edge case > next computer move: center
     * corner case > next computer move: corner
     * otherwise it changes in defensive mode
     */
    private int cornerCaseOffensive(int playerMove) {
        if (countMarksInSet(Constants.Game.indices.stream(), getComputerMark()) == 1) {
            if (playerMove == Constants.Game.indexCenter) {
                setDefensive(true);
                return Constants.Game.NO_MOVE;
            }

            //if the player chooses an edge
            if (Constants.Game.indicesEdges.contains(playerMove)) {
                return (isCellEmpty(Constants.Game.indexCenter)) ? Constants.Game.indexCenter : Constants.Game.NO_MOVE;
            }

            //if the player chooses a corner
            if (Constants.Game.indicesCorners.contains(playerMove)) {
                return getRandomSelectableCell(Constants.Game.indicesCorners.stream());
            }
        }

        return findForkMove();
    }

    //defensive strategies

    /*
     * first player move: center
     * > first computer move: random empty corner
     */
    private int centerCaseDefensive() {
        return getRandomSelectableCell(Constants.Game.indicesCorners.stream());
    }

    /*
     * first player move: edge
     * > first computer move: random empty cell of the same row or column
     */
    private int edgeCaseDefensive(int playerMove) {
        return getRandomSelectableCell(getRowAndColumnSet(playerMove).stream());
    }

    /*
     * first player move: corner
     * > first computer move: center
     * >> next computer move: edge
     */
    private int cornerCaseDefensive() {
        if (countMarksInSet(Constants.Game.indices.stream(), getComputerMark()) == 1) {
            return getRandomSelectableCell(Constants.Game.indicesEdges.stream());
        }

        return (isCellEmpty(Constants.Game.indexCenter)) ? Constants.Game.indexCenter : Constants.Game.NO_MOVE;
    }

    //========== SECONDARY METHODS ==========
    private int getMoveCase(int move) {
        if (Constants.Game.indicesEdges.contains(move)) {
            return 1;
        }

        if (Constants.Game.indicesCorners.contains(move)) {
            return 2;
        }

        return 0;   //center case
    }
    private Set<Integer> getSetOfIndex(List<Set<Integer>> setList, int index) {
        return setList.stream().filter(s -> s.contains(index)).toList().getFirst();
    }
    private int getRandomSelectableCell(Stream<Integer> stream) {
        List<Integer> emptyCells = new ArrayList<>(stream.filter(this::isCellEmpty).toList());
        if (emptyCells.isEmpty()) {
            return Constants.Game.NO_MOVE;
        }

        Collections.shuffle(emptyCells);
        return emptyCells.getFirst();
    }
    private int findForkMove() {
        for (Set<Integer> side : Constants.Game.indicesSides) {
            boolean sideHasTwoEmptyCell = side.stream().filter(this::isCellEmpty).count() == 2;
            boolean sideHasOneComputerMark = side.stream().filter(i -> getField().get(i).getMark().equals(getComputerMark())).count() == 1;

            if (sideHasTwoEmptyCell && sideHasOneComputerMark) {
                return side.stream().filter(this::isCellEmpty).filter(Constants.Game.indicesCorners::contains).toList().getFirst();
            }
        }

        return Constants.Game.NO_MOVE;
    }
    private List<Integer> getRowAndColumnSet(int index) {
        List<Integer> rowAndColumn = new ArrayList<>();
        rowAndColumn.addAll(getSetOfIndex(Constants.Game.indicesRows, index).stream().filter(this::isCellEmpty).toList());
        rowAndColumn.addAll(getSetOfIndex(Constants.Game.indicesColumns, index).stream().filter(this::isCellEmpty).toList());
        return rowAndColumn;
    }

}
