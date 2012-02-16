package surrealdefense.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import surrealdefense.map.objects.TowerType;

public class SaveGameDAO extends AbstractDAO implements Serializable {
	public static final String EMPTY_SAVE_GAME = "Neues Spiel";
	private String name = EMPTY_SAVE_GAME;
	private int level = 1;
	private int experience = 0;
	private ArrayList<TowerType> towers;

	public ArrayList<TowerType> getTower() {
		return towers;
	}

	public void addTower(TowerType tower) {
		this.towers.add(tower);
	}

	public SaveGameDAO(String name) {
		super();
		towers = new ArrayList<>();
		towers.add(new TowerType(10,100,1.0,50,"default") {
		});
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5523842833454433967L;

}
