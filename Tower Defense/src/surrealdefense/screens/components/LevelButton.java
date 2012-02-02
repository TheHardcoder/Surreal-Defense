package surrealdefense.screens.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import ggui.components.Button;

public class LevelButton extends Button {

	private static final int ICON_WIDTH = 80;
	private static final int ICON_HEIGHT = 120;
	private static BufferedImage lvlIcon;
	private static BufferedImage lvlIconOver;
	private int mapx = 0;
	private int nr;

	public static BufferedImage getLvlIcon(){
		if (lvlIcon == null){
			lvlIcon = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = lvlIcon.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GeneralPath gp = new GeneralPath();
			gp.moveTo(ICON_WIDTH/2, 0);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH, ICON_HEIGHT/2);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH/2, ICON_HEIGHT);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, 0, ICON_HEIGHT/2);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH/2, 0);
			gp.closePath();
			g.setPaint(new GradientPaint(0, 0, Color.YELLOW, ICON_WIDTH, ICON_HEIGHT, new Color(255,200,0)));
			g.fill(gp);
		}
		return lvlIcon;
	}
	
	public static BufferedImage getLvlIconOver(){
		if (lvlIconOver == null){
			lvlIconOver = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = lvlIconOver.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GeneralPath gp = new GeneralPath();
			gp.moveTo(ICON_WIDTH/2, 0);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH, ICON_HEIGHT/2);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH/2, ICON_HEIGHT);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, 0, ICON_HEIGHT/2);
			gp.quadTo(ICON_WIDTH/2, ICON_HEIGHT/2, ICON_WIDTH/2, 0);
			gp.closePath();
			g.setPaint(new GradientPaint(0, 0, new Color(255,255,150), ICON_WIDTH, ICON_HEIGHT, Color.YELLOW));
			g.fill(gp);
		}
		return lvlIconOver;
	}
	
	public LevelButton(int x, int y, int nr, Runnable r) {
		super(x, y, "", getLvlIcon(), null, r);
		this.nr = nr;
		mapx = x;
	}

	@Override
	public void renderComponent() {
		switch (state) {
		case BUTTON_OVER:
			g2d.drawImage(getLvlIcon(), 0, 0, null);
			break;
		case BUTTON_PRESSED:
			g2d.drawImage(getLvlIcon(), 0, 0, null);
			break;
		default:
			g2d.drawImage(getLvlIcon(), 0, 0, null);
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString("Level " + nr, ICON_WIDTH/4, ICON_HEIGHT+2);
	}

	public int getMapx() {
		return mapx;
	}

	@Override
	public void updateComponent(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
