import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
       Graphe g = new Graphe(4,5);
        Scanner scan = new Scanner(System.in);
        System.out.print("COMBIEN DE JOUEUR ?(MOINS DE 6) ENTREE: ");
        int n = scan.nextInt();
       Game game = new Game(g, g.creeJoueurs(n));
       game.start();
    }
}
