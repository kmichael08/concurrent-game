package gra.tests;

import java.util.ArrayList;
import java.util.List;

import gra.MojaPlansza;
import gra.Postać;
import gra.Unit;
import gra.behaviours.PrzesuniecieJednostki;
import gra.behaviours.UstawJednostke;
import gra.Kierunek;

/**
 * Test for deadlock.
 */
public class AnotherDeadlock {

	public static void main(String[] args) {
		MojaPlansza plansza = new MojaPlansza(11, 9);
		
		Postać p[] = { new Unit(1, 2), new Unit(3, 2), new Unit(2, 3), new Unit(5, 2), new Unit(1, 3) };
				
		
		List<Thread> threads = new ArrayList<>();
		threads.add(new Thread(new UstawJednostke(plansza, p[0], 1, 2)));
		threads.add(new Thread(new UstawJednostke(plansza, p[1], 2, 2)));
		threads.add(new Thread(new UstawJednostke(plansza, p[2], 5, 2)));
		threads.add(new Thread(new UstawJednostke(plansza, p[3], 2, 5)));
		threads.add(new Thread(new UstawJednostke(plansza, p[4], 1, 4)));
		
		for (Thread th : threads) {
			th.start();
		}
		
		for (Thread th : threads) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		threads.clear();
		
		Kierunek dirs[] = {Kierunek.DÓŁ, Kierunek.DÓŁ, Kierunek.PRAWO, Kierunek.GÓRA, Kierunek.LEWO};
		
		for (int i = 0; i < 5; i++) {
			threads.add(new Thread(new PrzesuniecieJednostki(plansza, p[i], dirs[i])));
		}
		
		for (Thread th : threads) {
			th.start();
		}
		
		for (Thread th : threads) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

}
}
