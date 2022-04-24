import java.util.Random;

public class Main {
    private Random random = new Random();

    public Joueur[] creeJoueurs(Graphe g,int n){
        Joueur[] j= new Joueur[n];
        int[] deja = new int[20];
        int compte=0;
        int x,y;
        while (compte<n) {
                do {
                    x = random.nextInt(g.getTx());
                    y = random.nextInt(g.getTy());
                } while (g.contains(x, y, deja));

                Case c = g.getCase(x,y);
                j[compte] = new Joueur("nom"+compte, c);

                deja[compte*2] = x;
                deja[compte*2 + 1] = y;
                compte++;
            }
        return j;
        }

    public static void main(String[] args) {
       Graphe g = new Graphe(4,5);
       Game game = new Game(g, g.creeJoueurs(4));
       game.start();
    }
}
