import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The DuckHunt class represents the entry point of the Duck Hunt game.
 */
public class DuckHunt extends Application {

    //you may occur an aim problem due to scaling of cross it is still working but hard to shot sometimes
    public double SCALE = 4;
    public static final double VOLUME = 0.025;

    /**
     * Returns the volume of the game.
     * @return The volume of the game
     */
    public static Double getVolume() {
        return VOLUME;
    }

    /**
     * The main method that launches the application.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method that initializes the game's main menu.
     * @param primaryStage The primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        MainMenu mainMenu = new MainMenu(primaryStage, SCALE, VOLUME);
        mainMenu.showMainMenu();
    }
}