package ggui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Button extends Label {
	public static final int BUTTON_NORMAL = 0;
	public static final int BUTTON_OVER = 1;
	public static final int BUTTON_PRESSED = 2;
	protected Runnable runnable;
	protected int state = BUTTON_NORMAL;

	public Button(int x, int y, String label, BufferedImage image, BufferedImage fontImage, Runnable r) {
		super(x, y, label, image, fontImage, false);
		this.runnable = r;
	}
	
	public void mouseClick(int x, int y, int button){
		state = BUTTON_PRESSED;
		runnable.run();
	}
	
	public void mouseMove(int x, int y){
		state = BUTTON_OVER;
	}
	
	public void mouseOver(){
		state = BUTTON_OVER;
	}
	
	public void mouseOut(){
		state = BUTTON_NORMAL;
	}
	
	public void renderComponent(Graphics2D g){
		switch (state){
		case BUTTON_NORMAL:
			g.setPaint(new GradientPaint(x, y, new Color(200,200,200), x+width, y+width, new Color(100,100,100)));
			break;
		case BUTTON_OVER:
			g.setPaint(new GradientPaint(x, y, new Color(250,250,250), x+width, y+width, new Color(150,150,150)));
			break;
		case BUTTON_PRESSED:
			g.setPaint(new GradientPaint(x, y, new Color(250,250,250), x+width, y+width, new Color(200,200,200)));
			break;
		}
		g.fillRoundRect(x-2, y-2, width+4, height+4,20,20);
		super.renderComponent(g);
	}

}
