import IG.ZoneCliquable;

public class Bouton extends ZoneCliquable {
    private Graphe g;

    public Bouton(Graphe g) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("fin de tour", 160, 50);
        this.g = g;
    }

    public void clicGauche() {

    }

    public void clicDroit() {}
}
