package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.main.Defaults;
import surrealdefense.tools.ScreenTools;

/**
 *
 * @author D056866
 */
public abstract class AbstractScreen {
    protected boolean changeScreen;
    protected AbstractScreen nextScreen;
    protected int width, height;
    protected BufferedImage background;
    protected InputListener inputListener;
    
    public AbstractScreen(InputListener inputListener){
        width = Defaults.windowWidth;
        height = Defaults.windowHeight;
        background = ScreenTools.getDefaultBackground();
        this.inputListener = inputListener;
    }
    
    public abstract void renderScreen(Graphics2D g);
    public abstract void update(long elapsedTime);
    
    public AbstractScreen getNextScreen(){
        return nextScreen;
    }
    
    public boolean changeScreen(){
        return changeScreen;
    }
    
    public void render(Graphics2D g){
        g.drawImage(background, 0, 0, null);
        renderScreen(g);
    }
}