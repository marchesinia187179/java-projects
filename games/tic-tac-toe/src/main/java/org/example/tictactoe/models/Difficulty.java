package org.example.tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Difficulty {
    EASY(50),
    MEDIUM(66),
    HARD(100),
    NOT_SELECTED(0);

    private final int value;    //probability that the computer chooses the correct move

}
