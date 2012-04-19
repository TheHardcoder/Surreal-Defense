package surrealdefense.screens;

import ggui.components.Label;
import ggui.main.InputListener;

import java.awt.Graphics2D;

import surrealdefense.dao.SaveGameDAO;

public class LoadingScreen extends AbstractScreen implements Runnable {
	private SaveGameDAO savegame;
	private LevelScreen lvlscreen = null;
	private Thread loadingThread;
	private Label info;

	public LoadingScreen(InputListener inputListener, SaveGameDAO savegame) {
		super(inputListener, "Loading...");
		this.savegame = savegame;
		info = new Label(0, 100, "Bitte warten...", null, null, true);
		info.setCenterWidth(this.width);
		cManager.add(info);
		loadingThread = new Thread(this);
		loadingThread.start();
	}

	@Override
	public void renderScreen(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateScreen(long elapsedTime) {
		if (lvlscreen != null){
			nextScreen = lvlscreen;
			changeScreen = true;
		}
	}

	@Override
	public void run() {
		try {
			info.setLabel("Sähe Rasen");
			Thread.sleep(1000);
			info.setLabel("Grabe Gräben aus............");
			Thread.sleep(1000);
			info.setLabel("Fülle Wasser");
			Thread.sleep(1000);
			info.setLabel("Setze Steine");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lvlscreen = new LevelScreen(inputListener, savegame);
	}

}
