package surrealdefense.screens;

import ggui.components.Button;
import ggui.components.Label;
import ggui.components.Textfield;
import ggui.main.InputListener;

import java.awt.Graphics2D;

import surrealdefense.dao.SaveGameDAO;

public class NewPlayerScreen extends AbstractScreen {
	private SaveGameDAO saveGame;

	public NewPlayerScreen(InputListener inputListener, SaveGameDAO saveGame) {
		super(inputListener, "Neuer Spieler");
		this.saveGame = saveGame;
		Label nameLabel = new Label(80, 120, "Name:", null, AbstractScreen.getFontImage(), true);
		cManager.add(nameLabel);
		Textfield name = new Textfield(300, 120, AbstractScreen.getFontImage());
		cManager.add(name);
		
		Button next = new Button(width-330, height - 70, "Weiter", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new WorldMapScreen(NewPlayerScreen.this.inputListener);
				NewPlayerScreen.this.changeScreen = true;
			}
		});
		next.setMinwidth(250);
		cManager.add(next);
	}

	@Override
	public void renderScreen(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScreen(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
