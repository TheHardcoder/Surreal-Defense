package ggui.components;

import ggui.main.InputListener;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent {
	protected int x, y;
	protected int width, height;
	protected List<AbstractComponent> children;
	protected boolean containedMouse = false;
	protected boolean focus;
	protected InputListener inputListener;
	protected boolean visible = true;
	
	public InputListener getInputListener() {
		return inputListener;
	}

	public void setInputListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	public boolean isFocused() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	protected AbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
		children = new ArrayList<AbstractComponent>();
	}
	
	public AbstractComponent (int x, int y, int width, int height){
		this(x,y);
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(int x, int y){
		return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
	}
	
	public boolean hasContainedMouse() {
		return containedMouse;
	}

	public void setContainedMouse(boolean containedMouse) {
		this.containedMouse = containedMouse;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<AbstractComponent> getChildren() {
		return children;
	}
	
	public void render(Graphics2D g){
		if (visible)
			renderComponent(g);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void update(long elapsedTime){
		updateComponent(elapsedTime);
	}

	public abstract void renderComponent(Graphics2D g);
	public abstract void updateComponent(long elapsedTime);
	
	public void mouseMove(int mousex, int mousey){}
	public void mouseClick(int mousex, int mousey, int button){}
	public void mouseOver(){}
	public void mouseOut(){}
	
	public void keyPressed(int keyCode){}
	public void keyReleased(int keyCode){}
}
