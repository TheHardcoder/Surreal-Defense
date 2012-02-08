package surrealdefense.map.objects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.map.MapDefaults;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class Tower extends AdvanceSprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6165149490296118159L;
	private static BufferedImage[] defaultImages;

	public static BufferedImage[] getDefaultImages(){
		if (defaultImages == null){
			defaultImages = new BufferedImage[1];
			defaultImages[0] = new BufferedImage(MapDefaults.TILESIZE, MapDefaults.TILESIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = defaultImages[0].createGraphics();
			g.setPaint(new GradientPaint(0, 0, Color.CYAN, MapDefaults.TILESIZE, MapDefaults.TILESIZE, Color.BLUE));
			g.fillOval(0, 0, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
		}
		return defaultImages;
	}
	
	public Tower(int tx, int ty){
		super(getDefaultImages(),tx * MapDefaults.TILESIZE, ty * MapDefaults.TILESIZE);
	}
	
}
