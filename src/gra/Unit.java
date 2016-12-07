package gra;

public class Unit implements Postać {
	private int wysokosc, szerokosc;
	
	public Unit(int wysokosc, int szerokosc) {
		this.wysokosc = wysokosc;
		this.szerokosc = szerokosc;
	}
	
	@Override
	public int dajWysokość() {
		return wysokosc;
	}

	@Override
	public int dajSzerokość() {
		return szerokosc;
	}
	
	

}
