package org.example.tictactoe.utils;

import javafx.stage.Modality;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WindowProperties {
    private String path;
    private String title;
    private int width;
    private int height;
    private Modality modality;

}
