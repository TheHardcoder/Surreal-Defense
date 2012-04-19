package surrealdefense.map;

import java.awt.Graphics2D;

import surrealdefense.map.MapDefaults.Terrain;
import surrealdefense.map.objects.Tower;

import com.golden.gamedev.object.background.abstraction.AbstractTileBackground;

public class LevelMap extends AbstractTileBackground {
	protected Terrain[][] terrain;
	protected Tower[][] tower;

	public LevelMap(int[][] map){
		this(convertToTerrain(map));
	}
	
	public LevelMap(Terrain[][] map) {
		super(map.length, map[0].length, MapDefaults.TILESIZE, MapDefaults.TILESIZE);
		terrain = map;
		tower = new Tower[map.length][map[0].length];
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
	
	public Tower getTower(int x, int y){
		return tower[x][y];
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
