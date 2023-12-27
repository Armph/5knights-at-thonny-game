package logic;

import java.util.ArrayList;

import entity.base.Items;
import entity.base.Unit;
import entity.item.BuffATKPotion;
import entity.item.BuffShieldPotion;
import entity.item.HealPotion;
import entity.item.RevivePotion;
import entity.item.deBuffPotion;
import entity.unit.*;

public class Player {
	private int money;
	private ArrayList<Unit> unitsDeck = new ArrayList<Unit>();
	private ArrayList<Items> itemsDeck = new ArrayList<Items>();

	public Player() {
		this.money = 100;
		DPS dps = new DPS("DPS", null);
		Tank tank = new Tank("Tank", null);
		Heal heal = new Heal("Heal", null);
		Mage mage = new Mage("Mage", null);
		Buff buff = new Buff("Buff", null);
		unitsDeck.add(dps);
		unitsDeck.add(tank);
		unitsDeck.add(heal);
		unitsDeck.add(mage);
		unitsDeck.add(buff);
		BuffATKPotion buffAtk = new BuffATKPotion();
		BuffShieldPotion buffShield = new BuffShieldPotion();
		deBuffPotion debuff = new deBuffPotion();
		HealPotion healpotion = new HealPotion();
		RevivePotion revive = new RevivePotion();
		itemsDeck.add(buffAtk);
		itemsDeck.add(buffShield);
		itemsDeck.add(debuff);
		itemsDeck.add(healpotion);
		itemsDeck.add(revive);
	}
	
	public void levelUp_all() {
		for (Unit unit : unitsDeck) {
			unit.levelUp();
		}
	}
	public void levelUp_all(int level) {
		for (Unit unit : unitsDeck) {
			int curLevel = unit.getLevel();			
			unit.setLevel(curLevel+level);
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money < 0) money = 0;
		this.money = money;
	}

	public ArrayList<Unit> getUnitsDeck() {
		return unitsDeck;
	}

	public void setUnitsDeck(ArrayList<Unit> unitsDeck) {
		this.unitsDeck = unitsDeck;
	}

	public ArrayList<Items> getItemsDeck() {
		return itemsDeck;
	}

	public void setItemsDeck(ArrayList<Items> itemsDeck) {
		this.itemsDeck = itemsDeck;
	}
	
	public boolean isAllDead() {
		for(Unit u : unitsDeck) {
			//System.out.println(u.isAlive());
			if(u.isAlive()) return false;
		}
		return true;
	}
	
	public void pay(int cost) {
		setMoney(getMoney()-cost);
	}
	
	public void reward(int money) {
		setMoney(money+getMoney());
	}
}
