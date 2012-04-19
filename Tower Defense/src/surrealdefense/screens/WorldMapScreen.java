package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Graphics2D;

import surrealdefense.dao.SaveGameDAO;
import surrealdefense.map.WorldMap;
import surrealdefense.screens.components.LevelButton;
import surrealdefense.screens.components.Menu;

public class WorldMapScreen extends AbstractScreen {
	protected int scroll = 0;
	protected WorldMap map;
	protected LevelButton level1Button;
	protected SaveGameDAO savegame;
	private int diff = 0;

	public WorldMapScreen(InputListener inputListener, SaveGameDAO save) {
		super(inputListener);
		this.savegame = save;
		map = new WorldMap(width, height);
		Menu menu = new Menu(width/2-400, height-100, 800, 100);
		cManager.add(menu);
		level1Button = new LevelButton(50, 50, 0, new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new LoadingScreen(WorldMapScreen.this.inputListener, savegame);
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
		diff = (int) (500.0*elapsedTime/1000); //500px pro Sekunde verschieben
		if (inputListener.getMouseX() < width/5) //Wenn Maus sich am linken Bildschirmrand befindet, nach links scrollen.
			scroll = (int) ((scroll - diff < 0) ? 0 : (scroll - diff));
		if (inputListener.getMouseX() > width*4/5)// "               rechten "                            rechts "
			scroll = (int) ((scroll + diff > width) ? width : (scroll +diff));
		map.getBackground().setToCenter(scroll, 0, scroll+width, height);
	}

}
