package entity.base;

public abstract class Items {
	protected String name;
	protected int count;
	protected String URL;
	

	public Items(String name) {
		setName(name);
		setCount(0);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if(count < 0) count = 0;
		this.count = count;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
