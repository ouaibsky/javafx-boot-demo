package org.icroco.boot.javafx.ref;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;
import org.icroco.boot.javafx.MainPane;

import javax.inject.Inject;

@FXMLController
@Slf4j
public class UnderlyingController {

	@Inject
    MainPane starter;


    @FXML
    public void initialize() {
        log.info("UnderlyingView initializing");
    }

    public void topButtonClicked(ActionEvent actionEvent) {
    }

    public void clickMiddleButton(ActionEvent actionEvent) {
    }
}
