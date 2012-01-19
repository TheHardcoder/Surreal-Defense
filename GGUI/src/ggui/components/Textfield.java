package ggui.components;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Textfield extends Label {
	protected int delay = 0;

	public Textfield(int x, int y, BufferedImage fontImage) {
		super(x, y, "", fontImage, true);
		setMinwidth(300);
	}

	@Override
	public void keyPressed(int keyCode) {
		if (delay == 0){
			if (keyCode == KeyEvent.VK_BACK_SPACE && label.length() > 0){
				label = label.substring(0,label.length()-1);
				delay = 300;
			}
			else if (keyCode >= 32 && keyCode <= 125) {
				label += ((char) keyCode);
				delay = 300;
			}
		}
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		delay -= elapsedTime;
		if (delay < 0)
			delay = 0;
	}

}
