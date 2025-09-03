package org.example.sudoku.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Time {
    public int minutes;
    public int seconds;
    public boolean pause;

    public void update() {
        if (!pause) {
            if (seconds == 61) {
                seconds = 0;
                minutes++;
            } else {
                seconds++;
            }
        }
    }

    @Override
    public String toString() {
        String minutesString = (minutes < 10) ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
        String secondsString = (seconds < 10) ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
        return minutesString + ":" + secondsString;
    }

}
