package gra.tests;

import gra.Kierunek;
import gra.MojaPlansza;
import gra.Unit;
import gra.behaviours.PrzesuniecieJednostki;
import gra.behaviours.UstawJednostke;

/**
 * Chain of moves, which are all triggered after the last one move. 
 */
public class PrzesuwanieTest {
	
	public static void main(String[] args) {
	    MojaPlansza plansza = new MojaPlansza(4, 4);
	    
	    Unit p1 = new Unit(1, 1);
	    Unit p2 = new Unit(1, 1);
	    Unit p3 = new Unit(1, 1);
	    Unit p4 = new Unit(1, 1);
	    

	    Thread th1 = new Thread(new UstawJednostke(plansza, p1, 1, 1));
	    Thread th2 = new Thread(new UstawJednostke(plansza, p2, 1, 2));
	    Thread th3 = new Thread(new UstawJednostke(plansza, p3, 2, 1));
	    Thread th4 = new Thread(new UstawJednostke(plansza, p4, 2, 2));

	    th1.start();
	    th2.start();
	    th3.start();
	    th4.start();

	    try {
	      th1.join();
	      th2.join();
	      th3.join();
	      th4.join();
	    }
	    catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    
	    plansza.wyswietl();
	    
	    	    
	    Thread th5 = new Thread(new PrzesuniecieJednostki(plansza, p4, Kierunek.GÓRA));
	    Thread th6 = new Thread(new PrzesuniecieJednostki(plansza, p2, Kierunek.LEWO));
	    Thread th7 = new Thread(new PrzesuniecieJednostki(plansza, p1, Kierunek.DÓŁ));
	    Thread th8 = new Thread(new PrzesuniecieJednostki(plansza, p3, Kierunek.DÓŁ));
	    
	    th5.start();
	    th6.start();
	    th7.start();
	    th8.start();
	    
	    try {
		      th5.join();
		      th6.join();
		      th7.join();
		      th8.join();
		}
		    catch (InterruptedException e) {
		      e.printStackTrace();
		 }
	    	    
	    plansza.wyswietl();

	  }
	

}


