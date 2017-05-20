/*
 * Filteration_PreprocessingApp.java
 */

package filteration_preprocessing;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Filteration_PreprocessingApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new Filteration_PreprocessingView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Filteration_PreprocessingApp
     */
    public static Filteration_PreprocessingApp getApplication() {
        return Application.getInstance(Filteration_PreprocessingApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Filteration_PreprocessingApp.class, args);
    }
}
