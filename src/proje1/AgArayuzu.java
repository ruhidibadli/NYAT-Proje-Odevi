package proje1;
import java.util.Scanner;

public class AgArayuzu {
	private IEyleyici ey1;
	private DataBaseOperations db1;
	final Scanner sc1 = new Scanner(System.in);
	
	
	public AgArayuzu(ISubject uyarici) {
		this.ey1 = new Eyleyici(uyarici);
		this.db1 = new DataBaseOperations.Builder().db_url("jdbc:postgresql://localhost:5432/nya_proje").user("postgres").password("3432646r").build();
	}
	
	public void Calistir() throws Exception {
		main:
		while(true) {
			int secim = 0;
			String ana_menu = """
					Sogutucu Sistem
					1- Sicaklik Goster
					2- Sogutucuyu ac
					3- Sogutucuyu kapat
					4- Cikis\n
					""";
			String giris_menu = """
					Sisteme hos geldiniz!
					Sistemi goruntulemek icin giris yapmaniz gerekmektedir.
					1- Giris
					2- Kayit ol
					3- Cikis\n
			
					""";
			System.out.println(giris_menu);
			secim = sc1.nextInt();
			
			
			
			switch (secim) {
			case 1:
				System.out.println("Kullanici adinizi giriniz: ");
				String username1 = sc1.next();
				System.out.println("Sifrenizi giriniz: ");
				String password1 = sc1.next();
				if(this.db1.login(username1, password1)) {
					if(this.db1.get_permission(username1) == 1) {
						menu:
						while(true) {
							System.out.println(ana_menu);
							secim = sc1.nextInt();
							
							switch(secim) {
								case 1:
									this.ey1.SicaklikOku();
									break;
								case 2:
									this.ey1.SogutucuAc();
									break;
								case 3:
									this.ey1.SogutucuKapat();
									break;
								case 4:
									System.out.println("Cikis islemi basarili !");
									break main;
							}
						}
					}
					else {
						System.out.println("Islem yapmak icin " + username1 + " kullanicisinin yetkisi yoktur!!!" );
						break main;
					}
				}
				break main;
			case 2:
				System.out.println("Kullanici adinizi giriniz: ");
				String username = sc1.next();
				System.out.println("Sifrenizi giriniz: ");
				String password = sc1.next();
				this.db1.registration(username, password);
				break main;
			case 3:
				System.out.println("Cikis islemi basarili!");
				break main;
			default:
				System.out.println("Gecerli bir islem seciniz lutfen.");
				break;
			}
		}
	}
}
