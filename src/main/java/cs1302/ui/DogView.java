package cs1302.ui;

import cs1302.RequestManager;
import cs1302.models.dog_api.DogResponse;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class DogView extends VBox {

    private final Toolbar toolbar;
    private final RequestManager<DogResponse[]> requestManager;
    private ListView<Label> content;

    public DogView() {
        this.content = new ListView<>();
        this.toolbar = new Toolbar();
        this.requestManager = new RequestManager<>(DogResponse[].class, query -> {
            String base = "https://api.thedogapi.com/v1/breeds/search?q=";
            String api = "&apikey=79486bc7-de60-4023-b94c-5b1f88a14897";
            return base + query + api;
        });
        getChildren().addAll(this.toolbar, content);
    }

    public DogView init() {

        getToolbar().getGo().setOnAction(event -> {
            String query = getToolbar().getSearch().getText();
            if (getRequestManager().newQuery(query)) {
                try {
                    Optional<DogResponse[]> result = getRequestManager().fetch(query);
                    result.ifPresent(dogResponse -> {
                        if(dogResponse.length > 0) {
                            Platform.runLater(() -> {
                                content.getItems().clear();
                            });
                            for (int i = dogResponse.length - 1; i >= 0; i--) {
                                Label label = new Label(dogResponse[i].toString());
                                Platform.runLater(() -> {
                                    content.getItems().add(label);
                                });
                            }
                        } else {
                            showAlertDialog("No Dog Breeds Found!: " + query);
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

    public RequestManager<DogResponse[]> getRequestManager() {
        return requestManager;
    }

    private void showAlertDialog(String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dog!");
            alert.setHeaderText("Oh no!");
            alert.setContentText(content);
            alert.setResizable(true);
            alert.showAndWait();
        });
    }
}
