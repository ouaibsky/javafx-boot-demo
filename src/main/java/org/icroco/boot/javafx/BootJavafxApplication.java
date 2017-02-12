package org.icroco.boot.javafx;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootJavafxApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
		launchApp(BootJavafxApplication.class, MainPane.class, args);
	}
}
