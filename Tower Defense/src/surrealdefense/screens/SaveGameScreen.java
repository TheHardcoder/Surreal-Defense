package surrealdefense.screens;

import ggui.components.AbstractComponent;
import ggui.main.InputListener;

import java.awt.Graphics2D;

public class SaveGameScreen extends AbstractScreen {
	
	private SaveGamePanel[] saveGamePanels = new SaveGamePanel[8];

	public SaveGameScreen(InputListener inputListener) {
		super(inputListener, "Spielstände");
		
	}

	@Override
	public void renderScreen(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScreen(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	private class SaveGamePanel extends AbstractComponent{

		protected SaveGamePanel(int x, int y) {
			super(x, y);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void renderComponent(Graphics2D g) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateComponent(long elapsedTime) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
