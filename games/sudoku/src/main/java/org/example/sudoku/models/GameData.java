package org.example.sudoku.models;

import lombok.*;
import org.example.sudoku.utils.Constants;
import java.util.*;
import java.util.random.RandomGenerator;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GameData {
    public Cell[][] field;
    public Difficulty difficulty;
    public int mistakes;
    public String status;
    public Time time;

    public List<Cell> getFieldHasList() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < Constants.Field.COLUMNS; i++) {
            cells.addAll(Arrays.asList(field[i]).subList(0, Constants.Field.ROWS));
        }
        return cells;
    }

    private boolean areRowsValid() {
        for (int i = 0; i < Constants.Field.ROWS; i++) {
            Set<String> row = new HashSet<>();

            for (int j = 0; j < Constants.Field.COLUMNS; j++) {
                String cellValue = field[i][j].button.getText();

                if (!cellValue.isEmpty()) {
                    if (!row.add(cellValue)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    private boolean areColumnsValid() {
        for (int j = 0; j < Constants.Field.COLUMNS; j++) {
            Set<String> column = new HashSet<>();

            for (int i = 0; i < Constants.Field.ROWS; i++) {
                String cellValue = field[i][j].button.getText();

                if (!cellValue.isEmpty()) {
                    if (!column.add(cellValue)) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    private boolean areSectorsValid() {
        for (List<int[]> list : Constants.Field.SECTORS) {
            Set<String> sector = new HashSet<>();

            for (int i : list.getFirst()) {     //row indexes
                for (int j : list.getLast()) {      //column indexes
                    String cellValue = field[i][j].button.getText();

                    if (!cellValue.isEmpty()) {
                        if (!sector.add(cellValue)) {
                            return false;
                        }
                    }

                }
            }

        }

        return true;
    }

    private boolean isFilled() {
        for (int i = 0; i < Constants.Field.ROWS; i++) {
            for (int j = 0; j < Constants.Field.COLUMNS; j++) {
                if (field[i][j].button.getText().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValid() {
        boolean valid = isFilled() && areRowsValid() && areColumnsValid() && areSectorsValid();

        if (!valid) {
            mistakes++;
            if (mistakes == Constants.Field.MAXIMUM_MISTAKES) {
                setStatus(Constants.GameStatus.FAILED);
            }
        } else {
            setStatus(Constants.GameStatus.COMPLETED);
        }

        return valid;
    }

    private boolean createCompleteField(int k, int i, int j, List<String> numbersToSelect) {
        if (!areRowsValid() || !areColumnsValid() || !areSectorsValid()) {      //pruning
            return false;
        }

        if (k >= 81) {      //end
            return true;
        }

        Collections.shuffle(numbersToSelect);
        for (String numberSelected : numbersToSelect) {      //recursive call
            field[i][j].setValue(numberSelected);
            field[i][j].setChangeable(false);

            int nextI = i;
            int nextJ = j + 1;
            if (nextJ > 8) {
                nextJ = 0;
                nextI++;
            }

            if (createCompleteField(k + 1, nextI, nextJ, numbersToSelect)) {
                return true;
            }

            field[i][j].setValue("");
            field[i][j].setChangeable(true);
        }

        return false;
    }

    public void eraseCells(int quantity) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        for (int i = 0; i < quantity; i++) {
            int rndRow = rnd.nextInt(0,Constants.Field.ROWS);
            int rndColumn = rnd.nextInt(0,Constants.Field.COLUMNS);

            if (!field[rndRow][rndColumn].button.getText().isEmpty()) {
                field[rndRow][rndColumn].setValue("");
                field[rndRow][rndColumn].setChangeable(true);
            }
        }
    }

    public void setField(Cell[][] field) {
        createCompleteField(0, 0, 0, Constants.Field.NUMBERS_TO_SELECT);
        eraseCells(difficulty.getValue());
        this.field = field;
    }

}
