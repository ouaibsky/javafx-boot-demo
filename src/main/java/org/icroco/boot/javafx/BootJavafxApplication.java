package org.icroco.boot.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_MODENA);
		super.start(stage);
	}

	public static void main(String[] args) {
		launchApp(BootJavafxApplication.class, MainPane.class, args);
	}

}
