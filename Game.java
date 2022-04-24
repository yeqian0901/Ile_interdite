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
    private JPanel panel4;
    private int n=0;

    private Color[] JOUEUR = {Color.black, Color.CYAN,Color.GREEN,Color.RED};
    private ImageIcon[] NOR = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/normal.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/inonde.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg") };
    private String[] ELEMENT = new String[]{"EAU","TERRE","FEU","AIR"};

    public Game(Graphe g, Joueur[] joueurs){
        this.graphe = g;
        this.joueurs = joueurs;
        this.frame = new JFrame("Ile d'interdite");
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.panel3 = new JPanel();
        this.panel4 = new JPanel();
    }

    public void setImage(){
        for(int i=0;i<3;i++){
            NOR[i].setImage(NOR[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
        }
    }

    public void start(){
        setImage();
        panel1.setLayout(new GridLayout(graphe.getTx(),graphe.getTy()));
        draw(joueurs[n]);
        frame.add(panel1);
        panel2.setLayout(new GridLayout(2,2));
        direction(joueurs[n]);
        frame.add(panel2, BorderLayout.EAST);
        JButton fin = new JButton("fin de tour");
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n++;
                update(joueurs[n]);
                if(n >= joueurs.length) {
                    n = n % joueurs.length;
                }
                move(joueurs[n]);
                updateJ();
            }
        });
        panel3.add(fin);
        frame.add(panel3, BorderLayout.SOUTH);
        panel4.setLayout(new GridLayout(4,1));
        cle();
        frame.add(panel4, BorderLayout.NORTH);
        frame.pack();
        frame.setBounds(100,100,800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(Joueur joueur){
        panel1.removeAll();
        graphe.floodAdd();
        draw(joueur);
        panel1.updateUI();
        frame.add(panel1);
    }

    public void updateJ(){
        panel2.removeAll();
        direction(joueurs[n]);
        panel2.updateUI();
        frame.add(panel2, BorderLayout.EAST);
    }

    public void updatecle(Joueur joueur){
        panel4.removeAll();
        move(joueur);
        cle();
        panel4.updateUI();
        frame.add(panel4, BorderLayout.NORTH);
    }

    public void cle(){
        JLabel[] labels = new JLabel[joueurs.length];
        for(int i=0;i< joueurs.length;i++){
            for(int j=0;j<4;j++) {
                labels[i] = new JLabel(joueurs[i].getNom() + " : " );
                if(joueurs[i].haveCle(j)){
                    labels[i].setText(labels[i].getText() + ELEMENT[j] + joueurs[i].nbCle(j));
                    panel4.add(labels[i]);
                }
            }
        }
    }

    public void draw(Joueur joueur){
        Case[][] cases = graphe.getCases();
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if(cases[i][j].isHelicoptere()){
                    ImageIcon image = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/heliport.jpg");
                    image.setImage(image.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                    if(cases[i][j].getJoueur() != null){
                        JButton c = new JButton(cases[i][j].getJoueur().getNom());
                        for(int m=0;m<joueurs.length;m++){
                            if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                c.setForeground(JOUEUR[m]);
                            }
                        }
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
                            NOR[cases[i][j].getFlood()].setImage(NOR[cases[i][j].getFlood()].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
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
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            if(joueur.getNom() == cases[i][j].getJoueur().getNom()) {
                                joueur.getCles(0);
                            }
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
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            if(joueur.getNom() == cases[i][j].getJoueur().getNom()) {
                                joueur.getCles(1);
                            }
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
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            if(joueur.getNom() == cases[i][j].getJoueur().getNom()) {
                                joueur.getCles(2);
                            }
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
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            if(joueur.getNom() == cases[i][j].getJoueur().getNom()) {
                                joueur.getCles(3);
                            }
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
                if(x-1 >= 0 && !graphe.existJ(x-1,y)) {
                    graphe.ajouteJ(x-1, y, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x-1, y);
                    updatecle(j);
                }
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
                if(y+1 < graphe.getTy() && !graphe.existJ(x, y+1)) {
                    graphe.ajouteJ(x, y+1, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y+1);
                    updatecle(j);
                }
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
                if(y-1 >= 0 && !graphe.existJ(x, y-1)) {
                    graphe.ajouteJ(x, y-1, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y-1);
                    updatecle(j);
                }
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
                if(x+1 < graphe.getTx() && !graphe.existJ(x+1,y)) {
                    graphe.ajouteJ(x+1, y, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x+1, y);
                    updatecle(j);
                }
            }
        });
        panel2.add(down);
    }

    public void move(Joueur joueur){
        panel1.removeAll();
        draw(joueur);
        panel1.updateUI();
        frame.add(panel1);
    }

}
