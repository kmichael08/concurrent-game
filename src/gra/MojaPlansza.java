package gra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MojaPlansza implements Plansza {
	int wysokosc, szerokosc;	
	List<Postać> naMapie;
	Postać[][] plansza;
	Map<Postać, Jednostka> jednostki;
	
	public MojaPlansza(int wysokosc, int szerokosc) {
		this.wysokosc = wysokosc;
		this.szerokosc = szerokosc;
		plansza = new Postać[wysokosc][szerokosc];
		naMapie = new ArrayList<Postać>();
		jednostki = new HashMap<Postać, Jednostka>();
	}
	
	/**
	 * @param postać
	 * @return id of the unit
	 */
	private synchronized int wezId(Postać postać) {
		if (postać == null)
			return 0;
		return jednostki.get(postać).dajId();
	}
		
	/**
	 * Display the board.
	 */
	public synchronized void wyswietl() {
		for (int i = 0; i < wysokosc; i++) {
			for (int j = 0; j < szerokosc; j++)
				System.out.print(wezId(plansza[i][j]) + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * @param y - y coordinate of top-left corner
	 * @param x - x coordinate of top-left corner
	 * @param h - height
	 * @param w - width
	 * @return is the area free
	 */
	private synchronized boolean jestWolne(int y, int x, int h, int w) {
		for (int i = y; i < y + h; i++)
			for (int j = x; j < x + w; j++)
				if (plansza[i][j] != null)
					return false;
		return true;
	}
	
	public synchronized void postaw(Postać postać, int wiersz, int kolumna)
			throws InterruptedException {
		
		if (naMapie.contains(postać))
			throw new IllegalArgumentException("Unit already on the board!");
		
		if (wiersz < 0 || kolumna < 0 || wiersz + postać.dajWysokość() >= wysokosc|| 
				kolumna + postać.dajSzerokość() >= szerokosc)
			throw new IllegalArgumentException("Unit can't go out of the board!");
		
		while(!jestWolne(wiersz, kolumna, postać.dajWysokość(), postać.dajSzerokość()))
			wait();
		
		naMapie.add(postać);
		Jednostka nowa = new Jednostka(postać.dajWysokość(), postać.dajSzerokość(), kolumna, wiersz);
		jednostki.put(postać, nowa);
		
		
		for (int i = wiersz; i < wiersz + postać.dajWysokość(); i++)
			for (int j = kolumna; j < kolumna + postać.dajSzerokość(); j++)
				plansza[i][j] = postać;
	}

	public synchronized void przesuń(Postać postać, Kierunek kierunek)
			throws InterruptedException, DeadlockException {
		if (!naMapie.contains(postać))
			throw new IllegalArgumentException("Unit is not on the board!");
		
		Jednostka jednostka = jednostki.get(postać);
		if (!isMovable(jednostka, kierunek))
			throw new IllegalArgumentException("Unit would go out of the board.");
		
		while (!wolnePrzesun(jednostka, kierunek)) {
			System.out.println("Wait for some reason!");
			wait();
		}
		
		przesunNaPlanszy(jednostka, kierunek);
		
		notifyAll();
	}
	
	private synchronized void przesunNaPlanszy(Jednostka jednostka, Kierunek kierunek) {
		Postać temp;
		
		if (kierunek == Kierunek.GÓRA) {
			for (int i = jednostka.x(); i < jednostka.x() + jednostka.dajSzerokość(); i++) {
				temp = plansza[jednostka.y() + jednostka.dajWysokość() - 1][i];
				plansza[jednostka.y() - 1][i] = temp;
				plansza[jednostka.y() + jednostka.dajWysokość() - 1][i] = null;
			}
			
			jednostka.moveUp();
		}
		
		if (kierunek == Kierunek.DÓŁ) {
			for (int i = jednostka.x(); i < jednostka.x() + jednostka.dajSzerokość(); i++) {
				temp = plansza[jednostka.y()][i];
				plansza[jednostka.y() + jednostka.dajWysokość()][i] = temp;
				plansza[jednostka.y()][i] = null;
			}
			
			jednostka.moveDown();
		}
		
		if (kierunek == Kierunek.LEWO) {
			for (int i = jednostka.y(); i < jednostka.y() + jednostka.dajWysokość(); i++) {
				temp = plansza[i][jednostka.x() + jednostka.dajSzerokość() - 1];
				plansza[i][jednostka.x() - 1] = temp;
				plansza[i][jednostka.x() + jednostka.dajSzerokość() - 1] = null;
			}
			
			jednostka.moveLeft();
		}
		
		if(kierunek == Kierunek.PRAWO) {
			for (int i = jednostka.y(); i < jednostka.y() + jednostka.dajWysokość(); i++) {
				temp = plansza[i][jednostka.x()];
				plansza[i][jednostka.x() + jednostka.dajSzerokość()] = temp;
				plansza[i][jednostka.x()] = null;
			}
			
			jednostka.moveRight();
		}
		
	}

	/**
	 * @param jednostka
	 * @param kierunek
	 * @return is the area free to move the unit.
	 */
	private synchronized boolean wolnePrzesun(Jednostka jednostka, Kierunek kierunek) {
		int h, w;
		int x, y;
		
		if (kierunek == Kierunek.DÓŁ) {
			h = 1;
			w = jednostka.dajSzerokość();
			x = jednostka.x();
			y = jednostka.y() + jednostka.dajWysokość();
		}
		else
		if (kierunek == Kierunek.GÓRA) {
			h = 1;
			w = jednostka.dajSzerokość();
			x = jednostka.x();
			y = jednostka.y() - 1;
		}
		else
		if (kierunek == Kierunek.LEWO) {
			h = jednostka.dajWysokość();
			w = 1;
			x = jednostka.x() - 1;
			y = jednostka.y();
		}
		else
		{
			h = jednostka.dajWysokość();
			w = 1;
			x = jednostka.x() + jednostka.dajSzerokość();
			y = jednostka.y();
		}
		
		return jestWolne(y, x, h, w);
	}
	

	/**
	 * @param jednostka
	 * @param kierunek
	 * @return can unit be moved, so that it will stay on the board
	 */
	private synchronized boolean isMovable(Jednostka jednostka, Kierunek kierunek) {
		if (kierunek == Kierunek.GÓRA && jednostka.y() == 0)
			return false;
		if (kierunek == Kierunek.DÓŁ && jednostka.y() == wysokosc - 1)
			return false;
		if (kierunek == Kierunek.LEWO && jednostka.x() == 0)
			return false;
		if (kierunek == Kierunek.PRAWO && jednostka.x() == szerokosc - 1);
		
		return true;
	}

	
	/**
	 * Delete the unit from the board.
	 */
	public synchronized void usuń(Postać postać) {
		if (!naMapie.contains(postać))
			throw new IllegalArgumentException("Unit is not on the board!");
		else {
			Jednostka jednostka = jednostki.get(postać);
			
			for (int i = jednostka.y(); i < jednostka.y() + jednostka.dajWysokość(); i++)
				for(int j = jednostka.x(); i < jednostka.x() + jednostka.dajSzerokość(); j++)
					plansza[i][j] = null;
			
			jednostki.remove(postać);
			naMapie.remove(postać);
		}
		
		notifyAll();		
	}
	

	public synchronized void sprawdź(int wiersz, int kolumna, Akcja jeśliZajęte,
			Runnable jeśliWolne) {
		Postać postać = plansza[wiersz][kolumna];
		if (postać != null)
			jeśliZajęte.wykonaj(postać);
		else
			new Thread(jeśliWolne).start();
	}

	
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
		
	}

}
