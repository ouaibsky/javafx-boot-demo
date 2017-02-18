package org.icroco.boot.javafx.scenario;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.icroco.boot.javafx.MainPane;

import javax.inject.Inject;

@FXMLController
public class ScenarioController {

	@Inject
	MainPane starter;

	@FXML
    StackPane pane;

//	@FXML
//    CodeArea codeArea;

	@FXML
	public void initialize() {
		CodeArea codeArea = new CodeArea();
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        //codeArea.setPrefSize(500,500);
        //codeArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        final VirtualizedScrollPane<CodeArea> vCodeArea = new VirtualizedScrollPane<>(codeArea);
        pane.getChildren().setAll(vCodeArea);

        //codeAreaVirtualizedScrollPane.getContent()
      //  pane.getChildren().addAll(codeAreaVirtualizedScrollPane);
        //pane.getChildren().addAll(codeArea);
        //pane.
        //pane.getChildren().addAll(codeArea);
        //codeArea.setMaxHeight(Double.MAX_VALUE);
        //codeArea.setMaxWidth(Double.MAX_VALUE);
        //pane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

}
