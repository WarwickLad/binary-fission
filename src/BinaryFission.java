import java.math.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

public class BinaryFission {

	int oxygen; // total oxygen available to colony
	int toxins; // total toxins present in colony
	int nAlive; // total number of bacteria alive
	int nDead; // total number of bacteria dead
	ArrayList<bacterium> bacteriaColony;
	ArrayList<bacterium> deadColony;

	public BinaryFission(int startingOxygen, int startingToxins, int nAlive) { // class constructor
		this.bacteriaColony = new ArrayList<bacterium>();
		this.deadColony = new ArrayList<bacterium>();
		this.oxygen = startingOxygen;
		this.toxins = startingToxins;
		for(int i = 0; i < nAlive; i++) {
			bacteriaColony.add(new bacterium());
		}
	}
	
	public int toxicity() {
		int toxicity = (int) Math.round(this.nAlive*(0.7 + 0.1*Math.random())); // value set at 0.4-0.64 of current population
		return toxicity;
	}
	
	public void metabolise() { // bacteria metabolise via aerobic or anaerobic pathways
		
		int toxinLevel = toxicity(); // sets toxin threshold
		int oxygenThreshold = 100*this.nAlive; // sets oxygen threshold
		if(this.oxygen > oxygenThreshold) {
			for(int i = 0; i < this.nAlive; i++) {
				this.bacteriaColony.get(i).aerobicMetabolise();
				this.oxygen--;
				this.toxins++;
			}
		}else if(this.oxygen < 0) {
			for(int i = 0; i < this.nAlive; i++) {
				this.bacteriaColony.get(i).anaerobicMetabolise();
				this.toxins--;
			}
		} else {
			for(int i = 0; i < toxinLevel; i++) {
				this.bacteriaColony.get(i).anaerobicMetabolise();
				this.toxins--;
			}
			for(int i = toxinLevel; i < this.nAlive; i++) {
				this.bacteriaColony.get(i).aerobicMetabolise();
				this.oxygen--;
				this.toxins++;
			}
		}
	}
	
	public void mitosis() {
		
		for(int i = 0; i < this.bacteriaColony.size(); i++) { // if bacteria metabolism = 2 then binary fission occurs next round
			if(this.bacteriaColony.get(i).getMetabolicRate() > 1) {
				this.bacteriaColony.add(new bacterium());
			}
		}
	}
	
	public void bacteriaDeath() {
		
		for(int i = 0; i < this.bacteriaColony.size(); i++) { // dead bacteria checked and altered to end of list positions
			this.bacteriaColony.get(i).deathCheck();
		}
		
		bacterium deadBacteria;
		for(int i = 0; i < this.bacteriaColony.size(); i++) {
			if(this.bacteriaColony.get(i).getLifeState() == 0) {
				deadBacteria = this.bacteriaColony.get(i);
				this.bacteriaColony.remove(i);
				this.deadColony.add(deadBacteria);
				this.toxins = this.toxins + deadBacteria.getToxins();
			}
		}
		
		this.nDead = this.deadColony.size();
		this.nAlive = this.bacteriaColony.size();
		System.out.println(this.nAlive + " " + this.nDead + " " + this.oxygen + " " + this.toxins);
	}
	
	public static void main(String[] args) {
		BinaryFission newColony = new BinaryFission(10000,0,1);
		newColony.metabolise();
		newColony.mitosis();
		newColony.bacteriaDeath();
		
		try {
			PrintWriter myWriter1 = new PrintWriter("binary_fission.csv");
			myWriter1.write("");
			myWriter1.close();
			
			FileWriter myWriter = new FileWriter("binary_fission.csv");
	        
	        while(newColony.getnAlive() > 0) {
	        	myWriter.write(newColony.getnAlive() + "," + newColony.getnDead() + "," + newColony.getOxygen() + "," + newColony.getToxins());
				myWriter.write("\r\n");
				newColony.metabolise();
				newColony.mitosis();
				newColony.bacteriaDeath();
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
		
		
	}
	
	public int getOxygen() {
		return this.oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getToxins() {
		return this.toxins;
	}

	public void setToxins(int toxins) {
		this.toxins = toxins;
	}

	public int getnAlive() {
		return this.nAlive;
	}

	public void setnAlive(int nAlive) {
		this.nAlive = nAlive;
	}

	public int getnDead() {
		return this.nDead;
	}

	public void setnDead(int nDead) {
		this.nDead = nDead;
	}

	public ArrayList<bacterium> getBacteriaColony() {
		return bacteriaColony;
	}

	public void setBacteriaColony(ArrayList<bacterium> bacteriaColony) {
		this.bacteriaColony = bacteriaColony;
	}

	public ArrayList<bacterium> getDeadColony() {
		return deadColony;
	}

	public void setDeadColony(ArrayList<bacterium> deadColony) {
		this.deadColony = deadColony;
	}
	
	
	
}
