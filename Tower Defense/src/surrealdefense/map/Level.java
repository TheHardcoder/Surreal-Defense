package surrealdefense.map;

import com.golden.gamedev.object.PlayField;

public class Level extends PlayField{
	
	public Level(int[][] map){
		super(new LevelMap(map));
	}
}
