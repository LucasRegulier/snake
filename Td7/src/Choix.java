
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Choix extends JFrame implements ActionListener, KeyListener{
  private JComboBox listeQuantiteNourriture, listeDimensionGrille;
  private ZoneDeJeu leJeu;
  private int x,y;
  private String direction;
  
  public Choix() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(new Dimension(350, 200));
    setTitle("Snake v1.0");
    
    JFrame frame = new JFrame();
    JLabel panneauHaut = new JLabel("Choix des paramètres");
    panneauHaut.setPreferredSize(new Dimension(350, 50));
    panneauHaut.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(panneauHaut, BorderLayout.NORTH);
    

    JPanel panneauCentre = new JPanel();
    panneauCentre.setLayout(new GridLayout(2,2));
    getContentPane().add(panneauCentre, BorderLayout.CENTER);
    
    // On créer les titres pour les listes
    JLabel titreQuantiteNourriture = new JLabel("Quantité de nourriture");
    JLabel titreDimensionGrille = new JLabel("Dimensions de la grille");
    
    // On créer les choxi possibles pour les listes
    String[] choixListeQuantiteNourriture = {"5", "10", "15", "20"};
    String[] choixListeDimensionGrille = {"20x20", "30x30", "40x40", "50x50"};
    
    // On créer les listes
    listeQuantiteNourriture = new JComboBox(choixListeQuantiteNourriture);
    listeDimensionGrille = new JComboBox(choixListeDimensionGrille);
    
    // On ajoute les titres et les listes du panneau central
    panneauCentre.add(titreQuantiteNourriture);
    panneauCentre.add(listeQuantiteNourriture);
    panneauCentre.add(titreDimensionGrille);
    panneauCentre.add(listeDimensionGrille);
    
    JButton panneauBas = new JButton("Valider");
    panneauBas.addActionListener(this);
    panneauBas.setPreferredSize(new Dimension(350, 35));
    getContentPane().add(panneauBas, BorderLayout.SOUTH);

    // fenetre visible
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String valeur1 = (String)listeQuantiteNourriture.getSelectedItem();
    String valeur2 = (String)listeDimensionGrille.getSelectedItem();
    String[] valeurs = valeur2.split("x");
    x = Integer.parseInt(valeurs[0]);
    y = Integer.parseInt(valeurs[1]);
    x = x*10;
    y = y*10;
    int quantite = Integer.parseInt(valeur1);
    setSize(new Dimension(x, y));
    getContentPane().removeAll();
    getContentPane().setPreferredSize(new Dimension(x,y));
    pack();
    getContentPane().addKeyListener(this);
    getContentPane().requestFocusInWindow();
    leJeu = new ZoneDeJeu(x, y, quantite, this);
    getContentPane().add(leJeu);
    validate();
    
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public boolean savoirSiGagne() {
		return leJeu.savoirSiGagne();
  }
 
  
  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
    case (KeyEvent.VK_UP):
      direction = "haut";
      System.out.println("C'est parti en haut");
      break;
    case (KeyEvent.VK_DOWN):
      direction = "bas";
      break;
    case (KeyEvent.VK_LEFT):
      direction = "gauche";
      break;
    case (KeyEvent.VK_RIGHT):
      direction = "droite";
      break;
    }
    leJeu.changerDirection(direction);
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub
    
  }


}
