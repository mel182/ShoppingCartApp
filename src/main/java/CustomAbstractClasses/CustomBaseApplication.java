package CustomAbstractClasses;

import Utility.SceneLauncher;
import javafx.application.Application;

/**
 * <p>This is the custom base application abstract class that will be used within the entire application.</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public abstract class CustomBaseApplication extends Application
{
    /**
     * Launch scene based on the properties provided
     * @param path The scene FXML file path
     * @param title The window title
     */
    protected void launchScene(String path, String title)
    {
        new SceneLauncher().launch(path,title);
    }
}
