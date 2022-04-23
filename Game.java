public class Game {
    protected Graphe graphe;
    private Draw draw;

    public Game(int x, int y){
        this.graphe = new Graphe(x,y);
        this.draw = new Draw(graphe);
        graphe.initial();
    }

    public void gameStart(){
        draw.initial();
    }
}
