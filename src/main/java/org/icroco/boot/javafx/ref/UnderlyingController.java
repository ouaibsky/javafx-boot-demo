package org.icroco.boot.javafx.ref;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.icroco.boot.javafx.MainPane;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class UnderlyingController {

	@Autowired
    MainPane starter;


    @FXML
    public void initialize() {
        System.out.println("UnderlyingView initializing");
    }

    public void topButtonClicked(ActionEvent actionEvent) {
    }

    public void clickMiddleButton(ActionEvent actionEvent) {
    }
}
