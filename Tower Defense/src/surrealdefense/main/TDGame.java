package surrealdefense.main;

import ggui.main.InputListener;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

public class TDGame extends Game implements InputListener {
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
    
    private AbstractScreen currentScreen = new MainScreen(this);
    private BufferedImage background;

    @Override
    public void initResources() {
    	showCursor();
    	this.bsGraphics.setWindowTitle("Surreal Defense");
    	try {
			this.bsGraphics.setWindowIcon(ImageIO.read(new File("resources/images/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        background = ScreenTools.getDefaultGradient();
    }

    @Override
    public void update(long elapsedTime) {
    	currentScreen.update(elapsedTime);
        if (this.keyPressed(KeyEvent.VK_ESCAPE))
            this.finish();
        if (currentScreen.changeScreen()){
    		currentScreen = currentScreen.getNextScreen();
    		bsTimer.setFPS(100);
    		bsTimer.refresh();
    		if (currentScreen == null){
    			this.finish();
    		}
    	}
    }

    @Override
    public void render(Graphics2D g) {
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.drawImage(background, 0, 0, null);
        currentScreen.render(g);
    }

	@Override
	public boolean isMousePressed(int button) {
		return bsInput.isMousePressed(button);
	}

	@Override
	public boolean isMouseReleased(int button) {
		return bsInput.isMouseReleased(button);
	}

	@Override
	public int getMousePressed() {
		return bsInput.getMousePressed();
	}

	@Override
	public int getMouseReleased() {
		return bsInput.getMouseReleased();
	}

	@Override
	public boolean isKeyDown(int keyCode) {
		return bsInput.isKeyDown(keyCode);
	}

	@Override
	public boolean isKeyPressed(int keyCode) {
		return bsInput.isKeyPressed(keyCode);
	}

	@Override
	public boolean isKeyReleased(int keyCode) {
		return bsInput.isKeyReleased(keyCode);
	}

	@Override
	public int getKeyPressed() {
		return bsInput.getKeyPressed();
	}

	@Override
	public int getKeyReleased() {
		return bsInput.getKeyReleased();
	}

	@SuppressWarnings("static-access")
	@Override
	public int getValueNoMouse() {
		return bsInput.NO_BUTTON;
	}

	@SuppressWarnings("static-access")
	@Override
	public int getValueNoKey() {
		return bsInput.NO_KEY;
	}
}
