package surrealdefense.screens;

import ggui.components.Button;
import ggui.components.Label;
import ggui.components.Textfield;
import ggui.main.InputListener;

import java.awt.Graphics2D;

import surrealdefense.dao.SaveGameDAO;
import surrealdefense.tools.SaveGameManager;

public class NewPlayerScreen extends AbstractScreen {
	private Textfield name;
	private SaveGameDAO savegame;

	public NewPlayerScreen(InputListener inputListener, SaveGameDAO save) {
		super(inputListener, "Neuer Spieler");
		this.savegame = save;
		Label nameLabel = new Label(80, 120, "Name:", null, AbstractScreen.getFontImage(), true);
		cManager.add(nameLabel);
		name = new Textfield(300, 120, AbstractScreen.getFontImage());
		cManager.add(name);
		
		Button next = new Button(width-330, height - 70, "Weiter", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				savegame.setName(name.getLabel());
				SaveGameManager.saveGame(savegame);
				nextScreen = new WorldMapScreen(NewPlayerScreen.this.inputListener, savegame);
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
