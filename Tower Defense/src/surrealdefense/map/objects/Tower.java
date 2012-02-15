package surrealdefense.map.objects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.map.MapDefaults;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class Tower extends AdvanceSprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6165149490296118159L;
	
	private TowerType type;
	private Timer attackTimer;
	
	public Tower(int tx, int ty, TowerType t){
		super(t.getImages(),tx * MapDefaults.TILESIZE, ty * MapDefaults.TILESIZE);
		this.type = t;
		attackTimer = new Timer( (int) (t.getAttackspeed()*1000));
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if (attackTimer.action(elapsedTime)){
			
		}
	}
}
