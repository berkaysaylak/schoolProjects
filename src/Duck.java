import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.File;
/**
 * The Duck class represents a duck object in the Duck Hunt game.
 */
class Duck extends ImageView{
    private double sceneX;
    private double sceneY;
    private double duckX;
    private double duckY;
    private final double SCENE_WIDTH;
    private final double SCENE_HEIGHT;
    private final double DUCK_WIDTH;
    private final double DUCK_HEIGHT;
    private double DUCK_SPEED;
    private final double DUCK_FLAP_DISTANCE;
    private Duration DUCK_FLAP_DURATION;
    private Timeline flapTimeline;
    private Timeline duckMoveTimeline;
    private Rectangle hitArea;
    private Image diagonalUpImage;
    private Image diagonalMiddleImage;
    private Image diagonalDownImage;
    private Image wingUpImage;
    private Image wingMiddleImage;
    private Image wingDownImage;
    private Image hitImage;
    private Image deadImage;
    public MediaPlayer fallSound;
    public boolean canHit=true;
    public boolean levelFinished;
    private double volume=DuckHunt.getVolume();
    public boolean isLevelFinished(){return levelFinished;}
    public void setLevelFinished(boolean levelFinished){
        this.levelFinished=levelFinished;
    }

    public boolean isCanHit() {
        return canHit;
    }

    public void setCanHit(boolean canHit) {
        this.canHit=canHit;
    }
    private boolean isCross;

    /**
     * Creates a new instance of the Duck class.
     *
     * @param SCENE_WIDTH         the width of the game scene
     * @param SCENE_HEIGHT        the height of the game scene
     * @param DUCK_WIDTH          the width of the duck
     * @param DUCK_HEIGHT         the height of the duck
     * @param DUCK_SPEED          the initial speed of the duck
     * @param DUCK_FLAP_DURATION  the duration of one complete flapping animation cycle
     * @param duckImageView       an array of strings containing paths to the duck images
     * @param DUCK_FLAP_DISTANCE  the distance the duck moves vertically during flapping
     * @param isCross             indicates whether the duck follows a diagonal flight path
     * @param sceneX              the initial X position of the duck within the game scene
     * @param sceneY              the initial Y position of the duck within the game scene
     */

    public Duck(double SCENE_WIDTH,double SCENE_HEIGHT,double DUCK_WIDTH,double DUCK_HEIGHT,double DUCK_SPEED,Duration DUCK_FLAP_DURATION,String[] duckImageView,double DUCK_FLAP_DISTANCE,boolean isCross,double sceneX,double sceneY){
        super(new Image(duckImageView[5]));
        this.SCENE_HEIGHT=SCENE_HEIGHT;
        this.SCENE_WIDTH=SCENE_WIDTH;
        this.DUCK_WIDTH=DUCK_WIDTH;
        this.DUCK_HEIGHT=DUCK_HEIGHT;
        this.DUCK_SPEED=DUCK_SPEED;
        this.DUCK_FLAP_DURATION=DUCK_FLAP_DURATION;
        this.DUCK_FLAP_DISTANCE=DUCK_FLAP_DISTANCE;
        this.setFitWidth(this.DUCK_WIDTH);
        this.setFitHeight(this.DUCK_HEIGHT);
        this.setLayoutX(this.SCENE_WIDTH);
        this.setLayoutY(this.SCENE_HEIGHT);
        this.diagonalUpImage=new Image(duckImageView[0]);
        this.diagonalMiddleImage=new Image(duckImageView[1]);
        this.diagonalDownImage=new Image(duckImageView[2]);
        this.wingUpImage=new Image(duckImageView[3]);
        this.wingMiddleImage=new Image(duckImageView[4]);
        this.wingDownImage=new Image(duckImageView[5]);
        this.hitImage=new Image(duckImageView[6]);
        this.deadImage=new Image(duckImageView[7]);
        this.duckX = this.DUCK_SPEED;
        this.sceneX=sceneX;
        this.sceneY=sceneY;
        this.canHit=true;
        this.levelFinished=false;
        this.isCross=isCross;
        Media fall = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
        this.fallSound = new MediaPlayer(fall);
        fallSound.setVolume(volume);
        if(isCross){
            this.duckY=this.DUCK_SPEED;
            this.setScaleY(this.getScaleY()*-1);
            flapTimeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> this.setImage(diagonalDownImage),
                            new KeyValue(this.translateYProperty(), 0)),
                    new KeyFrame(DUCK_FLAP_DURATION.divide(4), event -> this.setImage(diagonalMiddleImage),
                            new KeyValue(this.translateYProperty(), -DUCK_FLAP_DISTANCE / 2)),
                    new KeyFrame(DUCK_FLAP_DURATION.divide(4).multiply(2), event -> this.setImage(diagonalUpImage),
                            new KeyValue(this.translateYProperty(), -DUCK_FLAP_DISTANCE)),
                    new KeyFrame(DUCK_FLAP_DURATION, event -> this.setImage(diagonalDownImage),
                            new KeyValue(this.translateYProperty(), 0))

            );
        }else {
            flapTimeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> this.setImage(wingDownImage),
                            new KeyValue(this.translateYProperty(), 0)),
                    new KeyFrame(DUCK_FLAP_DURATION.divide(4), event -> this.setImage(wingMiddleImage),
                            new KeyValue(this.translateYProperty(), -DUCK_FLAP_DISTANCE / 2)),
                    new KeyFrame(DUCK_FLAP_DURATION.divide(4).multiply(2), event -> this.setImage(wingUpImage),
                            new KeyValue(this.translateYProperty(), -DUCK_FLAP_DISTANCE)),
                    new KeyFrame(DUCK_FLAP_DURATION, event -> this.setImage(wingDownImage),
                            new KeyValue(this.translateYProperty(), 0))
            );
        }
        flapTimeline.setCycleCount(Animation.INDEFINITE);
        duckMoveTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> this.move()));
        duckMoveTimeline.setCycleCount(Timeline.INDEFINITE);
        duckMoveTimeline.play();
        flapTimeline.play();
        hitArea = new Rectangle(DUCK_WIDTH, DUCK_HEIGHT);
    }
    /**
     * Moves the duck within the game scene.
     * The duck changes its direction when it reaches the boundaries of the scene.
     */
    public void move() {
        if (this.sceneX <= 0 || this.sceneX >= SCENE_WIDTH - DUCK_WIDTH) {
            this.duckX *= -1;
            this.setScaleX(this.getScaleX()*-1);
        }
        if (this.sceneY <= 0 || this.sceneY >= SCENE_HEIGHT - DUCK_HEIGHT) {
            this.duckY *= -1;
            this.setScaleY(this.getScaleY()*-1);

        }
        this.sceneX += this.duckX;
        this.sceneY += this.duckY;
        this.setLayoutX(this.sceneX);
        this.setLayoutY(this.sceneY);
    }
    /**
     * Handles the mouse click event on the duck.
     * If the duck is hit, it starts the death animation.
     * @param event the mouse click event
     */
    public void handleMouseClick(MouseEvent event) {
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        Rectangle2D duckBounds = new Rectangle2D(this.getLayoutX()-duckX, this.getLayoutY()-DUCK_FLAP_DISTANCE, DUCK_WIDTH+duckX, DUCK_HEIGHT+DUCK_FLAP_DISTANCE);
        if(duckBounds.contains(mouseX, mouseY)){
            startDuckDeathAnimation();
        }
    }
    /**
     * Starts the duck death animation.
     * The duck falls off the screen and plays a falling sound effect.
     */
    public void startDuckDeathAnimation() {
        if (canHit){
            if(isCross&&this.getScaleY()<0){
                this.setScaleY(this.getScaleY()*-1);
            }
            fallSound.play();
            double targetY = SCENE_HEIGHT;
            flapTimeline.stop();
            duckMoveTimeline.stop();
            KeyFrame duckDeathKeyFrame1 = new KeyFrame(Duration.seconds(0), event -> {
                this.setImage(hitImage);
            });

            KeyFrame duckDeathKeyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> {
                this.setImage(deadImage);
            });
            KeyFrame duckDeathKeyFrame3 = new KeyFrame(Duration.seconds(7), new KeyValue(this.translateYProperty(), targetY));

            Timeline duckDeathTimeline = new Timeline(duckDeathKeyFrame1, duckDeathKeyFrame2, duckDeathKeyFrame3);
            duckDeathTimeline.play();
            canHit= false;
            levelFinished=true;
        }
    }
}