import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Graphe extends Grille{
    private int tx,ty;
    private Case[][] cases;// Tableau contenant les cases
    private Random random = new Random();

    private Color[] NORMAL = {Color.white, Color.gray, Color.black};
    private Color[] HELI = {new Color(255, 0, 0), new Color(100, 0, 0), Color.black};
    private Color[] EAU = {new Color(80, 80, 255), new Color(0, 0, 120), Color.black};
    private Color[] TERRE = {new Color(40, 200, 0), new Color(20, 100, 0), Color.black};
    private Color[] FEU = {new Color(255, 100, 0), new Color(100, 50, 0), Color.black};
    private Color[] AIR = {new Color(0, 200, 200), new Color(0, 60, 60), Color.black};

    // Constructeur
    public Graphe(int tx,int ty) {
        super(tx,ty);
        this.tx = tx;
        this.ty = ty;
        cases = new Case[tx][ty];
            for(int i=0;i<tx;i++){
                for(int j=0;j<ty;j++){
                    Case c = new Case(false,i,j,0);
                    cases[i][j] = c;
                }
            }
            this.initial();
            for(int i=0;i<tx;i++) {
                for (int j = 0; j < ty; j++) {
                    if(cases[i][j].isHelicoptere()){
                        JButton c = new JButton("HELICOPTER");
                        c.setForeground(HELI[0]);
                        c.setPreferredSize(new Dimension(100, 100));
                        ajouteElement(c);
                    }else {
                        if (cases[i][j].getElement() == 0) {
                            JButton c = new JButton("NORMAL");
                            c.setForeground(NORMAL[0]);
                            c.setPreferredSize(new Dimension(100, 100));
                            ajouteElement(c);
                            //c.setBackground(Color.YELLOW);
                            //c.setOpaque(true);
                            //c.setBorderPainted(false);
                        } else if (cases[i][j].getElement() == 1) {
                            JButton c = new JButton("EAU");
                            c.setForeground(EAU[0]);
                            c.setPreferredSize(new Dimension(100, 100));
                            ajouteElement(c);
                        } else if (cases[i][j].getElement() == 2) {
                            JButton c = new JButton("TERRE");
                            c.setForeground(TERRE[0]);
                            c.setPreferredSize(new Dimension(100, 100));
                            ajouteElement(c);
                        } else if (cases[i][j].getElement() == 3) {
                            JButton c = new JButton("FEU");
                            c.setForeground(FEU[0]);
                            c.setPreferredSize(new Dimension(100, 100));
                            ajouteElement(c);
                        } else if (cases[i][j].getElement() == 4) {
                            JButton c = new JButton("AIR");
                            c.setForeground(AIR[0]);
                            c.setPreferredSize(new Dimension(100, 100));
                            ajouteElement(c);
                        }
                    }
            }
        }
    }

    public void initial(){
        int[] deja = new int[10];
        int x,y;
        int compte = 0;
        while (compte<5) {
            do {
                x = random.nextInt(tx);
                y = random.nextInt(ty);
            } while (contains(x, y, deja));

            if(compte == 0){
                cases[x][y].addHelicop();
            }else{
                cases[x][y].setElement(compte);
            }

            deja[compte*2] = x;
            deja[compte*2 + 1] = y;
            compte++;
        }
        //return deja.iterator().next();  // return le premier element de deja
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
        for(int i=0;i<tx;i++) {
            for (int j = 0; j < ty; j++) {
                cases[i][j].addflood();
            }
        }
    }
}
