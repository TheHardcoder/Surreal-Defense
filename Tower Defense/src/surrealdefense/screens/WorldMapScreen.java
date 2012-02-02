package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WorldMapScreen extends AbstractScreen {
	protected BufferedImage mapImage;
	protected int scroll = 0;

	public WorldMapScreen(InputListener inputListener) {
		super(inputListener);
		mapImage = new BufferedImage(width*2, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = mapImage.createGraphics();
		g.setPaint(new GradientPaint(0, 0, Color.RED, mapImage.getWidth(), 0, Color.GREEN.darker().darker()));
		g.fillRect(0, 0, mapImage.getWidth(), mapImage.getHeight());
	}

	@Override
	public void renderScreen(Graphics2D g) {
		g.drawImage(mapImage, 0, 0, width, height, scroll, 0, scroll+width, height, null);
	}

	@Override
	public void updateScreen(long elapsedTime) {
		int diff = (int) (500.0*elapsedTime/1000); //500px pro Sekunde verschieben
		if (inputListener.getMouseX() < width/5)
			scroll = (int) ((scroll - diff < 0) ? 0 : (scroll - diff));
		if (inputListener.getMouseX() > width*4/5)
			scroll = (int) ((scroll + diff > mapImage.getWidth()-width) ? mapImage.getWidth()-width : (scroll +diff));
	}

}
