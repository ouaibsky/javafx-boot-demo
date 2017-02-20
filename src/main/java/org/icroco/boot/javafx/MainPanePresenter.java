package org.icroco.boot.javafx;

import de.felixroske.jfxsupport.FXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.PropertySheet.Item;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.TaskProgressView;
import org.controlsfx.property.BeanProperty;
import org.icroco.boot.javafx.config.MyConfig;
import org.icroco.boot.javafx.pref.UserPref;
import org.icroco.boot.javafx.pref.UserPrefBeanInfo;
import org.icroco.boot.javafx.ref.UnderlyingView;
import org.icroco.boot.javafx.req.RfqSearchView;
import org.icroco.boot.javafx.scenario.ScenarioView;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;

@FXMLController
@Slf4j
@AutoConfigureAfter(MyConfig.class)
public class MainPanePresenter {
    @Value("${foo.bar}")
    private String name;

    @Inject
    @Lazy
    UserPref userPref;

    @Inject
    UnderlyingView underlyingView;

    @Inject
    RfqSearchView rfqSearchView;

    @Inject
    ScenarioView scenarioView;

	@FXML
	StatusBar topStatusBar;

    @FXML
    StatusBar bottomStatusBar;

    @FXML
    Accordion accordion;

    @FXML
    VBox actionMenu;

    PopOver popOver = new PopOver();
    private PropertySheet propertySheet = new PropertySheet();

    @FXML
    private StackPane pane;
    //private ScrollPane mainScrollPane;

    @FXML
    public void initialize() {
        log.info("MainPanePresenter --> initialize");
        final FontAwesomeIconFactory fontAwesomeIconFactory = FontAwesomeIconFactory.get();
        final Label gear = new Label("", fontAwesomeIconFactory.createIcon(FontAwesomeIcon.GEAR));
        gear.setMaxHeight(Double.MAX_VALUE);

        //final Label gear = new Label("", new Glyph("FontAwesome", FontAwesome.Glyph.GEAR));
        //final Label hamburger = new Label("", new Glyph("FontAwesome", FontAwesome.Glyph.BARS));
        final Label hamburger = new Label("", fontAwesomeIconFactory.createIcon(FontAwesomeIcon.BARS));
        hamburger.setMaxHeight(Double.MAX_VALUE);

        Button test = new Button("test");
        test.setOnMouseClicked(e -> popOver.hide());

//        Platform.runLater(() -> {
            ObservableList<Item> list = FXCollections.observableArrayList();
            BeanInfo beanInfo = new UserPrefBeanInfo();
            for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
                list.add(new BeanProperty(userPref, p));
            }
            propertySheet.getItems().setAll(list);
    //    });

        //propertySheet.setPrefSize(200, 200);
        popOver.setContentNode(propertySheet);

        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
        gear.setOnMouseClicked(e -> popOver.show(gear));
        hamburger.setOnMouseClicked((MouseEvent e) -> accordion.setVisible(!accordion.isVisible()));


        topStatusBar.getRightItems().add(gear);
        topStatusBar.getLeftItems().add(hamburger);
        findRequestOnClicked(null);
        //mainScrollPane.setContent(underlyingView.getView());

        Label taskLabel = new Label("no task to be executed");
        ProgressIndicator indicator = new ProgressIndicator();
        taskLabel.setMaxHeight(Double.MAX_VALUE);

        bottomStatusBar.getRightItems().add(taskLabel);
        indicator.setProgress(100);
        indicator.setVisible(false);
        bottomStatusBar.getRightItems().add(indicator);
        accordion.setExpandedPane(accordion.getPanes().get(0));

        TaskProgressView<Task<?>> taskView = new TaskProgressView<>();
    }

    protected final <T> T getTargetObject(Object proxy) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (T) ((Advised) proxy).getTargetSource().getTarget();
        } else {
            return (T) proxy;
        }
    }


    public void referentialOnClicked(MouseEvent mouseEvent) {
        log.info("clicked: "+name);
        pane.getChildren().setAll(underlyingView.getView());
    }

    public void findRequestOnClicked(MouseEvent mouseEvent) {

    }

    public void scenarioOnClicked(MouseEvent mouseEvent) {
        pane.getChildren().setAll(scenarioView.getView());
    }

    public void findRequestClicked(MouseEvent mouseEvent) {
        pane.getChildren().setAll(rfqSearchView.getView());
    }

    public void loadScenarioClicked(MouseEvent mouseEvent) {
        pane.getChildren().setAll(scenarioView.getView());
    }
}