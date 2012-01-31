package surrealdefense.screens;

import ggui.components.AbstractComponent;
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
		SaveGameDAO[] saves = SaveGameManager.getSaveGames();
		for (int i = 0; i < saveGamePanels.length; i++){
			saveGamePanels[i] = new SaveGamePanel(width/2-430 + 220 * (i%4), 120 + 220 * (i/4), saves[i]);
		}
	}

	@Override
	public void renderScreen(Graphics2D g) {
		for (int i = 0; i < saveGamePanels.length; i++){
			saveGamePanels[i].render(g);
		}
	}

	@Override
	public void updateScreen(long elapsedTime) {
		for (int i = 0; i < saveGamePanels.length; i++){
			saveGamePanels[i].update(elapsedTime);
		}
	}

	private class SaveGamePanel extends AbstractComponent{
		private SaveGameDAO saveGame;
		private Label name;

		public SaveGameDAO getSaveGame() {
			return saveGame;
		}

		protected SaveGamePanel(int x, int y, SaveGameDAO saveGame) {
			super(x, y);
			this.saveGame = saveGame;
			width = 200;
			height = 200;
			name = new Label(x, y, saveGame.getName(), null, AbstractScreen.getFontImage(), true);
			name.setMinwidth(200);
		}

		@Override
		public void renderComponent(Graphics2D g) {
			g.setPaint(new GradientPaint(x, y, Color.LIGHT_GRAY, x + width, y+width, Color.GRAY));
			g.fillRoundRect(x, y, width, height, 20, 20);
			name.render(g);
		}

		@Override
		public void updateComponent(long elapsedTime) {
			name.update(elapsedTime);
		}
    	
    }
}
