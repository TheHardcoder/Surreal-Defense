package surrealdefense.screens;

import ggui.components.Button;
import ggui.components.Checkbox;
import ggui.components.DropDown;
import ggui.components.Label;
import ggui.main.InputListener;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import surrealdefense.main.Defaults;

/**
 *
 * @author D056866
 */
public class PreferencesScreen extends AbstractScreen {
	private DropDown<String> resolution;
	private Checkbox fullscreen;

    public PreferencesScreen(InputListener inputListener) {
		super(inputListener, "Einstellungen");
		Button back = new Button(80, height - 70, "Zurück", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				nextScreen = new MainScreen(PreferencesScreen.this.inputListener);
				PreferencesScreen.this.changeScreen = true;
			}
		});
		back.setMinwidth(250);
		cManager.add(back);
		
		Button save = new Button(width-330, height - 70, "Speichern", null, AbstractScreen.getFontImage(), new Runnable() {
			
			@Override
			public void run() {
				Defaults.setFullScreen(fullscreen.isChecked());
				String[] res = resolution.getSelection().split("x");
				Defaults.setWindowWidth(Integer.parseInt(res[0]));
				Defaults.setWindowHeight(Integer.parseInt(res[1]));
				Defaults.saveProperty();
				nextScreen = new MainScreen(PreferencesScreen.this.inputListener);
				PreferencesScreen.this.changeScreen = true;
			}
		});
		save.setMinwidth(250);
		cManager.add(save);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode[] displayModes = env.getDefaultScreenDevice().getDisplayModes();
		List<String> modes = new ArrayList<>();
		
		for (int i = 0; i < displayModes.length; i++){
			if (!modes.contains(displayModes[i].getWidth() + "x" + displayModes[i].getHeight()) && displayModes[i].getWidth() >= 800 && displayModes[i].getHeight() >= 600)
				modes.add(displayModes[i].getWidth() + "x" + displayModes[i].getHeight());
		}
		if (!modes.contains(width + "x" + height))
				modes.add(width + "x" + height);
		
		Label resolutionLabel = new Label(80, 150, "Auflösung: ", null, AbstractScreen.getFontImage(), true);
		resolutionLabel.setMinwidth(200);
		cManager.add(resolutionLabel);
		
		resolution = new DropDown<String>(320, 150, modes.toArray(new String[0]), AbstractScreen.getFontImage());
		resolution.setSelection(width + "x" + height);
		cManager.add(resolution);
		
		Label fullScreenLabel = new Label(80, 200, "Vollbild: ", null, AbstractScreen.getFontImage(), true);
		fullScreenLabel.setMinwidth(200);
		cManager.add(fullScreenLabel);
		
		fullscreen = new Checkbox(320, 200);
		fullscreen.setChecked(Defaults.fullscreen);
		cManager.add(fullscreen);
	}

	@Override
    public void renderScreen(Graphics2D g) {
        
    }

    @Override
    public void updateScreen(long elapsedTime) {
        
    }
    
}
