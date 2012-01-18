package surrealdefense.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import surrealdefense.screens.AbstractScreen;
import surrealdefense.screens.MainScreen;
import surrealdefense.tools.ColorParser;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class TDGame extends Game {
	public static int windowWidth;
    public static int windowHeight;
    public static boolean fullscreen;
    public static Color backgroundColor1;
    public static Color backgroundColor2;
    public static TDGame game;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        InputStream stream;
        try {
            stream = TDGame.class.getResource("defaults.properties").openStream();
            properties.load(stream);
            stream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TDGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        windowWidth = Integer.parseInt((properties.getProperty("windowWidth") == null) ? "1024" : properties.getProperty("windowWidth"));
        windowHeight = Integer.parseInt((properties.getProperty("windowHeight") == null) ? "768" : properties.getProperty("windowHeight"));
        fullscreen = Boolean.parseBoolean((properties.getProperty("fullscreen") == null) ? "false" : properties.getProperty("fullscreen"));
        backgroundColor1 = ColorParser.parseColor((properties.getProperty("backgroundColor1") == null) ? "100;100;100" : properties.getProperty("backgroundColor1"));
        backgroundColor2 = ColorParser.parseColor((properties.getProperty("backgroundColor2") == null) ? "50;50;50" : properties.getProperty("backgroundColor2"));
        
        game = new TDGame();
        GameLoader loader = new GameLoader();
        loader.setup(game, new Dimension(windowWidth, windowHeight), fullscreen);
        loader.start();
    }
    
    private AbstractScreen currentScreen = new MainScreen();

    @Override
    public void initResources() {
        
    }

    @Override
    public void update(long elapsedTime) {
        if (this.keyPressed(KeyEvent.VK_ESCAPE))
            this.finish();
    }

    @Override
    public void render(Graphics2D g) {
        currentScreen.render(g);
    }
}
