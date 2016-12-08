package gra.tests;

import gra.Action;
import gra.MojaPlansza;
import gra.Postać;
import gra.Unit;
import gra.behaviours.NotOccupied;
import gra.behaviours.UstawJednostke;

/**
 * Simple test for the void sprawdź.
 */
public class AkcjaTest {	
		
		
	public static void main(String[] args) {
		MojaPlansza plansza = new MojaPlansza(6, 6);
				
		plansza.sprawdź(2, 2, new Action(), new NotOccupied());
		
		Postać postać = new Unit(1, 1);

		Thread th = new Thread(new UstawJednostke(plansza, postać, 2, 2));
				
		th.start();
		
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		plansza.sprawdź(2, 2, new Action(), new NotOccupied());

	}

}

