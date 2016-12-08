package gra;

import java.util.Random;

public class PostawienieIUsuniecieJednostki implements Runnable {
	  private final MojaPlansza plansza;
	  private final Postać postać;
	  private final int x;
	  private final int y;
	  private Random rand;

	  public PostawienieIUsuniecieJednostki(MojaPlansza plansza, Postać postać, int x, int y) {
	    this.plansza = plansza;
	    this.postać = postać;
	    this.x = x;
	    this.y = y;
	    rand = new Random();
	  }

	  @Override
	  public void run() {
	    
	    System.out.println("Thread: " + Thread.currentThread().getName() + " started putting");
	    try {
	      plansza.postaw(postać, x, y);
	      plansza.wyswietl();
	    } catch (InterruptedException e) {
	      System.out.println(e.getMessage());
	    }
	    System.out.println("Thread: " + Thread.currentThread().getName() + " has put a unit");
	    try {
			Thread.sleep(getRandom(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Thread: " + Thread.currentThread().getName() + " starts deleting");
	    plansza.usuń(postać);
	    plansza.wyswietl();
	    System.out.println("Thread: " + Thread.currentThread().getName() + " finished deleting.");
	  }

	private int getRandom(int i) {
		return rand.nextInt(i);
	}
}

