import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel {
    private Graphe graphe;
    private Joueur[] joueurs;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel5;
    private int n=0;
    private Case heli;
    private int click=0;

    private Color[] JOUEUR = {Color.black, Color.CYAN,Color.GREEN,Color.RED,Color.ORANGE,Color.BLUE};
    private ImageIcon[] NOR = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/normal.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/inonde.jpg"),new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg") };
    private ImageIcon[] EAU = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/eau.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/eau2.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg")};
    private ImageIcon[] TERRE = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/terre.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/terre2.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg")};
    private ImageIcon[] FEU = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/feu.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/feu2.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg")};
    private ImageIcon[] AIR = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/air.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/air2.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg")};
    private ImageIcon[] HELI = {new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/heliport.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/heliport2.jpg"),
            new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/submerge.jpg")};
    private String[] ELEMENT = new String[]{"EAU","TERRE","FEU","AIR"};

    public Game(Graphe g, Joueur[] joueurs){
        this.graphe = g;
        this.joueurs = joueurs;
        this.frame = new JFrame("Ile d'interdite");
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.panel3 = new JPanel();
        this.panel4 = new JPanel();
        this.label = new JLabel();
        this.label1 = new JLabel("");
        this.label2 = new JLabel("");
        this.label3 = new JLabel("");
        this.label4 = new JLabel("");
        this.label5 = new JLabel("");
        this.label6 = new JLabel("");
        this.panel5 = new JPanel();
        for(int i=0;i<graphe.getTx();i++){
            for (int j=0;j<graphe.getTy();j++){
                if(graphe.getCases()[i][j].isHelicoptere()){
                    this.heli = graphe.getCase(i,j);
                }
            }
        }
    }

    public void setImage(){ // redefinir la taille de tous les images
        for(int i=0;i<3;i++){
            NOR[i].setImage(NOR[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
            EAU[i].setImage(EAU[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
            TERRE[i].setImage(TERRE[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
            FEU[i].setImage(FEU[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
            AIR[i].setImage(AIR[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
            HELI[i].setImage(HELI[i].getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT));
        }
    }

    public void start(){
        setImage();
        panel1.setLayout(new GridLayout(graphe.getTx(),graphe.getTy()));
        draw();
        frame.add(panel1);
        panel2.setLayout(new GridLayout(3,2));
        direction(joueurs[n]);
        frame.add(panel2, BorderLayout.EAST);
        JButton fin = new JButton("fin de tour");
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    update();
            }
        });
        JButton change = new JButton("change joueur");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n++;
                if(n >= joueurs.length) {
                    n = n % joueurs.length;
                }
                move(joueurs[n]);
                updateJ();
                click = 0;
            }
        });
        panel3.add(fin);
        panel3.add(change);
        frame.add(panel3, BorderLayout.SOUTH);
        panel4.setLayout(new GridLayout(6,1));
        cle();
        frame.add(panel4, BorderLayout.NORTH);
        frame.pack();
        frame.setBounds(100,100,1000,800);
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

    public void updateJ(){
        panel2.removeAll();
        direction(joueurs[n]);
        panel2.updateUI();
        frame.add(panel2, BorderLayout.EAST);
    }

    public void updateCle(Joueur joueur){
        panel4.removeAll();
        move(joueur);
        getCle(joueur);
        cle();
        panel4.updateUI();
        frame.add(panel4, BorderLayout.NORTH);
    }

    public void cle(){
        label1 = new JLabel(joueurs[0].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[0].nbCle(0) + "; " + ELEMENT[1] + " -> "+ joueurs[0].nbCle(1) + "; " + ELEMENT[2] + " -> "+ joueurs[0].nbCle(2) + "; " + ELEMENT[3] + " -> "+ joueurs[0].nbCle(3));
        label1.setForeground(JOUEUR[0]);
        if(joueurs[1] != null) {
            label2 = new JLabel(joueurs[1].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[1].nbCle(0) + "; " + ELEMENT[1] + " -> " + joueurs[1].nbCle(1) + "; " + ELEMENT[2] + " -> " + joueurs[1].nbCle(2) + "; " + ELEMENT[3] + " -> " + joueurs[1].nbCle(3));
            label2.setForeground(JOUEUR[1]);
        }
        if(joueurs[2] != null) {
            label3 = new JLabel(joueurs[2].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[2].nbCle(0) + "; " + ELEMENT[1] + " -> " + joueurs[2].nbCle(1) + "; " + ELEMENT[2] + " -> " + joueurs[2].nbCle(2) + "; " + ELEMENT[3] + " -> " + joueurs[2].nbCle(3));
            label3.setForeground(JOUEUR[2]);
        }
        if(joueurs[3] != null) {
            label4 = new JLabel(joueurs[3].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[3].nbCle(0) + "; " + ELEMENT[1] + " -> " + joueurs[3].nbCle(1) + "; " + ELEMENT[2] + " -> " + joueurs[3].nbCle(2) + "; " + ELEMENT[3] + " -> " + joueurs[3].nbCle(3));
            label4.setForeground(JOUEUR[3]);
        }
        if(joueurs[4] != null) {
            label5 = new JLabel(joueurs[4].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[4].nbCle(0) + "; " + ELEMENT[1] + " -> " + joueurs[4].nbCle(1) + "; " + ELEMENT[2] + " -> " + joueurs[4].nbCle(2) + "; " + ELEMENT[3] + " -> " + joueurs[4].nbCle(3));
            label5.setForeground(JOUEUR[4]);
        }
        if(joueurs[5] != null) {
            label6 = new JLabel(joueurs[5].getNom() + " : " + ELEMENT[0] + " -> " + joueurs[5].nbCle(0) + "; " + ELEMENT[1] + " -> " + joueurs[5].nbCle(1) + "; " + ELEMENT[2] + " -> " + joueurs[5].nbCle(2) + "; " + ELEMENT[3] + " -> " + joueurs[5].nbCle(3));
            label6.setForeground(JOUEUR[5]);
        }
        panel4.add(label1);
        panel4.add(label2);
        panel4.add(label3);
        panel4.add(label4);
        panel4.add(label5);
        panel4.add(label6);
    }

    public void draw(){
        if(isFail()){
            panel1.setLayout(new GridLayout(1,1));
            ImageIcon fini = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/defaite.jpg");
            fini.setImage(fini.getImage().getScaledInstance(500,500, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(fini, JLabel.CENTER);
            panel1.add(label);
            return;
        }
        if(isWinning()){
            panel1.setLayout(new GridLayout(1,1));
            ImageIcon fini = new ImageIcon("/Users/apple/IdeaProjects/Ile_interdite/Icon/victory.png");
            fini.setImage(fini.getImage().getScaledInstance(500,500, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(fini, JLabel.CENTER);
            panel1.add(label);
            return;
        }
        Case[][] cases = graphe.getCases();
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if(cases[i][j].isHelicoptere()){
                    if(cases[i][j].getJoueur() != null){
                        JButton c = new JButton(cases[i][j].getJoueur().getNom());
                        for(int m=0;m<joueurs.length;m++){
                            if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                c.setForeground(JOUEUR[m]);
                            }
                        }
                        JLabel img = new JLabel(HELI[cases[i][j].getFlood()], JLabel.LEFT);
                        img.setLayout(new FlowLayout(FlowLayout.CENTER));
                        img.add(c);
                        panel1.add(img);
                    }else {
                        JLabel img = new JLabel(HELI[cases[i][j].getFlood()], JLabel.LEFT);
                        panel1.add(img);
                    }
                }else{
                    if(cases[i][j].getElement() == 0) {
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
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
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            JLabel img = new JLabel(EAU[cases[i][j].getFlood()], JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(EAU[cases[i][j].getFlood()], JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 2) {
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            JLabel img = new JLabel(TERRE[cases[i][j].getFlood()], JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(TERRE[cases[i][j].getFlood()], JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 3) {
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            JLabel img = new JLabel(FEU[cases[i][j].getFlood()], JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(FEU[cases[i][j].getFlood()], JLabel.LEFT);
                            panel1.add(img);
                        }
                    } else if (cases[i][j].getElement() == 4) {
                        if(cases[i][j].getJoueur() != null){
                            JButton c = new JButton(cases[i][j].getJoueur().getNom());
                            for(int m=0;m<joueurs.length;m++){
                                if(joueurs[m].getNom() == cases[i][j].getJoueur().getNom()){
                                    c.setForeground(JOUEUR[m]);
                                }
                            }
                            JLabel img = new JLabel(AIR[cases[i][j].getFlood()], JLabel.LEFT);
                            img.setLayout(new FlowLayout(FlowLayout.CENTER));
                            img.add(c);
                            panel1.add(img);
                        }else {
                            JLabel img = new JLabel(AIR[cases[i][j].getFlood()], JLabel.LEFT);
                            panel1.add(img);
                        }
                    }
                }
            }
        }
    }

    public void getCle(Joueur joueur){
        Case[][] cases = graphe.getCases();
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if (cases[i][j].getJoueur() != null) {
                    if (joueur.getNom() == cases[i][j].getJoueur().getNom() && cases[i][j].getElement() == 1) {
                        joueur.getCles(0);
                    } else if (joueur.getNom() == cases[i][j].getJoueur().getNom() && cases[i][j].getElement() == 2) {
                        joueur.getCles(1);
                    } else if (joueur.getNom() == cases[i][j].getJoueur().getNom() && cases[i][j].getElement() == 3) {
                        joueur.getCles(2);
                    } else if (joueur.getNom() == cases[i][j].getJoueur().getNom() && cases[i][j].getElement() == 4) {
                        joueur.getCles(3);
                    }
                }
            }
        }
    }

    public void direction(Joueur j){
        label = new JLabel(j.getNom() + " move step : "+ click + "/3");
        JButton up = new JButton("up");
        up.setForeground(Color.PINK);
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = j.getCaseJ().getX();
                int y = j.getCaseJ().getY();
                if(x-1 >= 0 && !graphe.existJ(x-1,y) && graphe.getCases()[x-1][y].getFlood() < 2 && click <= 2) {
                    graphe.ajouteJ(x-1, y, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x-1, y);
                    updateCle(j);
                    click++;
                    label.setText(j.getNom() +" move step : "+ click + "/3");
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
                if(y+1 < graphe.getTy() && !graphe.existJ(x, y+1) && graphe.getCases()[x][y+1].getFlood() < 2 && click <= 2) {
                    graphe.ajouteJ(x, y+1, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y+1);
                    updateCle(j);
                    click++;
                    label.setText(j.getNom() +" move step : "+ click + "/3");
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
                if(y-1 >= 0 && !graphe.existJ(x, y-1) && graphe.getCases()[x][y-1].getFlood() < 2 && click <= 2) {
                    graphe.ajouteJ(x, y-1, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x, y-1);
                    updateCle(j);
                    click++;
                    label.setText(j.getNom() +" move step : "+ click + "/3");
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
                if(x+1 < graphe.getTx() && !graphe.existJ(x+1,y) && graphe.getCases()[x+1][y].getFlood() < 2 && click <= 2) {
                    graphe.ajouteJ(x+1, y, j);
                    graphe.removeJ(x,y);
                    j.setCaseJ(x+1, y);
                    updateCle(j);
                    click++;
                    label.setText(j.getNom() +" move step : "+ click + "/3");
                }
            }
        });
        panel2.add(down);
        JButton assecher = new JButton("assecher");
        assecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Case> c = joueurs[n].voisin();
                for(Case x: c) {
                    if(x.getFlood() == 1) {
                        graphe.reduceFlood(x.getX(), x.getY());
                        move(joueurs[n]);
                        click++;
                        label.setText(j.getNom() +" move step : "+ click + "/3");
                        break;
                    }
                }
            }
        });
        panel2.add(assecher);
        panel2.add(label);
    }

    public void move(Joueur joueur){
        panel1.removeAll();
        draw();
        panel1.updateUI();
        frame.add(panel1);
    }

    public boolean isWinning(){
        for(int i=0;i< joueurs.length;i++){
            for(int j=0;j<3;j++) {
                if (joueurs[i].getCaseJ() != this.heli || joueurs[i].nbCle(j) < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFail(){
        if(heli.getFlood() >= 2){
            return true;
        }
        return false;
    }

}
