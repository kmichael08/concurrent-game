package gra.tests;

import gra.MojaPlansza;
import gra.Postać;
import gra.Unit;

/**
 * Very simple one thread test.
 */
public class TestUsun {
	public static void main(String args[]) {
		MojaPlansza plansza = new MojaPlansza(4, 4);
		
		plansza.wyswietl();
		
		Postać postać = new Unit(4, 4);
		
		try {
			plansza.postaw(postać, 0, 0);
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plansza.wyswietl();
		
		plansza.usuń(postać);
		
		plansza.wyswietl();
		
	}		
}
