package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.map.LevelMap;

public class LevelScreen extends AbstractScreen {
	private LevelMap levelMap;

	public LevelScreen(InputListener inputListener) {
		super(inputListener);
		int map[][] = new int[50][50];
		levelMap = new LevelMap(map);
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = background.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}

	@Override
	public void renderScreen(Graphics2D g) {
		levelMap.render(g);
	}

	@Override
	public void updateScreen(long elapsedTime) {
		levelMap.update(elapsedTime);
	}

}
