package org.icroco.boot.javafx.scenario.req;

import org.icroco.boot.javafx.MainPane;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;

@FXMLController
public class ScenarioController {

	@Autowired
    MainPane starter;
	
    public void topButtonClicked() {
        System.out.println("Du hast den topButton geklickt!");
    }
    
    public void clickMiddleButton() {
        System.out.println("MiddleButton!");
        System.out.println("ApplicationHash: " + starter.hashCode());
    }
}
