package surrealdefense.screens;

import ggui.components.AbstractComponent;
import ggui.components.Button;
import ggui.components.Label;
import ggui.main.InputListener;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import surrealdefense.dao.SaveGameDAO;
import surrealdefense.tools.SaveGameManager;

public class SaveGameScreen extends AbstractScreen {
	
	private SaveGamePanel[] saveGamePanels = new SaveGamePanel[8];

	public SaveGameScreen(InputListener inputListener) {
		super(inputListener, "Spielstände");
		final SaveGameDAO[] saves = SaveGameManager.getSaveGames();
		for (int i = 0; i < saveGamePanels.length; i++){
			final int index = i;
			saveGamePanels[i] = new SaveGamePanel(width/2-430 + 220 * (i%4), 120 + 220 * (i/4), saves[i], new Runnable() {
				
				@Override
				public void run() {
					if (saves[index].getName().equals(SaveGameDAO.EMPTY_SAVE_GAME)){
						nextScreen = new NewPlayerScreen(SaveGameScreen.this.inputListener, saves[index]);
						SaveGameScreen.this.changeScreen = true;
					}
					else {
						nextScreen = new WorldMapScreen(SaveGameScreen.this.inputListener, saves[index]);
						SaveGameScreen.this.changeScreen = true;
					}
				}
			});
			cManager.add(saveGamePanels[i]);
		}
		Button back = new Button(80, height - 70, "Zurück", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new MainScreen(SaveGameScreen.this.inputListener);
				SaveGameScreen.this.changeScreen = true;
			}
		});
		back.setMinwidth(250);
		back.setCenterWidth(width);
		cManager.add(back);
	}

	@Override
	public void renderScreen(Graphics2D g) {
	}

	@Override
	public void updateScreen(long elapsedTime) {
	}

	private class SaveGamePanel extends AbstractComponent{
		private SaveGameDAO saveGame;
		private Label name;
		private Runnable execute;
		private Label level;
		private Label xp;

		public SaveGameDAO getSaveGame() {
			return saveGame;
		}

		protected SaveGamePanel(int x, int y, SaveGameDAO saveGame, Runnable r) {
			super(x, y);
			this.saveGame = saveGame;
			this.execute = r;
			width = 200;
			height = 200;
			name = new Label(x, y, saveGame.getName(), null, AbstractScreen.getFontImage(), true);
			name.setMinwidth(200);
			children.add(name);
			if (!saveGame.getName().equals(SaveGameDAO.EMPTY_SAVE_GAME)){
				level = new Label(x, y+40, "Level: " + saveGame.getLevel(), null, AbstractScreen.getFontImage(), false);
				level.setMinwidth(200);
				children.add(level);
				xp = new Label(x, y+70, "XP: " + saveGame.getExperience(), null, AbstractScreen.getFontImage(), false);
				xp.setMinwidth(200);
				children.add(xp);
			}
			resize(width, height);
			renderComponent();
		}

		@Override
		public void mouseClick(int mousex, int mousey, int button) {
			execute.run();
		}

		@Override
		public void renderComponent() {
			g2d.setPaint(new GradientPaint(x, y, Color.LIGHT_GRAY, x + width, y+width, Color.GRAY));
			g2d.fillRoundRect(0, 0, width, height, 20, 20);
		}

		@Override
		public void updateComponent(long elapsedTime) {
			name.update(elapsedTime);
		}
    	
    }
}
