package ggui.components;

import java.awt.Color;

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
		renderComponent();
	}

	@Override
	public void renderComponent() {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(0, 0, width, height,5,5);
		g2d.setColor(Color.GRAY);
		g2d.drawRoundRect(0, 0, width, height,5,5);
		if (checked){
			g2d.drawLine(3, 3, width-3, width-3);
			g2d.drawLine(width-3, 3, 3, width-3);
		}
	}

	@Override
	public void mouseClick(int mousex, int mousey, int button) {
		checked = !checked;
		renderComponent();
	}

	@Override
	public void updateComponent(long elapsedTime) {
		
	}

}
