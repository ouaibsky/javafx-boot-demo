package org.icroco.boot.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javafx.application.Preloader;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
@Slf4j
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport {

	@Override
	public void init() throws Exception {
		log.info("App Before Init");
		super.init();
		log.info("App After Init");
	}

	@Override
	public void start(Stage stage) throws Exception {
		//setUserAgentStylesheet(STYLESHEET_MODENA);
		log.info("App Before start");

		super.start(stage);

//		// The Undecorator as a Scene
//		final UndecoratorScene undecoratorScene = new UndecoratorScene(stage, (Region)stage.getScene().getRoot());
//
//		// Enable fade transition
//		undecoratorScene.setFadeInTransition();
//
//        /*
//         * Fade out transition on window closing request
//         */
//		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent we) {
//				we.consume();   // Do not hide yet
//				undecoratorScene.setFadeOutTransition();
//			}
//		});
//
//		stage.setScene(undecoratorScene);

		log.info("App Before front");

		notifyPreloader(new Preloader.StateChangeNotification(
				Preloader.StateChangeNotification.Type.BEFORE_START));
		stage.toFront();
		log.info("App After stage");
	}

	public static void main(String[] args) {
		launchApp(PreloaderFX.class, BootJavafxApplication.class, MainPane.class, args);
	}

}
