package surrealdefense.map.objects;

import java.util.LinkedList;
import java.util.List;

public class LevelStats {
	private int money;
	private List<StatisticChangeListener> statisticChangeListeners;
	
	public LevelStats(){
		statisticChangeListeners = new LinkedList<>();
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		fireStatisticChangeEvent();
	}

	public void addMoney(int money){
		this.money += money;
		fireStatisticChangeEvent();
	}
	
	public void spendMoney(int money){
		this.money -= money;
		fireStatisticChangeEvent();
	}
	
	public void fireStatisticChangeEvent(){
		for (StatisticChangeListener scl : statisticChangeListeners){
			scl.onStatisticChange();
		}
	}
	
	public void addStatisticChangeListener(StatisticChangeListener lcs){
		statisticChangeListeners.add(lcs);
	}
	
	public static abstract class StatisticChangeListener{
		public abstract void onStatisticChange();
	}
}
