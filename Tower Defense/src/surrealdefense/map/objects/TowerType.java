package surrealdefense.map.objects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import surrealdefense.map.MapDefaults;

public class TowerType implements Serializable {
	private static BufferedImage[] defaultImages;
	
	public static final int BASE_DMG = 10;
	public static final int BAE_RANGE = 100;
	public static final double BASE_ATTACK_SPEED = 1.0;
	public static final double BASE_CRIT_CHANCE = 0.01;
	public static final double BASE_CRIT_DMG = 2;
	
	protected int dmg;
	protected int range;
	protected double attackspeed;
	protected int price;
	protected double crit;
	protected String name;
	protected BufferedImage[] images = null;
	
	protected int randdmg = 0;

	public double getAttackspeed() {
		return attackspeed;
	}

	public int getPrice() {
		return price;
	}

	public TowerType(int dmg, int range, double attackspeed, int price, String name) {
		super();
		this.dmg = dmg;
		this.range = range;
		this.attackspeed = attackspeed;
		this.price = price;
		this.name = name;
	}
	
	public double getDamage(){
		return (Math.random() <= crit) ? (dmg + randdmg) * BASE_CRIT_DMG : (dmg + randdmg);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static BufferedImage[] getDefaultImages(){
		if (defaultImages == null){
			defaultImages = new BufferedImage[1];
			defaultImages[0] = new BufferedImage(MapDefaults.TILESIZE, MapDefaults.TILESIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = defaultImages[0].createGraphics();
			g.setPaint(new GradientPaint(0, 0, Color.CYAN, MapDefaults.TILESIZE, MapDefaults.TILESIZE, Color.BLUE));
			g.fillOval(0, 0, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
		}
		return defaultImages;
	}
	
	public BufferedImage[] getImages(){
		if (images == null)
			return getDefaultImages();
		else
			return images;
	}
}
