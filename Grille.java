import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel {
    private Case[][] grille;

    public Grille(int ligne, int colonne){
        this.grille = new Case[ligne][colonne];
        setLayout(new GridLayout(ligne, colonne, 5,5));
    }

    public void ajouteElement(JComponent element){
        this.add(element);
    }
}
