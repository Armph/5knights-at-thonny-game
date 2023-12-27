package entity.item;

import entity.base.BuffShieldable;
import entity.base.Items;
import entity.base.Unit;
import entity.unit.Tank;

public class BuffShieldPotion extends Items implements BuffShieldable{
	int buffShield;
	
	public BuffShieldPotion() {
		super("Buff Shield Potion");
		this.setURL("BuffShieldPotion URL"); //มาแก้ URL ด้วย
		buffShield = 30;//เลข 50 เดี๋ยวค่อยมาแก้อีกที
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buffShield(Unit e) {
		// TODO Auto-generated method stub
		int phy = e.getShieldPhy()+buffShield;
		if(e instanceof Tank) {
			int magic = ((Tank) e).getShieldMagic()+buffShield;
			if(magic > ((Tank)e).getMaxShieldMagic()) magic = ((Tank)e).getMaxShieldMagic();
			((Tank) e).setShieldMagic(magic);
			System.out.println(e.getName()+" is set MagicShield to "+magic);
		}
		if(phy > e.getMaxShieldPhy()) phy = e.getMaxShieldPhy();
		e.setShieldPhy(phy);
		System.out.println(e.getName()+" is set PhyShield to "+phy);
		this.setCount(this.getCount()-1);
	}

}
