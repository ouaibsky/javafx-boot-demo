package org.icroco.boot.javafx;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sun.javafx.application.LauncherImpl;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Felix Roske 
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {

	private static String[] savedArgs;

	private static Class<? extends AbstractFxmlView> savedInitialView;

	private ConfigurableApplicationContext applicationContext;

	private Stage stage;
	private Scene scene; 

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(getClass(), savedArgs);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		showView(savedInitialView);
	}

	public void showView(Class<? extends AbstractFxmlView> newView) {
		AbstractFxmlView view = applicationContext.getBean(newView);
		stage.titleProperty().bind(view.titleProperty());
		if (scene == null) {
			scene = new Scene(view.getView());
		}
		else {  
			scene.setRoot(view.getView());
		}
		
		// stage.setTitle(windowTitle);
		stage.setScene(scene);
//		stage.setResizable(true);
//		stage.centerOnScreen();
		//stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();

		scene.setFill(Color.TRANSPARENT); //Makes scene background transparent
		FadeTransition fadeSplash = new FadeTransition(Duration.seconds(2), scene.getRoot());
		fadeSplash.setFromValue(0.0);
		fadeSplash.setToValue(1.0);
		//fadeSplash.setOnFinished(actionEvent -> stage.initStyle(StageStyle.DECORATED));
		fadeSplash.play();
	}

	@Override
	public void stop() throws Exception {

		super.stop();
		applicationContext.close();
	}

	protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass,
			Class<? extends AbstractFxmlView> view, String[] args) {
		savedInitialView = view;
		savedArgs = args;

		Application.launch(appClass, args);
	}

	protected static void launchApp(Class<? extends Preloader> preloader,
			Class<? extends AbstractJavaFxApplicationSupport> appClass,
									Class<? extends AbstractFxmlView> view, String[] args) {
		savedInitialView = view;
		savedArgs = args;

		LauncherImpl.launchApplication(appClass, preloader, args);
	}


}
