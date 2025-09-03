package org.example.sudoku.utils;

import javafx.stage.Modality;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WindowProperties {
    public String path;
    public String title;
    public int height;
    public int width;
    public Modality modality;

}
