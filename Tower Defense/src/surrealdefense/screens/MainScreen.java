package surrealdefense.screens;

import ggui.components.Button;
import ggui.main.InputListener;

import java.awt.Graphics2D;

/**
 *
 * @author D056866
 */
public class MainScreen extends AbstractScreen {
    
    public MainScreen(InputListener inputListener) {
		super(inputListener, "Surreal Defense");
		Button newGame = new Button(0, 120, "Neues Spiel", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		newGame.setMinwidth(250);
		newGame.setCenterWidth(width);
		cManager.add(newGame);
		Button preferences = new Button(0, 160, "Einstellungen", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new PreferencesScreen(MainScreen.this.inputListener);
				MainScreen.this.changeScreen = true;
			}
		});
		preferences.setMinwidth(250);
		preferences.setCenterWidth(width);
		cManager.add(preferences);
		Button exit = new Button(0, height - 70, "Beenden", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = null;
				MainScreen.this.changeScreen = true;
			}
		});
		exit.setMinwidth(250);
		exit.setCenterWidth(width);
		cManager.add(exit);
	}

	@Override
    public void renderScreen(Graphics2D g){
        
    }

    @Override
    public void updateScreen(long elapsedTime) {
        
    }
    
}
