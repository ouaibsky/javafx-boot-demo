package org.icroco.boot.javafx;/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SegmentedButtonBarApp extends Application {
    @Override public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setId("background");

        ToolBar toolBar = new ToolBar();
        root.setTop(toolBar);

        Region spacer = new Region();
        spacer.getStyleClass().setAll("spacer");

        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().setAll("segmented-button-bar");
        Button sampleButton = new Button("Tasks");
        sampleButton.getStyleClass().addAll("first");
        Button sampleButton2 = new Button("Administrator");
        Button sampleButton3 = new Button("Search");
        Button sampleButton4 = new Button("Line");
        Button sampleButton5 = new Button("Process");
        sampleButton5.getStyleClass().addAll("last", "capsule");
        buttonBar.getChildren().addAll(sampleButton, sampleButton2, sampleButton3, sampleButton4, sampleButton5);
        toolBar.getItems().addAll(spacer, buttonBar);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/segmented.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Segmented Button Bar");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}