package org.icroco.boot.javafx.req;/*
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

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ArrayNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class JsonNodeTreeItem extends TreeItem<JsonNode> {
    String name;
    private boolean isLeaf = false;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeft = true;

    public JsonNodeTreeItem(String name, JsonNode value) {
        super(value);
        this.name = name;
        //System.out.println("key: "+name+" value: "+value+" leaf:"+value.size());
    }


    public static JsonNodeTreeItem createNode(String name, JsonNode pathItem) {
        return new JsonNodeTreeItem(name, pathItem);
    }

    public static JsonNodeTreeItem createNode(JsonNode pathItem) {
        return new JsonNodeTreeItem("/", pathItem);
    }

    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeft) {
            isFirstTimeLeft = false;
            isLeaf = getValue().size() == 0;
        }
        return isLeaf;
    }

    @Override
    public ObservableList<TreeItem<JsonNode>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    private ObservableList<TreeItem<JsonNode>> buildChildren(TreeItem<JsonNode> treeItem) {

        JsonNode path = treeItem.getValue();
        if (path != null && !path.isValueNode()) {
            ObservableList<TreeItem<JsonNode>> childrens = FXCollections.observableArrayList();
            if (path instanceof ArrayNode) {
                for (int i = 0; i < path.size(); i++) {
                    childrens.add(createNode("["+i+"]", path.get(i)));
                }
            } else {
                path.fields().forEachRemaining(j -> childrens.add(createNode(j.getKey(), j.getValue())));
            }
            return childrens;
        }
        return FXCollections.emptyObservableList();
    }

    @Override
    public String toString() {
        return name;
    }
}
