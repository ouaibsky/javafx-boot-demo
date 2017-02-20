package org.icroco.boot.javafx.req;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.felixroske.jfxsupport.FXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.icroco.boot.javafx.MainPane;
import org.icroco.boot.javafx.config.Leg;
import org.icroco.boot.javafx.config.Product;
import org.icroco.boot.javafx.config.Request;
import org.icroco.boot.javafx.pref.UserPref;

import javax.inject.Inject;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;

@FXMLController
public class RfqSearchController {

	@Inject
	MainPane starter;

	@Inject
	UserPref userPref;

	@FXML
	CustomTextField idTextField;

	@FXML
	TreeTableView<JsonNode> treeTableView;

	@FXML
	ScrollPane scrollPane;
	private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

	@FXML
	public void initialize() {
		// final Glyph value =
		// fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY);
		final Text value = FontAwesomeIconFactory.get()
				.createIcon(FontAwesomeIcon.SEARCH); // .color(Color.LIGHTGRAY);
		ComboBox<String> cb = new ComboBox<>(
				FXCollections.observableArrayList("Id", "Uuid"));
		cb.setStyle(
				"-fx-opacity: 1; -fx-text-fill: blue; -fx-background-color: transparent;    -fx-padding: 0 0 0 0;"
						+ "    -fx-border-insets: 0 0 0 0;"
						+ "    -fx-font: 12px \"Arial\" ; -fx-font-style: italic;");
		// text4.setFont(Font.font("Arial", FontWeight.THIN, FontPosture.ITALIC, 16));
		// cb.setMaxWidth(50);
		cb.getSelectionModel().select(0);
		// ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(
		// "BizId",
		// "UUID"
		// ));
		// bizIdTextField.setLeft(cb);
		// uuidTextField.setRight(fontAwesome.create(FontAwesome.Glyph.SEARCH).color(Color.LIGHTGRAY));
		idTextField.setRight(cb);
		idTextField.setLeft(value);
		// TreeTableColumn<JsonNode, String> treeTableColumn = new
		// TreeTableColumn<>("Foo");
		// treeTableView.setRoot(TreeItem);
		// treeTableColumn.set
		// treeTableView.setTreeColumn();
		scrollPane.setContent(buildJsonTreeTableView());
		// scrollPane.setContent(buildFileBrowserTreeTableView());

	}

	public void clickMiddleButton(ActionEvent actionEvent) {
		System.out.println(UUID.randomUUID());
	}

	public void topButtonClicked(ActionEvent actionEvent) {
	}

	private TreeTableView buildJsonTreeTableView() {
		Set<Leg> legs = new TreeSet<>(Arrays.asList(Leg.builder().id(1).qty(10.0).way('B')
				.product(Product.builder().underlyingId("SG").build()).build()));
		Request req = Request.builder().bizId(123).uuid(UUID.randomUUID())
				.salesName("foo").counterPart("Foo")
				.legs(legs)
				.build();
		final JsonNode pathItem = new ObjectMapper().valueToTree(req);
		JsonNodeTreeItem root = JsonNodeTreeItem.createNode(pathItem);

		final TreeTableView<JsonNode> treeTableView = new TreeTableView<>();
		treeTableView.setShowRoot(true);
		treeTableView.setRoot(root);

		TreeTableColumn<JsonNode, String> keyColumn = new TreeTableColumn<>("Key");
		keyColumn.setId("keyC");

		keyColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<JsonNode, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<JsonNode, String> p) {
						JsonNodeTreeItem item = (JsonNodeTreeItem) p.getValue();
						return new ReadOnlyObjectWrapper<String>(item.name);
					}
				});
		keyColumn.setPrefWidth(200);
		TreeTableColumn<JsonNode, String> valueColumn = new TreeTableColumn<>("Value");
		valueColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<JsonNode, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<JsonNode, String> p) {
						JsonNodeTreeItem item = (JsonNodeTreeItem) p.getValue();
						if (item.getValue() == null) {
							System.out.println("Error null node");
							return new ReadOnlyObjectWrapper<String>("oups");
						}
						if (item.getValue().size() > 0) {
						    return new SimpleStringProperty("-");
                        }
						return new ReadOnlyObjectWrapper<String>(
								item.getValue().asText());
					}
				});
		valueColumn.setPrefWidth(200);
        valueColumn.setId("valueC");


        treeTableView.getColumns().setAll(keyColumn, valueColumn);

		root.setExpanded(true);
		return treeTableView;
	}

	private TreeTableView buildFileBrowserTreeTableView() {
		TreeItem<File> root = createNode(new File("/"));
		root.setExpanded(true);

		final TreeTableView<File> treeTableView = new TreeTableView<File>();
		treeTableView.setShowRoot(true);
		treeTableView.setRoot(root);

		// --- name column
		TreeTableColumn<File, String> nameColumn = new TreeTableColumn<File, String>(
				"Name");
		nameColumn.setPrefWidth(300);
		nameColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<File, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<File, String> p) {
						File f = p.getValue().getValue();
						String text = f.getParentFile() == null ? "/" : f.getName();
						return new ReadOnlyObjectWrapper<String>(text);
					}
				});

		// --- size column
		TreeTableColumn<File, File> sizeColumn = new TreeTableColumn<File, File>("Size");
		sizeColumn.setPrefWidth(100);
		sizeColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<File, File>, ObservableValue<File>>() {
					@Override
					public ObservableValue<File> call(
							TreeTableColumn.CellDataFeatures<File, File> p) {
						return new ReadOnlyObjectWrapper<File>(p.getValue().getValue());
					}
				});
		sizeColumn.setCellFactory(
				new Callback<TreeTableColumn<File, File>, TreeTableCell<File, File>>() {
					@Override
					public TreeTableCell<File, File> call(
							final TreeTableColumn<File, File> p) {
						return new TreeTableCell<File, File>() {
							@Override
							protected void updateItem(File item, boolean empty) {
								super.updateItem(item, empty);

								TreeTableView treeTable = p.getTreeTableView();

								// if the File is a directory, it has no size...
								if (getIndex() >= treeTable.getExpandedItemCount()) {// impl_getTreeItemCount())
																						// {
									setText(null);
								}
								else {
									TreeItem<File> treeItem = treeTable
											.getTreeItem(getIndex());
									if (item == null || empty || treeItem == null
											|| treeItem.getValue() == null
											|| treeItem.getValue().isDirectory()) {
										setText(null);
									}
									else {
										setText(NumberFormat.getInstance()
												.format(item.length()) + " KB");
									}
								}
							}
						};
					}
				});
		sizeColumn.setComparator(new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				long s1 = f1.isDirectory() ? 0 : f1.length();
				long s2 = f2.isDirectory() ? 0 : f2.length();
				long result = s1 - s2;
				if (result < 0) {
					return -1;
				}
				else if (result == 0) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});

		// --- modified column
		TreeTableColumn<File, Date> lastModifiedColumn = new TreeTableColumn<File, Date>(
				"Last Modified");
		lastModifiedColumn.setPrefWidth(130);
		lastModifiedColumn.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<File, Date>, ObservableValue<Date>>() {
					@Override
					public ObservableValue<Date> call(
							TreeTableColumn.CellDataFeatures<File, Date> p) {
						return new ReadOnlyObjectWrapper<Date>(
								new Date(p.getValue().getValue().lastModified()));
					}
				});
		lastModifiedColumn.setCellFactory(
				new Callback<TreeTableColumn<File, Date>, TreeTableCell<File, Date>>() {
					@Override
					public TreeTableCell<File, Date> call(TreeTableColumn<File, Date> p) {
						return new TreeTableCell<File, Date>() {
							@Override
							protected void updateItem(Date item, boolean empty) {
								super.updateItem(item, empty);

								if (item == null || empty) {
									setText(null);
								}
								else {
									setText(DateFormat.getDateInstance().format(item));
								}
							}
						};
					}
				});

		treeTableView.getColumns().setAll(nameColumn, sizeColumn, lastModifiedColumn);

		return treeTableView;
	}

	private TreeItem<File> createNode(final File f) {
		final TreeItem<File> node = new TreeItem<File>(f) {
			private boolean isLeaf;
			private boolean isFirstTimeChildren = true;
			private boolean isFirstTimeLeaf = true;

			@Override
			public ObservableList<TreeItem<File>> getChildren() {
				if (isFirstTimeChildren) {
					isFirstTimeChildren = false;
					super.getChildren().setAll(buildChildren(this));
				}
				return super.getChildren();
			}

			@Override
			public boolean isLeaf() {
				if (isFirstTimeLeaf) {
					isFirstTimeLeaf = false;
					File f = (File) getValue();
					isLeaf = f.isFile();
				}

				return isLeaf;
			}
		};
		return node;
	}

	private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
		File f = (File) TreeItem.getValue();
		if (f != null && f.isDirectory()) {
			File[] files = f.listFiles();
			if (files != null) {
				ObservableList<TreeItem<File>> children = FXCollections
						.observableArrayList();

				for (File childFile : files) {
					children.add(createNode(childFile));
				}

				return children;
			}
		}

		return FXCollections.emptyObservableList();
	}

    private void expandcollapseTreeView(TreeItem<?> item, boolean expandOrCollapse){
        if(item != null && !item.isLeaf()){
            item.setExpanded(expandOrCollapse);
            for(TreeItem<?> child:item.getChildren()){
                expandcollapseTreeView(child, expandOrCollapse);
            }
        }
    }
}
