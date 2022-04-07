import IG.Fenetre;

public class Main {
    public static void main(String[] args) {
        Fenetre f = new Fenetre("ile_interdite");
        Plateau gri = new Plateau(8);
        Bouton fin = new Bouton(gri);
        f.ajouteElement(gri);
        f.ajouteElement(fin);
        f.dessineFenetre();
    }
}
