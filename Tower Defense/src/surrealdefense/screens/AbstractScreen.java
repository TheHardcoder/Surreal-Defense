package surrealdefense.screens;

import ggui.components.Label;
import ggui.main.CManager;
import ggui.main.InputListener;

import java.awt.Font;
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
    protected CManager cManager;
    protected Font titleFont = new Font("Arial", Font.BOLD, 24);
    
    public AbstractScreen(InputListener inputListener){
        width = Defaults.windowWidth;
        height = Defaults.windowHeight;
        background = ScreenTools.getDefaultBackground();
        this.inputListener = inputListener;
        cManager = new CManager(inputListener);
        Label title = new Label(0, 0, "Test");
        title.setFont(titleFont);
        cManager.add(title);
    }
    
    public abstract void renderScreen(Graphics2D g);
    public abstract void updateScreen(long elapsedTime);
    
    public AbstractScreen getNextScreen(){
        return nextScreen;
    }
    
    public boolean changeScreen(){
        return changeScreen;
    }
    
    public void update(long elapsedTime){
    	cManager.update(elapsedTime);
    	updateScreen(elapsedTime);
    }
    
    public void render(Graphics2D g){
        g.drawImage(background, 0, 0, null);
        cManager.render(g);
        renderScreen(g);
    }
}