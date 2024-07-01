import javafx.animation.*;
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
import javafx.util.Duration;
import java.io.File;
/**
 * The main menu scene for the game.
 * Displays the title screen and handles keyboard events to start or exit the game.
 */

class MainMenu extends Scene{
    private Stage window;
    private double scale;
    private double volume;
    private Scene mainMenuScene;
    public static MediaPlayer titleSound;
    /**
     * Constructs a MainMenu object.
     *
     * @param primaryStage the primary stage of the JavaFX application
     * @param scale        the scale factor for resizing the scene
     * @param volume       the volume level for audio effects
     */
    public MainMenu(Stage primaryStage,double scale,double volume) {
        super(new Pane());
        this.scale=scale;
        this.window = primaryStage;
        this.mainMenuScene = createMainMenuScene();
        setKeyboardEventHandlers();
        this.volume=volume;
    }
    /**
     * Shows the main menu scene.
     * Sets the title, icon, and settings for the stage, and starts the title music.
     */
    public void showMainMenu() {
        window.setTitle("HUBBM Duck Hunt");
        Image icon = new Image("assets/favicon/1.png");
        window.getIcons().add(icon);
        window.setResizable(false);
        window.setScene(mainMenuScene);
        Media media = new Media(new File("assets/effects/Title.mp3").toURI().toString());
        this.titleSound = new MediaPlayer(media);
        titleSound.setVolume(volume);
        titleSound.setCycleCount(MediaPlayer.INDEFINITE);
        titleSound.play();
        window.show();
    }
    /**
     * Creates the main menu scene.
     * Sets the background image and adds the text with animation.
     * @return the main menu scene
     */
    private Scene createMainMenuScene() {
        Pane root = new Pane();
        root.setPrefSize(256 * scale, 240 * scale);
        Image backgroundImage = new Image("assets/favicon/1.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(240 * scale);
        backgroundImageView.setFitWidth(256 * scale);
        Text text = new Text("PRESS ENTER TO START\nPRESS ESC TO EXIT");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(text.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(text.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(2.5), new KeyValue(text.visibleProperty(), true))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 17 * scale));
        text.setFill(Color.ORANGE);
        text.setLayoutX(30 * scale);
        text.setLayoutY(160 * scale);

        root.getChildren().addAll(backgroundImageView, text);

        return new Scene(root, 256 * scale, 240 * scale);
    }
    /**
     * Sets the keyboard event handlers for the main menu scene.
     * Handles ESC key to exit the game and ENTER key to start the game.
     */
    private void setKeyboardEventHandlers() {
        mainMenuScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                titleSound.pause();
                window.close();
            }
            if (event.getCode() == KeyCode.ENTER) {
                SceneController.sceneChange(new scene(window,scale,volume),window);
            }
        });
    }
}