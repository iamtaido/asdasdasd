package cs1302.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends BorderPane {

    private final DogView dogView;
    private final CatView catView;
    private final Label title;
    private HBox chooseMenu;

    public MainView() {
        this.title = new Label(titleString("dog"));
        this.chooseMenu = new HBox();
        Button chooseCat = new Button("Cat");
        Button chooseDog = new Button("Dogs");
        chooseMenu.setSpacing(20);
        chooseMenu.setMinHeight(60);
        chooseMenu.setAlignment(Pos.CENTER);
        chooseMenu.getChildren().addAll(chooseCat, chooseDog);
        VBox box = new VBox(title, chooseMenu);
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        setTop(box);

        this.catView = new CatView().init();
        this.dogView = new DogView().init();
        setCenter(this.dogView);

        chooseCat.setOnAction(event -> {
            setCenter(null);
            setCenter(catView);
            title.setText(titleString("dog"));
        });

        chooseDog.setOnAction(event -> {
            setCenter(null);
            setCenter(dogView);
            title.setText(titleString("cat"));
        });
    }

    public String titleString(String view) {
        return "Search your favorite " + view + " breeds!";
    }
}
