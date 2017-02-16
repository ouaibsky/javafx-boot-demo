package org.icroco.boot.javafx;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.StatusBar;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.icroco.boot.javafx.ref.UnderlyingView;
import org.icroco.boot.javafx.req.RfqSearchView;
import org.icroco.boot.javafx.scenario.req.ScenarioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@FXMLController
public class MainPanePresenter {
    @Value("${foo.bar}")
    private String name;

    @Autowired
    UnderlyingView underlyingView;

    @Autowired
    RfqSearchView rfqSearchView;

    @Autowired
    ScenarioView scenarioView;

	@FXML
	StatusBar topStatusBar;

    PopOver popOver = new PopOver();


    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    public void initialize() {
        System.out.println("MainPanePresenter --> initialize");
        final Label gear = new Label("", new Glyph("FontAwesome", FontAwesome.Glyph.GEAR));
        Button test = new Button("test");
        popOver.setContentNode(test);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        gear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                popOver.show(gear);
            }
        });
        topStatusBar.getRightItems().add(gear);
        mainScrollPane.setContent(underlyingView.getView());
    }

    public void referentialOnClicked(MouseEvent mouseEvent) {
        System.out.println("clicked: "+name);
        mainScrollPane.setContent(underlyingView.getView());
    }

    public void findRequestOnClicked(MouseEvent mouseEvent) {
        mainScrollPane.setContent(rfqSearchView.getView());

    }

    public void scenarioOnClicked(MouseEvent mouseEvent) {
        mainScrollPane.setContent(scenarioView.getView());
    }
}