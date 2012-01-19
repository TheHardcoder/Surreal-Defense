package ggui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Button extends Label {
	protected Runnable runnable;

	public Button(int x, int y, String label, BufferedImage fontImage, Runnable r) {
		super(x, y, label, fontImage, false);
		this.runnable = r;
	}
	
	public void mouseClick(int x, int y, int button){
		runnable.run();
	}
	
	public void mouseOver(){
		setColor(Color.LIGHT_GRAY);
	}
	
	public void mouseOut(){
		setColor(Color.GRAY);
	}
	
	public void render(Graphics2D g){
		g.setColor(getColor());
		g.fillRoundRect(x-2, y-2, width+4, height+4,20,20);
		super.render(g);
	}

}
