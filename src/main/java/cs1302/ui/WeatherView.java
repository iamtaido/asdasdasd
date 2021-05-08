package cs1302.ui;

import cs1302.RequestManager;
import cs1302.models.mapbox_api.MapBoxResponse;
import cs1302.models.weather_api.DarkSkyResponse;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class WeatherView extends VBox {

    private final Toolbar toolbar;
    private final RequestManager<MapBoxResponse> mapBoxResponseRequestManager;
    private final RequestManager<DarkSkyResponse> darkSkyResponseRequestManager;
    private ListView<Label> content;

    public WeatherView() {

        this.content = new ListView<>();
        this.toolbar = new Toolbar();

        this.mapBoxResponseRequestManager = new RequestManager<>(MapBoxResponse.class, query -> {
            String base = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
            String api = ".json?access_token=pk.eyJ1IjoiaWFtdGFpZG8iLCJhIjoiY2s2bXp3ZXF4MHZuaTNlcGI4M244MXF5ayJ9.Q0er_8QYEqjoehWKleuo0A";
            return base + query + api;
        });

        this.darkSkyResponseRequestManager = new RequestManager<>(DarkSkyResponse.class, query -> {
            String base = "https://api.darksky.net/forecast/44f56579fc91205bf31b1f1add54bad0/";
            return base + query;
        });

        getChildren().addAll(this.toolbar, content);
    }

    public WeatherView init() {

        getToolbar().getGo().setOnAction(event -> {
            String query = getToolbar().getSearch().getText();
            System.out.println(query);
            if (getMapBoxResponseRequestManager().newQuery(query)) {
                try {
                    Optional<MapBoxResponse> result = getMapBoxResponseRequestManager().fetch(query);
                    result.ifPresent(darkSkyResponse -> {
                        Double x = darkSkyResponse.getFeatures().get(0).getCenter().get(0);
                        Double y = darkSkyResponse.getFeatures().get(0).getCenter().get(1);
                        System.out.println(x);
                        System.out.println(y);

                        try {
                            Optional<DarkSkyResponse> weatherResponse = getDarkSkyResponseRequestManager().fetch(x + "," + y);
                            weatherResponse.ifPresent(response -> {
                                content.getItems().clear();
                                DarkSkyResponse.Currently currently = response.getCurrently();
                                content.getItems().add(makeLabel("Location: " + query));
                                content.getItems().add(makeLabel("Temperature: " + currently.getTemperature()));
                                content.getItems().add(makeLabel("Status: " + currently.getIcon()));
                                content.getItems().add(makeLabel("Wind Speed: " + currently.getWindSpeed()));
                                content.getItems().add(makeLabel("Rain Probability: " + currently.getPrecipProbability()));
                                content.getItems().add(makeLabel("Thanks DarkSky and MapBox API for the Data :)"));
                            });
                        } catch (IOException e) {
                            content.getItems().clear();
                            content.getItems().add(makeLabel("Location Not Found!! Weather can not be determined"));
                        }

                    });

                } catch (IOException e) {
                    content.getItems().clear();
                    content.getItems().add(makeLabel("Location Not Found!! Weather can not be determined"));
                }
            }
        });
        return this;
    }

    public Label makeLabel(String content) {
        return new Label(content);
    }

    public RequestManager<MapBoxResponse> getMapBoxResponseRequestManager() {
        return mapBoxResponseRequestManager;
    }

    public RequestManager<DarkSkyResponse> getDarkSkyResponseRequestManager() {
        return darkSkyResponseRequestManager;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
