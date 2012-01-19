package ggui.main;

import ggui.components.AbstractComponent;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CManager {
	private InputListener inputListener;
	private List<AbstractComponent> components = new ArrayList<AbstractComponent>();
	private int mousex, mousey;

	public CManager(InputListener inputListener){
		this.inputListener = inputListener;
	}
	
	public void render(Graphics2D g){
		for (AbstractComponent component : components)
			component.render(g);
	}
	
	public void update(long elapsedTime){
		mousex = inputListener.getMouseX();
		mousey = inputListener.getMouseY();
		for (AbstractComponent component : components){
			component.update(elapsedTime);
			if (component.contains(mousex, mousey)){
				component.mouseMove(mousex, mousey);
				if (inputListener.getMousePressed() != inputListener.getValueNoMouse())
					component.mouseClick(mousex, mousey, inputListener.getMousePressed());
				if (inputListener.getKeyPressed() != inputListener.getValueNoKey())
					component.keyPressed(inputListener.getKeyPressed());
				if (inputListener.getKeyReleased() != inputListener.getValueNoKey())
					component.keyPressed(inputListener.getKeyReleased());
			}
		}
	}

	public void add(AbstractComponent component){
		components.add(component);
	}
}
