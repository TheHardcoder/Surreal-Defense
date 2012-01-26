package ggui.components;

import java.awt.Color;
import java.awt.Graphics2D;

public class Checkbox extends AbstractComponent {
	protected boolean checked = false;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Checkbox(int x, int y) {
		super(x, y, 20, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderComponent(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(x, y, width, height,5,5);
		g.setColor(Color.GRAY);
		g.drawRoundRect(x, y, width, height,5,5);
		if (checked){
			g.drawLine(x+2, y+2, x+width-3, y+width-3);
			g.drawLine(x+width-3, y+3, x+2, y+width-2);
		}
	}

	@Override
	public void mouseClick(int mousex, int mousey, int button) {
		checked = !checked;
	}

	@Override
	public void updateComponent(long elapsedTime) {
		
	}

}
