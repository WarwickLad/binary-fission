import java.math.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

public class BinaryFission {

	int oxygen;
	int toxins;
	int nAlive;
	int nDead;
	ArrayList<bacterium> bacteriaColony;
	ArrayList<bacterium> deadColony;
	
	
	
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

	public BinaryFission(int startingOxygen, int startingToxins, int nAlive) {
		this.bacteriaColony = new ArrayList<bacterium>();
		this.deadColony = new ArrayList<bacterium>();
		this.oxygen = startingOxygen;
		this.toxins = startingToxins;
		for(int i = 0; i < nAlive; i++) {
			bacteriaColony.add(new bacterium());
		}
	}
	
	public void generation() {
		
		int toxicity = (int) Math.round(this.nAlive*(0.4 + 0.25*Math.random()));
		if(this.oxygen > 10*this.nAlive) {
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
			for(int i = 0; i < toxicity; i++) {
				this.bacteriaColony.get(i).anaerobicMetabolise();
				this.toxins--;
			}
			for(int i = toxicity; i < this.nAlive; i++) {
				this.bacteriaColony.get(i).aerobicMetabolise();
				this.oxygen--;
				this.toxins++;
			}
		}
		
		for(int i = 0; i < this.bacteriaColony.size(); i++) { // if bacteria metabolism = 2 then binary fission occurs next round
			if(this.bacteriaColony.get(i).getMetabolicRate() == 2) {
				this.bacteriaColony.add(new bacterium());
			}
		}
		
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
		BinaryFission newColony = new BinaryFission(1000,0,1);
		newColony.generation();
		
		try {
			PrintWriter myWriter1 = new PrintWriter("binary_fission.csv");
			myWriter1.write("");
			myWriter1.close();
			
			FileWriter myWriter = new FileWriter("binary_fission.csv");
	        
	        while(newColony.getnAlive() > 0) {
	        	myWriter.write(newColony.getnAlive() + "," + newColony.getnDead() + "," + newColony.getOxygen() + "," + newColony.getToxins());
				myWriter.write("\r\n");
				newColony.generation();
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
		
		
	}
	
}
