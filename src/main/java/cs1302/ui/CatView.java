package cs1302.ui;

import cs1302.RequestManager;
import cs1302.models.cat_api.CatResponse;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class CatView extends VBox {

    private final Toolbar toolbar;
    private final RequestManager<CatResponse[]> requestManager;
    private final ListView<Label> content;

    public CatView() {
        this.content = new ListView<>();
        this.toolbar = new Toolbar();
        this.requestManager = new RequestManager<>(CatResponse[].class, query -> {
            String base = "https://api.thecatapi.com/v1/breeds?attach_breed=";
            String api = "&apikey=d3f76fd6-6581-4fce-a19d-ad987a2b9c76";
            return base + query + api;
        });
        getChildren().addAll(this.toolbar, content);
    }

    public CatView init() {

        getToolbar().getGo().setOnAction(event -> {
            String query = getToolbar().getSearch().getText();
            if (getRequestManager().newQuery(query)) {
                try {
                    Optional<CatResponse[]> result = getRequestManager().fetch(query);
                    result.ifPresent(catResponses -> {
                        if (catResponses.length > 0) {
                            Platform.runLater(() -> content.getItems().clear());
                            for (int i = catResponses.length - 1; i >= 0; i--) {
                                Label label = new Label(catResponses[i].toString());
                                Platform.runLater(() -> content.getItems().add(label));
                            }
                        } else {
                            showAlertDialog("No breeds found!: " + query);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return this;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public RequestManager<CatResponse[]> getRequestManager() {
        return requestManager;
    }

    private void showAlertDialog(String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cat!");
            alert.setHeaderText("Oh no!");
            alert.setContentText(content);
            alert.setResizable(true);
            alert.showAndWait();
        });
    }
}

