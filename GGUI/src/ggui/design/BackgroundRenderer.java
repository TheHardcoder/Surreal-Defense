package ggui.design;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class BackgroundRenderer {
	
	public static BufferedImage getBackground(int width, int height, Color c){
		BufferedImage bgImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        width--;
        height--;
        Graphics2D g = bgImage.createGraphics();
        int step = Math.min(width/2, height/2);
        Color[] colors = Helper.createColorArray(c, c.darker(), step);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.BLACK);
        g.fillRoundRect(0, 0, width, height, height, height);

        g.setStroke(new BasicStroke(2));
        for (int i = step; i > 0; i--){
            g.setColor(colors[i-1]);
            g.drawRoundRect(step-i, step-i, width-2*(step-i+1), height-2*(step-i+1),2*i,2*i);
        }
        //step /= 2.5;
        step = Math.round(step/2);
        Color[] glow = Helper.createColorArray(new Color(255,255,255,40), new Color(255, 255, 255, 0), step);
        for (int i = step; i > 0; i--){
            g.setColor(glow[i-1]);
            g.drawRoundRect(step-i+width/50, step-i+height/10, width-2*(step-i+1)-width/25, height/2-2*(step-i+1),2*i,2*i);
        }
        return bgImage;
	}
}
