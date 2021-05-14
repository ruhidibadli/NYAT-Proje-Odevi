package proje1;
import java.util.Random;


public class SicaklikAlgilayici {

	private int sicaklik  = 0;
	
	public SicaklikAlgilayici() {
		Random rn = new Random();
		this.sicaklik = rn.nextInt(150);
	}
	
	
	public int getSicaklik() {
		return this.sicaklik;
	}
	
	public void setSicaklik(int derece) {
		this.sicaklik = derece;
	}
	
}
