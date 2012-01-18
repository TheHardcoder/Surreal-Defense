package surrealdefense.tools;

import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import surrealdefense.main.Defaults;

/**
 *
 * @author D056866
 */
public class ScreenTools {
    private static BufferedImage defaultGradient;
    private static BufferedImage background;
    
    public static BufferedImage getDefaultGradient(){
        if (defaultGradient != null)
            return defaultGradient;
        else {
            defaultGradient = new BufferedImage(Defaults.windowWidth, Defaults.windowHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = defaultGradient.createGraphics();
            g.setPaint(new GradientPaint(0,0, Defaults.backgroundColor1, defaultGradient.getWidth(),defaultGradient.getHeight(), Defaults.backgroundColor2));
            g.fillRect(0, 0, defaultGradient.getWidth(), defaultGradient.getHeight());
            return defaultGradient;
        }
    }
    
    public static BufferedImage getDefaultBackground(){
        if (background != null)
            return background;
        else {
        	background = new BufferedImage((int) (Defaults.windowWidth), (int) (Defaults.windowHeight), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = background.createGraphics();
            g.setPaint(new GradientPaint(20,20, Defaults.backgroundColor2, background.getWidth()-40,background.getHeight()-40, Defaults.backgroundColor1));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g.fillRoundRect(20, 20, background.getWidth()-40, background.getHeight()-40,50,50);
            return background;
        }
    }
}
