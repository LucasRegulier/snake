
public class Coordonnees {
	int x;
	int y;
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public boolean estEgal(Coordonnees co1) {
		boolean reponse = true;
		if((co1.getX()) == (this.getX()) && (co1.getY()) == (this.getY())) {
			reponse = true;
		}
		return reponse;
	}
	
}
