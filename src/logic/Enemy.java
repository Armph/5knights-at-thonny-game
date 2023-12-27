package logic;

import java.util.ArrayList;

import entity.base.Unit;
import entity.unit.Buff;
import entity.unit.DPS;
import entity.unit.Heal;
import entity.unit.Mage;
import entity.unit.Tank;

public class Enemy {
	private ArrayList<Unit> unitsDeck = new  ArrayList<Unit>();
	
	public Enemy() {
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

	public ArrayList<Unit> getUnitsDeck() {
		return unitsDeck;
	}

	public void setUnitsDeck(ArrayList<Unit> unitsDeck) {
		this.unitsDeck = unitsDeck;
	}
	
	public boolean isAllDead() {
		for(Unit u : unitsDeck) {
			if(u.isAlive()) return false;
		}
		return true;
	}

	

}
