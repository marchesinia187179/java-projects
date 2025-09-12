package org.example.tictactoe.models;

import lombok.*;
import org.example.tictactoe.utils.Constants;

import java.util.*;
import java.util.stream.Stream;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GameData {
    private List<Cell> field;
    private GameTurn firstPlayer;
    private GameTurn secondPlayer;
    private GameStatus status;
    private GameTurn currentPlayer;

    public GameData(List<Cell> field, GameTurn firstPlayer, GameTurn secondPlayer) {
        this.field = field;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.status = GameStatus.ONGOING;
        this.currentPlayer = firstPlayer;
    }

    //========== UPDATE METHODS ==========
    public boolean makeMove(int index, String mark) {
        if (index == Constants.Game.NO_MOVE || !isCellEmpty(index)) {
            return false;
        }

        field.get(index).setMark(mark);
        checkStatus();
        return true;
    }
    public void updateCurrentPlayer() {
        currentPlayer = (currentPlayer.equals(firstPlayer)) ? secondPlayer : firstPlayer;
    }

    //========== VERIFICATION METHODS ==========
    protected void checkStatus() {
        for (Set<Integer> set : Constants.Game.allTriplets) {
            if (countMarksInSet(set.stream(), firstPlayer.getMark()) == 3) {
                status = GameStatus.FIRST_PLAYER_WIN;
                return;
            }

            if (countMarksInSet(set.stream(), secondPlayer.getMark()) == 3) {
                status = GameStatus.SECOND_PLAYER_WIN;
                return;
            }
        }

        if (isFieldFilled()) {
            status = GameStatus.DRAW;
        }
    }
    protected boolean isFieldFilled() {
        return Constants.Game.indices.stream().noneMatch(this::isCellEmpty);
    }
    protected boolean isCellEmpty(int index) {
        return field.get(index) != null && field.get(index).getMark().isEmpty();
    }

    //========== SECONDARY METHODS ==========
    protected long countMarksInSet(Stream<Integer> stream, String mark) {
        return stream.filter(i -> field.get(i).getMark().equals(mark)).count();
    }

}