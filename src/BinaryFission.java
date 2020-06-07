import java.math.*;
import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;

public class BinaryFission {

	int living;
	int dead;
	int oxygenLevel;
	int toxinLevel;
	public BinaryFission(int startingBacteria, int startingOxygen) {
		this.living = startingBacteria;
		this.dead = 0;
		this.oxygenLevel = startingOxygen;
		this.toxinLevel = 0;
	}
	
	public void binaryFission() {
		this.living = 2*this.living;
	}
	
	public void metabolise() {
		this.oxygenLevel = this.oxygenLevel - this.living;
		this.toxinLevel = this.toxinLevel + this.living;
	}
	
	public void bacterialDeath() {
		if(this.toxinLevel > 0.05*this.oxygenLevel) {
			int change = (int) (Math.round(0.5*this.living));
			this.dead = this.dead + change;
			this.living = this.living - change;
		}
		if(this.living > 0.05*this.oxygenLevel) {
			int change = (int) (Math.round(0.3*this.living));
			this.dead = this.dead + change;
			this.living = this.living - change;
		}
		if(this.oxygenLevel == 0) {
			this.dead = this.dead + this.living;
			this.living = 0;
		}
	}
	
	public int getLiving() {
		return this.living;
	}
	
	public int getDead() {
		return this.dead;
	}
	
	public int getOxygen() {
		return this.oxygenLevel;
	}
	
	public int getToxin() {
		return this.toxinLevel;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int startingBacteria = Math.round((int) (10*Math.random()));
		int startingOxygen = Math.round((int) (10000000*startingBacteria));
		BinaryFission bacteriaColony = new BinaryFission(startingBacteria, startingOxygen);

		try {
			PrintWriter myWriter1 = new PrintWriter("binary_fission.csv");
			myWriter1.write("");
			myWriter1.close();
			
			FileWriter myWriter = new FileWriter("binary_fission.csv");
	        int counter = 0;
			while(bacteriaColony.living > 0) {
				bacteriaColony.binaryFission();
				bacteriaColony.metabolise();
				bacteriaColony.bacterialDeath();
				myWriter.write(bacteriaColony.getLiving() + "," + bacteriaColony.getDead() + "," + bacteriaColony.getOxygen() + "," + bacteriaColony.getToxin());
				myWriter.write("\r\n");
				counter++;
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}

}
