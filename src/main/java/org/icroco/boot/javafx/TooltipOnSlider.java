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
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class TooltipOnSlider extends Application {

    private boolean useAxis;
    @Override
    public void start(Stage primaryStage) {
        Slider slider = new Slider(5, 25, 15);
        useAxis = true;
        // force an axis to be used
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(5);

        // slider.setOrientation(Orientation.VERTICAL);
        // hacking around the bugs in a custom skin
        //  slider.setSkin(new MySliderSkin(slider));
        //  slider.setSkin(new XSliderSkin(slider));

        Label label = new Label();
        Popup popup = new Popup();
        popup.getContent().add(label);

        double offset = 30 ;

        slider.setOnMouseMoved(e -> {
            NumberAxis axis = (NumberAxis) slider.lookup(".axis");
            StackPane track = (StackPane) slider.lookup(".track");
            StackPane thumb = (StackPane) slider.lookup(".thumb");
            if (useAxis) {
                // James: use axis to convert value/position
                Point2D locationInAxis = axis.sceneToLocal(e.getSceneX(), e.getSceneY());
                boolean isHorizontal = slider.getOrientation() == Orientation.HORIZONTAL;
                double mouseX = isHorizontal ? locationInAxis.getX() : locationInAxis.getY() ;
                double value = axis.getValueForDisplay(mouseX).doubleValue() ;
                if (value >= slider.getMin() && value <= slider.getMax()) {
                    label.setText("" + value);
                } else {
                    label.setText("Value: ---");
                }

            } else {
                // this can't work because we don't know the internals of the track
                Point2D locationInAxis = track.sceneToLocal(e.getSceneX(), e.getSceneY());
                double mouseX = locationInAxis.getX();
                double trackLength = track.getWidth();
                double percent = mouseX / trackLength;
                double value = slider.getMin() + ((slider.getMax() - slider.getMin()) * percent);
                if (value >= slider.getMin() && value <= slider.getMax()) {
                    label.setText("" + value);
                } else {
                    label.setText("Value: ---");
                }
            }
            popup.setAnchorX(e.getScreenX());
            popup.setAnchorY(e.getScreenY() + offset);
        });

        slider.setOnMouseEntered(e -> popup.show(slider, e.getScreenX(), e.getScreenY() + offset));
        slider.setOnMouseExited(e -> popup.hide());

        Label valueLabel = new Label("empty");
        valueLabel.textProperty().bind(slider.valueProperty().asString());
        BorderPane root = new BorderPane(slider);
        root.setBottom(valueLabel);
        primaryStage.setScene(new Scene(root, 350, 100));
        primaryStage.show();
        primaryStage.setTitle("useAxis: " + useAxis + " mySkin: " + slider.getSkin().getClass().getSimpleName());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(TooltipOnSlider.class
            .getName());
}