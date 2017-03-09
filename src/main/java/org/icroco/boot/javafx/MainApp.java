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

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class MainApp extends Application {
    BooleanProperty ready = new SimpleBooleanProperty(false);
Stage mainStage;
    public static void main(String[] args) throws Exception {
        LauncherImpl.launchApplication(MainApp.class, PreloaderFX.class, args);
        //launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("MainApp::init();");
       // notifyPreloader(new Preloader.ProgressNotification(1/11));
        //Thread.sleep(2000);
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        System.out.println("MainApp::start();");
        this.mainStage = initStage;
        System.out.println("initStage: "+initStage);
        longStart();

        ready.addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if (Boolean.TRUE.equals(t1)) {
                Platform.runLater(() -> {
                    System.out.println("MainApp::showMainStage();");
                    showMainStage();
                });
            }
        });
    }

    private void longStart() {
        //simulate long init in background
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 10;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(500);
                    System.out.println("longStart " + i);
                    // Send progress to preloader
                    notifyPreloader(new Preloader.ProgressNotification(((double) i)/max)); //this moves the progress bar of the preloader
                }
                // After init is ready, the app is ready to be shown
                // Do this before hiding the preloader stage to prevent the
                // app from exiting prematurely
                ready.setValue(Boolean.TRUE);

                notifyPreloader(new Preloader.StateChangeNotification(
                        Preloader.StateChangeNotification.Type.BEFORE_START));

                return null;
            }
        };
        new Thread(task).start();
    }

    private void showMainStage() {
        //showing the login window
    }
}
