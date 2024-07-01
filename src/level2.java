import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
public class level2 extends Levels {
    /**
     * Represents level 2 of the game.
     * Extends the Levels class.
     */
    private static final String[] blueDuckImages = {
            "assets/duck_blue/1.png",
            "assets/duck_blue/2.png",
            "assets/duck_blue/3.png",
            "assets/duck_blue/4.png",
            "assets/duck_blue/5.png",
            "assets/duck_blue/6.png",
            "assets/duck_blue/7.png",
            "assets/duck_blue/8.png"
    };
    /**
     * Constructs a level2 object.
     * Initializes the level with the provided parameters.
     */
    public level2(ImageView imageView, ImageView foreground, ImageView crossHair, Stage window, double scale) {
        super(imageView, foreground, crossHair, window, scale, 3,2,new Duck[]{
                new Duck(256 * scale, 240 * scale, 27 * scale, 31 * scale, 3 * scale,  Duration.seconds(1), blueDuckImages, 7 * scale, true,30*scale,30*scale)
        });
    }
}