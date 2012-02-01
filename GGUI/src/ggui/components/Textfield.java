package ggui.components;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Textfield extends Label {
	protected int delay = 0;

	public Textfield(int x, int y, BufferedImage fontImage) {
		super(x, y, "", null, fontImage, true);
		setMinwidth(300);
	}
	
	public void updateComponent(long elapsedTime){
		super.updateComponent(elapsedTime);
		delay -= elapsedTime;
		if (delay < 0)
			delay = 0;
		if (delay == 0){
			
			if (inputListener.isKeyDown(KeyEvent.VK_BACK_SPACE) && label.length() > 0){
				label = label.substring(0, label.length()-1);
				delay = 250;
			}
		}
		int keyCode = inputListener.getKeyPressed();
		if (keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z)
			label += (char) (keyCode + ((inputListener.isKeyDown(KeyEvent.VK_SHIFT)) ? 0 : 32));
		if (keyCode == KeyEvent.VK_SPACE && label.length() > 0 && label.lastIndexOf(" ") != label.length()-1)
			label += " ";
	}
	
	public void renderComponent(){
		if (isFocused()){
			g2d.setColor(Color.YELLOW);
			g2d.fillRoundRect(0, 0, width, height, 20, 20);
		}
		super.renderComponent();
	}

}
