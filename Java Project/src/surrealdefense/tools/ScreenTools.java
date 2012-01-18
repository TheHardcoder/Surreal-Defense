package surrealdefense.tools;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import surrealdefense.main.TDGame;;

/**
 *
 * @author D056866
 */
public class ScreenTools {
    private static BufferedImage background;
    
    public static BufferedImage getDefaultBackground(){
        if (background != null)
            return background;
        else {
            background = new BufferedImage(TDGame.windowWidth, TDGame.windowHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = background.createGraphics();
            g.setPaint(new GradientPaint(0,0, TDGame.backgroundColor1, background.getWidth(),background.getHeight(), TDGame.backgroundColor2));
            g.fillRect(0, 0, background.getWidth(), background.getHeight());
            return background;
        }
    }
}
