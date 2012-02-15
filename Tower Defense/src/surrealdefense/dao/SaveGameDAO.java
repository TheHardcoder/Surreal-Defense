package surrealdefense.dao;

import java.util.ArrayList;
import java.util.List;

import surrealdefense.map.objects.TowerType;

public class SaveGameDAO extends AbstractDAO {
	public static final String EMPTY_SAVE_GAME = "Neues Spiel";
	private String name = EMPTY_SAVE_GAME;
	private int level = 1;
	private int experience = 0;
	private List<TowerType> towers;

	public List<TowerType> getTower() {
		return towers;
	}

	public void addTower(TowerType tower) {
		this.towers.add(tower);
	}

	public SaveGameDAO(String name) {
		super();
		towers = new ArrayList<>();
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
