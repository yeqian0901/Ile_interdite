import IG.ZoneCliquable;

public class Bouton extends ZoneCliquable {
    private Plateau plateau;

    public Bouton(Plateau p) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("fin de tour", 160, 50);
        this.plateau = p;
    }

    public void clicGauche() {
    }

    public void clicDroit() {}
}
