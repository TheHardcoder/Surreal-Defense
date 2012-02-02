package surrealdefense.map;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

public class WorldMap extends PlayField {
	
	public WorldMap (int width, int height){
		super();
		BufferedImage mapImage = new BufferedImage(width*2, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = mapImage.createGraphics();
		g.setPaint(new GradientPaint(0, 0, Color.RED, mapImage.getWidth(), 0, Color.GREEN.darker().darker()));
		g.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());
		setBackground(new ImageBackground(mapImage));
	}

}
