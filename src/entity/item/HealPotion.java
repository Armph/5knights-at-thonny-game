package entity.item;

import entity.base.*;

public class HealPotion extends Items implements Healable {
	int healing;

	public HealPotion() {
		super("Heal Potion");
		this.setURL("HealPotion URL"); //มาแก้ URL ด้วย
		healing = 50; //เลข 50 เดี๋ยวค่อยมาแก้อีกที
		// TODO Auto-generated constructor stub
	}

	@Override
	public void heal(Unit e) {
		// TODO Auto-generated method stub
		int hp = e.getHealth()+healing;
		if(hp>e.getMaxHealth()) {
			hp = e.getMaxHealth();
		}
		e.setHealth(hp);
		System.out.println(e.getName()+" is set health to "+hp);
		this.setCount(this.getCount()-1);
	}

}
