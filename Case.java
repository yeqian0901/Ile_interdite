import javax.swing.*;
import java.util.ArrayList;

public class Case{
    private Graphe graphe;
    private int x,y;
    private int flood;//0 normal, et apres niveau 1 inonder, et enfin 2 submerger
    private boolean helicoptere;
    private int element;//0 est rien, 1 est eau, 2 est terre, 3 est feu, 4 est air
    private Joueur joueur;

    public Case(Graphe graphe,boolean heli, int x, int y, int element){
        this.graphe = graphe;
        this.x=x;
        this.y=y;
        this.element = element;
        this.flood = 0;
        this.helicoptere = heli;
        this.joueur = null;
    }

    public void addHelicop(){
        this.helicoptere = true;
    }

    public void addflood(){
        if(this.flood == 0 || this.flood == 1){
            this.flood++;
        }
    }

    public void reduceflood(){
       if(this.flood == 1){
           this.flood--;
       }
    }

    public int getElement() {
        return element;
    }

    public boolean isHelicoptere() {
        return helicoptere;
    }

    public void setElement(int element) {
        if(element < 0){
            return;
        }
        this.element = element;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addJoueur(Joueur j){
            this.joueur = j;
    }

    public int getFlood() {
        return flood;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public void removeJoueur(){
        this.joueur =null;
    }

    public ArrayList<Case> voisin(){
        ArrayList<Case> v = new ArrayList<>();
        if(x-1>=0){
            v.add(graphe.getCase(x-1,y));
        }
        if(x+1<graphe.getTx()){
            v.add(graphe.getCase(x+1,y));
        }
        if(y-1>=0){
            v.add(graphe.getCase(x,y-1));
        }
        if(y+1<graphe.getTy()){
            v.add(graphe.getCase(x,y+1));
        }
        return v;
    }
}
