package cs1302.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Toolbar extends HBox {

    private final Label searchLabel;
    private final TextField search;
    private final Button go;

    public Toolbar() {
        this.searchLabel = new Label("Search");
        this.search = new TextField();
        this.search.setPromptText("Anything you like...");
        this.go = new Button("Go!");
        getChildren().addAll(this.searchLabel, this.search, this.go);
        setSpacing(10);
        setAlignment(Pos.CENTER);
    }

    public Label getSearchLabel() {
        return searchLabel;
    }

    public TextField getSearch() {
        return search;
    }

    public Button getGo() {
        return go;
    }
}
