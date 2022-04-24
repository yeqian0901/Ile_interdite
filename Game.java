import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    private Graphe graphe;
    private Joueur[] joueurs;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private Color[] NORMAL = {Color.white, Color.gray, Color.black};
    private Color[] HELI = {new Color(255, 0, 0), new Color(100, 0, 0), Color.black};
    private Color[] EAU = {new Color(80, 80, 255), new Color(0, 0, 120), Color.black};
    private Color[] TERRE = {new Color(40, 200, 0), new Color(20, 100, 0), Color.black};
    private Color[] FEU = {new Color(255, 100, 0), new Color(100, 50, 0), Color.black};
    private Color[] AIR = {new Color(0, 200, 200), new Color(0, 60, 60), Color.black};
    private Color[] JOUEUR = {Color.black, Color.CYAN,Color.GREEN,Color.RED};
    private ImageIcon[] NOR = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/normal.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/inonde.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg") };

    public Game(Graphe g, Joueur[] joueurs){
        this.graphe = g;
        this.joueurs = joueurs;
        this.frame = new JFrame("Ile d'interdite");
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.panel3 = new JPanel();
    }

    public void start(){
        panel1.setLayout(new GridLayout(graphe.getTx(),graphe.getTy()));
        draw();
        frame.add(panel1);
        panel2.setLayout(new GridLayout(2,2));
        direction(joueurs[0]);
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
                    ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/heliport.jpg");
                    image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                    if(cases[i][j].getJoueur() != null){
                        JButton c = new JButton(cases[i][j].getJoueur().getNom());
                        c.setForeground(JOUEUR[0]);
                        JLabel img = new JLabel(image, JLabel.LEFT);
                        img.setLayout(new FlowLayout(FlowLayout.CENTER));
                        img.add(c);
                        panel1.add(img);
                    }else {
                        JLabel img = new JLabel(new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/heliport.jpg"), JLabel.LEFT);
                        panel1.add(img);
                    }
                }else{
                    if(cases[i][j].getElement() == 0) {
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                            NOR[cases[i][j].getFlood()].setImage(NOR[cases[i][j].getFlood()].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                            JLabel img = new JLabel(NOR[cases[i][j].getFlood()], JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(NOR[cases[i][j].getFlood()], JLabel.LEFT);
                            panel1.add(img);
                        }
                        //c.setBackground(Color.YELLOW);
                        //c.setOpaque(true);
                        //c.setBorderPainted(false);
                    } else if (cases[i][j].getElement() == 1) {
                        ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/eau.jpg");
                        image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 2) {
                        ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/terre.jpg");
                        image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 3) {
                        ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/feu.jpg");
                        image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 4) {
                        ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/air.jpg");
                        image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            c.setForeground(JOUEUR[0]);
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(image, JLabel.LEFT);
                            panel1.add(img);
                        }
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
