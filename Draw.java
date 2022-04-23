import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {
    private class Image{
        protected int x,y;
        protected String text;
        protected Color color;
        public Image(int x, int y, Color color){
            this.x = x;
            this.y = y;
            this.text = "";
            this.color = color;
        }
    }

    private Image[][] cases;
    private Graphe graphe;
    private boolean affiche;

    private Color[] NORMAL = {Color.white, Color.gray, Color.black};
    private Color[] HELI = {new Color(255, 0, 0), new Color(100, 0, 0), Color.black};
    private Color[] EAU = {new Color(80, 80, 255), new Color(0, 0, 120), Color.black};
    private Color[] TERRE = {new Color(40, 200, 0), new Color(20, 100, 0), Color.black};
    private Color[] FEU = {new Color(255, 100, 0), new Color(100, 50, 0), Color.black};
    private Color[] AIR = {new Color(0, 200, 200), new Color(0, 60, 60), Color.black};

    public Draw(Graphe graphe){
        this.graphe = graphe;
        cases = new Image[graphe.getTx()][graphe.getTy()];
        affiche = true;
    }

    public void initial(){
        for(int i=0;i<graphe.getTx();i++){
            for (int j=0;j<graphe.getTy();j++){
                    cases[i][j] = new Image(i, j, NORMAL[0]);
            }
        }
        createF("Ile d'Interdite");
        color();
    }

    /*
        créer une fenetre
     */
    public void createF(String nom){
        JFrame fenetre = new JFrame(nom);
        fenetre.setSize(600,600);
        JButton bf = new JButton("fin de tour");
        bf.setPreferredSize(new Dimension(100,100));
        this.add(bf);
        fenetre.getContentPane().add(this);
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void color(){
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if (graphe.getCases()[i][j].getElement() == 1) {
                    cases[i][j] = new Image(i, j, EAU[0]);
                    cases[i][j].text = "Eau";
                } else if (graphe.getCases()[i][j].getElement() == 2) {
                    cases[i][j] = new Image(i, j, TERRE[0]);
                    cases[i][j].text = "Terre";
                } else if (graphe.getCases()[i][j].getElement() == 3) {
                    cases[i][j] = new Image(i, j, FEU[0]);
                    cases[i][j].text = "Feu";
                } else if (graphe.getCases()[i][j].getElement() == 4) {
                    cases[i][j] = new Image(i, j, AIR[0]);
                    cases[i][j].text = "Air";
                } else if (graphe.getCases()[i][j].isHelicoptere()) {
                    cases[i][j] = new Image(i, j, HELI[0]);
                    cases[i][j].text = "Helicopter";
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        if(affiche){
            drawNotice(g);
        }else {
            //super.paintComponent(g);
            //drawGrille(g, 100, 200, 300, 400);
        }
    }

    private void drawGrille(Graphics g, int x0, int y0, int x1, int y1){
        // Un peu de trigo pour calculer les positions des lignes à dessiner.
        for(int i = 0; i < graphe.getTx()+1; i++) {
            int x0temp = x0 + ((x1 - x0) / (graphe.getTx())) * i;
            int y0temp = y0;
            int x1temp = x0 + ((x1 - x0) / (graphe.getTx())) * i;

            // Au lieu de y1, on calcul les positions des derniers points, pour éviter de faire dépasser
            // les lignes.
            int y1temp = y0 + ((y1 - y0) / (graphe.getTy())) * (graphe.getTy());
            g.drawLine(x0temp, y0temp, x1temp, y1temp);
        }
        for(int i = 0; i < graphe.getTy()+1; i++) {
            int x0temp = x0;
            int y0temp = y0 + ((y1 - y0) / (graphe.getTy())) * i;
            int x1temp = x0 + ((x1 - x0) / (graphe.getTx())) * (graphe.getTx());
            int y1temp = y0 + ((y1 - y0) / (graphe.getTy())) * i;
            g.drawLine(x0temp, y0temp, x1temp, y1temp);
        }
    }
    private void drawNotice(Graphics g){

        // On place des carrés.
        g.setColor(AIR[0]);
        // getWidth()/2 milieu de la fenètre, -25 moitié du carré(donc carré au milieu),
        // -75 moitié du carré + carré.
        g.fillRect(getWidth()/2-25-75, getHeight()/2-100, 50, 50);

        g.setColor(EAU[0]);
        g.fillRect(getWidth()/2-25-25, getHeight()/2-100, 50, 50);

        g.setColor(TERRE[0]);
        g.fillRect(getWidth()/2-25+25, getHeight()/2-100, 50, 50);

        g.setColor(FEU[0]);
        g.fillRect(getWidth()/2-25+75, getHeight()/2-100, 50, 50);

        g.setColor(NORMAL[0]);
        g.fillRect(getWidth()/2-25-50, getHeight()/2-50, 50, 50);

        g.setColor(NORMAL[1]);
        g.fillRect(getWidth()/2-25+00, getHeight()/2-50, 50, 50);

        g.setColor(NORMAL[2]);
        g.fillRect(getWidth()/2-25+50, getHeight()/2-50, 50, 50);

        // On met les bords noirs.
        g.setColor(Color.black);
        g.drawRect(getWidth()/2-25+25, getHeight()/2-100, 50, 50);
        g.drawRect(getWidth()/2-25-25, getHeight()/2-100, 50, 50);
        g.drawRect(getWidth()/2-25-75, getHeight()/2-100, 50, 50);
        g.drawRect(getWidth()/2-25+75, getHeight()/2-100, 50, 50);

        g.drawRect(getWidth()/2-25-50, getHeight()/2-50, 50, 50);
        g.drawRect(getWidth()/2-25-0, getHeight()/2-50, 50, 50);
        g.drawRect(getWidth()/2-25+50, getHeight()/2-50, 50, 50);

        // On met un titre.
        g.drawString("Projet POGL : Île Interdite",   getWidth()/2-65, getHeight()/2-120);

        // On met les légendes des cases dans ses cases.
        g.drawString("Air",   getWidth()/2-25-75+18, getHeight()/2-75);
        g.drawString("Eau",   getWidth()/2-25-25+15, getHeight()/2-75);
        g.drawString("Terre", getWidth()/2-25+25+12, getHeight()/2-75);
        g.drawString("Feu",   getWidth()/2-25+75+15, getHeight()/2-75);

        g.drawString("Normale",   getWidth()/2-25-50+1, getHeight()/2-25);
        g.drawString("Inondé",   getWidth()/2-25-00+6, getHeight()/2-25);
        g.setColor(Color.white);
        g.drawString("Sub", getWidth()/2-25+50+15, getHeight()/2-25);

        return;
    }
}
