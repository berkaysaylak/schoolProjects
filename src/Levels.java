import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * The abstract Levels class represents a base class for different levels in a game.
 * It extends the Scene class from the JavaFX library.
 * Each level consists of an image view, foreground, crosshair, and ducks.
 * The class provides methods for handling mouse clicks, keyboard events, and game progress.
 */
public abstract class Levels extends Scene {
    private Stage window;
    private Pane root;
    private ImageView imageView;
    private ImageView foreground;
    private int levelNo;
    private double scale;
    ImageView crossHair;
    private int ammo;
    private Text ammoText;
    private Text levelText;
    private Text winText1;
    private Text winText2;
    private Text overText1;
    private Text overText2;
    private Text completedText;
    private MediaPlayer levelSound;
    private MediaPlayer overSound;
    private MediaPlayer shotSound;
    private MediaPlayer completedSound;
    private boolean levelChange=false;
    private boolean isExit=false;
    private boolean gameOver;
    private boolean zeroAmmo=false;
    private boolean outOfAmmo=false;
    private ArrayList<Duck> ducks;
    private int duckCount=0;
    private double volume=DuckHunt.getVolume();
    /**
     * Constructs a Levels object with the specified parameters.
     *
     * @param imageView  the image view for the level background
     * @param foreground the image view for the foreground
     * @param crossHair  the image view for the crosshair
     * @param window     the stage window
     * @param scale      the scale factor for the level
     * @param ammo       the initial ammunition count
     * @param levelNo    the level number
     * @param ducks      an array of ducks in the level
     */
    public Levels(ImageView imageView, ImageView foreground, ImageView crossHair, Stage window, double scale, int ammo,int levelNo,Duck[] ducks) {
        super(new Pane(), 256 * scale, 240 * scale);
        root = (Pane) getRoot();
        this.window = window;
        this.imageView = imageView;
        this.foreground = foreground;
        this.ammo = ammo;
        this.scale = scale;
        this.levelNo = levelNo;
        this.ducks = new ArrayList<>(Arrays.asList(ducks));
        this.crossHair=crossHair;
        Cursor cursor = new ImageCursor(crossHair.getImage());
        this.setCursor(cursor);

        levelText = new Text("LEVEL" + this.levelNo);
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 8 * scale));
        levelText.setFill(Color.ORANGE);
        levelText.setLayoutX(100 * scale);
        levelText.setLayoutY(10 * scale);

        ammoText = new Text("AMMO LEFT: " + ammo);
        ammoText.setFont(Font.font("Arial", FontWeight.BOLD, 8 * scale));
        ammoText.setFill(Color.ORANGE);
        ammoText.setLayoutX(195 * scale);
        ammoText.setLayoutY(10 * scale);

        winText1 = new Text("\t\tYOU WIN");
        winText1.setFont(Font.font("Arial", FontWeight.BOLD, 15 * scale));
        winText1.setFill(Color.ORANGE);
        winText1.setLayoutX(20 * scale);
        winText1.setLayoutY(120 * scale);
        winText1.setVisible(false);

        winText2 = new Text("Press ENTER to play next level");
        winText2.setFont(Font.font("Arial", FontWeight.BOLD, 15 * scale));
        winText2.setFill(Color.ORANGE);
        winText2.setLayoutX(20 * scale);
        winText2.setLayoutY(135 * scale);
        winText2.setVisible(false);

        overText1 = new Text("\t\tGAME OVER");
        overText1.setFont(Font.font("Arial", FontWeight.BOLD, 15 * scale));
        overText1.setFill(Color.ORANGE);
        overText1.setLayoutX(20 * scale);
        overText1.setLayoutY(100 * scale);
        overText1.setVisible(false);

        overText2 = new Text("Press ENTER to play again\n\tPress ESC to exit");
        overText2.setFont(Font.font("Arial", FontWeight.BOLD, 15 * scale));
        overText2.setFill(Color.ORANGE);
        overText2.setLayoutX(30 * scale);
        overText2.setLayoutY(115 * scale);
        overText2.setVisible(false);

        completedText = new Text("You have completed the game!");
        completedText.setFont(Font.font("Arial", FontWeight.BOLD, 15 * scale));
        completedText.setFill(Color.ORANGE);
        completedText.setLayoutX(20 * scale);
        completedText.setLayoutY(100 * scale);
        completedText.setVisible(false);

        Media level = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
        levelSound = new MediaPlayer(level);
        levelSound.setVolume(volume);

        Media over = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
        overSound = new MediaPlayer(over);
        overSound.setVolume(volume);

        Media shot = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
        shotSound = new MediaPlayer(shot);
        shotSound.setVolume(volume);

        Media completed = new Media(new File("assets/effects/GameCompleted.mp3").toURI().toString());
        completedSound = new MediaPlayer(completed);
        completedSound.setVolume(volume);

        Pane duckLayer = new Pane();
        duckLayer.getChildren().addAll(ducks);
        Pane textLayer = new Pane();
        textLayer.getChildren().addAll(levelText, ammoText, winText1, winText2, overText1, overText2, completedText);
        root.getChildren().addAll(imageView, duckLayer, foreground, textLayer);
        for (int i = 0; i < ducks.length; i++) {
            this.duckCount++;
        }

        this.setOnMouseClicked(event -> {
            this.ammo--;
            for (Duck duck : ducks) {
                handleMouseClick(event, duck);
            }
        });

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (levelNo == 1 && levelChange) {
                    levelSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level2(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (levelNo == 2 && levelChange) {
                    levelSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level3(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (levelNo == 3 && levelChange) {
                    levelSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level4(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (levelNo == 4 && levelChange) {
                    levelSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level5(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (levelNo == 5 && levelChange) {
                    levelSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level6(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (levelNo == 6 && levelChange) {
                    completedSound.pause();
                    shotSound.pause();
                    for (Duck duck:ducks){
                        duck.fallSound.pause();
                    }
                    SceneController.sceneChange(new level1(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                } else if (gameOver) {
                    completedSound.pause();
                    shotSound.pause();
                    SceneController.sceneChange(new level1(imageView, foreground, crossHair, window, scale), window);
                    levelChange = false;
                }
            } else if (event.getCode() == KeyCode.ESCAPE && isExit) {
                shotSound.pause();
                overSound.pause();
                completedSound.pause();
                for (Duck duck:ducks){
                    duck.fallSound.pause();
                }
                MainMenu mainMenu = new MainMenu(window, scale, volume);
                mainMenu.showMainMenu();

            }
        });
    }
    /**
     * Handles the mouse click event for a duck.
     * If the duck can be hit, plays the shot sound, updates the duck's status,
     * handles the click event for the duck, and checks if the level is finished.
     *
     * @param event the mouse click event
     * @param duck  the duck to handle the click for
     */
    private void handleMouseClick(MouseEvent event,Duck duck) {
            if(duck.isCanHit() && !outOfAmmo){
                if (shotSound.getStatus() == MediaPlayer.Status.PLAYING) {
                    shotSound.stop();
                    shotSound.seek(Duration.ZERO);
                }
                shotSound.play();
                handleClick(duck);
                duck.handleMouseClick(event);
                handleFinish();
            }
    }
    /**
     * Handles the click event for a duck.
     * If there is ammo left, updates the ammo text.
     * If the duck can be hit and there is no ammo left, updates the duck's status and the ammo text.
     *
     * @param duck the duck to handle the click for
     */
    private void handleClick(Duck duck) {
        if (ammo >= 0 && !zeroAmmo) {
            if(ammo==0){
                zeroAmmo=true;
                if(duck.isCanHit()){
                    outOfAmmo=true;
                }
            }
            ammoText.setText("AMMO LEFT: " + ammo);
        } else {
            if (duck.isCanHit()) {
                duck.setCanHit(false);
                duck.setLevelFinished(false);
            }
            ammoText.setText("AMMO LEFT: " + ammo);
        }
    }
    /**
     * Handles the finish state of the level.
     * Checks if all ducks have been hit and updates the game state accordingly.
     * If all ducks are hit and the level is completed, displays the appropriate win message and plays the game completed sound.
     * If ammo is depleted and not all ducks are hit, displays the game over message and plays the game over sound.
     */
    private void handleFinish() {
        boolean allDucksHit=true;
        for (Duck duck : ducks) {
            if (!duck.isLevelFinished()) {
                allDucksHit = false;
                break;
            }
        }
        if (ammo >= 0 && allDucksHit) {
            if(levelNo==6){
                completedSound.play();
                completedText.setVisible(true);
                overText2.setVisible(true);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), new KeyValue(overText2.visibleProperty(), true)),
                        new KeyFrame(Duration.seconds(1.5), new KeyValue(overText2.visibleProperty(), false)),
                        new KeyFrame(Duration.seconds(2.5), new KeyValue(overText2.visibleProperty(), true))
                );
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }else{
                levelSound.play();
                winText1.setVisible(true);
                winText2.setVisible(true);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), new KeyValue(winText2.visibleProperty(), true)),
                        new KeyFrame(Duration.seconds(1.5), new KeyValue(winText2.visibleProperty(), false)),
                        new KeyFrame(Duration.seconds(2.5), new KeyValue(winText2.visibleProperty(), true))
                );
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
            levelChange=true;
            gameOver=false;
            isExit=true;
        } else if (ammo == 0 && !allDucksHit &&outOfAmmo) {
            overSound.play();
            overText1.setVisible(true);
            overText2.setVisible(true);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), new KeyValue(overText2.visibleProperty(), true)),
                    new KeyFrame(Duration.seconds(1.5), new KeyValue(overText2.visibleProperty(), false)),
                    new KeyFrame(Duration.seconds(2.5), new KeyValue(overText2.visibleProperty(), true))
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            gameOver=true;
            isExit=true;
        }
    }
}