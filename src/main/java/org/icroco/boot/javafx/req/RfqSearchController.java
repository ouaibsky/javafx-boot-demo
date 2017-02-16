package org.icroco.boot.javafx.req;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.icroco.boot.javafx.MainPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@FXMLController
public class RfqSearchController {


    private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

	@Autowired
    MainPane starter;

	@FXML
    CustomTextField bizIdTextField;
    @FXML
    CustomTextField uuidTextField;
    @FXML
    public void initialize() {
        final Glyph value = fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY);
        bizIdTextField.setRight(value);
//        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(
//                "BizId",
//                "UUID"
//        ));
//        bizIdTextField.setLeft(cb);
        uuidTextField.setRight(fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY));
    }

    public void clickMiddleButton(ActionEvent actionEvent) {
        System.out.println(UUID.randomUUID());
    }

    public void topButtonClicked(ActionEvent actionEvent) {
    }
}
