import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
/**
 * The scene class for the game level.
 * Displays the background, foreground, and crosshair images.
 * Handles keyboard events for navigation and starting the game.
 */

public class scene extends Scene{
    private Stage window;
    private static double scale;
    private int currentIndex=0;
    private int currentCross=0;
    private static Pane root;
    private static final String[] paths = {
            "assets/background/1.png",
            "assets/background/2.png",
            "assets/background/3.png",
            "assets/background/4.png",
            "assets/background/5.png",
            "assets/background/6.png"
    };
    private static final String[] crossHairs = {
            "assets/crosshair/1.png",
            "assets/crosshair/2.png",
            "assets/crosshair/3.png",
            "assets/crosshair/4.png",
            "assets/crosshair/5.png",
            "assets/crosshair/6.png"
    };
    private static final String[] foregrounds = {
            "assets/foreground/1.png",
            "assets/foreground/2.png",
            "assets/foreground/3.png",
            "assets/foreground/4.png",
            "assets/foreground/5.png",
            "assets/foreground/6.png"
    };
    private MediaPlayer introSound;
    private ImageView imageView;
    private ImageView foreground;
    private static ImageView cross;
    private double volume;
    private boolean choosing;
    private static Pane getRood(){
        root = new Pane();
        return root;
    }
    /**
     * Constructs a scene object for the game level.
     *
     * @param window  the primary stage of the JavaFX application
     * @param scale   the scale factor for resizing the scene
     * @param volume  the volume level for audio effects
     */
    public scene(Stage window,double scale,double volume) {
        super(getRood(), 256*scale, 240*scale);
        Image backgroundImage = new Image(paths[currentIndex]);
        this.imageView = new ImageView(backgroundImage);
        this.imageView.setFitHeight(240 * scale);
        this.imageView.setFitWidth(256 * scale);
        Image foreroundImage = new Image(foregrounds[currentIndex]);
        this.foreground = new ImageView(foreroundImage);
        this.foreground.setFitHeight(240 * scale);
        this.foreground.setFitWidth(256 * scale);
        this.cross = new ImageView(crossHairs[currentCross]);
        this.cross.setLayoutX((this.imageView.getFitWidth()-cross.getFitWidth())/2);
        this.cross.setLayoutY((this.imageView.getFitHeight()-cross.getFitHeight())/2);
        this.cross.setFitWidth(11*scale);
        this.cross.setFitHeight(11*scale);
        this.volume=volume;
        choosing=true;
        Text text = new Text("USE ARROW KEYS TO NAVIGATE\n\tPRESS ENTER TO START\n\t    PRESS ESC TO EXIT");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        text.setFill(Color.ORANGE);
        text.setLayoutX(80 * scale);
        text.setLayoutY(10 * scale);
        root.getChildren().setAll(this.imageView,this.foreground,this.cross,text);
        this.window=window;
        this.scale=scale;
        Media media = new Media(new File("assets/effects/Intro.mp3").toURI().toString());
        this.introSound = new MediaPlayer(media);
        introSound.setVolume(volume);
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                MainMenu.titleSound.pause();
                introSound.pause();
                MainMenu mainMenu = new MainMenu(window,scale,volume);
                mainMenu.showMainMenu();
            }else if (event.getCode() == KeyCode.LEFT && choosing) {
                currentIndex = (currentIndex - 1 + paths.length) % paths.length;
                this.imageView.setImage(new Image(paths[currentIndex]));
                this.foreground.setImage(new Image(foregrounds[currentIndex]));

            } else if (event.getCode() == KeyCode.RIGHT && choosing) {
                currentIndex = (currentIndex + 1) % paths.length;
                this.imageView.setImage(new Image(paths[currentIndex]));
                this.foreground.setImage(new Image(foregrounds[currentIndex]));
            }
            else if (event.getCode() == KeyCode.DOWN && choosing) {
                currentCross = (currentCross - 1 + paths.length) % paths.length;
                this.cross.setImage(new Image(crossHairs[currentCross]));

            } else if (event.getCode() == KeyCode.UP && choosing) {
                currentCross = (currentCross + 1) % paths.length;
                this.cross.setImage(new Image(crossHairs[currentCross]));
            }else if (event.getCode() == KeyCode.ENTER) {
                choosing=false;
                MainMenu.titleSound.pause();
                introSound.play();
                introSound.setOnEndOfMedia(()->SceneController.sceneChange(new level1(imageView,foreground,this.cross,window,scale),window));
            }
        });
    }
}
