package org.icroco.boot.javafx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.net.URL;
import java.util.ResourceBundle;

@SpringBootApplication
@Import(JacksonAutoConfiguration.class)
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport implements Initializable {

	@Override
	public void start(Stage stage) throws Exception {

		System.out.println("starting");

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Stage is closing: "+we);
			}
		});


		setUserAgentStylesheet(STYLESHEET_MODENA);
		super.start(stage);
	}

	public static void main(String[] args) {
		launchApp(BootJavafxApplication.class, MainPane.class, args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("starting");
	}
}
