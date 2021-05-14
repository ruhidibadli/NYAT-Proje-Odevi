package proje1;
import java.util.Scanner;
import java.util.Random;

public class Eyleyici implements IEyleyici {
	private boolean acikmi;
	private SicaklikAlgilayici algilayici1;
	private final Scanner sc1 = new Scanner(System.in);
	private Random rn = new Random();
	ISubject diyotlar;
	
	
	public Eyleyici(ISubject diyotlar) {
		this.acikmi = false;
		this.algilayici1 = new SicaklikAlgilayici();
		this.diyotlar = diyotlar;
	}
	
	
	public void SogutucuAc() throws Exception {
		String m = "Sogutucu calistirildi.";
		if(this.acikmi) 
			System.out.println("Sogutucu zaten acik\n");
		else {
			this.acikmi = true;
			diyotlar.notify(m);
			while(true) {
				int temp = rn.nextInt(150);
				if(temp < 150 && temp >= 20) {
					this.algilayici1.setSicaklik(temp);
					break;
				}
			}
			
		}
	}
	
	public void SicaklikOku() {
		System.out.println("Sicaklik: " + algilayici1.getSicaklik());
	}
	
	public void SogutucuKapat() {
		String m = "Sogutucu kapatildi.";
		if(this.acikmi == false)
			System.out.println("Sogutucu zaten kapali");
		else {
			this.acikmi = false;
			diyotlar.notify(m);
		}
	}


}
