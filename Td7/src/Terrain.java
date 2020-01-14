import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Terrain  {
	ArrayList<Coordonnees> nourritureTab = new ArrayList<Coordonnees>();
	private int nombreNourriture;
	private static final int TAILLE = 10;
	private Color couleur = Color.BLUE;
	private Coordonnees newCo;
	private int hauteur, largeur, quantite;

	public Terrain(int laHauteur, int laLargeur, int quant) {
		quantite = quant;
		nombreNourriture = 0;
		largeur = laLargeur;
		hauteur = laHauteur;
		boolean different = true;

		do {
			do {
				int x = (int) Math.abs(Math.random() * ((largeur)/10));
				int y = (int) Math.abs(Math.random() * ((hauteur)/10));
				newCo = new Coordonnees(x, y);
				if (nourritureTab.size() == 0) {
					different = false;
				} else {
					for (int i = 0; i < nourritureTab.size(); i++) {
						if (nourritureTab.get(i).estEgal(newCo)) {
							different = false;
						}
					}
				}
			} while (different == true);
			nourritureTab.add(nombreNourriture, newCo);
			nombreNourriture++;
			different = true;
		} while (nombreNourriture != this.quantite);

	}

	public boolean getCasePleine(Coordonnees co) {
		boolean reponse = false;
		for (int i = 0; i < nourritureTab.size(); i++) {
			if ((nourritureTab.get(i).getX() == co.getX()) & (nourritureTab.get(i).getY() == co.getY())) {
				reponse = true;
			}
		}
		return reponse;
	}

	public void supprimerNourriture(Coordonnees co) {
		for (int i = 0; i < nourritureTab.size(); i++) {
			if ((nourritureTab.get(i).getX() == co.getX()) && (nourritureTab.get(i).getY() == co.getY())) {
				nourritureTab.remove(i);
				nombreNourriture-- ;
			}
		}
	}

	public void dessiner(Graphics g) {
		for (int i = 0; i < nombreNourriture; i++) {
			g.setColor(couleur);
			int positionX = ((nourritureTab.get(i).getX())*10);
			int positionY = ((nourritureTab.get(i).getY())*10);
			g.fillRect(positionX, positionY, TAILLE, TAILLE);
		}

	}

}
