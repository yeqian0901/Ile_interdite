import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    private Graphe graphe;
    private Joueur[] joueurs;
    private JFrame frame;
    private JPanel panel;

    private Color[] NORMAL = {Color.white, Color.gray, Color.black};
    private Color[] HELI = {new Color(255, 0, 0), new Color(100, 0, 0), Color.black};
    private Color[] EAU = {new Color(80, 80, 255), new Color(0, 0, 120), Color.black};
    private Color[] TERRE = {new Color(40, 200, 0), new Color(20, 100, 0), Color.black};
    private Color[] FEU = {new Color(255, 100, 0), new Color(100, 50, 0), Color.black};
    private Color[] AIR = {new Color(0, 200, 200), new Color(0, 60, 60), Color.black};
    private Color[] JOUEUR = {Color.black, Color.CYAN,Color.GREEN,Color.RED};

    public Game(Graphe g){
        this.graphe = g;
        this.joueurs = new Joueur[6];
        this.frame = new JFrame("Ile d'interdite");
        this.panel = new JPanel();
    }

    public void start(){
        panel.setLayout(new GridLayout(graphe.getTx(),graphe.getTy()));
        draw();
        frame.add(panel);
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
        panel.removeAll();
        graphe.floodAdd();
        draw();
        panel.updateUI();
        frame.add(panel);
    }

    public void draw(){
        Case[][] cases = graphe.getCases();
        for(int i=0;i<graphe.getTx();i++) {
            for (int j = 0; j < graphe.getTy(); j++) {
                if(cases[i][j].isHelicoptere()){
                    JButton c = new JButton("HELICOPTER");
                    c.setForeground(HELI[cases[i][j].getFlood()]);
                    c.setPreferredSize(new Dimension(100, 100));
                    panel.add(c);
                }else{
                    if(cases[i][j].getElement() == 0) {
                        JButton c = new JButton("NORMAL");
                        c.setForeground(NORMAL[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel.add(c);
                        //c.setBackground(Color.YELLOW);
                        //c.setOpaque(true);
                        //c.setBorderPainted(false);
                    } else if (cases[i][j].getElement() == 1) {
                        JButton c = new JButton("EAU");
                        c.setForeground(EAU[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel.add(c);
                    } else if (cases[i][j].getElement() == 2) {
                        JButton c = new JButton("TERRE");
                        c.setForeground(TERRE[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel.add(c);
                    } else if (cases[i][j].getElement() == 3) {
                        JButton c = new JButton("FEU");
                        c.setForeground(FEU[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel.add(c);
                    } else if (cases[i][j].getElement() == 4) {
                        JButton c = new JButton("AIR");
                        c.setForeground(AIR[cases[i][j].getFlood()]);
                        c.setPreferredSize(new Dimension(100, 100));
                        panel.add(c);
                    }
                }
            }
        }
    }

    public void ajouteelement(JComponent b){
        this.frame.add(b);
    }
}
