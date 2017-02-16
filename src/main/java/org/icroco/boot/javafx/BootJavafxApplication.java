package org.icroco.boot.javafx;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JacksonAutoConfiguration.class)
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport  {

	@Override
	public void start(Stage stage) throws Exception {
		setUserAgentStylesheet(STYLESHEET_MODENA);
		super.start(stage);
	}

	public static void main(String[] args) {
		launchApp(BootJavafxApplication.class, MainPane.class, args);
	}

}
