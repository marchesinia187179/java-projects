package org.example.tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameStatus {
    FIRST_PLAYER_WIN("X IS THE WINNER"),
    SECOND_PLAYER_WIN("O IS THE WINNER"),
    DRAW("DRAW"),
    ONGOING("IN PROGRESS");

    private final String message;

}
