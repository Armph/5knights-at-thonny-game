package entity.unit;

import entity.base.Levelable;
import entity.base.Unit;

public class DPS extends Unit implements Levelable {

	public DPS(String name, String URL) {
		super(name, URL);
		// TODO Auto-generated constructor stub
		super.setAttack(super.getAttack() + 100*super.getLevel());
		super.setBasicAttack(super.getBasicAttack() + 100*super.getLevel());
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		this.level += 1;
		super.setBasicAttack(super.getBasicAttack()+50);
		super.setAttack(super.getAttack() + 50);
		super.setMaxHealth(super.getMaxHealth() + 30);
		super.setMaxShieldPhy(super.getMaxShieldPhy() + 10);
		reset();
	}
	
	public void reset() {
		setAlive(true);
		setHealth(maxHealth);
		setShieldPhy(maxShieldPhy);
		setAttack(basicAttack);
	}

}
