package surrealdefense.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import surrealdefense.map.MapDefaults.Terrain;
import surrealdefense.map.objects.Tower;

import com.golden.gamedev.object.background.abstraction.AbstractTileBackground;

public class LevelMap extends AbstractTileBackground {
	protected BufferedImage defaultImage;
	protected Terrain[][] terrain;
	protected Tower[][] tower;

	public LevelMap(int[][] map){
		this(convertToTerrain(map));
	}
	
	public LevelMap(Terrain[][] map) {
		super(map.length, map[0].length, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
		terrain = map;
		tower = new Tower[map.length][map[0].length];
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
	
	public void setTower(Tower t ,int x, int y){
		if (tower[x][y] == null){
			t.setBackground(this);
			tower[x][y] = t;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3503168114941568252L;

	@Override
	public void renderTile(Graphics2D g, int tileX, int tileY, int x, int y) {
		terrain[tileX][tileY].render(g, x, y);
		if (tower[tileX][tileY] != null)
			tower[tileX][tileY].render(g);
	}

}
