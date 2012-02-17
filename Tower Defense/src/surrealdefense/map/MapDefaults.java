package surrealdefense.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MapDefaults {
	public static final int TILESIZE = 48;

	public enum Terrain{
		EARTH(true,true, 0, new Color(200,100,0)),
		GRAS(true, true, 1, Color.GREEN),
		SAND(true, true, 2, Color.YELLOW),
		SWAMP(true,true, 3, new Color(100,200,0)),
		STONE(true,true, 4, Color.GRAY),
		MOUNTAIN(false,true, 5, Color.LIGHT_GRAY);
		
		private boolean walkable;
		private boolean canBuildOn;
		private int nr;
		private BufferedImage image;
		
		Terrain(boolean walkable, boolean canBuildOn, int nr, Color c){
			this.walkable = walkable;
			this.canBuildOn = canBuildOn;
			this.nr = nr;
			image = new BufferedImage(MapDefaults.TILESIZE, MapDefaults.TILESIZE, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(c);
			g.fillRect(0, 0, image.getWidth(), image.getHeight());
		}
		
		public int getNr() {
			return nr;
		}

		public static Terrain getTerrainByNr(int nr){
			for (Terrain t : Terrain.values()){
				if (t.getNr() == nr)
					return t;
			}
			return null;
		}

		public boolean isWalkable(){
			return walkable;
		}
		
		public boolean canBuildOn(){
			return canBuildOn;
		}
		
		public void render(Graphics2D g, int x, int y){
			g.drawImage(image, x, y, null);
		}
	}
}
