import java.util.HashSet;
import java.util.Set;

public class Graphe extends IG.Grille {
    private int taille;
    private Set<Case> cases;// Tableau contenant les cases
    private Set<Arete> aretes;

    // Constructeur
    public Graphe(int taille) {
        // Initialisation de la grille graphique
        super(taille, taille);
        // Initialisation des attributs statiques
        this.taille = taille;
        // Initialisation du tableau de cases
        cases = new HashSet<>();
        aretes = new HashSet<>();
    }

    public Case ajouteCase(String nom,int x,int y, int element){
        Case c = new Case(nom, x, y, element);
        this.ajouteElement(c);
        return c;
    }

    public void ajouteArete(Case c1, Case c2){
        aretes.add(new Arete(c1,c2));
    }

    public int getTaille() {
        return taille;
    }
}
