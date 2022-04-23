import javax.swing.*;
import java.awt.*;

public class Fin extends ZoneCliquable{
    private Game game;
    public Fin(Game game){
        super("fin de tour",80,30);
        this.game = game;
    }
    @Override
    public void clicGauche() {
        game.update();
    }
    @Override
    public void clicDroit() {

    }
}
