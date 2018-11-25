package Utility;

import Enumeration.SceneSelection;
import Interface.QuantitySelectionCallback;
import Model.CartItem;
import Model.ProductItem;
import Scenes.SceneController.PaySceneController;
import Scenes.SceneController.QuantitySelectionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * <p>This is the scene launcher class which launch target scenes</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class SceneLauncher
{
    /**
     * Launch target scene
     * @param path The FXML file path
     * @param title The window title
     */
    public void launch(String path, String title)
    {
        try{

            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Launch target scene
     * @param path The FXML file path
     * @param title The window title
     * @param productItem The target {@link ProductItem} object
     * @param quantitySelectionCallback The target {@link QuantitySelectionCallback} interface callback
     * @param sceneSelection The selected scene selection
     */
    public void launch(String path, String title, ProductItem productItem, QuantitySelectionCallback quantitySelectionCallback, SceneSelection sceneSelection)
    {
        try{

            FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(sceneLoader.load()));


            switch (sceneSelection)
            {
                case QUANTITY_SCENE:
                    QuantitySelectionController quantitySelectionController = sceneLoader.getController();
                    quantitySelectionController.initializeData(productItem,quantitySelectionCallback);
                    break;
                default: break;
            }

            stage.show();

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Launch target scene
     * @param path The FXML file path
     * @param title The window title
     * @param cartItemList The target {@link CartItem} object list
     * @param sceneSelection The selected scene selection
     */
    public void launch(String path, String title, List<CartItem> cartItemList, SceneSelection sceneSelection)
    {
        try{
            FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(sceneLoader.load()));

            switch (sceneSelection)
            {
                case PAY_SCENE:

                    PaySceneController paySceneController = sceneLoader.getController();
                    paySceneController.setPurchaseData(cartItemList);
                    break;
                default: break;
            }

            stage.show();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
