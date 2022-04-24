import java.util.Random;

public class Graphe{
    private int tx,ty;
    private Case[][] cases;// Tableau contenant les cases
    private Random random = new Random();
    private Joueur[] joueurs;

    // Constructeur
    public Graphe(int tx,int ty) {
        this.tx = tx;
        this.ty = ty;
        cases = new Case[tx][ty];
        this.joueurs = new Joueur[6];
            for(int i=0;i<tx;i++){
                for(int j=0;j<ty;j++){
                    Case c = new Case(false,i,j,0);
                    cases[i][j] = c;
                }
            }
        this.initial();
    }

    public void initial(){
        int[] deja = new int[20];
        int x,y;
        int compte = 0;
        while (compte<10) {
            do {
                x = random.nextInt(tx);
                y = random.nextInt(ty);
            } while (contains(x, y, deja));

            if(compte == 0){
                cases[x][y].addHelicop();
            }else{
                cases[x][y].setElement(compte % 5);
            }

            deja[compte*2] = x;
            deja[compte*2 + 1] = y;
            compte++;
        }
        //return deja.iterator().next();  // return le premier element de deja
    }

    public void initJoueur(){

    }

    public int getTx() {
        return tx;
    }

    public int getTy(){
        return ty;
    }

    public Case[][] getCases(){
        return cases;
    }

    protected boolean contains(int x, int y, int[] pos){
        for(int i = 1; i < pos.length; i+=2){
            if(pos[i-1] == x && pos[i] == y) return true;
        }
        return false;
    }

    public void floodAdd(){
        int compte = 0;
        int x,y;
        while(compte < 4){
            x = random.nextInt(tx);
            y = random.nextInt(ty);
            cases[x][y].addflood();
            compte++;
        }
    }

    public void ajouteJ(int x, int y, Joueur j) {
        if(x<0 || x>=tx || y<0 || y>=ty){
            return;
        }
        cases[x][y].addJoueur(j);
    }

    public void removeJ(int x, int y){
        if(x<0 || x>=tx || y<0 || y>=ty){
            return;
        }
        cases[x][y].removeJoueur();
    }
}
