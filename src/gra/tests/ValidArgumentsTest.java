package gra.tests;

import gra.Action;
import gra.DeadlockException;
import gra.Kierunek;
import gra.MojaPlansza;
import gra.Postać;
import gra.Unit;
import gra.behaviours.NotOccupied;

/**
 * Test for catching exceptions in all possible situations with IllegalArgumentException.
 */
public class ValidArgumentsTest {

	public static void main(String[] args) {
		MojaPlansza plansza = new MojaPlansza(7,  4);
		Postać p1 = new Unit(3, 3);
		
		/**
		 * Delete/Move unit that does not exist
		 */
		
		try {
		plansza.usuń(p1);
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		try {
			plansza.przesuń(p1, Kierunek.GÓRA);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  catch (IllegalArgumentException e) {
			  e.printStackTrace();
		}
		
		/**
		 * Check field outside the board
		 */
		try {
		plansza.sprawdź(2, 4, new Action(), new NotOccupied());
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		/**
		 * Put outside the board.
		 */
		Postać postać = new Unit(2, 2);
		
		try {
			plansza.postaw(postać, 0, 3);
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * Put an already existing unit.
		 */
		
		Postać p2 = new Unit(2, 2);
		
		try {
			plansza.postaw(p2, 1, 2);
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			plansza.postaw(p2, 1, 2);
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * Move the unit (twice) so it goes outside the board
		 */
		
		try {
			plansza.przesuń(p2, Kierunek.GÓRA);
		} catch (IllegalArgumentException | InterruptedException
				| DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			plansza.przesuń(p2, Kierunek.GÓRA);
		} catch (IllegalArgumentException | InterruptedException
				| DeadlockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
