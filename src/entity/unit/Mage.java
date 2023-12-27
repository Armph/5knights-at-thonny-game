package entity.unit;

import entity.base.Levelable;
import entity.base.Unit;

public class Mage extends Unit implements Levelable {
	
	protected int spelldamage = 100;

	public Mage(String name,String URL) {
		super(name, URL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		this.level += 1;
		super.setAttack(super.getAttack() + 20);
		super.setMaxHealth(super.getMaxHealth() + 50);
		super.setMaxShieldPhy(super.getMaxShieldPhy() + 25);
		setSpelldamage(this.getSpelldamage() + 50);
		reset();
	}

	public int getSpelldamage() {
		return spelldamage;
	}

	public void setSpelldamage(int spelldamage) {
		if (spelldamage < 0) spelldamage = 0;
		this.spelldamage = spelldamage;
	}
	
	public void reset() {
		setAlive(true);
		setHealth(maxHealth);
		setShieldPhy(maxShieldPhy);
		setAttack(basicAttack);
	}

}
