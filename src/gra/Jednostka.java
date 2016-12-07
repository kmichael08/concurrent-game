package gra;

public class Jednostka extends Unit {
	private static int autoIncr = 1;
	private int id;
	// upper left corner
	private int x, y;
	
	public Jednostka(int wysokosc, int szerokosc, int x, int y) {
		super(wysokosc, szerokosc);
		id = autoIncr++;
		this.x = x;
		this.y = y;
	}
	
	public int dajId() {
		return id;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void moveUp() {
		y--;
	}
	
	public void moveDown() {
		y++;
	}
	
	public void moveRight() {
		x++;
	}
	
	public void moveLeft() {
		x--;
	}
	
		
}
