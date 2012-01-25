package surrealdefense.screens;

import ggui.components.Button;
import ggui.components.DropDown;
import ggui.components.Textfield;
import ggui.main.InputListener;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

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
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode[] displayModes = env.getDefaultScreenDevice().getDisplayModes();
		List<String> modes = new ArrayList<>();
		for (int i = 0; i < displayModes.length; i++){
			System.out.println(displayModes[i].getWidth() + "x" + displayModes[i].getHeight() + ":" + displayModes[i].getBitDepth());
			if (!modes.contains(displayModes[i].getWidth() + "x" + displayModes[i].getHeight()) && displayModes[i].getWidth() >= 800 && displayModes[i].getHeight() >= 600)
				modes.add(displayModes[i].getWidth() + "x" + displayModes[i].getHeight());
		}
		DropDown resolution = new DropDown(40, 150, modes.toArray(new String[0]), AbstractScreen.getFontImage());
		cManager.add(resolution);
	}

	@Override
    public void renderScreen(Graphics2D g) {
        
    }

    @Override
    public void updateScreen(long elapsedTime) {
        
    }
    
}
