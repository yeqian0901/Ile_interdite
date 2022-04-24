public class Main {
    public static void main(String[] args) throws Exception{
       Graphe g = new Graphe(4,5);
       Game game = new Game(g, g.creeJoueurs(6));
       game.start();
    }
}
