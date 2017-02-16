package org.icroco.boot.javafx;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.control.HiddenSidesPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.StatusBar;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.icroco.boot.javafx.ref.UnderlyingView;
import org.icroco.boot.javafx.req.RfqSearchView;
import org.icroco.boot.javafx.scenario.req.ScenarioView;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;

@FXMLController
@Slf4j
public class MainPanePresenter {
    @Value("${foo.bar}")
    private String name;

    @Inject
    UnderlyingView underlyingView;

    @Inject
    RfqSearchView rfqSearchView;

    @Inject
    ScenarioView scenarioView;

	@FXML
	StatusBar topStatusBar;

    @FXML
    HiddenSidesPane hiddenPane;

    @FXML
    VBox actionMenu;

    PopOver popOver = new PopOver();

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    public void initialize() {
        log.info("MainPanePresenter --> initialize");
        final Label gear = new Label("", new Glyph("FontAwesome", FontAwesome.Glyph.GEAR));
        final Label hamburger = new Label("", new Glyph("FontAwesome", FontAwesome.Glyph.BARS));
        Button test = new Button("test");
        test.setOnMouseClicked(e -> popOver.hide());
        popOver.setContentNode(test);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        gear.setOnMouseClicked(e -> popOver.show(gear));
        hiddenPane.setTriggerDistance(25);
        hamburger.setOnMouseClicked((MouseEvent e) -> hiddenPane.setPinnedSide(hiddenPane.getPinnedSide() != null ? null : Side.LEFT));
        topStatusBar.getRightItems().add(gear);
        topStatusBar.getLeftItems().add(hamburger);
        mainScrollPane.setContent(underlyingView.getView());
    }

    public void referentialOnClicked(MouseEvent mouseEvent) {
        log.info("clicked: "+name);
        mainScrollPane.setContent(underlyingView.getView());
    }

    public void findRequestOnClicked(MouseEvent mouseEvent) {
        mainScrollPane.setContent(rfqSearchView.getView());

    }

    public void scenarioOnClicked(MouseEvent mouseEvent) {
        mainScrollPane.setContent(scenarioView.getView());
    }
}