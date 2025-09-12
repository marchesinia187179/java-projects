module org.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires javafx.graphics;
    requires javafx.base;
    //requires jdk.jfr;
    requires java.desktop;
    requires javafx.media;

    opens org.example.tictactoe to javafx.fxml;
    exports org.example.tictactoe;
    exports org.example.tictactoe.models;
    opens org.example.tictactoe.controls to javafx.fxml;
    exports org.example.tictactoe.controls;
    exports org.example.tictactoe.utils;
}