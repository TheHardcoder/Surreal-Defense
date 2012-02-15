package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Graphics2D;

import surrealdefense.map.WorldMap;
import surrealdefense.screens.components.LevelButton;
import surrealdefense.screens.components.Menu;

public class WorldMapScreen extends AbstractScreen {
	protected int scroll = 0;
	protected WorldMap map;
	protected LevelButton level1Button;

	public WorldMapScreen(InputListener inputListener) {
		super(inputListener);
		map = new WorldMap(width, height);
		Menu menu = new Menu(width/2-400, height-100, 800, 100);
		cManager.add(menu);
		level1Button = new LevelButton(50, 50, 0, new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new LevelScreen(WorldMapScreen.this.inputListener);
				changeScreen = true;
			}
		});
		cManager.add(level1Button);
	}

	@Override
	public void renderScreen(Graphics2D g) {
		map.render(g);
	}

	@Override
	public void updateScreen(long elapsedTime) {
		map.update(elapsedTime);
		level1Button.setX(level1Button.getMapx() - scroll);
		int diff = (int) (500.0*elapsedTime/1000); //500px pro Sekunde verschieben
		if (inputListener.getMouseX() < width/5)
			scroll = (int) ((scroll - diff < 0) ? 0 : (scroll - diff));
		if (inputListener.getMouseX() > width*4/5)
			scroll = (int) ((scroll + diff > width) ? width : (scroll +diff));
		map.getBackground().setToCenter(scroll, 0, scroll+width, height);
	}

}
