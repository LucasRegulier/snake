import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Lucas
 *
 */
public class ZoneDeJeu extends JPanel implements Runnable {
	private Serpent serpent;
	private Terrain terrain;
	private int hauteur, largeur, quantite;
	private boolean termine = false, perdu = false;
	private Choix fenetreChoix;
	private Choix newChoix;
	
	public ZoneDeJeu(int x, int y, int quant, Choix fenetre) {
		fenetreChoix = fenetre;
		hauteur = x;
		largeur = y;
		quantite = quant;
		serpent = new Serpent(hauteur, largeur);
		terrain = new Terrain(hauteur, largeur, quantite);
		Thread monProcess = new Thread(this);
		monProcess.start();
	}
	
	
	public void paint(Graphics gc) {
		super.paint(gc);
		serpent.dessiner(gc);
		terrain.dessiner(gc);
	}
	
	/**
	 * @return
	 */
	public Serpent avoirSerpent() {
		return serpent;
	}
	
	/**
	 * @param newDirection
	 */
	public void changerDirection(String newDirection) {
		serpent.changerDirection(newDirection);
	}
	
	public void run() {
		while(termine == false) {		
			System.out.println(serpent.getDirection());
			if(terrain.getCasePleine(serpent.getTetePosition())) {
				terrain.supprimerNourriture(serpent.getTetePosition());
				serpent.agrandirSerpent();
			}
			serpent.bougeSerpent(terrain.getCasePleine(serpent.getTetePosition()));	
			System.out.println(serpent.getTetePosition().getX()+" "+serpent.getTetePosition().getY());
			System.out.println(serpent.getTailleSerpent());
			repaint();
			if(serpent.getTailleSerpent() == (this.quantite + 1)) {
				termine = true;
			}
			if(serpent.seMordLaQueue()) {
				termine = true;
				perdu = true;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(perdu) {
			JOptionPane.showMessageDialog(this,"C'est terminé ! Tu as perdu ... ");
		}else {
			JOptionPane.showMessageDialog(this,"C'est terminé ! score :" + serpent.getTailleSerpent());
		}
		newChoix = new Choix();
		fenetreChoix.dispose();
		
	}
	
	/**
	 * @return
	 */
	public boolean savoirSiGagne() {
		return this.termine;
	}
}
