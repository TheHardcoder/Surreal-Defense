package ggui.components;

import ggui.main.InputListener;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent {
	protected BufferedImage image;
	protected Graphics2D g2d;
	protected int x, y;
	protected int width, height;
	protected int centerWidth = -1;
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
		if (focus)
			onFocus();
		else
			onFocusLoss();
	}

	protected AbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
		children = new ArrayList<AbstractComponent>();
		resize(1, 1);
	}
	
	public AbstractComponent (int x, int y, int width, int height){
		this(x,y);
		this.width = width;
		this.height = height;
		resize(width, height);
	}
	
	public boolean contains(int x, int y){
		if (centerWidth > -1)
			return centerWidth/2-width/2 <= x && centerWidth/2+width/2 >= x && this.y <= y && this.y + this.height >= y;
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
		if (visible && centerWidth > -1)
			g.drawImage(image, centerWidth/2-width/2, y, null);
		else if (visible)
			g.drawImage(image, x, y, null);
		for (AbstractComponent child : children){
			child.render(g);
		}
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void onFocus(){}
	
	public void onFocusLoss(){}

	public void update(long elapsedTime){
		updateComponent(elapsedTime);
	}
	
	public int getCenterWidth() {
		return centerWidth;
	}

	public void setCenterWidth(int centerWidth) {
		this.centerWidth = centerWidth;
	}
	
	public void resize(int width, int height){
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	}

	public abstract void renderComponent();
	public abstract void updateComponent(long elapsedTime);
	
	public void mouseMove(int mousex, int mousey){
		for (AbstractComponent child :children){
			if (child.contains(mousex, mousey)){
				child.mouseMove(mousex, mousey);
				child.setContainedMouse(true);
			}
			else {
				if (child.hasContainedMouse()){
					child.mouseOut();
				}
				child.setContainedMouse(false);
			}
		}
	}
	public void mouseClick(int mousex, int mousey, int button){
		for (AbstractComponent child :children){
			if (child.contains(mousex, mousey)){
				child.mouseClick(mousex, mousey, button);
				if (!child.hasContainedMouse()){
					child.mouseOver();
					child.setContainedMouse(true);
				}
			}
		}
	}
	public void mouseOver(){
	}
	public void mouseOut(){
		for (AbstractComponent child :children){
			child.mouseOut();
			child.setContainedMouse(false);
		}
	}
	
	public void keyPressed(int keyCode){}
	public void keyReleased(int keyCode){}
}
