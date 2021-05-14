package proje1;
import java.util.ArrayList;
import java.util.List;

public class Uyarici implements ISubject {

	private List<IObserver> diyotlar = new ArrayList<>();
		
	@Override
	public void attach(IObserver diyot) {
		diyotlar.add(diyot);
		
	}

	@Override
	public void detach(IObserver diyot) {
		diyotlar.remove(diyot);
		
	}

	@Override
	public void notify(String m) {
		for(IObserver diyot:this.diyotlar) {
			diyot.update(m);
		}
		
	}

}
