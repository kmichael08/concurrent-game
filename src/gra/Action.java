package gra;

public class Action implements Akcja {


	@Override
	public void wykonaj(Postać postać) {
		System.out.println("Field is occupied!");
		
	}

}
