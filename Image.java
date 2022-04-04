import javax.swing.*;

public class Image {
    private JFrame frame;
    private int width;
    private int height;
    private JPanel panel;

    public Image(int w, int h){
        this.width = w;
        this.height = h;

        this.frame = new JFrame("Ile Interdite");
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        placeComponents(this.panel);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void placeComponents(JPanel panel){
        JButton fin = new JButton("fin de tour");
        fin.setBounds(0,0,20,20);
        panel.add(fin);
    }
}
