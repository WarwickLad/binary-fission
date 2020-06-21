
public class bacterium {
	
	int lifeState; // represents whether bacterium is dead or alive
	int metabolicRate; // establishes how much O2 is consumed per round. If 2, next round binary fission occurs
	int oxygen; // establishes O2 content of bacterium
	int toxins; // establishes toxin content of bacterium
	int fatalToxin; // establishes threshold for fatal toxin consumption before death
	
	public bacterium() { // class constructor with default values
		this.lifeState = 1;
		this.metabolicRate = 1;
		this.oxygen = 0;
		this.toxins = 0;
		this.fatalToxin = 2;
	}
	
	public void aerobicMetabolise() { // aerobic metabolism in presence of O2
		if(this.lifeState == 1) {
			for(int i = 0; i < this.metabolicRate; i++) {
				this.oxygen++;
			}
			this.metabolicRate++;
		}
	}
	
	public void anaerobicMetabolise() { // anaerobic metabolism without O2 presence
		if(this.lifeState == 1) {
			for(int i = 0; i < this.metabolicRate; i++) {
				this.toxins++;
			}
			if(this.metabolicRate > 1) {
				this.metabolicRate--;
			}
		}
	}
	
	public void deathCheck() { // checks if toxins consumed are at lethal dose
		if(this.fatalToxin <= this.toxins) {
			this.lifeState = 0;
		}
	}
	
	public int getLifeState() {
		return lifeState;
	}
	public void setLifeState(int lifeState) {
		this.lifeState = lifeState;
	}
	public int getMetabolicRate() {
		return metabolicRate;
	}
	public void setMetabolicRate(int metabolicRate) {
		this.metabolicRate = metabolicRate;
	}
	public int getOxygen() {
		return oxygen;
	}
	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}
	public int getToxins() {
		return toxins;
	}
	public void setToxins(int toxins) {
		this.toxins = toxins;
	}
	public int getFatalToxin() {
		return fatalToxin;
	}
	public void setFatalToxin(int fatalToxin) {
		this.fatalToxin = fatalToxin;
	}
}
