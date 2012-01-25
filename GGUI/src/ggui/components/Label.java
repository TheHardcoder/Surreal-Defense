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
	protected Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
	protected int centerWidth = -1;
	protected BufferedImage fontImage;
	protected boolean drawBackground = false;
	protected int paddingLeft = 4;
	protected int paddingRight = 4;
	protected int paddingTop = 2;
	protected int paddingBottom = 2;
	protected int minwidth = 0;
	protected BufferedImage image = null;
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
	}

	public Label(int x, int y, String label, BufferedImage image,
			BufferedImage fontImage, boolean drawBackground) {
		super(x, y);
		this.label = label;
		this.fontImage = fontImage;
		this.drawBackground = drawBackground;
		this.image = image;
		FontMetrics metrics = getDummyGraphics().getFontMetrics(font);
		int imageWidth = (image != null) ? image.getWidth() : 0;
		int imageHeight = (image != null) ? image.getHeight() : 0;
		int cwidth = metrics.stringWidth(label) + paddingLeft + paddingRight + imageWidth;
		width = (cwidth > minwidth) ? cwidth : minwidth;
		height = ((metrics.getHeight() > imageHeight) ? metrics.getHeight()
				: imageHeight) + paddingTop + paddingBottom;
	}

	@Override
	public void renderComponent(Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics(font);
		int imageWidth = (image != null) ? image.getWidth() : 0;
		int imageHeight = (image != null) ? image.getHeight() : 0;
		int cwidth = metrics.stringWidth(label) + paddingLeft + paddingRight + imageWidth;
		width = (cwidth > minwidth) ? cwidth : minwidth;
		height = ((metrics.getHeight() > imageHeight) ? metrics.getHeight()
				: imageHeight) + paddingTop + paddingBottom;
		if (centerWidth >= 0)
			x = centerWidth / 2 - width / 2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (drawBackground) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x, y, width, height, 20, 20);
		}
		if (image != null) {
			g.drawImage(image, x - imageWidth / 2 + metrics.stringWidth(label)
					/ 2 + width / 2, y + paddingTop, null);
		}
		if (fontImage != null) {
			TextLayout tl = new TextLayout((label.length() <= 0) ? " " : label,
					font, g.getFontRenderContext());
			AffineTransform transform = new AffineTransform();
			transform.translate(x + width / 2 - tl.getBounds().getWidth() / 2
					+ imageWidth / 2, y + paddingTop + metrics.getAscent());
			Shape shape = tl.getOutline(transform);
			Rectangle r = shape.getBounds();
			g.setColor(new Color(50, 50, 255));
			g.draw(shape);
			Shape s = g.getClip();
			g.setClip(shape);
			g.drawImage(fontImage, r.x, r.y, r.width, r.height, null);
			g.setClip(s);
		} else {
			g.setFont(font);
			g.setColor(color);
			g.drawString(label, x + width / 2 + imageWidth / 2
					- metrics.stringWidth(label) / 2, y + paddingTop + metrics.getAscent());
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
	public void updateComponent(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	public enum Padding {
		LEFT, TOP, RIGHT, BOTTOM;
	}
}
