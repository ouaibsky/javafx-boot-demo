package org.icroco.boot.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import org.controlsfx.control.SegmentedButton;


public class MainToolbar extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Tooltip Sample");
        stage.setWidth(300);
        stage.setHeight(150);
        scene.getStylesheets().add(getClass().getResource("/toggle-custom.css").toExternalForm());

        ToolBar toolBar = new ToolBar(
                new Button("New"),
                new Button("Open"),
                new Button("Save"),
                new Separator(),
                new Button("Clean"),
                new Button("Compile"),
                new Button("Run"),
                new Separator(),
                new Button("Debug"),
                new Button("Profile")
        );

        ToggleButton tg1 = new ToggleButton("th 1");
        ToggleButton tg2 = new ToggleButton("th 1");
        ToggleButton tg3 = new ToggleButton("th 1");

        //tg1.getStyleClass().setAll("custom");

        SegmentedButton sn = new SegmentedButton(tg1, tg2, tg3);

        ((Group) scene.getRoot()).getChildren().add(sn);

        stage.setScene(scene);
        stage.show();
    }
}