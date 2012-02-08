package surrealdefense.map;

public class MapDefaults {
	public static final int TILESIZE = 64;

	public enum Terrain{
		EARTH(true,true, 0),
		GRAS(true, true, 1),
		SAND(true, true, 2),
		SWAMP(true,true, 3),
		STONE(true,true, 4),
		MOUNTAIN(false,true, 5);
		
		private boolean walkable;
		private boolean canBuildOn;
		private int nr;
		
		Terrain(boolean walkable, boolean canBuildOn, int nr){
			this.walkable = walkable;
			this.canBuildOn = canBuildOn;
			this.nr = nr;
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
	}
}
