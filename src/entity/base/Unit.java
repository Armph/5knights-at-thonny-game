package entity.base;

public abstract class Unit implements Levelable {
	protected String name;
	protected int maxHealth = 100;
	protected int health = 100;
	protected int basicAttack = 20;
	protected int attack = 20;
	protected int maxShieldPhy = 50;
	protected int shieldPhy = 50;
	protected int level = 1;
	protected boolean isAlive = true;
	protected String URL;

	public Unit(String name, String URL) {
		setName(name);
		setURL(URL);
	}
	
	public abstract void levelUp();
	
	public abstract void reset();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		if (maxHealth < 1)
			maxHealth = 1;
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health < 1)
			health = 1;
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		if (attack < 0)
			attack = 1;
		this.attack = attack;
	}

	public int getShieldPhy() {
		return shieldPhy;
	}

	public void setShieldPhy(int shieldPhy) {
		if (shieldPhy < 0)
			shieldPhy = 0;
		this.shieldPhy = shieldPhy;
	}

	public int getMaxShieldPhy() {
		return maxShieldPhy;
	}

	public void setMaxShieldPhy(int maxShieldPhy) {
		this.maxShieldPhy = maxShieldPhy;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		// can't decrease level
		if (level < 1)
			level = 1;
		while (this.level < level) {
			levelUp();
		}
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public int getBasicAttack() {
		return basicAttack;
	}

	public void setBasicAttack(int basicAttack) {
		this.basicAttack = basicAttack;
	}
	
	public void decreaseHealth(int damage) {
		if(damage > this.health) damage = this.health;
		this.health -= damage;
		if(health <= 0) this.isAlive = false;
	}
	
	public int decreaseShieldPhy(int damage) {
		if(damage > this.shieldPhy) {
			int damageTohealth = damage-shieldPhy;
			this.shieldPhy = 0;
			return damageTohealth;
		}
		else {
			this.shieldPhy -= damage;
			return 0;
		}
	}

}
