package surrealdefense.main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import surrealdefense.screens.AbstractScreen;
import surrealdefense.screens.MainScreen;
import surrealdefense.tools.ScreenTools;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class TDGame extends Game {
	public static TDGame game;
	
	{
		distribute = false;
	}

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
    private BufferedImage background;

    @Override
    public void initResources() {
    	showCursor();
    	this.bsGraphics.setWindowTitle("Surreal Defense");
    	try {
			this.bsGraphics.setWindowIcon(ImageIO.read(new File("resources/images/icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        background = ScreenTools.getDefaultGradient();
    }

    @Override
    public void update(long elapsedTime) {
        if (this.keyPressed(KeyEvent.VK_ESCAPE))
            this.finish();
    }

    @Override
    public void render(Graphics2D g) {
    	g.drawImage(background, 0, 0, null);
        currentScreen.render(g);
    }
}
