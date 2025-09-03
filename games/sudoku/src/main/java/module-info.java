module org.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.json;
    requires java.rmi;
    requires java.desktop;

    opens org.example.sudoku to javafx.fxml;
    exports org.example.sudoku;
    exports org.example.sudoku.controllers.mainpages;
    exports org.example.sudoku.controllers.popups;
    exports org.example.sudoku.models;
    exports org.example.sudoku.utils;

}