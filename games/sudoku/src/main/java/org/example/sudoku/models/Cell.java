package org.example.sudoku.models;

import javafx.scene.control.Button;
import lombok.*;
import org.example.sudoku.utils.Constants;

import java.util.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cell {
    public int x;
    public int y;
    public Button button;
    public boolean changeable;
    public List<String> notes;

    public void setValue(String value) {
        button.setFont(Constants.Windows.MAIN_STYLE);
        button.setText(value);
        setNotes(new ArrayList<>());
    }

    public void removeNotes() {
        setNotes(new ArrayList<>());
        button.setText("");
    }

    public void addNote(String note) {
        if (!notes.contains(note)) {
            button.setFont(Constants.Windows.NOTES_STYLE);
            notes.add(note);
            button.setText(getNotesToString());
        }
    }

    private String getNotesToString() {
        StringBuilder notesString = new StringBuilder();
        Collections.sort(notes);

        int counterNotes = 0;
        for (String s : notes) {
            notesString.append(s).append(" ");
            counterNotes++;
            if (counterNotes % 3 == 0) {
                notesString.append("\n");
            }
        }

        return notesString.toString();
    }

}
