package surrealdefense.screens;

import ggui.components.AbstractComponent;
import ggui.main.InputListener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import surrealdefense.map.LevelMap;
import surrealdefense.map.MapDefaults;
import surrealdefense.map.objects.Tower;
import surrealdefense.map.objects.TowerType;

public class LevelScreen extends AbstractScreen {
	private LevelMap levelMap;

	public LevelScreen(InputListener inputListener) {
		super(inputListener);
		int map[][] = new int[50][50];
		Random r = new Random();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = r.nextInt(MapDefaults.Terrain.values().length);
			}
		}
		levelMap = new LevelMap(map);
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		levelMap.setTower(new Tower(1, 1, new TowerType(10,100,1.0,200) {
		}), 1, 1);
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

	private class TowerMenu extends AbstractComponent{

		protected TowerMenu(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void renderComponent() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateComponent(long elapsedTime) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
