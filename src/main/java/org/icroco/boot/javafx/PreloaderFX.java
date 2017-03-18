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

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PreloaderFX extends Preloader {

    Stage stage;
    //boolean noLoadingProgress = true;

    public static final String APPLICATION_ICON
            = "http://cdn1.iconfinder.com/data/icons/Copenhagen/PNG/32/people.png";
//    public static final String SPLASH_IMAGE
//            = "http://fxexperience.com/wp-content/uploads/2010/06/logo.png";
    public static final String SPLASH_IMAGE
            = "/1_onyx.jpeg";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 1140;
    private static final int SPLASH_HEIGHT = 445;

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                getClass().getResourceAsStream(SPLASH_IMAGE)
        ));
        loadProgress = new ProgressBar(0.4);
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Loading . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; "
                        + "-fx-background-color: transparent; "
                        + "-fx-border-width:5; "
        );
       // splashLayout.setStyle("-fx-padding: 5; -fx-background-color: cornsilk; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, chocolate, derive(chocolate, 50%));");

        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("PreloaderFx::start();");

        Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/test2.fxml"));
        //this.stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.getIcons().add(new Image(APPLICATION_ICON));
        stage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        final Scene scene = new Scene(root2, Color.TRANSPARENT);
        //scene.getStylesheets().add(getClass().getResource("/splash.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        stage.show();
        System.out.println("stage: "+stage);

        this.stage = stage;
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        System.out.println("PreloaderFx::handleProgressNotification(); progress = " + pn.getProgress());
        //application loading progress is rescaled to be first 50%
        //Even if there is nothing to load 0% and 100% events can be
        // delivered
        if (pn.getProgress() != 1.0 /*|| !noLoadingProgress*/) {
            loadProgress.setProgress(pn.getProgress() / 2);
                /*if (pn.getProgress() > 0) {
                noLoadingProgress = false;
                }*/
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        //ignore, hide after application signals it is ready
        System.out.println("PreloaderFx:::handleStateChangeNotification(); state = " + evt.getType());

    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
            //expect application to send us progress notifications
            //with progress ranging from 0 to 1.0
            double v = ((ProgressNotification) pn).getProgress();
            System.out.println("PreloaderFx::handleApplicationNotification(); progress = " + v);
            //if (!noLoadingProgress) {
            //if we were receiving loading progress notifications
            //then progress is already at 50%.
            //Rescale application progress to start from 50%
            v = 0.5 + v / 2;
            //}
            loadProgress.setProgress(v);
        } else if (pn instanceof StateChangeNotification) {
            System.out.println("PreloaderFx::handleApplicationNotification(); state = " + ((StateChangeNotification) pn).getType());
            //hide after get any state update from application
            FadeTransition fadeSplash = new FadeTransition(Duration.seconds(0.6), splashLayout);
            fadeSplash.setFromValue(1.0);
            fadeSplash.setToValue(0.0);
            fadeSplash.setOnFinished(actionEvent -> stage.hide());
            fadeSplash.play();
            //stage.hide();
        }
    }
}