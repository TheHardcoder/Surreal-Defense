package surrealdefense.screens.components;

import java.awt.Color;
import java.awt.GradientPaint;

import surrealdefense.map.objects.LevelStats;
import surrealdefense.map.objects.LevelStats.StatisticChangeListener;
import surrealdefense.map.objects.TowerType;
import ggui.components.AbstractComponent;
import ggui.components.Button;
import ggui.components.Label;

public class LevelMenu extends AbstractComponent {
	private SelectionChangeListener scl;
	
	private TowerType selectedTowerType;
	private TowerType[] towerTypes;
	private LevelStats lvlstats;
	private Label moneylbl;

	public LevelMenu(int x, int y, int width, int height, TowerType[] towers, LevelStats lvlstats) {
		super(x, y, width, height);
		renderComponent();
		this.lvlstats = lvlstats;
		towerTypes = towers;
		moneylbl = new Label(x+20, y+30, ""+ lvlstats.getMoney(), null, null, true);
		moneylbl.setMinwidth(150);
		children.add(moneylbl);
		System.out.println(towers.length);
		for (int i = 0; i < towers.length; i++){
			final int nr = i;
			Button b = new Button(x + 20, 80 + i * 60, "", towers[i].getImages()[0], null, new Runnable() {
				
				@Override
				public void run() {
					setSelectedTowerType(towerTypes[nr]);
				}
			});
			b.setPadding(Label.Padding.BOTTOM, 60);
			children.add(b);
		}
		lvlstats.addStatisticChangeListener(new StatisticChangeListener() {
			
			@Override
			public void onStatisticChange() {
				moneylbl.setLabel(""+LevelMenu.this.lvlstats.getMoney());
			}
		});
	}

	@Override
	public void renderComponent() {
		g2d.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), Color.GRAY));
		g2d.fillRoundRect(0, 0, width, height, 50, 50);
	}

	@Override
	public void updateComponent(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	public SelectionChangeListener getScl() {
		return scl;
	}

	public void setScl(SelectionChangeListener scl) {
		this.scl = scl;
	}

	public TowerType getSelectedTowerType() {
		return selectedTowerType;
	}

	public void setSelectedTowerType(TowerType selectedTowerType) {
		this.selectedTowerType = selectedTowerType;
		if (scl != null){
			scl.onSelectionChange();
		}
	}

	public static abstract class SelectionChangeListener{
		public abstract void onSelectionChange();
	}
}
