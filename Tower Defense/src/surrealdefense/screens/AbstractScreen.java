package surrealdefense.screens;

import ggui.components.Label;
import ggui.main.CManager;
import ggui.main.InputListener;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    protected Font titleFont = new Font("Comic Sans MS", Font.BOLD, 40);
    protected Label title;
    private static BufferedImage fontImage;
    
    public static BufferedImage getFontImage(){
    	if (fontImage != null)
    		return fontImage;
    	else {
    		try {
				fontImage = ImageIO.read(new File("resources/images/font.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    		return fontImage;
    	}
    }
    
    public AbstractScreen(InputListener inputListener, String titleName){
        width = Defaults.windowWidth;
        height = Defaults.windowHeight;
        background = ScreenTools.getDefaultBackground();
        this.inputListener = inputListener;
        cManager = new CManager(inputListener);
        this.title = new Label(0, 30, titleName, null, getFontImage(),true);
        this.title.setCenterWidth(width);
        this.title.setFont(titleFont);
        cManager.add(this.title);
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