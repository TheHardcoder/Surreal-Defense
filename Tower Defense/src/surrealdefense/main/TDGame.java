package surrealdefense.main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import surrealdefense.screens.AbstractScreen;
import surrealdefense.screens.MainScreen;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class TDGame extends Game {
	public static TDGame game;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Defaults.loadProperties();
        game = new TDGame();
        GameLoader loader = new GameLoader();
        loader.setup(game, new Dimension(Defaults.windowWidth, Defaults.windowHeight), Defaults.fullscreen);
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
