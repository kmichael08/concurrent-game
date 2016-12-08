package gra.tests;

import gra.Kierunek;
import gra.MojaPlansza;
import gra.Postać;
import gra.Unit;
import gra.behaviours.PrzesuniecieJednostki;
import gra.behaviours.UstawJednostke;

/**
 * Test, where moving one unit enable putting three others.
 */
public class Test2 {

	public static void main(String[] args) {
		MojaPlansza plansza = new MojaPlansza(6,  7);
		
		Postać p1 = new Unit(3, 2);
		Postać p2 = new Unit(2, 1);
		Postać p3 = new Unit(1, 2);
		Postać p4 = new Unit(2, 2);
		
		Thread th1 = new Thread(new UstawJednostke(plansza, p1, 1, 1));
		Thread th2 = new Thread(new UstawJednostke(plansza, p2, 0, 1));
		Thread th3 = new Thread(new UstawJednostke(plansza, p3, 2, 0));
		Thread th4 = new Thread(new UstawJednostke(plansza, p4, 3, 0));
		
		
		th1.start();
		
		try {
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		th2.start();
		th3.start();
		th4.start();
		
				
		Thread th5 = new Thread(new PrzesuniecieJednostki(plansza, p1, Kierunek.PRAWO));
		
		th5.start();
		
		try {
			th2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			th3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			th4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			th5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
