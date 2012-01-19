package ggui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Label extends AbstractComponent {
	protected String label = "";
	protected Color color = Color.BLACK;
	protected Font font = new Font("Arial", Font.PLAIN, 18);
	protected int centerWidth = -1;
	protected BufferedImage fontImage;
	protected boolean drawBackground = false;

	public Label(int x, int y, String label, BufferedImage fontImage, boolean drawBackground) {
		super(x, y);
		this.label = label;
		this.fontImage = fontImage;
		this.drawBackground = drawBackground;
	}

	@Override
	public void render(Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics(font);
		width = metrics.stringWidth(label);
		height = metrics.getHeight();
		if (centerWidth >= 0)
			x = centerWidth/2-width/2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (drawBackground){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x-2, y-2, width+4, height+4, 20, 20);
		}
		if (fontImage != null){
			TextLayout tl = new TextLayout(label, font, g.getFontRenderContext());
			AffineTransform transform = new AffineTransform();
			transform.translate(x, y + metrics.getAscent());
			Shape shape = tl.getOutline(transform);
			Rectangle r = shape.getBounds();
			g.setColor(new Color(50,50,255));
			g.draw(shape);
			g.setClip(shape);
			g.drawImage(fontImage, r.x, r.y, r.width, r.height, null);
		}
		else {
			g.setFont(font);
			g.setColor(color);
			g.drawString(label, x, y + metrics.getAscent());
		}
	}

	public int getCenterWidth() {
		return centerWidth;
	}

	public void setCenterWidth(int centerWidth) {
		this.centerWidth = centerWidth;
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
