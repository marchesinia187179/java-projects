package org.example.tictactoe.models;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cell {
    private int index;
    private Button button;
    private String mark;

    public Cell(int index, Button button) {
        this.index = index;
        this.button = button;
        this.mark = button.getText();
    }

    public void setMark(String mark) {
        this.mark = mark;
        button.setText(mark);
    }

}
