import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Represents level 1 of the game.
 * Extends the Levels class.
 */
public class level1 extends Levels {

    private static final String[] blackDuckImages = {
            "assets/duck_black/1.png",
            "assets/duck_black/2.png",
            "assets/duck_black/3.png",
            "assets/duck_black/4.png",
            "assets/duck_black/5.png",
            "assets/duck_black/6.png",
            "assets/duck_black/7.png",
            "assets/duck_black/8.png"
    };

    /**
     * Constructs a level1 object.
     * Initializes the level with the provided parameters.
     *
     * @param imageView   The ImageView for the background image.
     * @param foreground  The ImageView for the foreground image.
     * @param crossHair   The ImageView for the crosshair image.
     * @param window      The Stage for the game window.
     * @param scale       The scale factor for resizing.
     */
    public level1(ImageView imageView, ImageView foreground, ImageView crossHair, Stage window, double scale) {
        super(imageView, foreground, crossHair, window, scale, 3,1,new Duck[]{
                new Duck(256 * scale, 240 * scale, 27 * scale, 31 * scale, 3 * scale,  Duration.seconds(1), blackDuckImages, 7 * scale, false,70*scale,100*scale)
        });
    }
}