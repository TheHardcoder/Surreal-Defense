package ggui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
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
	protected Font font = new Font("Comic Sans MS", Font.BOLD, 18);
	protected BufferedImage fontImage;
	protected boolean drawBackground = false;
	protected int paddingLeft = 4;
	protected int paddingRight = 4;
	protected int paddingTop = 2;
	protected int paddingBottom = 2;
	protected int minwidth = 0;
	protected BufferedImage iconImage = null;
	private static Graphics2D dummyG;

	protected static Graphics2D getDummyGraphics() {
		if (dummyG != null)
			return dummyG;
		else {
			BufferedImage img = new BufferedImage(1, 1,
					BufferedImage.TYPE_INT_RGB);
			dummyG = img.createGraphics();
			return dummyG;
		}
	}

	public BufferedImage getFontImage() {
		return fontImage;
	}

	public void setFontImage(BufferedImage fontImage) {
		this.fontImage = fontImage;
	}

	public boolean isDrawBackground() {
		return drawBackground;
	}

	public void setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
	}

	public int getPadding(Padding p) {
		switch (p) {
		case TOP:
			return paddingTop;
		case BOTTOM:
			return paddingBottom;
		case RIGHT:
			return paddingRight;
		default:
			return paddingLeft;
		}
	}

	public void setPadding(Padding p, int margin) {
		switch (p) {
		case TOP:
			paddingTop = margin;
			break;
		case BOTTOM:
			paddingBottom = margin;
			break;
		case RIGHT:
			paddingRight = margin;
			break;
		case LEFT:
			paddingLeft = margin;
			break;
		}
	}

	public int getMinwidth() {
		return minwidth;
	}

	public void setMinwidth(int minwidth) {
		this.minwidth = minwidth;
		renderComponent();
	}

	public Label(int x, int y, String label, BufferedImage iconImage,
			BufferedImage fontImage, boolean drawBackground) {
		super(x, y);
		this.label = label;
		this.fontImage = fontImage;
		this.drawBackground = drawBackground;
		this.iconImage = iconImage;
		FontMetrics metrics = getDummyGraphics().getFontMetrics(font);
		int imageWidth = (iconImage != null) ? iconImage.getWidth() : 0;
		int imageHeight = (iconImage != null) ? iconImage.getHeight() : 0;
		int cwidth = metrics.stringWidth(label) + paddingLeft + paddingRight + imageWidth;
		resize((cwidth > minwidth) ? cwidth : minwidth, ((metrics.getHeight() > imageHeight) ? metrics.getHeight()
				: imageHeight) + paddingTop + paddingBottom);
		renderComponent();
	}

	@Override
	public void renderComponent() {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int imageWidth = (iconImage != null) ? iconImage.getWidth() : 0;
		int imageHeight = (iconImage != null) ? iconImage.getHeight() : 0;
		int cwidth = metrics.stringWidth(label) + paddingLeft + paddingRight + imageWidth;
		width = (cwidth > minwidth) ? cwidth : minwidth;
		height = ((metrics.getHeight() > imageHeight) ? metrics.getHeight()
				: imageHeight) + paddingTop + paddingBottom;
		if (width != image.getWidth() | height != image.getHeight()){
			resize(width, height);
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (drawBackground) {
			g2d.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, width, height, Color.LIGHT_GRAY.darker()));
			g2d.fillRoundRect(0, 0, width, height, 20, 20);
		}
		if (iconImage != null) {
			g2d.drawImage(iconImage, -imageWidth / 2 + metrics.stringWidth(label)
					/ 2 + width / 2, paddingTop, null);
		}
		if (fontImage != null) {
			TextLayout tl = new TextLayout((label.length() <= 0) ? " " : label,
					font, g2d.getFontRenderContext());
			AffineTransform transform = new AffineTransform();
			transform.translate(width / 2 - tl.getBounds().getWidth() / 2
					+ imageWidth / 2, paddingTop + metrics.getAscent());
			Shape shape = tl.getOutline(transform);
			Rectangle r = shape.getBounds();
			g2d.setColor(new Color(50, 50, 255));
			g2d.draw(shape);
			Shape s = g2d.getClip();
			g2d.setClip(shape);
			g2d.drawImage(fontImage, r.x, r.y, r.width, r.height, null);
			g2d.setClip(s);
		} else {
			g2d.setFont(font);
			g2d.setColor(color);
			g2d.drawString(label, width / 2 + imageWidth / 2
					- metrics.stringWidth(label) / 2, paddingTop + metrics.getAscent());
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		resize(width, height);
		renderComponent();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		renderComponent();
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		renderComponent();
	}

	@Override
	public void updateComponent(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	public enum Padding {
		LEFT, TOP, RIGHT, BOTTOM;
	}
}
