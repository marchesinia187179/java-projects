package org.example.tictactoe.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.io.IOException;
import java.util.Stack;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PageNavigatorService {
    private final Stage mainStage;
    private final Stack<Parent> history = new Stack<>();
    private String currentStylesheet = Constants.Stylesheet.WHITE_THEME;

    public PageNavigatorService(Stage stage) {
        this.mainStage = stage;
    }

    public FXMLLoader initMainScene(WindowProperties window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(window.getPath()));
        Parent root = fxmlLoader.load();

        setScene(root, window.getTitle(), window.getWidth(), window.getHeight(), currentStylesheet);
        mainStage.show();
        return fxmlLoader;
    }

    public FXMLLoader changePage(WindowProperties window, ActionEvent event, String stylesheet) throws IOException {
        if (mainStage.getScene() != null && mainStage.getScene().getRoot() != null) {
            history.push(mainStage.getScene().getRoot());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(window.getPath()));
        Parent root = fxmlLoader.load();

        currentStylesheet = stylesheet;
        setScene(root, window.getTitle(), window.getWidth(), window.getHeight(), stylesheet);

        return fxmlLoader;
    }

    public void goBack() {
        if (!history.isEmpty()) {
            Parent previousRoot = history.pop();
            mainStage.getScene().setRoot(previousRoot);
        }
    }

    public void setStylesheet(String stylesheet) {
        if (mainStage.getScene() != null && mainStage.getScene().getRoot() != null) {
            Parent root = mainStage.getScene().getRoot();
            root.getStylesheets().clear();
            root.getStylesheets().add(stylesheet);
            currentStylesheet = stylesheet;
        }
    }

    private void setScene(Parent root, String title, int width, int height, String stylesheet) {
        Scene scene = mainStage.getScene();
        if (scene == null) {
            scene = new Scene(root, width, height);
            mainStage.setScene(scene);
        } else {
            scene.setRoot(root);
        }

        mainStage.setTitle(title);
        mainStage.setMinWidth(width);
        mainStage.setMinHeight(height);
        mainStage.setWidth(width);
        mainStage.setHeight(height);
        mainStage.setMaxWidth(width);
        mainStage.setMaxHeight(height);

        root.getStylesheets().clear();
        root.getStylesheets().add(stylesheet);

        mainStage.addEventFilter(ActionEvent.ACTION, e -> {
            if (e.getTarget() instanceof Button || e.getTarget() instanceof ToggleButton) {
                Toolkit.getDefaultToolkit().beep();
            }
        });

        mainStage.setOnCloseRequest(e -> System.exit(0));
    }

}
