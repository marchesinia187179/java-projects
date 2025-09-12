package org.example.tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameTurn {
    FIRST_PLAYER("First player: X", "X"),
    SECOND_PLAYER("Second player: O", "O"),
    PLAYER_IS_FIRST("Player: X", "X"),
    COMPUTER_IS_FIRST("Computer: X", "X"),
    PLAYER_IS_SECOND("Player: O", "O"),
    COMPUTER_IS_SECOND("Computer: O", "O");

    private final String message;
    private final String mark;

}
