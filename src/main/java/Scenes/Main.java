package Scenes;

import CustomAbstractClasses.CustomBaseApplication;
import javafx.stage.Stage;

/**
 * <p>This is the main class which inherit the {@link CustomBaseApplication}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class Main extends CustomBaseApplication {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage)
    {
        launchScene("/main_scene.fxml","Shopping cart app");
    }

    /**
     * {@inheritDoc}
     */
    public static void main(String[] args) {
        launch(args);
    }
}
