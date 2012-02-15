package surrealdefense.map.objects;

public abstract class TowerType {
	public static final int BASE_DMG = 10;
	public static final int BAE_RANGE = 100;
	public static final double BASE_ATTACK_SPEED = 1.0;
	public static final double BASE_CRIT_CHANCE = 0.01;
	public static final double BASE_CRIT_DMG = 2;
	
	protected int dmg;
	protected int range;
	protected double attackspeed;
	protected int price;
	protected double crit;
	
	protected int randdmg = 0;

	public double getAttackspeed() {
		return attackspeed;
	}

	public int getPrice() {
		return price;
	}

	public TowerType(int dmg, int range, double attackspeed, int price) {
		super();
		this.dmg = dmg;
		this.range = range;
		this.attackspeed = attackspeed;
		this.price = price;
	}
	
	public double getDamage(){
		return (Math.random() <= crit) ? (dmg + randdmg) * BASE_CRIT_DMG : (dmg + randdmg);
	}
}
