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
		Button newGame = new Button(0, 90, "Neues Spiel", AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		newGame.setCenterWidth(width);
		cManager.add(newGame);
	}

	@Override
    public void renderScreen(Graphics2D g){
        
    }

    @Override
    public void updateScreen(long elapsedTime) {
        
    }
    
}
