package entity.unit;

import entity.base.Healable;
import entity.base.Levelable;
import entity.base.Unit;

public class Heal extends Unit implements Healable, Levelable {
	
	private int heal;
	
	public Heal(String name, String URL) {
		super(name, URL);
		// TODO Auto-generated constructor stub
		setHeal(this.getHeal() + 20*super.getLevel());
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		this.level += 1;
		super.setBasicAttack(super.getBasicAttack() + 20);
		super.setAttack(super.getAttack() + 20);
		super.setMaxHealth(super.getMaxHealth() + 40);
		super.setMaxShieldPhy(super.getMaxShieldPhy() + 25);
		super.setMaxHealth(this.getMaxHealth() + 20);
		setHeal(getHeal() + 20); 
		reset();
	}

	@Override
	public void heal(Unit e) {
		// TODO Auto-generated method stub
		e.setHealth(Math.min(e.getMaxHealth(), e.getHealth()+this.heal));
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(int heal) {
		if (heal < 0) heal = 0;
		this.heal = heal;
	}
	
	public void reset() {
		setAlive(true);
		setHealth(maxHealth);
		setShieldPhy(maxShieldPhy);
		setAttack(basicAttack);
	}

}
