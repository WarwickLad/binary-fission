
public class bacterium {
	
	int lifeState;
	int metabolicRate;
	int oxygen;
	int toxins;
	int fatalToxin;
	
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
	
	public bacterium() {
		this.lifeState = 1;
		this.metabolicRate = 1;
		this.oxygen = 0;
		this.toxins = 0;
		this.fatalToxin = 2;
	}
	
	public void aerobicMetabolise() {
		if(this.lifeState == 1) {
			for(int i = 0; i < metabolicRate; i++) {
				this.oxygen = this.oxygen + 1;
				this.metabolicRate = 2;
			}
		}
	}
	
	public void anaerobicMetabolise() {
		if(this.lifeState == 1) {
			for(int i = 0; i < metabolicRate; i++) {
				this.toxins = this.toxins + 1;
				this.metabolicRate = 1;
			}
		}
	}
	
	public void deathCheck() {
		if(this.fatalToxin == this.toxins) {
			this.lifeState = 0;
		}
	}
	
}
