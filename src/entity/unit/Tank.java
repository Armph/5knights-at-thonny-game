package entity.unit;

import entity.base.Levelable;
import entity.base.Unit;

public class Tank extends Unit implements  Levelable {
	protected int maxShieldMagic = 150;
	protected int shieldMagic = 150;

	public Tank(String name, String URL) {
		super(name, URL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		this.level += 1;
		super.setBasicAttack(super.getBasicAttack() + 20);
		super.setAttack(super.getAttack() + 20);
		super.setMaxHealth(super.getMaxHealth() + 100);
		super.setMaxShieldPhy(super.getMaxShieldPhy() + 120);
		setMaxShieldMagic(this.getMaxShieldMagic() + 20);
		reset();
	}

	public int getMaxShieldMagic() {
		return maxShieldMagic;
	}

	public void setMaxShieldMagic(int maxShieldMagic) {
		this.maxShieldMagic = maxShieldMagic;
	}

	public int getShieldMagic() {
		return shieldMagic;
	}

	public void setShieldMagic(int shieldMagic) {
		this.shieldMagic = shieldMagic;
	}
	
	public int decreaseShieldMagic(int damage) {
		if(damage > getShieldMagic()) {
			int damageTohealth = damage - getShieldMagic();
			this.setShieldMagic(0);
			return damageTohealth;
		}
		else {
			this.shieldMagic -= damage;
			return 0;
		}
	}
	
	public void reset() {
		setAlive(true);
		setHealth(maxHealth);
		setShieldPhy(maxShieldPhy);
		setShieldMagic(maxShieldMagic);
		setAttack(basicAttack);
	}

}
