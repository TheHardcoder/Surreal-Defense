package surrealdefense.tools;

import ggui.design.Helper;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import surrealdefense.main.Defaults;
import surrealdefense.map.MapDefaults;

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
        	background = createBackground(Defaults.windowWidth, Defaults.windowHeight, new Color(240,240,240), Color.GRAY);
            return background;
        }
    }
    
    public static BufferedImage createBackground(int width, int height, Color light, Color dark){
        BufferedImage bgimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bgimage.createGraphics();
        Stroke s = g.getStroke();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(new GradientPaint(0, 0, light, width, height, dark));
        g.fillRoundRect(0, 0, width, height, 15, 15);
        g.setPaint(null);
        g.setStroke(new BasicStroke(6));
        g.setColor(dark);
        g.drawRoundRect(3, 3, width-7, height-7, 15, 15);
        g.setStroke(new BasicStroke(2));
        g.setColor(dark);
        g.drawRoundRect(0, 0, width-1, height-1, 15, 15);
        g.setColor(dark.darker().darker());
        g.drawRoundRect(2, 2, width-5, height-5, 15, 15);
        g.setColor(dark);
        g.drawRoundRect(4, 4, width-9, height-9, 15, 15);
        g.setColor(dark.brighter());
        g.drawRoundRect(6, 6, width-13, height-13, 15, 15);
        g.setStroke(s);
        /*BufferedImage bgTower = null;
        try {
            bgTower = ImageIO.read(Preferences.class.getResource("/files/images/towerbg.png"));
        } catch (Exception ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bgTower != null){
            double factor = (windowSize.height+0.0)/bgTower.getHeight();
            bgTower = com.golden.gamedev.util.ImageUtil.resize(bgTower, (int) (bgTower.getWidth() * factor), windowSize.height);
            int x1 = (windowSize.width - bgTower.getWidth())/2;
            g.drawImage(bgTower, x1, 0, null);
        }*/
        return bgimage;
    }
}
