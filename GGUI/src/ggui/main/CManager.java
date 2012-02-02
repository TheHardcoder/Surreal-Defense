package ggui.main;

import ggui.components.AbstractComponent;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CManager {
	private InputListener inputListener;
	private List<AbstractComponent> components = new ArrayList<AbstractComponent>();
	private int mousex, mousey;
	private AbstractComponent focusedComp = null;
	private boolean catchedInput = false;

	public CManager(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	public void render(Graphics2D g) {
		for (AbstractComponent component : components)
			component.render(g);
		if (focusedComp != null)
			focusedComp.render(g);
	}

	public void update(long elapsedTime) {
		mousex = inputListener.getMouseX();
		mousey = inputListener.getMouseY();
		catchedInput = false;
		for (AbstractComponent component : components){
			component.update(elapsedTime);
		}
		if (focusedComp != null && focusedComp.contains(mousex, mousey)){
			catchedInput = true;
			focusedComp.mouseMove(mousex, mousey);
			if (!focusedComp.hasContainedMouse())
				focusedComp.mouseOver();
			if (inputListener.getMousePressed() != inputListener
					.getValueNoMouse()) {
				focusedComp.mouseClick(mousex, mousey,
						inputListener.getMousePressed());
			}
			if (inputListener.getKeyPressed() != inputListener
					.getValueNoKey())
				focusedComp.keyPressed(inputListener.getKeyPressed());
			if (inputListener.getKeyReleased() != inputListener
					.getValueNoKey())
				focusedComp.keyPressed(inputListener.getKeyReleased());
		}
		else {
			for (AbstractComponent component : components) {
				if (component.contains(mousex, mousey) && !catchedInput) {
					catchedInput = true;
					component.mouseMove(mousex, mousey);
					if (!component.hasContainedMouse())
						component.mouseOver();
					if (inputListener.getMousePressed() != inputListener
							.getValueNoMouse()) {
						component.setFocus(true);
						focusedComp = component;
						component.mouseClick(mousex, mousey,
								inputListener.getMousePressed());
					}
					if (inputListener.getKeyPressed() != inputListener
							.getValueNoKey())
						component.keyPressed(inputListener.getKeyPressed());
					if (inputListener.getKeyReleased() != inputListener
							.getValueNoKey())
						component.keyPressed(inputListener.getKeyReleased());
					component.setContainedMouse(true);
				} 
				else {
					if (inputListener.getMousePressed() != inputListener
							.getValueNoMouse()) {
						component.setFocus(false);
						focusedComp = null;
					}
					if (component.hasContainedMouse()) {
						component.mouseOut();
						component.setContainedMouse(false);
					}
				}
			}
		}
	}

	public void add(AbstractComponent component) {
		component.setInputListener(inputListener);
		components.add(component);
	}
}
