public class Joueur {
    private String nom;
    private Case caseJ;
    private int[] cles;//0 est eau, 1 est terre, 2 est feu, 3 est air

    public Joueur(String nom, Case c){
        this.nom = nom;
        this.caseJ = c;
        this.cles = new int[]{0,0,0,0};
    }

    public void getCles(int cle){
        if(cle < 0 || cle >3){
            return;
        }
        this.cles[cle]++;
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
}
