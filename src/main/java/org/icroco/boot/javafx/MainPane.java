package org.icroco.boot.javafx;/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Value;

@FXMLView("/fxml/main.fxml")
public class MainPane  extends AbstractFxmlView {

    @Value("${foo.bar}")
    private String name;

    public MainPane() {
        setTitle("This is a test");
    }

    public void onClicked(MouseEvent mouseEvent) {
        System.out.println("clicked: "+name);
    }

    public void lblClicked(MouseEvent mouseEvent) {
    }
}