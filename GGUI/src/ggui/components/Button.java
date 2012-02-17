package ggui.components;

import ggui.design.BackgroundRenderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Button extends Label {
	public static final int BUTTON_NORMAL = 0;
	public static final int BUTTON_OVER = 1;
	public static final int BUTTON_PRESSED = 2;
	private BufferedImage normal;
    private BufferedImage over;
    private BufferedImage pressed;
	protected Runnable runnable;

	public Button(int x, int y, String label, BufferedImage bImage,
			BufferedImage fontImage, Runnable r) {
		super(x, y, label, bImage, fontImage, false);
		renderComponent();
		this.runnable = r;
	}

	public void mouseClick(int x, int y, int button) {
		image = pressed;
		runnable.run();
	}

	public void mouseMove(int x, int y) {
		image = over;
	}

	public void mouseOver() {
		image = over;
	}

	public void mouseOut() {
		image = normal;
	}

	public void renderComponent() {
		normal = renderImage(BUTTON_NORMAL);
		over = renderImage(BUTTON_OVER);
		pressed = renderImage(BUTTON_PRESSED);
		image = normal;
	}
	
	protected BufferedImage renderImage(int state){
		FontMetrics metrics = g2d.getFontMetrics(font);
		int imageWidth = (iconImage != null) ? iconImage.getWidth() : 0;
		int imageHeight = (iconImage != null) ? iconImage.getHeight() : 0;
		int cwidth = metrics.stringWidth(label) + paddingLeft + paddingRight + imageWidth;
		width = (cwidth > minwidth) ? cwidth : minwidth;
		height = ((metrics.getHeight() > imageHeight) ? metrics.getHeight()
				: imageHeight) + paddingTop + paddingBottom;
		resize(width, height);
		BufferedImage pImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = pImage.createGraphics();
		switch (state) {
		case BUTTON_OVER:
			g.drawImage(BackgroundRenderer.getBackground(getWidth(), getHeight(), Color.LIGHT_GRAY), 0, 0, null);
			g.setPaint(new GradientPaint(x, y, new Color(250, 250, 250), x
					+ width, y + width, new Color(150, 150, 150)));
			break;
		case BUTTON_PRESSED:
			g.drawImage(BackgroundRenderer.getBackground(getWidth(), getHeight(), Color.LIGHT_GRAY.brighter()), 0, 0, null);
			g.setPaint(new GradientPaint(x, y, new Color(250, 250, 250), x
					+ width, y + width, new Color(200, 200, 200)));
			break;
		default:
			g.drawImage(BackgroundRenderer.getBackground(getWidth(), getHeight(), new Color(150,150,150)), 0, 0, null);
			g.setPaint(new GradientPaint(x, y, new Color(200, 200, 200), x
					+ width, y + width, new Color(100, 100, 100)));
		}
		if (iconImage != null) {
			g.drawImage(iconImage, -imageWidth / 2 + metrics.stringWidth(label)
					/ 2 + width / 2-1, paddingTop-1, null);
		}
		if (fontImage != null) {
			TextLayout tl = new TextLayout((label.length() <= 0) ? " " : label,
					font, g.getFontRenderContext());
			AffineTransform transform = new AffineTransform();
			transform.translate(width / 2 - tl.getBounds().getWidth() / 2
					+ imageWidth / 2, paddingTop + metrics.getAscent());
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
			g.drawString(label, width / 2 + imageWidth / 2
					- metrics.stringWidth(label) / 2, paddingTop + metrics.getAscent());
		}
		return pImage;
	}

}
