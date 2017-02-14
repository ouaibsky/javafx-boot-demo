package org.icroco.boot.javafx;

import javafx.scene.layout.Border;
import org.controlsfx.control.StatusBar;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.springframework.beans.factory.annotation.Value;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

@FXMLController
public class MainPaneController {
    @Value("${foo.bar}")
    private String name;

	@FXML
	StatusBar topStatusBar;

    @FXML
    public void initialize() {
        System.out.println("MainPaneController --> initialize");
        final Button gear = new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.GEAR));
        gear.setBorder(Border.EMPTY);
        topStatusBar.getRightItems().add(gear);
    }

    public void onClicked(MouseEvent mouseEvent) {
        System.out.println("clicked: "+name);
    }

    public void lblClicked(MouseEvent mouseEvent) {
    }
}