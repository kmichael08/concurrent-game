package gra;

public class StawianieJednostek {
	  public static void main(String[] args) {

		    MojaPlansza plansza = new MojaPlansza(4, 4);
		    
		    Unit p1 = new Unit(2, 2);
		    Unit p2 = new Unit(2, 2);
		    Unit p3 = new Unit(1, 1);
		    Unit p4 = new Unit(2, 2);
		    Unit p5 = new Unit(4, 3);

		    Thread th1 = new Thread(new PostawienieIUsuniecieJednostki(plansza, p1, 0, 2));
		    Thread th2 = new Thread(new PostawienieIUsuniecieJednostki(plansza, p2, 1, 1));
		    Thread th3 = new Thread(new PostawienieIUsuniecieJednostki(plansza, p3, 2, 2));
		    Thread th4 = new Thread(new PostawienieIUsuniecieJednostki(plansza, p4, 1, 1));
		    Thread th5 = new Thread(new PostawienieIUsuniecieJednostki(plansza, p5, 0, 0));

		    th1.start();
		    th2.start();
		    th3.start();
		    th4.start();
		    th5.start();

		    try {
		      th1.join();
		      th2.join();
		      th3.join();
		      th4.join();
		      th5.join();
		    }
		    catch (InterruptedException e) {
		      e.printStackTrace();
		    }


		  }

}
