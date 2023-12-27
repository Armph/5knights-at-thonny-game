package entity.item;

import entity.base.Items;
import entity.base.Reviveable;
import entity.base.Unit;

public class RevivePotion extends Items implements Reviveable {

	public RevivePotion() {
		super("Revive Potion");
		this.setURL("RevivePotion URL"); //มาแก้ URL ด้วย
		// TODO Auto-generated constructor stub
	}

	@Override
	public void revive(Unit e) {
		// TODO Auto-generated method stub
		e.setAlive(true);
		e.setHealth(e.getMaxHealth());
		System.out.println(e.getName()+" is revived");
		this.setCount(this.getCount()-1);
	}

}
