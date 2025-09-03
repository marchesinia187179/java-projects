package org.example.sudoku.service;

import org.example.sudoku.models.Cell;
import org.example.sudoku.models.Difficulty;
import org.example.sudoku.models.GameData;
import org.example.sudoku.models.Time;
import org.example.sudoku.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameDataService {
    private static final String GAME_DATA_PATH = "data/game_data.json";

    public static void JsonFileWriter(GameData gameData) throws IOException {
        JSONArray field = new JSONArray();
        for (int i = 0; i < Constants.Field.COLUMNS; i++) {
            JSONArray row = new JSONArray();
            for (int j = 0; j < Constants.Field.ROWS; j++) {
                Cell currentCell = gameData.getField()[i][j];
                row.put(new JSONObject()
                        .put("x", currentCell.getX())
                        .put("y", currentCell.getY())
                        .put("value", currentCell.button.getText())
                        .put("changeable", currentCell.changeable)
                        .put("notes", currentCell.getNotes())
                );
            }
            field.put(row);
        }

        Files.writeString(Paths.get(GAME_DATA_PATH),
                new JSONObject()
                        .put("field", field)
                        .put("difficulty", new JSONObject()
                                .put("name", gameData.getDifficulty().getName())
                                .put("value", gameData.getDifficulty().getValue()))
                        .put("mistakes", gameData.getMistakes())
                        .put("status", gameData.getStatus())
                        .put("time", new JSONObject()
                                .put("minutes", gameData.getTime().getMinutes())
                                .put("seconds", gameData.getTime().getSeconds())
                                .put("pause", gameData.getTime().pause))
                        .toString(2)
        );
    }

    public static GameData JsonFileReader(Cell[][] field) throws IOException {
        if (!isJsonFileEmpty()) {
            JSONObject jsonObject = new JSONObject(Files.readString(Paths.get(GAME_DATA_PATH)));
            JSONArray jsonArrayField = jsonObject.getJSONArray("field");
            for (int i = 0; i < Constants.Field.COLUMNS; i++) {
                for (int j = 0; j < Constants.Field.ROWS; j++) {
                    field[i][j].setValue(jsonArrayField.getJSONArray(i).getJSONObject(j).get("value").toString());
                    field[i][j].setChangeable((Boolean) jsonArrayField.getJSONArray(i).getJSONObject(j).get("changeable"));

                    //obtain the list
                    List<String> list = new ArrayList<>();
                    for (int k = 0; k < jsonArrayField.getJSONArray(i).getJSONObject(j).getJSONArray("notes").length(); k++) {
                        list.add(jsonArrayField.getJSONArray(i).getJSONObject(j).getJSONArray("notes").getString(k));
                    }

                    field[i][j].setNotes(list);
                }
            }

            return new GameData(
                    field,
                    JsonFileGetDifficulty(),
                    jsonObject.getInt("mistakes"),
                    jsonObject.getString("status"),
                    JsonFileGetTime()
            );
        } else {
            throw new RuntimeException("Error JsonFileReader.");
        }
    }

    public static Difficulty JsonFileGetDifficulty() throws IOException {
        if (!isJsonFileEmpty()) {
            JSONObject jsonObject = new JSONObject(Files.readString(Paths.get(GAME_DATA_PATH)));
            return new Difficulty(
                    jsonObject.getJSONObject("difficulty").get("name").toString(),
                    Integer.parseInt(jsonObject.getJSONObject("difficulty").get("value").toString())
            );
        } else {
            return null;
        }
    }

    public static Time JsonFileGetTime() throws IOException {
        if (!isJsonFileEmpty()) {
            JSONObject jsonObject = new JSONObject(Files.readString(Paths.get(GAME_DATA_PATH)));
            return new Time(
                    Integer.parseInt(jsonObject.getJSONObject("time").get("minutes").toString()),
                    Integer.parseInt(jsonObject.getJSONObject("time").get("seconds").toString()),
                    Boolean.getBoolean(jsonObject.getJSONObject("time").get("pause").toString())
            );
        } else {
            return null;
        }
    }

    public static boolean isJsonFileEmpty() throws IOException {
        return new JSONObject(Files.readString(Paths.get(GAME_DATA_PATH))).isEmpty();
    }

    public static void JsonFileRemover() throws IOException {
        Files.writeString(Paths.get(GAME_DATA_PATH), new JSONObject().toString(2));
    }

}
