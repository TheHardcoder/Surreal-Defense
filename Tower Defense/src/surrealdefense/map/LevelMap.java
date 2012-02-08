package surrealdefense.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.map.MapDefaults.Terrain;

import com.golden.gamedev.object.background.abstraction.AbstractTileBackground;

public class LevelMap extends AbstractTileBackground {
	protected BufferedImage defaultImage;
	protected Terrain[][] terrain;

	public LevelMap(int[][] map){
		this(convertToTerrain(map));
	}
	
	public LevelMap(Terrain[][] map) {
		super(map.length, map[0].length, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
		terrain = map;
		defaultImage = new BufferedImage(MapDefaults.TILESIZE, MapDefaults.TILESIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = defaultImage.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, defaultImage.getWidth(), defaultImage.getHeight());
	}
	
	public static Terrain[][] convertToTerrain(int[][] map){
		Terrain[][] t = new Terrain[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				t[i][j] = Terrain.getTerrainByNr(map[i][j]);
			}
		}
		return t;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3503168114941568252L;

	@Override
	public void renderTile(Graphics2D g, int tileX, int tileY, int x, int y) {
		g.drawImage(defaultImage, x, y, null);
		
		switch(terrain[tileX][tileY]){
		case EARTH:
			g.setColor(new Color(200,100,0));
			g.fillRect(x, y, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
			break;
		case GRAS:
			g.setColor(new Color(0,200,0));
			g.fillRect(x, y, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
			break;
		case SAND:
			g.setColor(new Color(200,200,0));
			g.fillRect(x, y, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
			break;
		}
	}

}
