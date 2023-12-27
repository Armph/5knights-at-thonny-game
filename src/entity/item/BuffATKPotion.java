package entity.item;

import entity.base.BuffATKable;
import entity.base.Items;
import entity.base.Unit;

public class BuffATKPotion extends Items implements BuffATKable{
	int BuffATK;

	public BuffATKPotion() {
		super("Buff Attack Potion");
		this.setURL("BuffATKPotion URL"); //มาแก้ URL ด้วย
		BuffATK = 30; //เลข 50 เดี๋ยวค่อยมาแก้อีกที
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buffATK(Unit e) {
		// TODO Auto-generated method stub
		int atk = e.getAttack()+BuffATK;
		e.setAttack(atk);
		System.out.println(e.getName()+" is set Attack to "+atk);
		this.setCount(this.getCount()-1);
	}

}
