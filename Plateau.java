public class Plateau extends IG.Grille {
    private int taille;
    private Case[][] plateau;// Tableau contenant les cases

    public Case getCase(int i, int j) {
        return plateau[i][j];
    }

    // Constructeur
    public Plateau(int taille) {
        // Initialisation de la grille graphique
        super(taille, taille);
        // Initialisation des attributs statiques
        this.taille = taille;
        // Initialisation du tableau de cases
        this.plateau = new Case[taille][taille];
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                // Création d'une case du plateau
                if(i==5 && j==5){
                    Case cas = new Case(i,j,0,this, "helicopter");
                    cas.addHelicop();
                    plateau[i][j] = cas;
                    this.ajouteElement(cas);
                }else {
                    Case cas = new Case(i, j, 0, this, "rien");
                    plateau[i][j] = cas;
                    this.ajouteElement(cas);
                }
            }
        }
    }

    public Plateau(int taille, Case[][] c){
        super(taille,taille);
        this.taille = taille;
        this.plateau = c;
    }
}
