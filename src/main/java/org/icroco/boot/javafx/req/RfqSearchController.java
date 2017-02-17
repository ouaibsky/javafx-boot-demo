package org.icroco.boot.javafx.req;

import de.felixroske.jfxsupport.FXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.icroco.boot.javafx.MainPane;

import javax.inject.Inject;
import java.util.UUID;

@FXMLController
public class RfqSearchController {


    private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

	@Inject
    MainPane starter;

	@FXML
    CustomTextField idTextField;

    @FXML
    public void initialize() {
//        final Glyph value = fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY);
        final Text value = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.SEARCH); //.color(Color.LIGHTGRAY);
        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList("Id", "Uuid"));
        cb.setStyle("-fx-opacity: 1; -fx-text-fill: blue; -fx-background-color: transparent;    -fx-padding: 0 0 0 0;" +
                "    -fx-border-insets: 0 0 0 0;" +
                "    -fx-font: 12px \"Arial\" ; -fx-font-style: italic;");
        //text4.setFont(Font.font("Arial", FontWeight.THIN, FontPosture.ITALIC, 16));
        //cb.setMaxWidth(50);
        cb.getSelectionModel().select(0);
//        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(
//                "BizId",
//                "UUID"
//        ));
//        bizIdTextField.setLeft(cb);
        //uuidTextField.setRight(fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY));
        idTextField.setRight(cb);
        idTextField.setLeft(value);

    }

    public void clickMiddleButton(ActionEvent actionEvent) {
        System.out.println(UUID.randomUUID());
    }

    public void topButtonClicked(ActionEvent actionEvent) {
    }
}
