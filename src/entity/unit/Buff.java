package entity.unit;

import entity.base.BuffATKable;
import entity.base.BuffShieldable;
import entity.base.Levelable;
import entity.base.Unit;

public class Buff extends Unit implements BuffATKable, BuffShieldable, Levelable{

	private int BuffAttack = 30;
	private int BuffShield = 20;

	public Buff(String name, String URL) {
		super(name, URL);
		// TODO Auto-generated constructor stub
	}

	public int getBuffAttack() {
		return BuffAttack;
	}

	public void setBuffAttack(int buffAttack) {
		if (buffAttack < 0) buffAttack = 0;
		BuffAttack = buffAttack;
	}

	public int getBuffShield() {
		return BuffShield;
	}

	public void setBuffShield(int buffShield) {
		if (buffShield < 0) buffShield = 0;
		BuffShield = buffShield;
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		this.level += 1;
		super.setBasicAttack(super.getBasicAttack() + 20);
		super.setAttack(super.getAttack() + 20);
		super.setMaxHealth(super.getMaxHealth() + 50);
		super.setMaxShieldPhy(super.getMaxShieldPhy() + 10);
		setBuffAttack(this.getBuffAttack() + 30);
		setBuffShield(this.getBuffShield() + 20);
		reset();
	}

	@Override
	public void buffShield(Unit e) {
		// TODO Auto-generated method stub
		int phy = e.getShieldPhy()+BuffShield;
		if(e instanceof Tank) {
			int magic = ((Tank) e).getShieldMagic()+BuffShield;
			if(magic > ((Tank)e).getMaxShieldMagic()) magic = ((Tank)e).getMaxShieldMagic();
			((Tank) e).setShieldMagic(magic);
			System.out.println(e.getName()+" is set MagicShield to "+magic);
			
		}
		if(phy > e.getMaxShieldPhy()) phy = e.getMaxShieldPhy();
		e.setShieldPhy(phy);
		System.out.println(e.getName()+" is set PhyShield to "+phy);
	}

	@Override
	public void buffATK(Unit e) {
		// TODO Auto-generated method stub
		int atk = e.getAttack()+BuffAttack;
		e.setAttack(atk);
		System.out.println(e.getName()+" is set Attack to "+atk);
	}
	
	public void reset() {
		setAlive(true);
		setHealth(maxHealth);
		setShieldPhy(maxShieldPhy);
		setAttack(basicAttack);
	}

}
