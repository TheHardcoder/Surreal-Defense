package ggui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Label extends AbstractComponent {
	protected String label = "";
	protected Color color = Color.BLACK;
	protected Font font = new Font("Arial", Font.PLAIN, 14);

	public Label(int x, int y, String label) {
		super(x, y);
		this.label = label;
		
	}

	@Override
	public void render(Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics(font);
		width = metrics.stringWidth(label);
		height = metrics.getHeight();
		g.setFont(font);
		g.setColor(color);
		g.drawString(label, x, y + metrics.getAscent());
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub

	}

}
