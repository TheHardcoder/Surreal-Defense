package ggui.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Button extends Label {
	public static final int BUTTON_NORMAL = 0;
	public static final int BUTTON_OVER = 1;
	public static final int BUTTON_PRESSED = 2;
	protected Runnable runnable;
	protected int state = BUTTON_NORMAL;

	public Button(int x, int y, String label, BufferedImage image,
			BufferedImage fontImage, Runnable r) {
		super(x, y, label, image, fontImage, false);
		renderComponent();
		this.runnable = r;
	}

	public void mouseClick(int x, int y, int button) {
		state = BUTTON_PRESSED;
		renderComponent();
		runnable.run();
	}

	public void mouseMove(int x, int y) {
		state = BUTTON_OVER;
		renderComponent();
	}

	public void mouseOver() {
		state = BUTTON_OVER;
		renderComponent();
	}

	public void mouseOut() {
		state = BUTTON_NORMAL;
		renderComponent();
	}

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
		switch (state) {
		case BUTTON_OVER:
			g2d.setPaint(new GradientPaint(x, y, new Color(250, 250, 250), x
					+ width, y + width, new Color(150, 150, 150)));
			break;
		case BUTTON_PRESSED:
			g2d.setPaint(new GradientPaint(x, y, new Color(250, 250, 250), x
					+ width, y + width, new Color(200, 200, 200)));
			break;
		default:
			g2d.setPaint(new GradientPaint(x, y, new Color(200, 200, 200), x
					+ width, y + width, new Color(100, 100, 100)));
		}
		g2d.fillRoundRect(0, 0, width, height, 20, 20);
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

}
