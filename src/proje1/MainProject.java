package proje1;


public class MainProject {
	public static void main(String[] args) throws Exception {
		DataBaseOperations db1 = new DataBaseOperations.Builder().db_url("jdbc:postgresql://localhost:5432/nya_proje").user("postgres").password("3432646r").build();
		Uyarici u = new Uyarici();
		Diyot1 d1 = new Diyot1();
		Diyot2 d2  = new Diyot2();
		u.attach(d1);
		u.attach(d2);
		
		AgArayuzu ag1 = new AgArayuzu(u);
		ag1.Calistir();
		//System.out.println(db1.get_permission("ruhidibadli"));
	}
}
