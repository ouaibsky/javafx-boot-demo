package org.icroco.boot.javafx;

import javafx.scene.layout.Region;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import insidefx.undecorator.UndecoratorScene;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_MODENA);
		super.start(stage);

		// The Undecorator as a Scene
		final UndecoratorScene undecoratorScene = new UndecoratorScene(stage, (Region)stage.getScene().getRoot());

		// Enable fade transition
		undecoratorScene.setFadeInTransition();

        /*
         * Fade out transition on window closing request
         */
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				we.consume();   // Do not hide yet
				undecoratorScene.setFadeOutTransition();
			}
		});

		stage.setScene(undecoratorScene);

		stage.toFront();
	}

	public static void main(String[] args) {
		launchApp(BootJavafxApplication.class, MainPane.class, args);
	}

}
