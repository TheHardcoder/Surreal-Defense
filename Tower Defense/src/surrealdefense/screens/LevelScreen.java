package surrealdefense.screens;

import ggui.components.AbstractComponent;
import ggui.components.Button;
import ggui.main.InputListener;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import surrealdefense.dao.SaveGameDAO;
import surrealdefense.map.LevelMap;
import surrealdefense.map.MapDefaults;
import surrealdefense.map.objects.Tower;
import surrealdefense.map.objects.TowerType;

public class LevelScreen extends AbstractScreen {
	private LevelMap levelMap;
	private TowerMenu menu;
	private SaveGameDAO save;

	public LevelScreen(InputListener inputListener, SaveGameDAO save) {
		super(inputListener);
		this.save = save;
		int map[][] = new int[50][50];
		Random r = new Random();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = r.nextInt(MapDefaults.Terrain.values().length);
			}
		}
		levelMap = new LevelMap(map);
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		levelMap.setTower(new Tower(1, 1, new TowerType(10,100,1.0,200, "default") {
		}), 1, 1);
		Graphics2D g = background.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		menu = new TowerMenu(width-300, 0, 300, height, save.getTower().toArray(new TowerType[0]));
		cManager.add(menu);
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

		protected TowerMenu(int x, int y, int width, int height, TowerType[] towers) {
			super(x, y, width, height);
			renderComponent();
			for (int i = 0; i < towers.length; i++){
				Button b = new Button(x + 20, 30, towers[i].getName(), towers[i].getImages()[0], null, new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
					}
				});
				b.setMinwidth(getWidth()-40);
				children.add(b);
			}
		}

		@Override
		public void renderComponent() {
			g2d.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), Color.GRAY));
			g2d.fillRoundRect(0, 0, width, height, 50, 50);
		}

		@Override
		public void updateComponent(long elapsedTime) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
