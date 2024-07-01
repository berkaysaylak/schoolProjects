import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents level 5 of the game.
 * Extends the Levels class.
 */
public class level5 extends Levels {
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
    private static final String[] redDuckImages = {
            "assets/duck_red/1.png",
            "assets/duck_red/2.png",
            "assets/duck_red/3.png",
            "assets/duck_red/4.png",
            "assets/duck_red/5.png",
            "assets/duck_red/6.png",
            "assets/duck_red/7.png",
            "assets/duck_red/8.png"
    };
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
     * Constructs a level5 object.
     * Initializes the level with the provided parameters.
     */
    public level5(ImageView imageView, ImageView foreground, ImageView crossHair, Stage window, double scale) {
        super(imageView, foreground, crossHair, window, scale, 9,5,new Duck[]{
                new Duck(256 * scale, 240 * scale, 27 * scale, 31 * scale, 4 * scale,  Duration.seconds(1), blackDuckImages, 7 * scale, false,10*scale,10*scale),
                new Duck(256 * scale, 240 * scale, 27 * scale, 31 * scale, 5 * scale,  Duration.seconds(1), redDuckImages, 7 * scale, true,200*scale,150*scale),
                new Duck(256 * scale, 240 * scale, 27 * scale, 31 * scale, 4 * scale,  Duration.seconds(1), blueDuckImages, 7 * scale, true,200*scale,100*scale)
        });
    }
}