import java.util.ArrayList;

public class Joueur {
    private Graphe graphe;
    private String nom;
    private Case caseJ;
    private int[] cles;//0 est eau, 1 est terre, 2 est feu, 3 est air

    public Joueur(Graphe graphe, String nom, Case c){
        this.graphe = graphe;
        this.nom = nom;
        this.caseJ = c;
        this.cles = new int[]{0,0,0,0};
    }

    public void getCles(int cle){ //obtenir cle
        if(cle < 0 || cle > 3){
            return;
        }
        this.cles[cle]++;
    }

    public ArrayList<Case> voisin(){
        return this.caseJ.voisin();
    }

    public void usecle(int cle){
        if(cle<0 || cle>3 || cles[cle]==0){
            return;
        }
        this.cles[cle]--;
    }

    public void setCaseJ(int x, int y){
        this.caseJ.setX(x);
        this.caseJ.setY(y);
    }

    public Case getCaseJ() {
        return caseJ;
    }

    public String getNom(){
        return nom;
    }

    public boolean haveCle(int n){
        if(cles[n]>0){
            return true;
        }
        return false;
    }

    public int[] getCle() {
        return cles;
    }

    public int nbCle(int cle){
        return this.cles[cle];
    }
}
