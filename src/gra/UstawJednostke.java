package gra;

public class UstawJednostke implements Runnable {
	  private final MojaPlansza plansza;
	  private final Postać postać;
	  private final int x;
	  private final int y;

	  public UstawJednostke(MojaPlansza plansza, Postać postać, int x, int y) {
	    this.plansza = plansza;
	    this.postać = postać;
	    this.x = x;
	    this.y = y;
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
	  }

}

