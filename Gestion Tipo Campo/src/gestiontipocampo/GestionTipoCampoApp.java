/*
 * GestionTipoCampoApp.java
 */
package gestiontipocampo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class GestionTipoCampoApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        try {
            show(new GestionTipoCampoView(this));
        } catch (InterruptedException ex) {
            Logger.getLogger(GestionTipoCampoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of GestionTipoCampoApp
     */
    public static GestionTipoCampoApp getApplication() {
        return Application.getInstance(GestionTipoCampoApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(GestionTipoCampoApp.class, args);
    }
}
