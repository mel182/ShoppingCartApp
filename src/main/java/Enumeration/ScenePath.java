package Enumeration;

/**
 * <p>This is enumeration class that contains the scene files path.</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public enum ScenePath
{
    QUANTITY_SELECTION_SCENE("/quantity_selection_scene.fxml"),
    PAY_SCENE("/pay_scene.fxml"),
    VIEW_PRODUCT_LIST("/view_product_scene.fxml"),
    ADD_NEW_PRODUCT_SCENE("/add_product_scene.fxml"),
    ABOUT_SCENE("/about_scene.fxml"),
    MAIN_SCENE("/main_scene.fxml");

    private final String path;

    /**
     * constructor
     * @param path The target scene FXML file path
     */
    ScenePath(final String path)
    {
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return path;
    }
}
