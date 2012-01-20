package surrealdefense.screens;

import ggui.components.Button;
import ggui.components.DropDown;
import ggui.components.Textfield;
import ggui.main.InputListener;

import java.awt.Graphics2D;

/**
 *
 * @author D056866
 */
public class PreferencesScreen extends AbstractScreen {

    public PreferencesScreen(InputListener inputListener) {
		super(inputListener, "Einstellungen");
		Textfield name = new Textfield(0, 120, AbstractScreen.getFontImage());
		name.setCenterWidth(width);
		cManager.add(name);
		Button back = new Button(0, height - 70, "Zurück", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new MainScreen(PreferencesScreen.this.inputListener);
				PreferencesScreen.this.changeScreen = true;
			}
		});
		back.setMinwidth(250);
		back.setCenterWidth(width);
		cManager.add(back);
		DropDown resolution = new DropDown(0, 150, new String[]{"1. Bla", "2.Bla"}, AbstractScreen.getFontImage());
		cManager.add(resolution);
	}

	@Override
    public void renderScreen(Graphics2D g) {
        
    }

    @Override
    public void updateScreen(long elapsedTime) {
        
    }
    
}
