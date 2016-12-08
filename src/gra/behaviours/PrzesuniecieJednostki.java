package gra.behaviours;

import gra.DeadlockException;
import gra.Kierunek;
import gra.MojaPlansza;
import gra.Postać;

import java.util.Random;

public class PrzesuniecieJednostki implements Runnable {
	  private final MojaPlansza plansza;
	  private final Postać postać;
	  private final Kierunek kierunek;
	  private Random rand;

	  public PrzesuniecieJednostki(MojaPlansza plansza, Postać postać, Kierunek kierunek) {
	    this.plansza = plansza;
	    this.postać = postać;
	    this.kierunek = kierunek;
	    rand = new Random();
	  }

	  @Override
	  public void run() {
	    
	    System.out.println("Thread: " + Thread.currentThread().getName() + " started moving");
	    
	    try {
			Thread.sleep(getRandom(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			plansza.przesuń(postać, kierunek);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    plansza.wyswietl();
	    System.out.println("Thread: " + Thread.currentThread().getName() + " finished moving.");
	  }

	private int getRandom(int i) {
		return rand.nextInt(i);
	}
}

