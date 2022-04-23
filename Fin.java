import java.awt.*;

public class Fin extends ZoneCliquable{
    private Graphe graphe;

    public Fin(Graphe g){
        super("fin de tour", 80,30);
        setForeground(Color.YELLOW);
        this.graphe = g;
    }
    @Override
    public void clicGauche() {
        graphe.floodAdd();
    }

    @Override
    public void clicDroit() {

    }
}
