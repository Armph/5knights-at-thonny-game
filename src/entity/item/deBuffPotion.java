package entity.item;

import entity.base.Items;
import entity.base.Unit;
import entity.base.deBuffable;
import entity.unit.Tank;

public class deBuffPotion extends Items implements deBuffable {
	int deBuffShield;

	public deBuffPotion() {
		super("Debuff Potion");
		deBuffShield = 50;
		this.setURL("deBuffPotion URL");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void debuff(Unit e) {
		// TODO Auto-generated method stub
		if(e instanceof Tank) {
			((Tank) e).decreaseShieldMagic(deBuffShield);
		}
		e.decreaseShieldPhy(deBuffShield);
		this.setCount(this.getCount()-1);
	}

}
