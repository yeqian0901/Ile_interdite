public class Main {
    public static void main(String[] args) {
       Graphe g = new Graphe(4,5);
       Game game = new Game(g);
       game.start();
    }
}
