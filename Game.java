import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    private Graphe graphe;
    private Joueur joueur;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;

    private Color[] NORMAL = {Color.white, Color.gray, Color.black};
    private Color[] HELI = {new Color(255, 0, 0), new Color(100, 0, 0), Color.black};
    private Color[] EAU = {new Color(80, 80, 255), new Color(0, 0, 120), Color.black};
    private Color[] TERRE = {new Color(40, 200, 0), new Color(20, 100, 0), Color.black};
    private Color[] FEU = {new Color(255, 100, 0), new Color(100, 50, 0), Color.black};
    private Color[] AIR = {new Color(0, 200, 200), new Color(0, 60, 60), Color.black};
    private Color[] JOUEUR = {Color.black, Color.CYAN,Color.GREEN,Color.RED};

    public Game(Graphe g){
        this.graphe = g;
        this.joueur = new Joueur(this, "nom", graphe.getCases()[0][0]);
        graphe.getCases()[0][0].addJoueur(this.joueur);
        this.frame = new JFrame("Ile d'interdite");
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
    }

    public void start(){
        panel1.setLayout(new GridLayout(graphe.getTx(),graphe.getTy()));
        draw();
        frame.add(panel1);
        panel2.setLayout(new GridLayout(2,2));
        direction(joueur);
        frame.add(panel2, BorderLayout.EAST);
        JButton fin = new JButton("fin de tour");
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        frame.add(fin, BorderLayout.SOUTH);
        frame.pack();
        frame.setBounds(100,100,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(){
        panel1.removeAll();
        graphe.floodAdd();
        draw();
        panel1.updateUI();
        frame.add(panel1);
    }

    public void draw(){
        Case[][] cases = graphe.getCases();
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if(cases[i][j].isHelicoptere()){
                    JButton c;
                    if(cases[i][j].getJoueur() != null){
                        c = new JButton("HELICOPTER   " + cases[i][j].getJoueur().getNom());
                    }else {
                        c = new JButton("HELICOPTER");
                    }
                    c.setForeground(HELI[cases[i][j].getFlood()]);
                    c.setPreferredSize(new Dimension(100, 100));
                    panel1.add(c);
                }else{
                    if(cases[i][j].getElement() == 0) {
                        JButton c;
                        if(cases[i][j].getJoueur() != null){
                            c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                        }else {
                            c = new JButton("NORMAL");
                            c.setForeground(NORMAL[cases[i][j].getFlood()]);
                        }
                        c.setPreferredSize(new Dimension(100, 100));
                        panel1.add(c);
                        //c.setBackground(Color.YELLOW);
                        //c.setOpaque(true);
                        //c.setBorderPainted(false);
                    } else if (cases[i][j].getElement() == 1) {
                        JButton c;
                        if(cases[i][j].getJoueur() != null){
                            c = new JButton("EAU   " + cases[i][j].getJoueur().getNom());
                        }else {
                            c = new JButton("EAU");
                        }
                        c.setForeground(EAU[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel1.add(c);
                    } else if (cases[i][j].getElement() == 2) {
                        JButton c;
                        if(cases[i][j].getJoueur() != null){
                            c = new JButton("TERRE   " + cases[i][j].getJoueur().getNom());
                        }else {
                            c = new JButton("TERRE");
                        }
                        c.setForeground(TERRE[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel1.add(c);
                    } else if (cases[i][j].getElement() == 3) {
                        JButton c;
                        if(cases[i][j].getJoueur() != null){
                            c = new JButton("FEU   " + cases[i][j].getJoueur().getNom());
                        }else {
                            c = new JButton("FEU");
                        }
                        c.setForeground(FEU[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel1.add(c);
                    } else if (cases[i][j].getElement() == 4) {
                        JButton c;
                        if(cases[i][j].getJoueur() != null){
                            c = new JButton("AIR   " + cases[i][j].getJoueur().getNom());
                        }else {
                            c = new JButton("AIR");
                        }
                        c.setForeground(AIR[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel1.add(c);
                    }
                }
            }
        }
    }

    public void direction(Joueur j){
        JButton up = new JButton("up");
        up.setForeground(Color.PINK);
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = j.getCaseJ().getX();
                int y = j.getCaseJ().getY();
                if(x-1 >= 0) {
                    graphe.removeJ(x,y);
                    j.setCaseJ(x-1, y);
                    graphe.ajouteJ(x-1, y, j);
                }
                move();
            }
        });
        panel2.add(up);
        JButton right = new JButton("right");
        right.setForeground(Color.PINK);
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = j.getCaseJ().getX();
                int y = j.getCaseJ().getY();
                if(y+1 < graphe.getTy()) {
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y+1);
                    graphe.ajouteJ(x, y+1, j);
                }
                move();
            }
        });
        panel2.add(right);
        JButton left = new JButton("left");
        left.setForeground(Color.PINK);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = j.getCaseJ().getX();
                int y = j.getCaseJ().getY();
                if(y-1 >= 0) {
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y-1);
                    graphe.ajouteJ(x, y-1, j);
                }
                move();
            }
        });
        panel2.add(left);
        JButton down = new JButton("down");
        down.setForeground(Color.PINK);
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = j.getCaseJ().getX();
                int y = j.getCaseJ().getY();
                if(x+1 < graphe.getTx()) {
                    graphe.removeJ(x,y);
                    j.setCaseJ(x+1, y);
                    graphe.ajouteJ(x+1, y, j);
                }
                move();
            }
        });
        panel2.add(down);
    }

    public void move(){
        panel1.removeAll();
        draw();
        panel1.updateUI();
        frame.add(panel1);
    }
}
