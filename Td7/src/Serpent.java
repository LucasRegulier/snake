import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Serpent {
	ArrayList<Coordonnees> coordonneesTab = new ArrayList<Coordonnees>();
	public int laTaille;
	private static final int TAILLE = 10;
	private Color couleur = Color.RED;
	private Coordonnees coDepart;
	private String direction;
	private int maxX, maxY;

		public Serpent(int mX, int mY) {
			maxX = (mX / 10) -1;
			maxY = (mY / 10) -1;
			laTaille = 1;
			coordonneesTab = new ArrayList<Coordonnees>();
			coDepart = new Coordonnees(0, 0);
			coordonneesTab.add(0, coDepart);
			direction = randomDirection();
		}
		
		public String randomDirection() {
			String newDirection = "";
			int num = (int)(Math.random() * 4) + 1;
			switch (num) {
			case 1 : 
				newDirection = "gauche";
				break;
			case 2 : 
				newDirection = "haut";
				break;
			case 3 : 
				newDirection = "bas";
				break;
			case 4 : 
				newDirection = "droite";
				break;
			}
			return newDirection;
		}
		
		public void agrandirSerpent() {
			int taille = coordonneesTab.size();
			coordonneesTab.add(taille, new Coordonnees(coordonneesTab.get(taille-1).getX(), coordonneesTab.get(taille-1).getY()));
			taille ++;
			laTaille ++;
		}
		
		public void bougeSerpent(boolean pomme) {
			int taille = coordonneesTab.size();
			int num;
			Coordonnees newCo = new Coordonnees(coordonneesTab.get(0).getX(), coordonneesTab.get(0).getY());
			for(int i = 1; i < taille; i++) {
				coordonneesTab.set((taille - i), coordonneesTab.get(taille - i - 1));
			}
			switch(direction) {
			case "haut":
				newCo.setY((newCo.getY())-1);
				break;
			case "bas":
				newCo.setY((newCo.getY())+1);
				break;
			case "gauche":
				newCo.setX((newCo.getX())-1);
				break;
			case "droite":
				newCo.setX((newCo.getX())+1);
				break;
			}
			System.out.println("max : " + maxY + newCo.getY());
			if((newCo.getY() < 0) || (newCo.getY() > maxY)) {
				if(newCo.getY() < 0) {
					newCo.setY((newCo.getY())+1);
				}else {
					newCo.setY((newCo.getY())-1);
				}
				num = (int)(Math.random() * 2) + 1;
				switch (num) {
				case 1 :
					newCo.setX((newCo.getX())+1);
					this.changerDirection("droite");
					break;
				case 2 :
					newCo.setX((newCo.getX())-1);
					this.changerDirection("gauche");
					break;
				}
			}
			if((newCo.getX() < 0) || (newCo.getX() > maxX)) {
				if(newCo.getX() < 0) {
					newCo.setX((newCo.getX())+1);
				}else {
					newCo.setX((newCo.getX())-1);
				}
				num = (int)(Math.random() * 2) + 1;
				switch (num) {
				case 1 :
					newCo.setY((newCo.getY())+1);
					this.changerDirection("bas");
					break;
				case 2 :
					newCo.setY((newCo.getY())-1);
					this.changerDirection("haut");
					break;
				}
			}
			
			coordonneesTab.set(0, newCo);
		}
		
		public int getTailleSerpent() {
			return laTaille;
		}
		
		public void dessiner(Graphics g) {
			g.setColor(couleur);
			for(int i = 0; i < coordonneesTab.size(); i++) {
				int posX = coordonneesTab.get(i).getX() * 10;
				int posY = coordonneesTab.get(i).getY() * 10;
				g.fillRect(posX,posY,TAILLE,TAILLE);
			}
			g.setColor(Color.BLACK);
			String points = "taille : " + laTaille;
			g.drawString(points  , ((maxX * 10) - 50), 10);
		}
		
		public boolean seMordLaQueue() {
			boolean reponse = false;
			for(int i=1; i < coordonneesTab.size(); i++) {
				if((coordonneesTab.get(0).getX() == coordonneesTab.get(i).getX()) && (coordonneesTab.get(0).getY() == coordonneesTab.get(i).getY())) {
					reponse = true;
				}
			}
			return reponse;
		}
		
		/*public int[] toucheUnMur() {
			
		}*/
		
		public boolean mangeUnePomme() {
			this.agrandirSerpent();
			return false;
		}
		
		public Coordonnees getTetePosition() {
			return coordonneesTab.get(0);
		}
		
		public void changerDirection(String newDirection) {
			direction = newDirection;
		}
		
		public String getDirection() {
			return direction;
		}
}
