package surrealdefense.screens;

import ggui.main.InputListener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import surrealdefense.dao.SaveGameDAO;
import surrealdefense.map.LevelMap;
import surrealdefense.map.MapDefaults;
import surrealdefense.map.objects.LevelStats;
import surrealdefense.map.objects.Tower;
import surrealdefense.map.objects.TowerType;
import surrealdefense.screens.components.LevelMenu;
import surrealdefense.screens.components.LevelMenu.SelectionChangeListener;

public class LevelScreen extends AbstractScreen {
	private LevelMap levelMap;
	private LevelMenu menu;
	private SaveGameDAO save;
	private TowerType selectedTowerType;
	private LevelStats lvlstats;

	public LevelScreen(InputListener inputListener, SaveGameDAO save) {
		super(inputListener);
		this.save = save;
		resetLevel();
		int map[][] = new int[50][50];
		Random r = new Random();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = r.nextInt(MapDefaults.Terrain.values().length);
			}
		}
		levelMap = new LevelMap(map);
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		levelMap.setTower(new Tower(1, 1, new TowerType(10,100,1.0,200, "default")), 1, 1);
		Graphics2D g = background.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		menu = new LevelMenu(width-300, 0, 300, height, save.getTower().toArray(new TowerType[0]), lvlstats);
		menu.setScl(new SelectionChangeListener() {
			
			@Override
			public void onSelectionChange() {
				selectedTowerType = menu.getSelectedTowerType();
			}
		});
		cManager.add(menu);
	}
	
	private void resetLevel(){
		lvlstats = new LevelStats();
		lvlstats.setMoney(500);
	}

	@Override
	public void renderScreen(Graphics2D g) {
		levelMap.render(g);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (selectedTowerType!= null){
			BufferedImage img = selectedTowerType.getImages()[0];
			g.drawImage(img, inputListener.getMouseX()-img.getWidth()/2, inputListener.getMouseY()-img.getHeight()/2, null);
		}
	}

	@Override
	public void updateScreen(long elapsedTime) {
		levelMap.update(elapsedTime);
		if (inputListener.isMousePressed(MouseEvent.BUTTON1) && 
				!this.cManager.catchedMouseClick(inputListener.getMouseX(), inputListener.getMouseY())){
			int tx = inputListener.getMouseX()/MapDefaults.TILESIZE;
			int ty = inputListener.getMouseY()/MapDefaults.TILESIZE;
			if(selectedTowerType != null &&
					levelMap.getTower(tx, ty) == null &&
					lvlstats.getMoney() >= selectedTowerType.getPrice()){
				
				levelMap.setTower(new Tower(tx, ty, selectedTowerType), tx, ty);
				lvlstats.spendMoney(selectedTowerType.getPrice());
			}
			if (!inputListener.isKeyDown(KeyEvent.VK_SHIFT)){
				selectedTowerType = null;
			}
		}
	}
}
