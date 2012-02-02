package surrealdefense.screens.components;

import java.awt.Color;
import java.awt.GradientPaint;

import ggui.components.AbstractComponent;

public class Menu extends AbstractComponent {

	public Menu(int x, int y, int width, int height) {
		super(x, y, width, height);
		renderComponent();
	}

	@Override
	public void renderComponent() {
		g2d.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, width, height, Color.GRAY));
		g2d.fillRoundRect(0, 0, width, height*2,height*2,height*2);
	}

	@Override
	public void updateComponent(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
