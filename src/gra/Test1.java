package gra;

public class Test1 {
	public static void main(String args[]) {
		System.out.println("Hello!");
		
		MojaPlansza board = new MojaPlansza(5, 8);
				
		Postać postać = new Unit(2, 2);
		
		try {
			board.postaw(postać, 1, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.LEWO);
		} catch (InterruptedException | DeadlockException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.GÓRA);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.PRAWO);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.DÓŁ);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.LEWO);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
						
		try {
			board.postaw(postać, 0, 4);
		} catch (InterruptedException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		Postać p2 = new Unit(3, 1);
		
		try {
			board.postaw(p2, 0, 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(p2, Kierunek.LEWO);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(p2, Kierunek.LEWO);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.DÓŁ);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
		
		try {
			board.przesuń(postać, Kierunek.DÓŁ);
		} catch (InterruptedException | DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.wyswietl();
	}

}