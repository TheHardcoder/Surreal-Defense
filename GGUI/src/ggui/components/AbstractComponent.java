package ggui.components;

import ggui.main.InputListener;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent {
	protected int x, y;
	protected int width, height;
	protected BufferedImage image = null;
	protected List<AbstractComponent> children;
	protected List<InputListener> listeners = new ArrayList<InputListener>();
	
	protected AbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
		children = new ArrayList<AbstractComponent>();
	}
	
	public AbstractComponent (int x, int y, BufferedImage image){
		this(x, y);
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public AbstractComponent (int x, int y, int width, int height){
		this(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void addListener(InputListener inputListener){
		listeners.add(inputListener);
	}
	
	public boolean contains(int x, int y){
		return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
	}
	
	public abstract void render(Graphics2D g);
	public abstract void update(long elapsedTime);
	
	public void mouseMove(int mousex, int mousey){}
	public void mouseClick(int mousex, int mousey, int button){}
	
	public void keyPressed(int keyCode){}
	public void keyReleased(int keyCode){}
}
