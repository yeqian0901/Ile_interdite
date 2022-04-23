public class Main {
    public static void main(String[] args) {
       Fenetre f = new Fenetre("Ile d'interdite");
       Graphe g = new Graphe(4,5);
       Fin b = new Fin(g);
       f.ajouteElement(g);
       f.ajouteElement(b);
       f.dessineFenetre();
    }
}
