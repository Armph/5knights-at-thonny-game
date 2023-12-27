package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import entity.base.Items;
import entity.base.Unit;
import entity.item.*;
import entity.unit.*;

public class GameLogic {
	private static Player player;
	private static ArrayList<Unit> PlayerField = new ArrayList<Unit>();
	private static Enemy enemy;
	private static ArrayList<Unit> EnemyField = new ArrayList<Unit>();
	private static boolean isGameEnd = false;
	private static boolean isPlayerWin = false;
	private static boolean isPlayerLose = false;
	private static int Level;

	public static void init() {

	}

	public static void gameStart() {// start button
		Player player = new Player();
		setPlayer(player);
		resetEnemy();
	}
	
	public static void resetEnemy() {
		Level = 1;
		createEnemy(Level);
	}

	public static void createEnemy(int level) {// เลือกด่าน
		Enemy enemy = new Enemy();
		enemy.levelUp_all(level);
		setEnemy(enemy);
		ArrayList<Unit> EnemyDeck = getEnemy().getUnitsDeck();
		Collections.shuffle(EnemyDeck);

		ArrayList<Unit> enemyField = new ArrayList<Unit>();
		enemyField.add(EnemyDeck.get(0));
		enemyField.add(EnemyDeck.get(1));
		enemyField.add(EnemyDeck.get(2));
		setEnemyField(enemyField);
	}
	public static void reset_gameEnd() {
		setGameEnd(false);
		setPlayerLose(false);
		setPlayerWin(false);
	}
	public static void next_level() {
		setLevel(getLevel()+1);
		createEnemy(Level);
		reset_gameEnd();
	}
	
	public static void updatePlayerField(ArrayList<Unit> PlayerField) {
		setPlayerField(PlayerField);
	}

	public static void enemyAttack() {
		if (isGameEnd == true)
			return;
		int attackerIndex = ThreadLocalRandom.current().nextInt(0, 3);
		while (!EnemyField.get(attackerIndex).isAlive()) {
			attackerIndex = ThreadLocalRandom.current().nextInt(0, 3);
		}
		int defendIndex = 0;
		for (int i = 0; i < 3; i++) {
			if (PlayerField.get(i) == null)
				continue;
			if (PlayerField.get(i) instanceof Tank && PlayerField.get(i).isAlive()) {
				defendIndex = i;
				break;
			} else if (PlayerField.get(i).isAlive()) {
				defendIndex = i;
			}
		}
		Unit attack = getEnemyField().get(attackerIndex);
		Unit defend = getPlayerField().get(defendIndex);
		if (attack instanceof Mage) {
			if (defend instanceof Tank) {
				int damageToHealth = ((Tank) defend).decreaseShieldMagic(((Mage) attack).getSpelldamage());
				((Tank) defend).decreaseHealth(damageToHealth);
			} else {
				defend.decreaseHealth(((Mage) attack).getSpelldamage());
			}
		} else {
			int dth = defend.decreaseShieldPhy(attack.getAttack());
			defend.decreaseHealth(dth);
		}
		checkGameEnd();
	}

	public static void calculatePlayerAttack(int attacker, int defender) {
		if (isGameEnd == true)
			return;
		Unit attack = getPlayerField().get(attacker);
		Unit defend = getEnemyField().get(defender);
		if (attack instanceof Mage) {
			if (defend instanceof Tank) {
				int damageToHealth = ((Tank) defend).decreaseShieldMagic(((Mage) attack).getSpelldamage());
				((Tank) defend).decreaseHealth(damageToHealth);
			} else {
				defend.decreaseHealth(((Mage) attack).getSpelldamage());
			}
		} else {
			int dth = defend.decreaseShieldPhy(attack.getAttack());
			defend.decreaseHealth(dth);
		}
		checkGameEnd();
	}
	
	public static void healAll(int indexHealer) {
		Heal healer = (Heal) PlayerField.get(indexHealer);
		for(int i = 0;i<3;i++) {
			if(PlayerField.get(i) == null) continue;
			healer.heal(PlayerField.get(i));
		}
	}
	
	public static void buffAll(int bufferIndex) {
		Buff buffer = (Buff) PlayerField.get(bufferIndex);
		for(int i = 0;i<3;i++) {
			if(PlayerField.get(i) == null) continue;
			buffer.buffATK(PlayerField.get(i));
			buffer.buffShield(PlayerField.get(i));
		}
	}

	public static void usePotion(int indexPotion, int indexUnit) {
		Items it = getPlayer().getItemsDeck().get(indexPotion);
		if (it.getCount() == 0)
			return;
		boolean isPlayerTeam = true;
		if (indexUnit > 2) {
			isPlayerTeam = false;
		}
		Unit unit;
		if (isPlayerTeam) {
			unit = getPlayerField().get(indexUnit);
		} else {
			unit = getEnemyField().get(indexUnit - 3);
		}

		if (it instanceof deBuffPotion) {
			((deBuffPotion) it).debuff(unit);
		} else if (it instanceof BuffATKPotion) {
			((BuffATKPotion) it).buffATK(unit);
		} else if (it instanceof BuffShieldPotion) {
			((BuffShieldPotion) it).buffShield(unit);
		} else if (it instanceof HealPotion) {
			((HealPotion) it).heal(unit);
		} else if (it instanceof RevivePotion) {
			((RevivePotion) it).revive(unit);
		}
	}

	public static void updateGameStatus() {
		if (checkPlayerLose()) {
			setPlayerLose(true);
			setPlayerWin(false);
		}
		if (checkPlayerwin()) {
			setPlayerLose(false);
			setPlayerWin(true);
		}
	}

	public static boolean checkPlayerLose() {
		for (Unit u : PlayerField) {
			if (u == null)
				continue;
			if (u.isAlive())
				return false;
		}
		return true;
	}

	public static boolean checkPlayerwin() {
		for (Unit u : EnemyField) {
			if (u == null)
				continue;
			if (u.isAlive())
				return false;
		}
		return true;
	}

	public static void checkGameEnd() {
		updateGameStatus();
		isGameEnd = isPlayerLose | isPlayerWin;
	}

	public static void resetPlayerUnit() {
		for (int i = 0; i < player.getUnitsDeck().size(); i++) {
			Unit u = player.getUnitsDeck().get(i);
			u.reset();
		}
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		GameLogic.player = player;
	}

	public static ArrayList<Unit> getPlayerField() {
		return PlayerField;
	}

	public static void setPlayerField(ArrayList<Unit> playerField) {
		PlayerField = playerField;
	}

	public static Enemy getEnemy() {
		return enemy;
	}

	public static void setEnemy(Enemy enemy) {
		GameLogic.enemy = enemy;
	}

	public static ArrayList<Unit> getEnemyField() {
		return EnemyField;
	}

	public static void setEnemyField(ArrayList<Unit> enemyField) {
		EnemyField = enemyField;
	}

	public static boolean isGameEnd() {
		return isGameEnd;
	}

	public static void setGameEnd(boolean isGameEnd) {
		GameLogic.isGameEnd = isGameEnd;
	}

	public static int getLevel() {
		return Level;
	}

	public static void setLevel(int level) {
		GameLogic.Level = level;
	}

	public static boolean isPlayerWin() {
		return isPlayerWin;
	}

	public static void setPlayerWin(boolean isPlayerWin) {
		GameLogic.isPlayerWin = isPlayerWin;
	}

	public static boolean isPlayerLose() {
		return isPlayerLose;
	}

	public static void setPlayerLose(boolean isPlayerLose) {
		GameLogic.isPlayerLose = isPlayerLose;
	}

}
