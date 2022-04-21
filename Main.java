import IG.Fenetre;

public class Main {
    public static void main(String[] args) {
        Fenetre f = new Fenetre("ile_interdite");
        Graphe g = new Graphe(5);
        Case a= g.ajouteCase("a",0);
        Case b= g.ajouteCase("b",1);
        Case c= g.ajouteCase("c",2);
        Case d= g.ajouteCase("d",3);
        g.ajouteArete(a,b);
        g.ajouteArete(b,c);
        g.ajouteArete(a,d);
        g.ajouteArete(b,d);
        Bouton fin = new Bouton(g);
        f.ajouteElement(g);
        f.ajouteElement(fin);
        f.dessineFenetre();
    }
}
