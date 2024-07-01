import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Interface for managing scene changes in Duck Hunt game.
 */
public interface SceneController {
    /**
     * Changes the scene of the application window.
     *
     * @param scene  The new scene to be set.
     * @param window The application window to set the scene on.
     */
    static void sceneChange(Scene scene, Stage window){
        window.setScene(scene);
        window.show();
    }
}
